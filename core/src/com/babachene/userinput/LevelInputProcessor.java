package com.babachene.userinput;

import com.babachene.Baba;
import com.babachene.cliserv.InputEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

/**
 * Process the input. Use Gdx.input.setInputProcessor(this class)
 * and call its update() method at each frame rendering
 * to make libgdx take an instance of this class into account. 
 * <p>Under the hood, this class makes use of both event listening
 * model and Gdx.input utility. So it requires to be registered with
 * both ways.
 * 
 * @author jeremy
 *
 */
public class LevelInputProcessor extends InputAdapter {
	/*
	 * move events and handled with Gdx.input.isKeyPressed(...), it's polling.
	 * Undo/Reset/Wait request events are handled with the keyDown/keyUp from event listener model.
	 */
	
	
	
	/** No comment */
	public static final float DURATION = 0.03125f / Baba.BASE_ENTITY_MOVING_SPEED;
	public static final float DURATION_OVER_EVENT = 0.028f / Baba.BASE_ENTITY_MOVING_SPEED;
	
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
//			waitDown = false, // wait is not implemented in the logic yet.
			zRequestDown = false,
			resetRequestDown = false;
	/** For counting time. */
	private float
			move1Time = -1,
			move2Time = -1;
	
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
	}
	
	///////////////////////////////
	
	@Override
	public boolean keyDown(int keycode) {
		
		flag = false; // If the event is processed, flag will be set to true.
		
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
		
		/*
		 * Request
		 */
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
		
		/* ================
		 * Player 1
		 */
		
		if (move1Time > 0) {
			move1Time -= Gdx.graphics.getDeltaTime();
			
			/*
			 * Handling quickly pressed key, the result is immediatly sent.
			 */
			if (Gdx.input.isKeyJustPressed(keymap.GO_UP)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, 1));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_RIGHT)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_RIGHT, 1));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_LEFT)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, 1));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_DOWN)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, 1));
			}
			
		} else { // It's okay, the player can move.
			if (Gdx.input.isKeyPressed(keymap.GO_UP)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, 1));    // There is an event exclusion.
				move1Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_RIGHT)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_RIGHT, 1));
				move1Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_LEFT)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, 1));
				move1Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_DOWN)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, 1));
				move1Time = DURATION;
			}
		}
		
//		/*
//		 * However, the over moveEvent cannot live forever. It is removed after some time.
//		 */
//		if (moveOver1Time > 0) {
//			moveOver1Time -= Gdx.graphics.getDeltaTime();
//		} else {
//			move1Event = null;
//		}
		
		
		
		/*
		 *     =============================
		 *     Player 2 (if solo mode is on)
		 *     =============================
		 */
		
		
		if (move2Time > 0) {
			move2Time -= Gdx.graphics.getDeltaTime();
			
			/*
			 * Handling quickly pressed key, the result is immediatly sent.
			 */
			if (Gdx.input.isKeyJustPressed(keymap.GO_UP_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, 2));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_RIGHT_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_RIGHT, 2));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_LEFT_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, 2));
			} else if (Gdx.input.isKeyJustPressed(keymap.GO_DOWN_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, 2));
			}
			
		} else { // It's okay, the player can move.
			if (Gdx.input.isKeyPressed(keymap.GO_UP_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_UP, 2));    // There is an event exclusion.
				move2Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_RIGHT_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_RIGHT, 2));
				move2Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_LEFT_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_LEFT, 2));
				move2Time = DURATION;
			} else if (Gdx.input.isKeyPressed(keymap.GO_DOWN_2)) {
				eventGiver.addEvent(new InputEvent(InputEvent.MOVE_DOWN, 2));
				move2Time = DURATION;
			}
		}
		
	}
	
}
