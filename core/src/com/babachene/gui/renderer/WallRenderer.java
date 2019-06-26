package com.babachene.gui.renderer;

import com.babachene.gui.Rsrc;
import com.babachene.logic.data.LevelMap;

public class WallRenderer extends NeightboorRenderer {
	
	private final LevelMap levelMap;
	
	public WallRenderer(RenderableEntity e, MapRenderingData mapData, LevelMap levelMap) {
		super(e, mapData);
		this.levelMap = levelMap;
	}
	
	@Override
	public void update() {
		super.update();
		// All we need to do is to ask the index of the right texture to WallTextureChooser.
		int i = WallTextureChooser.index(levelMap,
				levelMap.getxLength() - getRenderableEntity().getY(),
				getRenderableEntity().getX(),
				getRenderableEntity().getId());
		setTexture(Rsrc.WALL_ARRAY_TEXTURE[i]);
	}
	
}
