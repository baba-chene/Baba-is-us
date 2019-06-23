package com.babachene.gui.renderer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A queue for storing events about a RenderableMap's structure.<p>
 * The renderer classes will fetch this object to get updates from the game
 * module. This queue is at best use if it stores events that are fired at
 * a low frequency (lower then the render() loop) and that operate expensive
 * changes on the renderers. For example: adding a new entity to the map.
 * <p>
 * A counter-example is the (x, y) coord that are fetched at every render()
 * call from the enities.
 * @author jeremy
 *
 */
public final class MapUpdateQueue {
	
	private boolean updateRequired;
	
	private Queue<RenderableEntity> createdEntities;
	private Queue<RenderableEntity> removedEntities;
	private Queue<Short> removedGroups;
	
	/**
	 * Instanciate a new empty queue.
	 */
	public MapUpdateQueue() {
		createdEntities = new LinkedList<RenderableEntity>();
		removedEntities = new LinkedList<RenderableEntity>();
		removedGroups = new LinkedList<Short>();
		
		updateRequired = true;
	}
	
	/////////////////////
	
	/**
	 * Tells if this queue contains fresh events and so if
	 * the renderers need to be updated. After being called, this
	 * method will send false again util it recieves new events.
	 * @return true if this queue needs to be fetched for updates.
	 * <br> false if no events are stored or if it was previously called
	 * and no new event came meantime.
	 */
	public boolean requiresUpdate() {
		if ( ! updateRequired)
			return false;
		updateRequired = false;
		return true;
	}
	
	/////////////////////
	
	/**
	 * Add this entity to the queue as a new entity the map has created.
	 * @param e - The renderable entity freshly created
	 */
	public void pushCreatedEntity(RenderableEntity e) {
		createdEntities.add(e);
		updateRequired = true;
	}
	
	/**
	 * Add this entity to the queue as a destroyed entity that is no longer
	 * known by the map. It thus should not be rendered anymore.
	 * @param e - the entity to remove from the renderers.
	 */
	public void pushRemovedEntity(RenderableEntity e) {
		removedEntities.add(e);
		updateRequired = true;
	}
	
	/**
	 * Add to the queue all the entities to that share the same ID
	 * and were destroyed and removed from the map.
	 * <br> This is a shortcut to {@link #pushRemovedEntity}
	 * @param removedEntityId - the id of all entities that are to
	 * be removed from the renderers.
	 */
	public void pushRemovedGroup(short removedEntityId) {
		removedGroups.add(removedEntityId);
		updateRequired = true;
	}
	
	/////////////////////
	
	public boolean hasCreatedEntity() {
		return ! createdEntities.isEmpty();
	}
	
	public RenderableEntity popCreatedEntity() {
		return createdEntities.poll();
	}
	
	public boolean hasRemovedEntity() {
		return ! removedEntities.isEmpty();
	}
	
	public RenderableEntity popRemovedEntity() {
		return removedEntities.poll();
	}
	
	public boolean hasRemovedGroup() {
		return ! removedGroups.isEmpty();
	}
	
	public short popRemovedGroup() {
		Short s = removedGroups.poll();
		return (s != null) ? s.shortValue() : -1 ;
	}
	
	
}
