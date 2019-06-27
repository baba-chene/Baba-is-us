package com.babachene.controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Event;
import com.babachene.cliserv.InputEvent;
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
import com.babachene.logic.data.MapEditorConverter;
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
	
	private ArrayBlockingQueue<Event> eventsWaitingForACK;

    public ClientEventController(MainGame mainGame, String ipAddress, int port) {
    	this.game = mainGame;
		this.client = new Client(game.getMetaController(), 10, 10);
		client.connect(ipAddress, port);
		
		this.eventGiver = new EventGiver();
		inputProcessor = new LevelInputProcessor(new KeyboardMap(Keys.Z, Keys.S, Keys.Q, Keys.D, Keys.A, Keys.E, Keys.A, Keys.A,
				Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT), eventGiver);
		logic = null;
		
		eventsWaitingForACK = new ArrayBlockingQueue<Event>(10);
		
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
		if(fetchEvent() && logic != null) {
        	LOGGER.fine("[Client Event Controller] Event sent to client");
			client.addEvent(event);
        	if (event instanceof InputEvent) {
        		event.setPlayer(2);
    			if(((InputUpdate) logic.processEvent(event)).updated)
    				eventsWaitingForACK.add(event);
        	}
		}
		if(fetchUpdate()) {
	    	if (update instanceof InputUpdate) assert ((InputUpdate) update).getPlayer() == 2;//What
			if (logic == null) {
				// TODO log
				// wait, if there is no logic yet, maybe we can receive one through update!
				if (update instanceof LevelUpdate)
					launchLevel(((LevelUpdate) update).name);
			} else {
	        	if (update instanceof InputUpdate)
	        		checkUpdateAgainstQueue((InputUpdate) update);
	        	else {
	        		logic.processUpdate(update);
	        		LOGGER.fine("[Client Event Controller] Update sent to logic");
	        	}
			}
        	LOGGER.fine("[Client Event Controller] Notified the game controller of the update");
			game.getMetaController().notifyUpdate(update);
		}
    }
	
	private void checkUpdateAgainstQueue(InputUpdate update) {
		if(update.getPlayer() == 1) {
			int size = eventsWaitingForACK.size();
			for(int i = 0; i < size; i++) {
	        	LOGGER.fine("[Client Event Controller] Undoing some events as an update arrived before theirs");
				logic.processUpdate(new InputUpdate(InputEvent.Z_REQUEST, 2, true));
			}
        	LOGGER.fine("[Client Event Controller] Processing the update");
			logic.processUpdate(update);
			Event[] queue = eventsWaitingForACK.toArray(new Event[10]);
			for(int i = 0; i < size; i++) {
	        	LOGGER.fine("[Client Event Controller] Processing the events again");
				logic.processEvent(queue[i]);
			}
		}
		else {
			if (update.updated) {
				if(eventsWaitingForACK.poll() == null)
		        	LOGGER.warning("[Client Event Controller] Update received for client but no event was left in the queue");
			}
		}
	}
	
	@Override
	public void launchLevel(String arg) {
		
		/*
		 * That's where the client should ask the server for the level, or a key to find the level.
		 */
		
		MapEditorConverter mapEditorConverter = new MapEditorConverter(30, 20);
		try {
			mapEditorConverter.open("maps/"+arg+".txt");
		} catch (IOException e) {
			System.out.println("Level doesn't exist");
			e.printStackTrace();
		}
		LevelMap map = mapEditorConverter.getMap();
		logic = new GameLogic(map);
		
		game.push(new LevelState(game, map, inputProcessor, this));
	}
	
	@Override
	public void close() {
		client.shutdown();
	}
}
