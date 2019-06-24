package com.babachene.userinput;

import com.babachene.Baba;
import com.babachene.cliserv.InputEvent;
import com.badlogic.gdx.Gdx;
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
	/** No comment */
	public static final float DURATION = 23f / Baba.BASE_ENTITY_MOVING_SPEED;
	
	
	/** If this boolean is true, then player is taken into account. Otherwise no. */
	private boolean soloOn;
	/** The keyboard mapping. */
	private KeyboardMap keymap;
	/** The EventGiver to actually offer the events to.*/
	private EventGiver eventGiver;
	/** flag for insuring the event methods return the correct boolean. */
	private boolean flag = false;
	/** For remembering if the key was pressed or released when an event occurs on it. */
	private boolean
			move1Down = false,
			move2Down = false,
//			waitDown = false, // wait is not implemented in the logic yet.
			zRequestDown = false,
			resetRequestDown = false;
	/** For counting time. */
	private float
			move1Time = -1,
			move2Time = -1;
	private InputEvent
			move1Event,
			move2Event;
	
	public LevelInputProcessor(KeyboardMap km, EventGiver eventGiver, boolean isSolo) {
		if (km == null)
			throw new IllegalArgumentException("KeyboardMap is null for LevelInputProcessor.");
		keymap = km;
		if (eventGiver == null)
			throw new IllegalArgumentException("EventGiver is null for LevelInputProcessor.");
		this.eventGiver = eventGiver;
		soloOn = isSolo;
	}
	public LevelInputProcessor(KeyboardMap km, EventGiver eventGiver) {
		this (km, eventGiver, false);
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
	
	public boolean isSoloOn() {
		return soloOn;
	}
	/**
	 * Tells this input processor whether it should send events
	 * for both player 1 and 2 (one keyboard mode) or just player 1.
	 * @param isSoloOn
	 */
	public void setSoloMode(boolean isSoloOn) {
		soloOn = isSoloOn;
		move2Down = false;
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
			move1Event = (new InputEvent(InputEvent.MOVE_UP, (byte)1));
			move1Down = flag = true;
		}
		if (keycode == keymap.GO_DOWN) {
			move1Event = (new InputEvent(InputEvent.MOVE_DOWN, (byte)1));
			move1Down = flag = true;
		}
		if (keycode == keymap.GO_LEFT) {
			move1Event = (new InputEvent(InputEvent.MOVE_LEFT, (byte)1));
			move1Down = flag = true;
		}
		if (keycode == keymap.GO_RIGHT) {
			move1Event = (new InputEvent(InputEvent.MOVE_RIGHT, (byte)1));
			move1Down = flag = true;
		}
		/*
		 * Event from player 2.
		 */
		if (soloOn) {
			if (keycode == keymap.GO_UP_2) {
				move2Event = (new InputEvent(InputEvent.MOVE_UP, (byte)2));
				move2Down = flag = true;
			}
			if (keycode == keymap.GO_DOWN_2) {
				move2Event = (new InputEvent(InputEvent.MOVE_DOWN, (byte)2));
				move2Down = flag = true;
			}
			if (keycode == keymap.GO_LEFT_2) {
				move2Event = (new InputEvent(InputEvent.MOVE_LEFT, (byte)2));
				move2Down = flag = true;
			}
			if (keycode == keymap.GO_RIGHT_2) {
				move2Event = (new InputEvent(InputEvent.MOVE_RIGHT, (byte)2));
				move2Down = flag = true;
			}
		}
		/*
		 *  The request event.
		 */
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
	
	/**
	 * Update this input processor. This method is necessary for the input
	 * processor is at charge for sending move events at regular time intervals in
	 * case the player keeps a key down to move.
	 */
	public void update() {
		if (move1Time > 0) {
			move1Time -= Gdx.graphics.getDeltaTime();
		}
		if (move1Down) {
			if (move1Time > 0) { // Here the event shall not be sent now.
				
			} else { // There we send the event and reset the time counter.
				eventGiver.addEvent(move1Event);
			}
		}
	}
	
}
