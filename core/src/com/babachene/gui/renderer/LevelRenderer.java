package com.babachene.gui.renderer;

import com.babachene.gui.BabaIsUs;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
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
	
	
	@Override
	public void render(SpriteBatch batch) {
		
		
		
		// Render map
		map.render(batch);
		
		
	}
	
}
