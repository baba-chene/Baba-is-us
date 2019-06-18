package com.babachene.libgdx;

import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Root class for all the states of the game. It handles basic
 * libgdx stuff, and awaits implementation for the three method
 * that are called in the game loop :
 * <li><code>processInput</code> (already depreciated)</li><code>
 * <li>update</li>
 * <li>render</li>
 * </code>
 * <p>
 * Game states are commonly used to manage the different
 * states of the game, e.g. The game menu, the option setting,
 * or the frame where mario is jumping.
 * @author jeremy
 *
 */
public abstract class GameState {
	
	private FitViewport viewport;
	
	// Pour les objets de type Screen, la méthode create() est comme le constructeur.
	public GameState() {
		viewport = new FitViewport(BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
	}
	
	//////////////////////////////////////////////////////
	
	
	/**
	 * Process the user's input. Not to be mistaken
	 * with <code>update()</code>
	 * <p>
	 * <u>HOWERVER</u><br>There is another way to process input which
	 * is libgdx's InputProcessor interface.
	 */
	public void processInput() {}
	
	/**
	 * Update this game state, the <code>processInput</code>
	 * method having been called before. Not to be mistaken with
	 * <code>render()</code>.
	 */
	public abstract void update();
	
	/**
	 * Render this game state. <p> Don't forget to put <code><br>
	 * Gdx.gl.glClearColor(.0f, .0f, .0f, 1);<br>
	 * Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)</code><br>
	 * at the begining of the implementation.
	 */
	public abstract void render();
	
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
	
	public FitViewport getViewport() {
		return viewport;
	}
	
	public void setViewport(FitViewport viewport) {
		this.viewport = viewport;
	}
	
	public abstract void resume();
	
	public abstract void pause();
	
	public abstract void dispose();
	
	/** Called when this game state becomes the current state for a {@link StateBasedGame}. */
	public abstract void show();
	
	/** Called when this state is no longer the current state for a {@link StateBasedGame}. */
	public abstract void hide();
	
}
