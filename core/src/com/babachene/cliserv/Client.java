package com.babachene.cliserv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InterruptedIOException;
import java.util.logging.Logger;

public class Client implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String IPAdress;
    private int port;
    private boolean hostSet = false;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Event[] eventBuffer;
    private int eventBufferSize = 0;
    private Update[] updateBuffer;
    private int updateBufferSize = 0;
    private int updateBufferStartIndex = 0;

    private long updateTime;
    private long lastEventTime;

    private Thread thread;

    private enum State {
        Connecting,
        Connected,
        Disconnecting,
        Disconnected,
    }

    private State state = State.Disconnected;

    public Client(int eventBufferLength, int updateBufferLength) {
        eventBuffer = new Event[eventBufferLength];
        updateBuffer = new Update[updateBufferLength];
        thread = new Thread(this);
        thread.start();
        LOGGER.info("[Client] Thread started");
    }

    public void setHost(String IPAdress, int port) {
        this.IPAdress = IPAdress;
        this.port = port;
        hostSet = true;
        LOGGER.info("[Client] Set host at " + IPAdress + ":" + port + "...");
    }

    public void connect() {
        synchronized(this) {
            LOGGER.info("[Client] Attempting to connect...");
            if(state == State.Disconnecting || state == State.Disconnected) {
                if(hostSet) {
                    this.state = State.Connecting;
                    this.notify();
                    LOGGER.info("[Client] Connecting to host at " + IPAdress + ":" + port + "...");
                } else
                    LOGGER.warning("[Client] Can't connect, IPAdress or port is missing");

            } else
                LOGGER.info("[Client] You're already connected, no need connect again");
        }
    }

    public void connect(String IPAdress, int port) {
        synchronized(this) {
            this.IPAdress = IPAdress;
            this.port = port;
            state = State.Connecting;
            this.notify();
            hostSet = true;
            LOGGER.info("[Client] Connecting to host at " + IPAdress + ":" + port + "...");
        }
    }

    public void disconnect() {
        if(state == State.Disconnecting || state == State.Disconnected) {
            LOGGER.info("[Client] Can't disconnect, you aren't connected to any server");
        }
        state = State.Disconnecting;
        LOGGER.info("[Client] Disconnecting...");
    }

    public void addEvent(Event event) {
        if(eventBufferSize < eventBuffer.length) {
            eventBuffer[eventBufferSize] = event;
            eventBufferSize++;
        } else
            LOGGER.warning("[Client] Buffer full, event dropped");
    }

    public boolean isUpdateBufferEmpty() {
        return updateBufferSize == 0;
    }

    public Update getUpdate() {
        updateBufferSize--;
        int resIndex = updateBufferStartIndex;
        updateBufferStartIndex = (updateBufferStartIndex + 1 ) % updateBuffer.length;
        return updateBuffer[resIndex];
    }

    public void run() {
        while(true) {
            switch (state) {
                case Connecting :
                    startConnection();
                    break;
                case Connected :
                    sendEvents();
                    receiveUpdates();
                    break;
                case Disconnecting :
                    endConnection();
                    break;
                case Disconnected :
                    synchronized(this) {
                    try {
                        wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    break;
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private void startConnection() {
        if(!hostSet) {
            LOGGER.warning("[Client] Can't connect, IPAdress or port is missing");
            state = State.Disconnected;
            return;
        }
        try {
            socket = new Socket(IPAdress, port);
            socket.setSoTimeout(10);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            LOGGER.info("[Client] Connection established with host");
            state = State.Connected;
            updateTime = System.currentTimeMillis();
        } catch (UnknownHostException e) {
            state = State.Disconnected;
            LOGGER.warning("[Client] No host found at " + IPAdress + ":" + port);
        } catch (IOException e) {
            state = State.Disconnected;
            e.printStackTrace();
        }
    }

    private void clearEventBuffer() {
        for (int i = 0; i < eventBufferSize; i++) {
            eventBuffer[i] = null;
        }
        eventBufferSize = 0;
        LOGGER.fine("[Client] Event buffer cleared");
    }

    private void clearUpdateBuffer() {
        int n = updateBuffer.length;
        for (int i = 0; i < updateBufferSize; i++) {
            eventBuffer[(i + updateBufferStartIndex) % n] = null;
        }
        updateBufferSize = 0;
        updateBufferStartIndex = 0;
        LOGGER.fine("[Client] Update buffer cleared");
    }

    private void endConnection() {
        clearEventBuffer();
        clearUpdateBuffer();
        try {
			out.writeObject(new DisconnectEvent());
	        socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        LOGGER.info("[Client] Connection closed.");
        state = State.Disconnected;
    }

    private void sendEvents() {
    	boolean sent = false;
        for (int i = 0; i < eventBufferSize; i++) {
            try {
				out.writeObject(eventBuffer[i]);
				sent = true;
	            lastEventTime = System.currentTimeMillis();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastEventTime > 1000) {
            try {
				out.writeObject(new KeepAliveEvent());
				sent = true;
	            lastEventTime = currentTime;
	            LOGGER.fine("[Client] Empty event sent to keep connection alive");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if(sent) {
            try {
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}     	
        }
        clearEventBuffer();
    }
    
    private void checkTime() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - updateTime > 10000) {
            LOGGER.warning("[Client] Connection lost : no update received for too long");
            state = State.Disconnecting;
        }
    }

    private void receiveUpdates() {
        try {
            Update update = (Update) in.readObject();
            updateTime = System.currentTimeMillis();
            update.updateConnection(this);

            if(updateBufferSize < updateBuffer.length) {
                updateBuffer[updateBufferSize] = update;
                updateBufferSize++;
            } else
                LOGGER.warning("[Client] Buffer full, update dropped");

        } catch (InterruptedIOException iioe) {
        	checkTime();
        } catch (ClassNotFoundException e) {
        	checkTime();
			e.printStackTrace();
		} catch (IOException e) {
			checkTime();
			e.printStackTrace();
		}
    }
}
