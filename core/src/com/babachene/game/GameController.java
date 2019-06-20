package com.babachene.game;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import com.babachene.cliserv.Client;
import com.babachene.cliserv.Server;
import com.babachene.cliserv.Update;
import com.babachene.controller.ClientEventController;
import com.babachene.controller.ServerEventController;
import com.babachene.logic.IdentityRequest;
import com.babachene.logic.LevelRequest;
import com.babachene.logic.Logic;
import com.babachene.userinput.EventGiver;

public class GameController  implements Observer {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private ClientEventController clientController;
	private ServerEventController serverController;
	private Logic logic;
	private EventGiver eventGiver;
	private Client client;
	private Server server;
	
	private GameState state;
	
	private enum GameState {
		Idle,
	    Hosting,
	    Joining,;
	}

	public GameController(Logic logic) {
		this.logic = logic;
	}
	
	public void notifyUpdate(Update update) {
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		LOGGER.info("[Game Controller] Connection failed, or disconnected from server");
		state = GameState.Idle;
	}

	public void joinServer(String IPAdress, int port) {
		client = new Client(this, 10, 10);
		client.connect(IPAdress, port);
	    clientController = new ClientEventController(this, client, eventGiver, logic);
	    client.addEvent(new LevelRequest());
	    client.addEvent(new IdentityRequest());
	    state = GameState.Joining;
		LOGGER.info("[Game Controller] Joining a game");
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
