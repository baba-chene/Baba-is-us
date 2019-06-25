package com.babachene.logic.data;

import java.util.LinkedList;

import com.babachene.logic.data.entities.Entity;
import com.babachene.logic.data.entities.EntityEmpty;

public class LevelMapCase {
	/* A case of the map contains a lot of information. First of all it contains the stack of entities that are located on this case
	 * but also the properties of the entities that it contains.  
	 */

	private int xPosition;
	private int yPosition;
	private LevelMap map;
	private boolean canMoveRight;
	private boolean canMoveLeft;
	private boolean canMoveUp;
	private boolean canMoveDown;
	private boolean isKill;
	private boolean isBlock;
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
		this.isBlock = false;
		this.isFree = true;
		this.isSink = false;
		this.isWin = false;
		this.canMoveDown = false;
		this.canMoveUp = false;
		this.canMoveLeft = false;
		this.canMoveRight = false;
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
		updateIsKill();
		updateIsBlock();
	}
	
	public void removeEntity(Entity entity) {						// /!\ Only removes the entity from the map case and not from the group of entity,
		entityStack.remove(entity);									// use map.removeEntity to remove the entity from the case and the group
		pushableEntityList.remove(entity);
		if (entityStack.isEmpty())
			this.map.addEntity(xPosition, yPosition, entityEmpty);
		 															// We look if the removal of the entity has special effects on the case
		updateIsFree();
		updateContainsPushable();
		updateIsBlock();
		
	}
	
	public boolean isBlock() {
		return isBlock;
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
	
	public void updateIsKill() {
		if(isKill && entityStack.size()>1) {
			this.clearEntitiesKill();
		}
		this.isKill = false;
		for (Entity e:entityStack) {
			if (e.isKill())
				this.isKill=true;
		}
	}
	
	public void updateIsFree() {
		this.isFree = true;
		for (Entity e:entityStack) {
			if (e.isBlock() || e.isPushable())
				this.isFree = false;
		}
	}
	
	public void updateIsBlock() {
		this.isBlock = true;
		for (Entity e:entityStack) {
			if (e.isBlock() )
				this.isBlock = true;
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
	
	public void clearEntitiesKill() {
		int size = entityStack.size();
		for (int i =size -1; i>-1;i--) //On retire des éléments d'une liste qu'on parcourt, on les retire donc en partant de la fin.
			{Entity entity = entityStack.get(i);
			if(!entity.isKill()) {
			if (!entity.getTypeOfEntity().equalsIgnoreCase("empty"))
				this.map.getMapUpdateQueue().pushRemovedEntity(entity); 
			this.map.removeEntity(entity); 
			}
			}
		this.updateContainsPushable();
		this.updateIsFree();
		this.updateIsBlock();
		this.updateIsSink();
		this.updateIsWin();
		
	}
	
	public void updateCanMove() {
		updateCanMoveDown();
		updateCanMoveLeft();
		updateCanMoveRight();
		updateCanMoveUp();
	}
	
	public void updateCanMoveRight() {
		this.canMoveRight = false;
		int x = this.xPosition;
		int y = this.yPosition;
		while (y < map.getyLength() -1)
		{
			if(map.getMapMatrix()[x][y+1].isFree()) {
				this.canMoveRight = true;
				return;
				}
			if(map.getMapMatrix()[x][y+1].containsPushableEntity()) {
				y++;
			}
				
		}
	}
	public void updateCanMoveLeft() {
		this.canMoveLeft = false;
		int x = this.xPosition;
		int y = this.yPosition;
		while (y > 0)
		{
			if(map.getMapMatrix()[x][y-1].isFree()) {
				this.canMoveLeft = true;
				return;
				}
			if(map.getMapMatrix()[x][y-1].containsPushableEntity()) {
				y--;
			}
				
		}
	}
	public void updateCanMoveUp() {
		this.canMoveUp = false;
		int x = this.xPosition;
		int y = this.yPosition;
		while (x >0 )
		{
			if(map.getMapMatrix()[x-1][y].isFree()) {
				this.canMoveUp = true;
				return;
				}
			if(map.getMapMatrix()[x-1][y].containsPushableEntity()) {
				x--;
			}
				
		}
	}
	public void updateCanMoveDown() {
		this.canMoveDown = false;
		int x = this.xPosition;
		int y = this.yPosition;
		while (x < map.getxLength() -1)
		{
			if(map.getMapMatrix()[x+1][y].isFree()) {
				this.canMoveDown = true;
				return;
				}
			if(map.getMapMatrix()[1+x][y].containsPushableEntity()) {
				x++;
			}
				
		}
	}
	public boolean isCanMoveRight() {
		return canMoveRight;
	}
	public boolean isCanMoveLeft() {
		return canMoveLeft;
	}
	public boolean isCanMoveUp() {
		return canMoveUp;
	}
	public boolean isCanMoveDown() {
		return canMoveDown;
	}
	
	
}