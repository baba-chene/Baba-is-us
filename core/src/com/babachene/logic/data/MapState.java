package com.babachene.logic.data;

import java.util.LinkedList;

public class MapState {

	private LinkedList<Entity> destroyedEntities;
	private LinkedList<Entity> createdEntities;
	private LinkedList<Entity> movedRightEntities;
	private LinkedList<Entity> movedLeftEntities;
	private LinkedList<Entity> movedUpEntities;
	private LinkedList<Entity> movedDownEntities;
	private LevelMap map;
	
	public MapState(LevelMap map) {
		this.createdEntities = new LinkedList<Entity>();
		this.destroyedEntities = new LinkedList<Entity>();
		this.movedDownEntities = new LinkedList<Entity>();
		this.movedUpEntities = new LinkedList<Entity>();
		this.movedLeftEntities = new LinkedList<Entity>();
		this.movedRightEntities = new LinkedList<Entity>();
		this.map = map;

	}
	
	public void addDestroy(Entity entity) {
		if(!entity.getTypeOfEntity().equalsIgnoreCase("empty"))
		destroyedEntities.add(entity);
		
	}
	
	public void addCreated(Entity entity) {
		if(!entity.getTypeOfEntity().equalsIgnoreCase("empty"))
		createdEntities.add(entity);
	}
	
	public void addLeft(Entity entity) {
		movedLeftEntities.add(entity);
	}
	
	public void addRight(Entity entity) {
		movedRightEntities.add(entity);
	}
	public void addUp( Entity entity) {
		movedUpEntities.add(entity);
	}
	
	public void addDown (Entity entity) {
		movedDownEntities.add(entity);
	}
	
	public void undo() {
		for (Entity e : destroyedEntities)
			map.addEntity(e);
		for (Entity e : createdEntities)
			map.removeEntity(e);
		for (Entity e : movedDownEntities)
			map.moveUp(e);
		for (Entity e : movedUpEntities)
			map.moveDown(e);
		for (Entity e : movedLeftEntities)
			map.moveRight(e);
		for (Entity e : movedRightEntities)
			map.moveLeft(e);
		}
	}
