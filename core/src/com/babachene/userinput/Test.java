package com.babachene.userinput;

import java.io.IOException;

import com.babachene.cliserv.InputEvent;
import com.badlogic.gdx.Input.Keys;

class Test {
	
	public static void main(String[] args) throws IOException {
		
		
		LevelInputProcessor lip = new LevelInputProcessor(new KeyboardMap(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), null);
		lip.keyDown(Keys.A);
		
		InputEvent e = new InputEvent(InputEvent.MOVE_LEFT, (byte) -1);
		
		
		
		EventGiver giver = new EventGiver();
		assert giver.capacity() == EventGiver.CAPACITY;
		int s = giver.size();
		giver.addEvent(e);
		assert s + 1 == giver.size();
		InputEvent e2 = giver.pollEvent();
		assert e == e2;
		giver.pollEvent();
		assert s == giver.size();
		
		lip = new LevelInputProcessor(new KeyboardMap(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), giver);
		lip.keyDown(1);
		
		giver.clear();
		e = giver.pollEvent();
		assert e == null;
		
		lip.keyDown(lip.getKeyboardMap().GO_DOWN);
		e = giver.pollEvent();
		assert e.eventType == InputEvent.MOVE_DOWN;
		assert e.getPlayer() == 0;
		
		for (int i = 0; i < 266; i++) {
			giver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, (byte) i));
		}
		for (int i = 0; i < giver.capacity(); i++) {
			assert giver.pollEvent().getPlayer() == (byte) i;
		}
		assert giver.size()==0;
		

		lip.keyDown(lip.getKeyboardMap().GO_UP);
		e = giver.pollEvent();
		assert e.eventType == InputEvent.MOVE_UP;

		lip.keyDown(lip.getKeyboardMap().GO_LEFT);
		e = giver.pollEvent();
		assert e.eventType == InputEvent.MOVE_LEFT;
		lip.keyDown(lip.getKeyboardMap().GO_RIGHT);
		e = giver.pollEvent();
		assert e.eventType == InputEvent.MOVE_RIGHT;
		lip.keyDown(lip.getKeyboardMap().GO_DOWN_2);
		e = giver.pollEvent();
		assert e.getPlayer() == 1;
		assert e.eventType == InputEvent.MOVE_DOWN;
		lip.keyDown(lip.getKeyboardMap().GO_LEFT_2);
		e = giver.pollEvent();
		assert e.eventType == InputEvent.MOVE_LEFT;
		assert e.getPlayer() == 1;
		
		lip.keyUp(lip.getKeyboardMap().Z_REQUEST);
		assert giver.size() == 0;
		e = giver.pollEvent();
		assert e == null;

		lip.keyDown(lip.getKeyboardMap().Z_REQUEST);
		assert giver.size() == 1;
		e = giver.pollEvent();
		assert e.eventType == InputEvent.Z_REQUEST;
		
		lip.keyDown(lip.getKeyboardMap().RESET_REQUEST);
		lip.keyDown(lip.getKeyboardMap().RESET_REQUEST);
		lip.keyUp(lip.getKeyboardMap().RESET_REQUEST);
		assert giver.size() == 2;
		e = giver.pollEvent();
		assert e.eventType == InputEvent.RESET_REQUEST;
		e = giver.pollEvent();
		assert e.eventType == InputEvent.RESET_REQUEST_STOP;
		
		
		System.out.println("Ok (" + Test.class.getSimpleName() + ")");
	}
	
}
