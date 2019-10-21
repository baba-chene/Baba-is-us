package com.babachene.gui.renderer;

import com.babachene.logic.data.Direction;

/**
 * Represents an entity that is to be rendered. It must provide
 * information about the entity only.
 * <p>
 * Some of these methods can be called during in the renderer objects'
 * constructors and some others at each game loop render.
 * @author jeremy
 *
 */
public interface RenderableEntity {
	
	/**
	 * The identifiant, or type, of the entity. So that
	 * the renderers will match the correct texture.<br>
	 * This method will not be frequently called, the
	 * renderer will store the texture in order to save the
	 * match searching cost.
	 * @return Entity's id
	 */
	String getId();
	
	/**
	 * The X tile number of this entity, tile (0,0) is at bottom left of
	 * the map.<br>
	 * This method will be frequently called, so please make it fast and
	 * light.
	 * @return The x position
	 */
	int getX();
	
	/**
	 * The Y tile number of this entity, tile (0,0) is at bottom left of
	 * the map.<br>
	 * This method will be frequently called, so please make it fast and
	 * light.
	 * @return The y position
	 */
	int getY();
	
	/**
	 * Tells whether this entity support a directional data. North,
	 * east, south or west. This means that is if this method returns
	 * true, then <code>getDirection()</code> should return a valid
	 * direction.
	 */
	boolean hasDirection();
	
	/**
	 * @return The direction the entity is looking at.
	 */
	Direction getDirection();
	
	/**
	 * Tells whether this entity may come in more than one appearance
	 * and therefore requires additional data to choose what texture
	 * to use.
	 * <br>
	 * This means that is if this method returns
	 * true, then <code>getVaration()</code> should return a valid
	 * number of varation.
	 */
	boolean hasVaration();
	
	/**
	 * 
	 * @return The appearance varation id of this entity
	 */
	Object getVaration(); // TODO change the type.
	
}
