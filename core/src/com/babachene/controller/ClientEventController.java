package com.babachene.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Event;
import com.babachene.cliserv.Update;
import com.babachene.logic.Logic;
import com.badlogic.gdx.InputProcessor;

public class ClientEventController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Client client;
	private InputProcessor inputProcessor;
	private Logic logic;

	private Event event;
	private Update update;

    public ClientEventController(Client client, InputProcessor inputProcessor, Logic logic) {
    	
		this.client = client;
		this.inputProcessor = inputProcessor;
		this.logic = logic;
        
		LOGGER.info("[Client Event Controller] Started");
	}

    private boolean fetchEvent() {
        if(!inputProcessor.isEventBufferEmpty()) {
            addEvent(inputProcessor.getEvent());
            return true;
        }
        return false;
    }

    private boolean fetchUpdate() {
    	if(!client.isUpdateBufferEmpty()) {
    		update = client.getUpdate();
    		return true;
    	}
    	return false;
    }

	public void update() {
		if(fetchUpdate())
			logic.processUpdate(update);
		if(fetchEvent()) {
			client.addEvent(event);
			logic.processEvent(event);
		}
    }
}
