package com.babachene.gui.renderer;

import com.babachene.gui.Rsrc;
import com.babachene.logic.data.LevelMap;

public class LavaRenderer extends NeightboorRenderer {
	
	private LevelMap levelMap;
	
	public LavaRenderer(RenderableEntity e, MapRenderingData mapData, LevelMap levelmap) {
		super(e, mapData);
		this.levelMap = levelmap;
	}
	
	@Override
	public void update() {
		int i = WallTextureChooser.index(levelMap,
				levelMap.getxLength() - getRenderableEntity().getY(),
				getRenderableEntity().getX(),
				getRenderableEntity().getId());
		setTexture(Rsrc.LAVA_ARRAY_TEXTURE[i]);
	}
	
}
