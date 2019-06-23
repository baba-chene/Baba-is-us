package com.babachene.logic.data;

import java.util.LinkedList;

public class LevelMapCase {
	/* A case of the map contains a lot of information. First of all it contains the stack of entities that are located on this case
	 * but also the properties of the entities that it contains.  
	 */

	private int xPosition;
	private int yPosition;
	private LevelMap map;
	private boolean isSink;			 //True if it contains an entity with isSink
	private boolean isWin;			 //after all the other entities of the case moved.
	private boolean isFree;			 //Means that there is no pushable or block entities on the case
	private boolean containsPushableEntity;
	private EntityEmpty entityEmpty; //This entity will be alone in the stack as long as there is no other entities. It comes back in the stack if there is nothing else.
	private LinkedList<Entity> pushableEntityList;
	private LinkedList<Entity> entityStack;

	public LevelMapCase(int x, int y, LevelMap map) {
		//Only used when a map is created and before adding anything to it, so it's initially empty.
		this.xPosition = x;
		this.yPosition = y;
		this.map = map;
		this.isFree = true;
		this.isSink = false;
		this.isWin = false;
		this.containsPushableEntity = false;
		this.entityStack = new LinkedList<Entity>();
		entityEmpty = new EntityEmpty(x, y, map);
		this.pushableEntityList = new LinkedList<Entity>();
		}
	public boolean isFree() {
		return isFree;
	}

	public boolean containsPushableEntity() {
		return containsPushableEntity;
	}
	
	public LinkedList<Entity> getEntityStack() {
		return entityStack;
	}

	public void addEntity(Entity entity) {
		entityStack.add(entity);
		if(entity.getTypeOfEntity()== "empty")       //Is only used when the map is created, to ensure there is always an EmptyEntity in Empty cases.  
			this.entityEmpty = (EntityEmpty) entity;
		if (entity.isPushable())
			pushableEntityList.add(entity);
		if (entityStack.size()>1)					 // We remove the EntityEmpty when something else is on the case
		{
			removeEntityEmpty();
		}											 // We look if the new entity has special effects on the case
		updateIsFree();
		updateContainsPushable();
		updateIsSink();	
	}
	
	public void removeEntity(Entity entity) {						// /!\ Only removes the entity from the map case and not from the group of entity,
		entityStack.remove(entity);									// use map.removeEntity to remove the entity from the case and the group
		pushableEntityList.remove(entity);
		if (entityStack.isEmpty())
			this.map.addEntity(xPosition, yPosition, entityEmpty);
		 															// We look if the removal of the entity has special effects on the case
		updateIsFree();
		updateContainsPushable();
		
	}
	

	public LinkedList<Entity> getPushableEntityList() {
		return pushableEntityList;
	}
	public void setPushableEntityList(LinkedList<Entity> pushableEntityList) {
		this.pushableEntityList = pushableEntityList;
	}
	
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	public void updateIsSink() { //We look after all the entities to check if one is Sink, so we initially set it to false.
		if (isSink && entityStack.size()>1 ) //If 2 or more entities are on the same case and one of them is sink then they both blow up.
		{
			this.clearEntities();
		}
		if (isSink && entityEmpty.isSink() && !(entityStack.get(0).getTypeOfEntity() == "empty"))
		{
			this.clearEntities();
		}
		this.isSink = false;
		for (Entity e:entityStack) {
			if (e.isSink())
				this.isSink=true;
		}

		
	}
	
	public void updateIsFree() {
		this.isFree = true;
		for (Entity e:entityStack) {
			if (e.isBlock() || e.isPushable)
				this.isFree = false;
		}
	}

	public void updateContainsPushable() {
		this.containsPushableEntity = false;
		this.pushableEntityList = new LinkedList<Entity>();
		for (Entity e:entityStack) {
			if (e.isPushable())
			{
				this.containsPushableEntity = true;
				if(!(pushableEntityList.contains(e))) //To avoid duplicates.
					pushableEntityList.add(e);
			}
		}
	}
	
	public boolean isWin() {
		return isWin;
	}
	public void updateIsWin() {
		this.isWin = false;
		for (Entity e:entityStack) {
			if (e.isWin())
				this.isWin = true;
		}
	}
	
	public void removeEntityEmpty() {
		this.map.removeEntity(entityEmpty);
	}
	public void clearEntities() {
		int size = entityStack.size();
		for (int i =size -1; i>-1;i--) //On retire des éléments d'une liste qu'on parcourt, on les retire donc en partant de la fin.
			{Entity entity = entityStack.get(i);
			if (!entity.getTypeOfEntity().equalsIgnoreCase("empty"))
				this.map.getMapUpdateQueue().pushRemovedEntity(entity); 
			this.map.removeEntity(entityStack.get(i)); 
			}
		this.pushableEntityList = new LinkedList<Entity>();
		this.containsPushableEntity = false;
		this.isFree= true;
		this.isSink = false;

	}
}