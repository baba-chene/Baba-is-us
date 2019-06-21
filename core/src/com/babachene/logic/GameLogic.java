package com.babachene.logic;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.InputEvent;
import com.babachene.cliserv.InputUpdate;
import com.babachene.cliserv.KeepAliveUpdate;
import com.babachene.cliserv.Update;
import com.babachene.logic.data.LevelMap;

public class GameLogic implements Logic {
	
	private LevelMap levelMap;
	
	public GameLogic(LevelMap levelMap) {
		this.levelMap = levelMap;
	}

	@Override
	public Update processEvent(Event event) {
		int id = event.getId();
		switch(id) {
		case 10:
			return null;
		case 20:
			return processInput((InputEvent) event);
		default:
			return new KeepAliveUpdate();
		}
	}

	@Override
	public void processUpdate(Update update) {
		int id = update.getId();
		switch(id) {
		case 10:
			break;
		case 20:
			processInput((InputUpdate) update);
			break;
		default:
			break;
		}
	}
	
	@Override
	public LevelMap getLevelMap() {
		return levelMap;
	}
	
	public Update processInput(InputEvent inputEvent) {
		
		int player = (inputEvent.additionalData == 2) ? 2 : 1;
		
		switch(inputEvent.eventType) {
		case InputEvent.MOVE_DOWN:
			levelMap.moveDown();
			break;
		case InputEvent.MOVE_UP:
			levelMap.moveUp();
			break;
		case InputEvent.MOVE_LEFT:
			levelMap.moveLeft();
			break;
		case InputEvent.MOVE_RIGHT:
			levelMap.moveRight();
			break;
		default: break;
		}
		return new InputUpdate(inputEvent.eventType, inputEvent.additionalData);
	}
	
	public void processInput(InputUpdate inputUpdate) {
		switch(inputUpdate.eventType) {
		case InputEvent.MOVE_DOWN:
			levelMap.moveDown();
			break;
		case InputEvent.MOVE_UP:
			levelMap.moveUp();
			break;
		case InputEvent.MOVE_LEFT:
			levelMap.moveLeft();
			break;
		case InputEvent.MOVE_RIGHT:
			levelMap.moveRight();
			break;
		default: break;
		}	
	}
}
