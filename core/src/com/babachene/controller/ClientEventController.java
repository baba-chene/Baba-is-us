package com.babachene.controller;

import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Event;
import com.babachene.cliserv.Update;
import com.babachene.game.GameController;
import com.babachene.logic.Logic;
import com.babachene.userinput.EventGiver;

public class ClientEventController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private GameController gameController;
	private Client client;
	private EventGiver eventGiver;
	private Logic logic;

	private Event event;
	private Update update;

    public ClientEventController(GameController gameController, Client client, EventGiver eventGiver, Logic logic) {
    	this.gameController = gameController;
		this.client = client;
		this.eventGiver = eventGiver;
		this.logic = logic;
        
		LOGGER.info("[Client Event Controller] Started");
	}

    private boolean fetchEvent() {
        if(eventGiver.size() > 0) {
        	LOGGER.fine("[Client Event Controller] Event received from input processor");
            event = eventGiver.pollEvent();
            return true;
        }
        return false;
    }

    private boolean fetchUpdate() {
    	if(!client.isUpdateBufferEmpty()) {
        	LOGGER.fine("[Client Event Controller] Update received from client");
    		update = client.getUpdate();
    		return true;
    	}
    	return false;
    }

	public void update() {
		if(fetchEvent()) {
        	LOGGER.fine("[Client Event Controller] Event sent to client");
			client.addEvent(event);
        	/*LOGGER.fine("[Client Event Controller] Event sent to logic");
			logic.processEvent(event);*/
		}
		if(fetchUpdate()) {
        	LOGGER.fine("[Client Event Controller] Update sent to logic");
			logic.processUpdate(update);
        	LOGGER.fine("[Client Event Controller] Notified the game controller of the update");
			gameController.notifyUpdate(update);
		}
    }
}
