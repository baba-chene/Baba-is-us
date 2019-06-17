package levelClass;

import java.util.LinkedList;

public class LevelMapCase {

	private int xPosition;
	private int yPosition;
	private int blockEntityCounter;
	private boolean isFree;
	private boolean containsPushableEntity;
	private LinkedList<Entity> pushableEntityList;
	private LinkedList<Entity> entityStack;

	public LevelMapCase(int x, int y, LevelMap map) {
		this.xPosition = x;
		this.yPosition = y;
		this.blockEntityCounter =0;
		this.isFree = true;
		this.containsPushableEntity = false;
		this.entityStack = new LinkedList<Entity>();
		entityStack.add(new EntityEmpty(x, y, map));
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
		if (entity.isPushable())
			pushableEntityList.add(entity);
		if(entity.isBlock)
			blockEntityCounter +=1;
		updateIsFree();
		updateContainsPushable();
	}
	
	public void removeEntity(Entity entity) {
		if (entityStack.contains(entity))
		{
			entityStack.remove(entity);
			if (entity.isBlock())
				blockEntityCounter -=1;
		}
		if (pushableEntityList.contains(entity))
		{
			pushableEntityList.remove(entity);
		}
		updateIsFree();
		updateContainsPushable();
		
	}
	
	public LinkedList<Entity> getPushableEntityList() {
		return pushableEntityList;
	}
	public void setPushableEntityList(LinkedList<Entity> pushableEntityList) {
		this.pushableEntityList = pushableEntityList;
	}
	public void updateIsFree() {
		if (blockEntityCounter>0 || !(pushableEntityList.isEmpty()))
			isFree = false;
		else
			isFree = true;
	}

	public void updateContainsPushable() {
		if (pushableEntityList.isEmpty())
			containsPushableEntity = false;
		else
			containsPushableEntity = true;
	}
	
	public void clearEntities() {
		this.entityStack = new LinkedList<Entity>();
	}
}