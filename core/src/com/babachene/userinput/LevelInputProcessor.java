package com.babachene.userinput;

import com.babachene.cliserv.InputEvent;
import com.badlogic.gdx.InputAdapter;

/**
 * Process the input. Use Gdx.input.setInputProcessor(this class)
 * to make libgdx take an instance of this class into account.
 * @author jeremy
 *
 */
/*
 * TODO If a key is kept pressed, the player sould keep moving at each update.
 * However, this is for later, once we managed the movement speed.
 */
public class LevelInputProcessor extends InputAdapter {
	
	private KeyboardMap keymap;
	/** The EventGiver to actually offer the events to.*/
	private EventGiver eventGiver;
	/** flag for insuring the event methods return the correct boolean. */
	private boolean flag = false;
	/** For remembering if the key was pressed or released when an event occurs on it. */
	private boolean zRequestDown = false, resetRequestDown = false;
	
	public LevelInputProcessor(KeyboardMap km, EventGiver eventGiver) {
		if (km == null)
			throw new IllegalArgumentException("KeyboardMap is null for LevelInputProcessor.");
		keymap = km;
		this.eventGiver = eventGiver;
	}
	
	///////////////////////////////
	
	
	public KeyboardMap getKeyboardMap() {
		return keymap;
	}
	
	public void setKeyboardMap(KeyboardMap km) {
		keymap = km;
	}
	
	public EventGiver getEventGiver() {
		return eventGiver;
	}
	
	///////////////////////////////
	
	@Override
	public boolean keyDown(int keycode) {
		
		flag = false; // If the event is processed, flag will be set to true.
		/*
		 * For now, there is no event-exclusion.
		 * I do know this is ugly...
		 */
		if (keycode == keymap.GO_UP) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, (byte)0));
			flag = true;
		}
		if (keycode == keymap.GO_DOWN) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, (byte)0));
			flag = true;
		}
		if (keycode == keymap.GO_LEFT) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, (byte)0));
			flag = true;
		}
		if (keycode == keymap.GO_RIGHT) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_RIGHT, (byte)0));
			flag = true;
		}
		if (keycode == keymap.GO_UP_2) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, (byte)1));
			flag = true;
		}
		if (keycode == keymap.GO_DOWN_2) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, (byte)1));
			flag = true;
		}
		if (keycode == keymap.GO_LEFT_2) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, (byte)1));
			flag = true;
		}
		if (keycode == keymap.GO_RIGHT_2) {
			eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, (byte)1));
			flag = true;
		}
		
		// The request event.
		if (keycode == keymap.Z_REQUEST) {
			if (zRequestDown == false) {
				zRequestDown = true;
				eventGiver.addEvent(new InputEvent(InputEvent.Z_REQUEST, (byte)0));
			}
			flag = true;
		}
		if (keycode == keymap.RESET_REQUEST) {
			if (resetRequestDown == false) {
				resetRequestDown =true;
				eventGiver.addEvent(new InputEvent(InputEvent.RESET_REQUEST, (byte)0));
			}
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		flag = false;
		
		if (keycode == keymap.Z_REQUEST) {
			if (zRequestDown == true) {
				zRequestDown = false;
				eventGiver.addEvent(new InputEvent(InputEvent.Z_REQUEST_STOP, (byte)0));
			}
			flag = true;
		}
		if (keycode == keymap.RESET_REQUEST) {
			if (resetRequestDown == true) {
				resetRequestDown = false;
				eventGiver.addEvent(new InputEvent(InputEvent.RESET_REQUEST_STOP, (byte)0));
			}
			flag = true;
		}
		
		return flag;
	}
	
}
