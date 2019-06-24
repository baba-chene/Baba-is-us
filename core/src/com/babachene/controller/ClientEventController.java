package com.babachene.controller;

import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Event;
import com.babachene.cliserv.InputUpdate;
import com.babachene.cliserv.Update;
import com.babachene.gui.LevelState;
import com.babachene.gui.MainGame;
import com.babachene.logic.GameLogic;
import com.babachene.logic.IdentityRequest;
import com.babachene.logic.LevelRequest;
import com.babachene.logic.LevelUpdate;
import com.babachene.logic.Logic;
import com.babachene.logic.data.LevelMap;
import com.babachene.userinput.EventGiver;
import com.babachene.userinput.KeyboardMap;
import com.babachene.userinput.LevelInputProcessor;
import com.badlogic.gdx.Input.Keys;

public class ClientEventController extends Controller {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private MainGame game;
	private Client client;
	
	private LevelInputProcessor inputProcessor;
	private EventGiver eventGiver;
	private Logic logic;

	private Event event;
	private Update update;

    public ClientEventController(MainGame mainGame, String ipAddress, int port) {
    	this.game = mainGame;
		this.client = new Client(game.getMetaController(), 10, 10);
		client.connect(ipAddress, port);
		
		this.eventGiver = new EventGiver();
		inputProcessor = new LevelInputProcessor(new KeyboardMap(Keys.Z, Keys.S, Keys.Q, Keys.D, Keys.A, Keys.A, Keys.A, Keys.A,
				Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT), eventGiver);
		logic = null;
        
		LOGGER.info("[Client Event Controller] Started");
		
		// So we hope to recieve the map lol.
		client.addEvent(new LevelRequest());
	    client.addEvent(new IdentityRequest());
	}

    private boolean fetchEvent() {
        if(eventGiver != null && ! eventGiver.isEmpty()) {
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
	    	if (update instanceof InputUpdate) assert ((InputUpdate) update).getPlayer() == 2;
			if (logic == null) {
				// TODO log
				// wait, if there is no logic yet, maybe we can revieve one through update!
				if (update instanceof LevelUpdate)
					launchLevel(null);
			} else {
	        	LOGGER.fine("[Client Event Controller] Update sent to logic");
				logic.processUpdate(update);
			}
        	LOGGER.fine("[Client Event Controller] Notified the game controller of the update");
			game.getMetaController().notifyUpdate(update);
		}
    }
	
	@Override
	public void launchLevel(String arg) {
		
		/*
		 * That's where the client should ask the server for the level, or a key ot find the level.
		 */
		LevelMap lvl = CtrlTest.gimmeLevel();
		
		logic = new GameLogic(lvl);
		eventGiver.clear();
		
		game.push(new LevelState(game, lvl, inputProcessor));
	}
	
	@Override
	public void close() {
		client.shutdown();
	}
}
