package com.babachene.game;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Server;
import com.babachene.cliserv.Update;
import com.babachene.controller.ClientEventController;
import com.babachene.controller.ServerEventController;
import com.babachene.gui.LevelState;
import com.babachene.gui.MainGame;
import com.babachene.logic.GameLogic;
import com.babachene.logic.IdentityRequest;
import com.babachene.logic.LevelRequest;
import com.babachene.logic.Logic;
import com.babachene.logic.data.LevelMap;
import com.babachene.userinput.EventGiver;
import com.babachene.userinput.KeyboardMap;
import com.babachene.userinput.LevelInputProcessor;
import com.badlogic.gdx.Input.Keys;

public class GameController implements Observer {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private MainGame game;
	private ClientEventController clientController;
	private ServerEventController serverController;
	private Logic logic;
	private LevelInputProcessor levelInputProcessor;
	private EventGiver eventGiver;
	private Client client;
	private Server server;
	
	private GameState state;
	
	private enum GameState {
		Idle,
	    Hosting,
	    Joining,;
	}

	public GameController(MainGame game) {
		LevelMap map = new LevelMap(10, 10);
			map.addEntity(2, 4, "baba");
			map.addEntity(5, 5, "textis");
			map.addEntity(5, 8, "textbaba");
			map.addEntity(4, 2, "rock");
			map.addEntity(1, 1, "textbaba");
			map.addEntity(2, 1, "textis");
			map.addEntity(3, 1, "textyou");
			
		this.game = game;
		logic = new GameLogic(map);
		eventGiver = new EventGiver();
		levelInputProcessor = new LevelInputProcessor(new KeyboardMap(Keys.Z, Keys.S, Keys.Q, Keys.D, 5, 6, 7, 8, 9, 10, 11, 12), eventGiver);
		state = GameState.Idle;
	}
	
	public void notifyUpdate(Update update) {
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		LOGGER.info("[Game Controller] Connection failed, or disconnected from server");
		state = GameState.Idle;
		game.pop();
	}

	public void joinServer(String IPAdress, int port) {
		client = new Client(this, 10, 10);
		client.connect(IPAdress, port);
	    clientController = new ClientEventController(this, client, eventGiver, logic);
	    client.addEvent(new LevelRequest());
	    client.addEvent(new IdentityRequest());
	    state = GameState.Joining;
		LOGGER.info("[Game Controller] Joining a game");
		game.push(new LevelState(logic.getLevelMap(), levelInputProcessor));
	}
	
	public void disconnect() {
		client.disconnect();
		LOGGER.info("[Game Controller] Reconnecting to game");
	}
	
	public void reconnect() {
		client.connect();
		LOGGER.info("[Game Controller] Disconnecting from game");
	}
	
	public void hostServer(int port) {
		server = new Server(this, 10, 10);
		server.open(port);
		serverController = new ServerEventController(this, server, eventGiver, logic, 10);
	    state = GameState.Hosting;
		LOGGER.info("[Game Controller] Hosting a game");
		game.push(new LevelState(logic.getLevelMap(), levelInputProcessor));
	}

	public void update() {
		switch(state) {
		case Idle :
			break;
		case Hosting :
			serverController.update();
			break;
		case Joining :
			clientController.update();
			break;
			
		}
	}
 }
