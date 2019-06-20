package com.babachene.gui;

import java.util.Deque;
import java.util.LinkedList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 * Abstract class for implementing a state based game,
 * a game that delegates the work to sub-games (instances
 * of {@link GameState}) and supports push() and pop()
 * operations to add or remove the states of the game
 * state stack.
 * @author jeremy
 *
 */
public abstract class StateBasedGame implements ApplicationListener {
	
	private Deque<GameState> stack;
	private GameState currentState;
	
	public StateBasedGame() {
		stack = new LinkedList<GameState>();
	}
	
	/**
	 * Called when the Application should render itself.
	 * <br><br>
	 * StateBasedGame calls all those three method to the current
	 * GameState, in order:
	 * <code><br>- processInput
	 * <br>- update
	 * <br>- render
	 * </code>
	 */
	@Override
	public void render() {
		if (currentState != null) {
			
			currentState.processInput();
			
			currentState.update();
			
			currentState.render();
		}
	}
	
	@Override
	public void resize(int width, int height) {
		if (currentState != null) currentState.resize(width, height);
	}
	
	@Override
	public void pause() {
		if (currentState != null) currentState.pause();
	}

	@Override
	public void resume() {
		if (currentState != null) currentState.resume();
	}
	
	/**
	 * Called when the Application is destroyed.
	 * Preceded by a call to {@link #pause()}.
	 * <p>
	 * This medthod calls {@link #dispose()} to <b>ALL</b>
	 * its GameStates intances previously added by push() and
	 * not poped. Use carefully.
	 */
	@Override
	public void dispose() {
		for (GameState g : stack)
			if (g != null) g.dispose();
	}
	
	/////////////////////////// game state stack
	
	/**
	 * Push a new game state.
	 * @param newState - the newgame state.
	 */
	public synchronized void push(GameState newState) {
		
		if (newState == null)
			throw new IllegalArgumentException("Pushed game state is null.");
		stack.push(newState);
		
		setState(newState);
		
		System.out.println("Stack length = "+stack.size());
	}
	
	/**
	 * Pop the current game state, making the game return
	 * to the previous one.
	 * @throws RuntimeException - if no game states are left
	 * for the game.
	 */
	public synchronized void pop() {
	
		assert ! stack.isEmpty(); // Ben ouais.
		if (stack.isEmpty())
			throw new RuntimeException("The game state stack is empty : it shouldn't be.");
		stack.pop();
		if (stack.isEmpty())
			throw new RuntimeException("The game state stack has been made empty : it shouldn't be.");
		// Set the game state to the new head of the stack.
		setState(stack.peek());
		
		System.out.println("Stack length = "+stack.size());
	}
	
	private final void setState(GameState newState) {
		// notify the game states of the change.
		if (currentState != null) currentState.hide();
		if (newState != null) {
			newState.show();
			newState.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		currentState = newState;
	}
	
}
