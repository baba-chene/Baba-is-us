package com.babachene.gui.renderer;

import com.babachene.gui.Rsrc;
import com.babachene.logic.data.Direction;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * For rendering entities such as Baba, which change sprite at each
 * movement along with ax direction dependence.
 * @author jeremy
 *
 */
class DirectionalEntityRenderer extends EntityRenderer { // Not a public class
	
	/** To increment at each movement. */
	private int spriteNumber;
	
	private final TextureRegion [] north, east, south, west;
	
	public DirectionalEntityRenderer(RenderableEntity e, MapRenderingData mapData) {
		super(e, mapData);
		spriteNumber = -1;
		north = Rsrc.getDirectionTextureArray(e.getId(), Direction.NORTH);
		east  = Rsrc.getDirectionTextureArray(e.getId(), Direction.EAST);
		south = Rsrc.getDirectionTextureArray(e.getId(), Direction.SOUTH);
		west  = Rsrc.getDirectionTextureArray(e.getId(), Direction.WEST);
	}
	
	@Override
	protected void handleMovement() {
		
		final TextureRegion [] t;
		switch (getRenderableEntity().getDirection()) {
		case EAST: t = east;
			break;
		case NORTH: t = north;
			break;
		case SOUTH: t = south;
			break;
		case WEST: t = west;
			break;
		default: t = north;
			break;
		
		}
		
		spriteNumber = (spriteNumber + 1) % t.length;
		
		setTexture(t[spriteNumber]);
	}
	
}
