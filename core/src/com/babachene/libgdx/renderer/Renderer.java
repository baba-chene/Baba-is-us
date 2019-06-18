package com.babachene.libgdx.renderer;

/**
 * Objects designed to render graphics.
 * @author jeremy
 *
 */
public abstract class Renderer {
	
	public Renderer() {
		
	}
	
	/**
	 * core method.
	 */
	abstract void render();
	
	/*
	 * Considered method. In case the renderers stores their data (i.e. x y) and
	 * require a call to update() to upadte any data.
	 */
	//update()
}
