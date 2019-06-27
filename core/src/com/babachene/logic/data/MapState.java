package com.babachene.logic.data;

import java.util.Collections;
import java.util.LinkedList;

import com.babachene.logic.data.entities.Entity;

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
	
	public boolean isEmpty() {
		return (movedDownEntities.isEmpty()&&movedLeftEntities.isEmpty()&&movedUpEntities.isEmpty()&&movedRightEntities.isEmpty()&&destroyedEntities.isEmpty()&&createdEntities.isEmpty());
	}
	public void undo() {
		this.map.setUndoing(true);
		if (!this.isEmpty()) {
		for (Entity e : destroyedEntities)
			map.addEntity(e);
		for (Entity e : createdEntities)
			map.removeEntity(e);
		Collections.sort(movedDownEntities,new xPositionComparator());
		for (Entity e : movedDownEntities) {
			map.moveUp(e);
			e.setvDirection(Direction.SOUTH);
			e.setDirection(Direction.SOUTH);}
		Collections.sort(movedUpEntities,new xPositionComparator());
		Collections.reverse(movedUpEntities);
		for (Entity e : movedUpEntities) {
			e.setvDirection(Direction.NORTH);
			e.setDirection(Direction.NORTH);
			map.moveDown(e);}
		Collections.sort(movedLeftEntities,new yPositionComparator());
		Collections.reverse(movedLeftEntities);
		for (Entity e : movedLeftEntities) {
			e.sethDirection(Direction.WEST);
			e.setDirection(Direction.WEST);
			map.moveRight(e);}
		Collections.sort(movedRightEntities,new yPositionComparator());
		for (Entity e : movedRightEntities) {
			e.sethDirection(Direction.EAST);
			e.setDirection(Direction.EAST);
			map.moveLeft(e);
		}}
		this.map.setUndoing(false);
		}

	public LinkedList<Entity> getDestroyedEntities() {
		return destroyedEntities;
	}

	public LinkedList<Entity> getCreatedEntities() {
		return createdEntities;
	}

	public LinkedList<Entity> getMovedRightEntities() {
		return movedRightEntities;
	}

	public LinkedList<Entity> getMovedLeftEntities() {
		return movedLeftEntities;
	}

	public LinkedList<Entity> getMovedUpEntities() {
		return movedUpEntities;
	}

	public LinkedList<Entity> getMovedDownEntities() {
		return movedDownEntities;
	}
	
	
	}
