package com.babachene.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import com.babachene.cliserv.Update;
import com.babachene.gui.MainGame;

public final class MetaController implements Observer {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/** MetaController handles a controller. */
	private Controller controller;
	private MainGame game;
	
	private enum ControllerState {
		Running,
		Disconnecting,
		Disconnected,
	}
	
	private ControllerState state = ControllerState.Disconnected;
	
	////////////////////////////////////
	
	public MetaController(MainGame game) {
		this.game = game;
	}
	
	////////////////////////////////////
	
	public void update() {
		try {
		ControllerState currentState;
		synchronized(this) {
			currentState = state;
		}
		switch(currentState) {
		case Running:
			controller.update();
			break;
		case Disconnecting:
			controller.close();
			game.pop();
			state = ControllerState.Disconnected;
			break;
		case Disconnected:
			break;
		}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void launchLevel(String levelName) { // TODO change the argument
		// TODO Maybe do here some checking in case another level is running.
		if (controller != null)
			controller.launchLevel(levelName);
		else
			(controller = new SoloController(game)).launchLevel(levelName);
		state = ControllerState.Running;
	}

	public void joinServer(String ip, int parseInt) {
		controller = new ClientEventController(game, ip, parseInt);
		state = ControllerState.Running;
	}

	public void hostServer(int parseInt) {
		controller = new ServerEventController(game, parseInt, 10);
		state = ControllerState.Running;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		LOGGER.info("[Game Controller] Connection failed, or disconnected from server");

		synchronized(this) {
			state = ControllerState.Disconnecting;
		}
	}

	public void notifyUpdate(Update update) {
	}
	
}
