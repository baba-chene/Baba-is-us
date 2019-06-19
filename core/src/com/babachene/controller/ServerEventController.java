package com.babachene.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.Server;
import com.babachene.cliserv.Update;
import com.babachene.logic.Logic;
import com.babachene.userinput.EventGiver;

public class ServerEventController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Server server;
	private EventGiver eventGiver;
	private Logic logic;

	private ArrayBlockingQueue<Event> eventBuffer;
	private int eventBufferLength;
	private Update update;

    public ServerEventController(Server server, EventGiver eventGiver, Logic logic, int eventBufferLength) {
    	
		this.eventBufferLength = eventBufferLength;
		this.server = server;
		this.eventGiver = eventGiver;
		this.logic = logic;
        eventBuffer = new ArrayBlockingQueue<Event>(eventBufferLength);
        
		LOGGER.info("[Server Event Controller] Started");
	}

    private void addEvent(Event event) {
    	if(eventBuffer.size() < eventBufferLength)
    		eventBuffer.add(event);
    	else
    		LOGGER.warning("[Server Event Controller] Event buffer full, event dropped");
    }

    private void fetchEvents() {
        if(!server.isEventBufferEmpty()) {
    		LOGGER.fine("[Server Event Controller] Event received from server");
            addEvent(server.getEvent());
        }
        if(eventGiver.size() > 0) {
    		LOGGER.fine("[Server Event Controller] Event received from input processor");
            addEvent(eventGiver.pollEvent());
        }
    }

    private boolean solveEvent() {
    	if(eventBuffer.size() > 0) {
    		LOGGER.fine("[Server Event Controller] Event sent to logic");
    		update = logic.processEvent(eventBuffer.poll());
    		return true;
    	}
    	return false;
    }

    private void sendUpdate() {
		LOGGER.fine("[Server Event Controller] Update sent to server");
    	server.addUpdate(update);
    }

	public void update() {
		fetchEvents();
		if(solveEvent())
			sendUpdate();
    }
}
