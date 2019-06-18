package com.babachene.gui.renderer;

/**
 * Represents an entity that is to be rendered. It must provide
 * information about the entity only.
 * <p>
 * These methods can be called during in the renderer objects'
 * constructors or at each game loop render.
 * @author jeremy
 *
 */
public interface RenderableEntity {
	
	/**
	 * The identifiant, or type, of the entity. So that
	 * the renderers will match the correct texture.
	 * @return Entity's id
	 */
	short getId();
	
	/**
	 * The X tile number of this entity, tile (0,0) is at bottom left of
	 * the map.
	 * @return The x position
	 */
	int getX();
	
	/**
	 * The Y tile number of this entity, tile (0,0) is at bottom left of
	 * the map.
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
	Object getDirection(); // TODO change the type of Ojbect.
	
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
	 * @return The appearance varation of this 
	 */
	Object getVaration(); // TODO change the type.
	
}
