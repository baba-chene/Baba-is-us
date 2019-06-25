package com.babachene.gui.renderer;

/**
 * Super class for all Entity renderers that wish to change
 * its appearance on the presence on 
 * @author jeremy
 *
 */
abstract class NeightboorRenderer extends EntityRenderer { // not a public class
	
	
	public NeightboorRenderer(RenderableEntity e, MapRenderingData mapData) {
		super(e, mapData);
	}
	
	/**
	 * Calls the <code>update()</code> method in order to
	 * recompute the right texture.
	 */
	@Override
	protected void handleMovement() {
		update();
	}
	
}
