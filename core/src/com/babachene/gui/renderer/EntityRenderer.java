package com.babachene.gui.renderer;

import com.babachene.Baba;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A renderer for ONE entity.
 * @author jeremy
 *
 */
class EntityRenderer extends Renderer { // Not a public class
	
	private RenderableEntity entity;
	
	private TextureRegion tex;
	
	private MapRenderingData mapData;
	
	/** Tells whether this entity is moving or not. */
	private boolean moving;
	/** The tile index coord, on the discrete data map. */
	private int intX, intY;
	/** The position in the graphical map. */
	private float floatX, floatY;
	private float speedX, speedY;
	
	/////////////////////////////////////////
	
	public EntityRenderer(RenderableEntity e, MapRenderingData mapData) {
		if (e == null)
			throw new IllegalArgumentException("RenderableEntity arg cannot be null");
		if (mapData == null)
			throw new IllegalArgumentException("MapRenderingData arg cannot be null");
		
		this.mapData = mapData;
		entity = e;
		
		short entityId = e.getId();
		
		// The big id:texture table is here.
		tex = Mapper.textureById(entityId);
		
		moving = false;
		intX = entity.getX();
		intY = entity.getY();
		
		floatX = mapData.xPosition(intX);
		floatY = mapData.yPosition(intY);
		
		speedX = speedY = 0f;
	}
	
	
	@Override
	public void render(SpriteBatch batch) {
		
		/*
		 * Here is the system that handle the movement of the texture.
		 * It is not perfect and frenetically typing the keys may
		 * lead to a wrong texture position.
		 */
		// Verify if the entity has not moved.
		if (entity.getX() != intX || entity .getY() != intY) {
			
			int ex = entity.getX(), ey = entity.getY();
			
			if (ex != intX) {
				
				int dx = ex - intX; // de la position graphique à la position réelle.
				
				if (dx != 1 && dx != -1) {
					fastMove(dx, ey - intY);
				} else {
					if (ey != intY) {
						fastMove(dx, ey - intY);
					} else {
						/*
						 * case: normal move on X axis.
						 */
						
						speedX = mapData.speedX * dx;
						moving = true;
						
					}
				}
			} else {
				
				int dy = ey - intY; // de la position graphique à la position réelle.
				
				if (dy != 1 && dy == -1) {
					fastMove(0, dy);
				} else {
					/*
					 * case: normal move on Y axis.
					 */
					
					intY = ey;
					speedY = mapData.speedY * dy;
					moving = true;
				}
			}
			
			intX = ex; // update those, thus the renderer won't perform any more movement
			intY = ey; // initialisation until the entity moves again.
		}
		
		// When in a moving phase, update the float position and possibly end the move.
		if (moving) {
			
			if (Math.abs(floatX - mapData.xPosition(intX)) < 2 * mapData.speedX) {
				
				floatX = mapData.xPosition(intX);
				
				if (Math.abs(floatY - mapData.yPosition(intY)) < 2 * mapData.speedY) {
					
					floatY = mapData.yPosition(intY);
					
					moving = false; // The movement is done.
					
				} else {
					floatY += speedY;
				}
				
			} else {
				if (Math.abs(floatY - mapData.yPosition(intY)) < 2 * mapData.speedY) {
					
					floatY = mapData.yPosition(intY);
					
				} else {
					floatY += speedY;
				}
				
				floatX += speedX;
				
			}
			
		}
		
		/*
		 *  Draw the texture.
		 */
		batch.draw(tex, floatX,
		                floatY,
		                mapData.tileWidth,
		                mapData.tileHeight);
		
	}
	
	@Override
	public void update() {
		
	}
	
	/////////////////////
	
	public RenderableEntity getRenderableEntity() {
		return entity;
	}
	
	public MapRenderingData getMapRenderingData() {
		return mapData;
	}
	
	/////////////////////
	
	/**
	 * Used to manage a fast movement. For all cases when
	 * the enity does not move simply on a direct neightboor
	 * tile.
	 */
	private final void fastMove(int dx, int dy) {
		
		speedX = mapData.fastSpeedX * Math.signum((float) dx);
		speedY = mapData.fastSpeedY * Math.signum((float) dy);
		moving = true;
	}
	
}
