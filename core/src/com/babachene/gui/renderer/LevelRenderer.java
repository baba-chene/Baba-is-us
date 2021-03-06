package com.babachene.gui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main class for rendering a level on the screen. It must contains
 * all the necessary data for rendering a level:
 * <br> - meta data on the level (e.g. background music)
 * <br> - the {@link RenderableMap} of this level
 * @author jeremy
 *
 */
public class LevelRenderer extends Renderer {
	
	
	private MapRenderer map;
	
	
	
	// TODO Music and theme data
	
	/**
	 * 
	 * @param level
	 */
	public LevelRenderer(RenderableLevel level) {
		if (level == null)
			throw new IllegalArgumentException("LevelRendener : Renderable level instance cannot be null.");
		
		
		map = new MapRenderer(level.getMap(), level.getThemeId());
	}
	
	public void update() {
		map.update();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		
		
		// Render map
		map.render(batch);
		
		
	}
	
}
