package com.babachene.gui.renderer;

import java.util.List;

/**
 * Represents a map. A map is a 2D array of tiles, and each tile may
 * contains a set of entities to render. The map also encapsulates
 * data such as its dimension.
 * 
 * @author jeremy
 *
 */
public interface RenderableMap extends Iterable<RenderableEntity> {
	
	/**
	 * 
	 * @return The width of the map, x axe.
	 */
	int getWidth();
	
	/**
	 * 
	 * @return The height of the map, y axe.
	 */
	int getHeight();
	
	/**
	 * Each map should have a queue to store events of entity creation/destruction.
	 * This method allow the map renderer to get a reference to this queue so it
	 * can take the creations and destructions of entities into account.
	 * @return The queue storing update for the map renderer.
	 */
	MapUpdateQueue getMapUpdateQueue();
	
	/**
	 * May be optional or removed in the futur. The <code>iterator()</code>
	 * method is more important.
	 * @param x - x coordinate of the tile
	 * @param y - y coordinate of the tile
	 * @return The list containing all the entities on the specified tile.<br>
	 * The first element of the list shall be the one at bottom.
	 */
	List<RenderableEntity> getEntity(int x, int y);

}
