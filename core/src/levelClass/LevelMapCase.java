package levelClass;

import java.util.LinkedList;

public class LevelMapCase {

	private int xPosition;
	private int yPosition;
	private LevelMap map;
	private boolean isSink;
	private Entity sinkEntity;
	private EntityEmpty entityEmpty;
	private boolean isFree;
	private boolean containsPushableEntity;
	private boolean isEmpty;
	private LinkedList<Entity> pushableEntityList;
	private LinkedList<Entity> entityStack;

	public LevelMapCase(int x, int y, LevelMap map) {
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
		if(entity.getTypeOfEntity()== "empty")           //Is only used when the map is created, to ensure there is always an EmptyEntity in Empty cases.  
			this.entityEmpty = (EntityEmpty) entity;
		if (entity.isPushable())
			pushableEntityList.add(entity);
		if (entity.isSink())
			sinkEntity = entity;
		if (entityStack.size()>1) // We remove the EntityEmpty when something else is on the case
		{
			removeEntityEmpty();
		}
			
		updateIsFree();
		updateContainsPushable();
		updateIsSink();
		
			
	}
	
	public void removeEntity(Entity entity) {
		if (entityStack.contains(entity))
		{
			entityStack.remove(entity);

		}
		if (pushableEntityList.contains(entity))
		{
			pushableEntityList.remove(entity);
		}
		if (entityStack.isEmpty())
			this.map.addEntity(xPosition, yPosition, entityEmpty);
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
	
	public void updateIsSink() {
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
				if(!(pushableEntityList.contains(e)))
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