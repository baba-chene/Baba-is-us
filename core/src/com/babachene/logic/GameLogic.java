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
			return new KeepAliveUpdate();
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
	
	/**
	 * Don't trust the envent, supposed to check the rule
	 * if the movement is legal. No implemented.
	 * @param inputEvent
	 * @return
	 */
	public Update processInput(InputEvent inputEvent) {
		
		int player = (inputEvent.getPlayer() == 2) ? 2 : 1;
		/*
		 * For now, there is three players : YOU, P1 and P2.
		 * so the YOU will be seen as a US and will move should any player asks movement.
		 * That should not be a big issue as no level sould have YOU and P1/2 text blocks.
		 */
		switch(inputEvent.eventType) {
		case InputEvent.MOVE_DOWN:
			levelMap.moveDown();
			levelMap.moveDown(player);
			break;
		case InputEvent.MOVE_UP:
			levelMap.moveUp();
			levelMap.moveUp(player);
			break;
		case InputEvent.MOVE_LEFT:
			levelMap.moveLeft();
			levelMap.moveLeft(player);
			break;
		case InputEvent.MOVE_RIGHT:
			levelMap.moveRight();
			levelMap.moveRight(player);
			break;
		default: break;
		}
		return new InputUpdate(inputEvent.eventType, inputEvent.getPlayer());
	}
	
	/**
	 * Supposed to trust the update.
	 * @param inputUpdate
	 */
	public void processInput(InputUpdate inputUpdate) {
		
		int player = (inputUpdate.getPlayer() == 2) ? 2 : 1;
		/*
		 * For now, there is three players : YOU, P1 and P2.
		 * so the YOU will be seen as a US and will move should any player asks movement.
		 * That should not be a big issue as no level sould have YOU and P1/2 text blocks.
		 */
		switch(inputUpdate.eventType) {
		case InputEvent.MOVE_DOWN:
			levelMap.moveDown();
			levelMap.moveDown(player);
			break;
		case InputEvent.MOVE_UP:
			levelMap.moveUp();
			levelMap.moveUp(player);
			break;
		case InputEvent.MOVE_LEFT:
			levelMap.moveLeft();
			levelMap.moveLeft(player);
			break;
		case InputEvent.MOVE_RIGHT:
			levelMap.moveRight();
			levelMap.moveRight(player);
			break;
		default: break;
		}	
	}
}
