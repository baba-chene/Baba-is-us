package com.babachene.cliserv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observer;
import java.util.logging.Logger;

public class Client implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    private Observer disconnectionObserver;

    private volatile String IPAdress;
    private volatile int port;
    private boolean hostSet = false;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private volatile Event[] eventBuffer;
    private volatile int eventBufferSize = 0;
    private volatile Update[] updateBuffer;
    private volatile int updateBufferSize = 0;
    private volatile int updateBufferStartIndex = 0;

    private long updateTime;
    private long lastEventTime;

    private Thread thread;

    private volatile boolean running = true;
    
    private enum State {
        Connecting("CONNECTING"),
        Connected("CONNECTED"),
        Disconnecting("DISCONNECTING"),
        Disconnected("DISCONNECTED"),
        ShuttingDown("SHUTTINGDOWN");
        
        private String name = "";
        
        State(String name) {
        	this.name = name;
        }
        
        public String toString() {
        	return name;
        }
    }

    private volatile State state = State.Disconnected;
    private volatile State nextState = State.Disconnected;

    public Client(Observer disconnectionObserver, int eventBufferLength, int updateBufferLength) {
    	this.disconnectionObserver = disconnectionObserver;
        eventBuffer = new Event[eventBufferLength];
        updateBuffer = new Update[updateBufferLength];
        thread = new Thread(this);
        thread.setDaemon(true); // No Client if the app is terminated.
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
        LOGGER.info("[Client] Asked to connect to current host.");
    	State currentState;
        synchronized(this) {
        	currentState = state;
        }
        LOGGER.info("[Client] Attempting to connect...");
        if(currentState != State.Connected) {
            if(hostSet) {
                this.nextState = State.Connecting;
                synchronized(this) {
                	this.notify();
                }
                LOGGER.info("[Client] Connecting to host at " + IPAdress + ":" + port + "...");
            } else
        LOGGER.warning("[Client] Can't connect, IPAdress or port is missing");

        } else
            LOGGER.info("[Client] You're already connected, no need connect again");
    }

    public void connect(String IPAdress, int port) {
        LOGGER.info("[Client] Asked to connect to new host at " + IPAdress + ":" + port + "...");
        this.IPAdress = IPAdress;
        this.port = port;
        nextState = State.Connecting;
        synchronized(this) {
            this.notify();
        }
        hostSet = true;
        LOGGER.info("[Client] Connecting to host at " + IPAdress + ":" + port + "...");
    }

    public void disconnect() {
    	LOGGER.info("[Client] Asked to disconnect.");
    	State currentState;
    	synchronized(this) {
    		currentState = state;
    	}
        if(currentState == State.Disconnecting || currentState == State.Disconnected) {
            LOGGER.info("[Client] Can't disconnect, you aren't connected to any server");
            return;
        }
        nextState = State.Disconnecting;
        LOGGER.info("[Client] Disconnecting...");
    }

    public synchronized void addEvent(Event event) {
        if(eventBufferSize < eventBuffer.length) {
            eventBuffer[eventBufferSize] = event;
            eventBufferSize++;
        } else
            LOGGER.warning("[Client] Buffer full, event dropped");
    }

    public synchronized boolean isUpdateBufferEmpty() {
        return updateBufferSize == 0;
    }

    public synchronized Update getUpdate() {
        updateBufferSize--;
        int resIndex = updateBufferStartIndex;
        updateBufferStartIndex = (updateBufferStartIndex + 1 ) % updateBuffer.length;
        return updateBuffer[resIndex];
    }
    
    public void shutdown() {
		LOGGER.info("[Client] Shutting down for good...");
		try {
		    out.writeObject(new DisconnectEvent());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = false;
    	synchronized(this) {
    		this.notify();
    	}
		nextState = State.ShuttingDown;
    }

    public void run() {
        while(running) {
        	synchronized(this) {
        		if (state != nextState)
        			LOGGER.info("[Client] Changed state from " + state + " to " + nextState);
        		state = nextState;
        	}
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
                case ShuttingDown :
                	break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
		LOGGER.info("[Client] Shut down.");
    }

    private void startConnection() {
        if(!hostSet) {
            LOGGER.warning("[Client] Can't connect, IPAdress or port is missing");
            nextState = State.Disconnected;
            return;
        }
        try {
        	socket = new Socket();
        	socket.connect(new InetSocketAddress(IPAdress, port), 3000);
            socket.setSoTimeout(10);
            out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.flush();
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            LOGGER.info("[Client] Connection established with host");
            nextState = State.Connected;
            updateTime = System.currentTimeMillis();
        } catch (InterruptedIOException iioe) {
        	iioe.printStackTrace();
            //LOGGER.info("[Client] No response from host, trying again...");
        } catch (UnknownHostException e) {
            nextState = State.Disconnected;
            LOGGER.warning("[Client] No host found at " + IPAdress + ":" + port);
        } catch (IOException e) {
            nextState = State.Disconnected;
            LOGGER.warning("[Client] Connection could not be established with " + IPAdress + ":" + port);
            e.printStackTrace();
        }
    }

    private synchronized void clearEventBuffer() {
        for (int i = 0; i < eventBufferSize; i++) {
            eventBuffer[i] = null;
        }
        eventBufferSize = 0;
        LOGGER.fine("[Client] Event buffer cleared");
    }

    private synchronized void clearUpdateBuffer() {
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
        	if(out != null)
			    out.writeObject(new DisconnectEvent());
	        socket.close();
        } catch (SocketException ex) {
		} catch (IOException e) {
			e.printStackTrace();
		}
        LOGGER.info("[Client] Connection closed.");
        disconnectionObserver.update(null, this);
        nextState = State.Disconnected;
    }

    private void sendEvents() {
    	boolean sent = false;
    	
    	synchronized(this) {
            for (int i = 0; i < eventBufferSize; i++) {
                try {
    				out.writeObject(eventBuffer[i]);
    				sent = true;
    	            lastEventTime = System.currentTimeMillis();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            }
            clearEventBuffer();
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
            } catch (SocketException ex) {
                nextState = State.Disconnecting;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}     	
        }
    }
    
    private void checkTime() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - updateTime > 10000) {
            LOGGER.warning("[Client] Connection lost : no update received for too long");
            nextState = State.Disconnecting;
        }
    }

    private void receiveUpdates() {
        try {
            Update update = (Update) in.readObject();
            updateTime = System.currentTimeMillis();
            update.updateConnection(this);

            synchronized(this) {
                if(updateBufferSize < updateBuffer.length) {
                    updateBuffer[(updateBufferStartIndex + updateBufferSize) % updateBuffer.length] = update;
                    updateBufferSize++;
                } else
                    LOGGER.warning("[Client] Buffer full, update dropped");
            }

        } catch (InterruptedIOException iioe) {
        	checkTime();
        } catch (SocketException ex) {
            nextState = State.Disconnecting;
        } catch (ClassNotFoundException e) {
        	checkTime();
			e.printStackTrace();
		} catch (IOException e) {
			checkTime();
			e.printStackTrace();
		}
    }
}
