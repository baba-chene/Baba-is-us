package com.babachene.gui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Objects designed to render graphics.
 * @author jeremy
 *
 */
public abstract class Renderer {
	
	public Renderer() {
		
	}
	
	/**
	 * core method. FIXME where to put the begin() and end() ?
	 */
	public abstract void render(SpriteBatch batch);
	
	/*
	 * Considered method. In case the renderers stores their data (i.e. x y) and
	 * require a call to update() to upadte any data.
	 */
	//update()
}
