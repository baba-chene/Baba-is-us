package levelClass;

import java.util.LinkedList;

public class LevelMapCase {
	/* A case of the map contains a lot of information. First of all it contains the stack of entities that are located on this case
	 * but also the properties of the entities that it contains.  
	 */

	private int xPosition;
	private int yPosition;
	private LevelMap map;
	private boolean isSink;			 //True if it contains an entity with isSink
	private Entity sinkEntity; 		 //There can only be one entity with isSink, we keep track of it to destroy it if another entity comes by.
	private EntityEmpty entityEmpty; //This entity will be alone in the stack as long as there is no other entities. It comes back in the stack
									 //after all the other entities of the case moved.
	private boolean isFree;			 //Means that there is no pushable or block entities on the case
	private boolean containsPushableEntity;
	private boolean isEmpty;		 //There is only entityEmpty in the stack. 
	private LinkedList<Entity> pushableEntityList;
	private LinkedList<Entity> entityStack;

	public LevelMapCase(int x, int y, LevelMap map) {
		//Only used when a map is created and before adding anything to it, so it's initially empty.
		this.xPosition = x;
		this.yPosition = y;
		this.map = map;
		this.isFree = true;
		this.isEmpty = true;
		this.isSink = false;
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
		if (entity.isSink())
			sinkEntity = entity;
		if (entityStack.size()>1)					 // We remove the EntityEmpty when something else is on the case
		{
			removeEntityEmpty();
		}											 //We look if the new entity has special effects on the case
		updateIsFree();
		updateContainsPushable();
		updateIsSink();	
	}
	
	public void removeEntity(Entity entity) {
		entityStack.remove(entity);
		pushableEntityList.remove(entity);
		if (entityStack.isEmpty())
			this.map.addEntity(xPosition, yPosition, entityEmpty);
		 															//We look if the removal of the entity has special effects on the case
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
		for (Entity e:entityStack) {
			if (e.isPushable())
			{
				this.containsPushableEntity = true;
				if(!(pushableEntityList.contains(e))) //To avoid duplicates.
					pushableEntityList.add(e);
			}
		}
	}
	
	public void removeEntityEmpty() {
		this.map.removeEntity(entityEmpty);
	}
	public void clearEntities() {
		this.entityStack = new LinkedList<Entity>();
	}
}