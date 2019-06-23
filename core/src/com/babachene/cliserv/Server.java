package com.babachene.cliserv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observer;
import java.util.logging.Logger;

public class Server implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    private Observer disconnectionObserver;

    private volatile int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private volatile Event[] eventBuffer;
    private volatile int eventBufferSize = 0;
    private volatile int eventBufferStartIndex = 0;
    private volatile Update[] updateBuffer;
    private volatile int updateBufferSize = 0;

    private long eventTime;
    private long lastUpdateTime;

    private Thread thread;
    
    private volatile boolean running = true;

    private enum State {
        Closed("CLOSED"),
        Opening("OPENING"),
        Opened("OPENED"),
        Connected("CONNECTED"),
        Disconnecting("DISCONNECTING"),
        DisconnectingAndClosing("DISCONNECTINGANDCLOSING"),
        Closing("CLOSING"),
        ShuttingDown("SHUTTINGDOWN");
        
        private String name = "";
        
        State(String name) {
        	this.name = name;
        }
        
        public String toString() {
        	return name;
        }
    }

    private volatile State state = State.Closed;
    private volatile State nextState = State.Closed;

    public Server(Observer disconnectionObserver, int eventBufferLength, int updateBufferLength) {
    	this.disconnectionObserver = disconnectionObserver;
        eventBuffer = new Event[eventBufferLength];
        updateBuffer = new Update[updateBufferLength];
        thread = new Thread(this);
        thread.setDaemon(true); // No server if the app is terminated.
        thread.start();
        LOGGER.info("[Server] Thread started");
    }

    public void open(int port) {
        synchronized(this) {
            this.port = port;
            LOGGER.info("[Server] Server set to listen on " + port);
            LOGGER.info("[Server] Asked to listen on " + port);
            nextState = State.Opening;
            this.notify();
        }
    }
    
    public void open() {
        LOGGER.info("[Server] Asked to listen on " + port);
        nextState = State.Opening;
        synchronized(this) {
            this.notify();
        }
    }

    public void close() {
    	LOGGER.info("[Server] Asked to close");
    	State currentState;
    	synchronized(this) {
    		currentState = state;	
    	}
        if(currentState == State.Connected)
            nextState = State.DisconnectingAndClosing;
        else
            nextState = State.Closing;
        LOGGER.info("[Server] Server is closing...");
    }

    public void disconnect() {
    	LOGGER.info("[Server] Asked to disconnect");
    	State currentState;
    	synchronized(this) {
    		currentState = state;	
    	}
        if(currentState != State.Connected) {
    	    LOGGER.warning("[Server] Can't disconnect, no client is connected");
    	    return;
        }
	    nextState = State.Disconnecting;
	    LOGGER.info("[Server] Closing connection to client...");
    }
    
    public void shutdown() {
		LOGGER.info("[Server] Shutting down for good...");
		running = false;
    	synchronized(this) {
    		this.notify();
    	}
		nextState = State.ShuttingDown;
    }

    public synchronized void addUpdate(Update update) {
        if(updateBufferSize < updateBuffer.length) {
            updateBuffer[updateBufferSize] = update;
            updateBufferSize++;
        } else
            LOGGER.warning("[Server] Buffer full, update dropped");
    }

    public synchronized boolean isEventBufferEmpty() {
        return eventBufferSize == 0;
    }

    public synchronized Event getEvent() {
        eventBufferSize--;
        int resIndex = eventBufferStartIndex;
        eventBufferStartIndex = (eventBufferStartIndex + 1 ) % eventBuffer.length;
        return eventBuffer[resIndex];
    }

    public void run() {
        while(running) {
        	synchronized(this) {
        		if (state != nextState)
        			LOGGER.info("[Server] Changed state from " + state + " to " + nextState);
        		state = nextState;
        	}
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
                case ShuttingDown:
                	break;
            }
            try {
                Thread.sleep(1);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
		LOGGER.info("[Server] Shut down.");
    }

    private void listen() {
        try {
            clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(10);
            out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            out.flush();
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            nextState = State.Connected;
            LOGGER.info("[Server] Client connected.");
            eventTime = System.currentTimeMillis();
        } catch(InterruptedIOException iioe) {
            try {
                Thread.sleep(1);
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
    		serverSocket.setSoTimeout(10);
            LOGGER.info("[Server] Server listening on port " + port);
    		nextState = State.Opened;
    	} catch (IOException e) {
    		e.printStackTrace();
            LOGGER.warning("[Server] Server could no listen on port " + port);
    		nextState = State.Closed;
    	}
    }

    private synchronized void clearUpdateBuffer() {
        for (int i = 0; i < updateBufferSize; i++) {
            updateBuffer[i] = null;
        }
        updateBufferSize = 0;
        LOGGER.fine("[Server] Update buffer cleared");
    }

    private synchronized void clearEventBuffer() {
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
        	if(out != null)
			    out.writeObject(new DisconnectUpdate());
	        clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        LOGGER.info("[Server] Connection with client closed.");
        disconnectionObserver.update(null, this);
        nextState = State.Opened;
    }

    private void stopListening() {
        try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        nextState = State.Closed;
        LOGGER.info("[Server] Server stopped listening.");
    }

    private void sendUpdates() {
    	boolean sent = false;
    	
    	synchronized(this) {
            for (int i = 0; i < updateBufferSize; i++) {
                try {
			    	out.writeObject(updateBuffer[i]);
				    sent = true;
	                lastUpdateTime = System.currentTimeMillis();
			    } catch (IOException e) {
				    e.printStackTrace();
			    }
            }
            clearUpdateBuffer();
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
    }
    
    private void checkTime() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - eventTime > 10000) {
            LOGGER.warning("[Server] Connection lost : no event received for too long");
            nextState = State.Disconnecting;
        }
    }

    private void receiveEvents() {
        try {
            Event event = (Event) in.readObject();
            eventTime = System.currentTimeMillis();
            event.updateConnection(this);

            synchronized(this) {
                if(eventBufferSize < eventBuffer.length) {
                    eventBuffer[(eventBufferStartIndex + eventBufferSize) % eventBuffer.length] = event;
                    eventBufferSize++;
                } else
                    LOGGER.warning("[Server] Buffer full, event dropped");
            }

        } catch (InterruptedIOException iioe) {
        	checkTime();
        } catch (ClassNotFoundException e) {
        	checkTime();
			e.printStackTrace();
        } catch (EOFException e) {
        	checkTime();
        	
		} catch (IOException e) {
			checkTime();
			e.printStackTrace();
		}
    }
}
