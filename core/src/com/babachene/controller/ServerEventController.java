package com.babachene.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.Server;
import com.babachene.cliserv.Update;
import com.babachene.gui.LevelState;
import com.babachene.gui.MainGame;
import com.babachene.logic.GameLogic;
import com.babachene.logic.LevelRequest;
import com.babachene.logic.LevelUpdate;
import com.babachene.logic.Logic;
import com.babachene.logic.data.LevelMap;
import com.babachene.userinput.EventGiver;
import com.babachene.userinput.KeyboardMap;
import com.babachene.userinput.LevelInputProcessor;
import com.badlogic.gdx.Input.Keys;

/**
 * A blend of input and network event.
 * @author Colin, Jeremy
 */
public class ServerEventController extends Controller {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private MainGame game;
	private Server server;
	
	private LevelInputProcessor inputProcessor;
	private EventGiver eventGiver;
	private Logic logic;

	private ArrayBlockingQueue<Event> eventBuffer;
	private int eventBufferLength;
	private Update update;

    public ServerEventController(MainGame mainGame, int port, int eventBufferLength) {
    	this.game = mainGame;
		this.eventBufferLength = eventBufferLength;
		
		server = new Server(mainGame.getMetaController(), 10, 10);
		server.open(port);
        eventBuffer = new ArrayBlockingQueue<Event>(eventBufferLength);
        
        eventGiver = new EventGiver();
		inputProcessor = new LevelInputProcessor(new KeyboardMap(Keys.Z, Keys.S, Keys.Q, Keys.D, Keys.A, Keys.E, Keys.A, Keys.A,
													Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT), eventGiver);
		
        
		LOGGER.info("[Server Event Controller] Started");
		
		launchLevel(null);
	}
    
    //////////////////////////////////////////////////////////
    
    private void addEvent(Event event) {
    	if(eventBuffer.size() < eventBufferLength)
    		eventBuffer.add(event);
    	else
    		LOGGER.warning("[Server Event Controller] Event buffer full, event dropped");
    }
    
    private void fetchEvents() {
        if(!server.isEventBufferEmpty()) {
    		LOGGER.fine("[Server Event Controller] Event received from server");
            Event event = server.getEvent();
    		event.setPlayer(2);
            addEvent(event);
            
            // Responde to LevelRequest. To delete once the structured is good.
            if (event instanceof LevelRequest)
            	sendUpdate(new LevelUpdate());
        }
        // If eventGiver is null, only server events are fetched. Is it bad?
        if(eventGiver != null && ! eventGiver.isEmpty()) {
    		LOGGER.fine("[Server Event Controller] Event received from input processor");
    		Event event = eventGiver.pollEvent();
    		event.setPlayer(1);
            addEvent(event);
        }
        
    }
    
    private boolean solveEvent() {
    	if(eventBuffer.size() > 0) {
    		
    		if (logic == null) {
//    			LOGGER.warning("[Server Event Controller ] Attempted to update logic but it is null.");
    		} else {
    			LOGGER.fine("[Server Event Controller] Event sent to logic.");
    			update = logic.processEvent(eventBuffer.poll());
    			return true;
    		}
    	}
    	return false;
    }

    private void sendUpdate() {
    	LOGGER.fine("[Server Event Controller] Update sent to server");
    	server.addUpdate(update);
		LOGGER.fine("[Server Event Controller] Notified the game controller of the update");
		game.getMetaController().notifyUpdate(update);
    }
    private void sendUpdate(Update update) {
    	LOGGER.fine("[Server Event Controller] Update sent to server");
    	server.addUpdate(update);
		LOGGER.fine("[Server Event Controller] Notified the game controller of the update");
		game.getMetaController().notifyUpdate(update);
    }

	public void update() {
		fetchEvents();
		if(solveEvent())
			sendUpdate();
    }
	
	@Override
	public void launchLevel(String arg) {
		
		LevelMap lvl = CtrlTest.gimmeLevel(); // It's for testing purpose.
		
		
		
		logic = new GameLogic(lvl);
		eventGiver.clear();
		
		game.push(new LevelState(lvl, inputProcessor));
		
	}
	
	@Override
	public void close() {
		server.shutdown();
	}
 }
