package com.babachene.gui.renderer;

import java.util.LinkedList;
import java.util.Queue;

public final class MapUpdateQueue {
	
	private boolean updateRequired;
	
	private Queue<RenderableEntity> createdEntities;
	private Queue<RenderableEntity> removedEntities;
	private Queue<Short> removedGroups;
	
	public MapUpdateQueue() {
		createdEntities = new LinkedList<RenderableEntity>();
		removedEntities = new LinkedList<RenderableEntity>();
		removedGroups = new LinkedList<Short>();
		
		updateRequired = true;
	}
	
	/////////////////////
	
	public boolean requiresUpdate() {
		if ( ! updateRequired)
			return false;
		updateRequired = false;
		return true;
	}
	
	/////////////////////
	
	public void pushCreatedEntity(RenderableEntity e) {
		createdEntities.add(e);
		updateRequired = true;
	}
	
	public void pushRemovedEntity(RenderableEntity e) {
		removedEntities.add(e);
		updateRequired = true;
	}
	
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
