package com.babachene.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.Server;
import com.babachene.cliserv.Update;
import com.babachene.logic.Logic;
import com.badlogic.gdx.InputProcessor;

public class ServerEventController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Server server;
	private InputProcessor inputProcessor;
	private Logic logic;

	private ArrayBlockingQueue<Event> eventBuffer;
	private int eventBufferLength;
	private Update update;

    public ServerEventController(Server server, InputProcessor inputProcessor, Logic logic, int eventBufferLength) {
    	
		this.eventBufferLength = eventBufferLength;
		this.server = server;
		this.inputProcessor = inputProcessor;
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
        if(!server.isEventBufferEmpty())
            addEvent(server.getEvent());
        if(!inputProcessor.isEventBufferEmpty())
            addEvent(inputProcessor.getEvent());
    }

    private boolean solveEvent() {
    	if(eventBuffer.size() > 0) {
    		update = logic.processEvent(eventBuffer.poll());
    		return true;
    	}
    	return false;
    }

    private void sendUpdate() {
    	server.addUpdate(update);
    }

	public void update() {
		fetchEvents();
		if(solveEvent())
			sendUpdate();
    }
}
