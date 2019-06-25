package com.babachene.gui.renderer;

import com.babachene.gui.Rsrc;
import com.babachene.logic.data.LevelMap;

/**
 * Renderers water.
 * @author jeremy
 *
 */
class WaterRenderer extends EntityRenderer { // Not a public class
	
	private final LevelMap levelMap;
	
	public WaterRenderer(RenderableEntity e, MapRenderingData mapData, LevelMap levelMap) {
		super(e, mapData);
		this.levelMap = levelMap;
	}
	
	@Override
	protected void handleMovement() {
		// All we need to do is to ask the index of the right texture to WaterTextureChooser.
		int i = WaterTextureChooser.index(levelMap, levelMap.getHeight() - getRenderableEntity().getY(), getRenderableEntity().getX());
		setTexture(Rsrc.WATER_ARRAY_TEXTURE[i]);
	}
	
}
