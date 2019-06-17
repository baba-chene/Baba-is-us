package com.babachene.cliserv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InterruptedIOException;
import java.util.logging.Logger;

public class Server implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Event[] eventBuffer;
    private int eventBufferSize = 0;
    private int eventBufferStartIndex = 0;
    private Update[] updateBuffer;
    private int updateBufferSize = 0;

    private long eventTime;
    private long lastUpdateTime;

    private Thread thread;

    private enum State {
        Closed,
        Opening,
        Opened,
        Connected,
        Disconnecting,
        DisconnectingAndClosing,
        Closing,
    }

    private State state = State.Closed;

    public Server(int eventBufferLength, int updateBufferLength) {
        eventBuffer = new Event[eventBufferLength];
        updateBuffer = new Update[updateBufferLength];
        thread = new Thread(this);
        thread.start();
        LOGGER.info("[Server] Thread started");
    }

    public void open(int port) {
        synchronized(this) {
            this.port = port;
            LOGGER.info("[Server] Server set to listen on " + port);
            LOGGER.info("[Server] Server is asked to listen on " + port);
            state = State.Opening;
            this.notify();
        }
    }
    
    public void open() {
        synchronized(this) {
            LOGGER.info("[Server] Server is asked to listen on " + port);
            state = State.Opening;
            this.notify();
        }
    }

    public void close() {
        if(state == State.Connected)
            state = State.DisconnectingAndClosing;
        else
            state = State.Closing;
        LOGGER.info("[Server] Server is closing...");
    }

    public void addUpdate(Update update) {
        if(updateBufferSize < updateBuffer.length) {
            updateBuffer[updateBufferSize] = update;
            updateBufferSize++;
        } else
            LOGGER.warning("[Server] Buffer full, update dropped");
    }

    public boolean isEventBufferEmpty() {
        return eventBufferSize == 0;
    }

    public Event getEvent() {
        eventBufferSize--;
        int resIndex = eventBufferStartIndex;
        eventBufferStartIndex = (eventBufferStartIndex + 1 ) % eventBuffer.length;
        return eventBuffer[resIndex];
    }

    public void disconnect() {
        try {
			clientSocket.close();
	        LOGGER.info("[Server] Closing connection...");
	        state = State.Disconnecting;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void run() {
        while(true) {
            switch (state) {
                case Closed :
                    synchronized(this) {
                        try {
                            wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    break;
                case Opening :
                    openHost();
                    break;
                case Opened :
                    listen();
                    break;
                case Connected :
                    sendUpdates();
                    receiveEvents();
                    break;
                case Disconnecting :
                    endConnection();
                    break;
                case DisconnectingAndClosing :
                    endConnection();
                    stopListening();
                    break;
                case Closing:
                    stopListening();
                    break;
            }
            try {
                Thread.sleep(1);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private void listen() {
        try {
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            state = State.Connected;
            LOGGER.info("[Server] Client connected.");
        } catch(InterruptedIOException iioe) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void openHost() {
    	try {
    		serverSocket = new ServerSocket(port);
    		serverSocket.setSoTimeout(1000);
            LOGGER.info("[Server] Server listening on port " + port);
    		state = State.Opened;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    private void clearUpdateBuffer() {
        for (int i = 0; i < updateBufferSize; i++) {
            updateBuffer[i] = null;
        }
        updateBufferSize = 0;
        LOGGER.fine("[Server] Update buffer cleared");
    }

    private void clearEventBuffer() {
        int n = eventBuffer.length;
        for (int i = 0; i < eventBufferSize; i++) {
            eventBuffer[(i + eventBufferStartIndex) % n] = null;
        }
        eventBufferSize = 0;
        eventBufferStartIndex = 0;
        LOGGER.fine("[Server] Event buffer cleared");
    }

    private void endConnection() {
        clearEventBuffer();
        clearUpdateBuffer();
        try {
			out.writeObject(new DisconnectUpdate());
	        clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        LOGGER.info("[Server] Connection with client closed.");
        state = State.Opened;
    }

    private void stopListening() {
        try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        state = State.Closed;
        LOGGER.info("[Server] Server stopped listening.");
    }

    private void sendUpdates() {
    	boolean sent = false;
        for (int i = 0; i < updateBufferSize; i++) {
            try {
				out.writeObject(updateBuffer[i]);
				sent = true;
	            lastUpdateTime = System.currentTimeMillis();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastUpdateTime > 1000) {
            try {
				out.writeObject(new KeepAliveUpdate());
				sent = true;
	            lastUpdateTime = currentTime;
	            LOGGER.fine("[Server] Empty update sent to keep connection alive");
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
        clearUpdateBuffer();
    }

    private void receiveEvents() {
        try {
            Event event = (Event) in.readObject();
            eventTime = System.currentTimeMillis();
            event.updateConnection(this);

            if(eventBufferSize < eventBuffer.length) {
                eventBuffer[eventBufferSize] = event;
                eventBufferSize++;
            } else
                LOGGER.warning("[Server] Buffer full, event dropped");

        } catch (InterruptedIOException iioe) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - eventTime > 10000) {
                LOGGER.warning("[Server] Connection lost : no event received for too long");
                state = State.Disconnecting;
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
