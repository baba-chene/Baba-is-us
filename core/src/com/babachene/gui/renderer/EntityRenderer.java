package com.babachene.gui.renderer;

import java.util.MissingResourceException;

import com.babachene.gui.Rsrc;
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
		
		// The big id:texture table is here.
		tex = Rsrc.getTextureFromID(e.getId());
		if (tex == null)
			throw new MissingResourceException("Texture is not initialized. Entity id: "+e.getId(), "Rsrc", e.getId());
		
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
		 * Here is the system that handles the movement of the texture.
		 * It is perfect and frenetically typing the keys should not
		 * lead to a wrong texture position.
		 */
		// Verify if the entity has not moved.
		if (entity.getX() != intX || entity .getY() != intY) {
			// For sub-classes.
			handleMovement();
			
			int ex = entity.getX(), ey = entity.getY();
			
			if (moving) {
				fastMove();
			} else {           // Below is the real treatement of a slow movement.
				
				if (ex != intX) {
					
					int dx = ex - intX; // de la position graphique à la position réelle.
					
					if (dx != 1 && dx != -1) {
						fastMove();//dx, ey - intY);
					} else {
						if (ey != intY) {
							fastMove();//dx, ey - intY);
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
					
					if (dy != 1 && dy != -1) {
						fastMove();//0, dy);
					} else {
						/*
						 * case: normal move on Y axis.
						 */
						
						speedY = mapData.speedY * dy;
						moving = true;
					}
				}
				
			}
			intX = ex; // update those, thus the renderer won't perform any more movement
			intY = ey; // initialisation until the entity moves again.
		}
		
//		if (entity.getX() != intX || entity .getY() != intY) {
//			
//			int ex = entity.getX(), ey = entity.getY();
//			int dx = ex - intX; // de la position graphique à la position réelle.
//			int dy = ey - intY; // de la position graphique à la position réelle.
//			
//			// All the condition for a fullspeed movement.
//			if (moving || dx < -1 || 1 < dx || dy < -1 || 1 < dy || dx*dy != 0) {
//				System.out.println(entity.getId() + " is FAST");
//				fastMove(dx, dy);
//			} else {
//				System.out.println(entity.getId() + " is slow");
//			}
//			
//			intX = ex; intY = ey;
//		}
		
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
	/**
	 * Called automatically when the entity has just moved.
	 * <p> Extend this method to introduce new behaviors on
	 * movement for your sub-classes of EntityRenderer.
	 */
	protected void handleMovement() {
		
	}
	
	/////////////////////
	
	public RenderableEntity getRenderableEntity() {
		return entity;
	}
	
	public MapRenderingData getMapRenderingData() {
		return mapData;
	}
	
	
	
	public TextureRegion getTexture() {
		return tex;
	}


	public void setTexture(TextureRegion tex) {
		this.tex = tex;
	}
	
	/////////////////////
	
	/**
	 * Used to manage a fast movement. For all cases when
	 * the enity does not move simply on a direct neightboor
	 * tile.
	 */
	private final void fastMove() {
		
		
		speedX = mapData.xPosition(entity.getX()) - floatX;
		speedY = mapData.yPosition(entity.getY()) - floatY;
		
		speedX /= 5f;
		speedY /= 5f;
		
		moving = true;
	}
	
}
