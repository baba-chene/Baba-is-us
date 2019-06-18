package levelClass;

import java.util.LinkedList;

public class LevelMapCase {

	private int xPosition;
	private int yPosition;
	private int blockEntityCounter;
	private boolean isSink;
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
		for (Entity e:entityStack) {
			if (e.isPushable())
			{
				this.containsPushableEntity = true;
				if(!(pushableEntityList.contains(e)))
					pushableEntityList.add(e);
			}
		}
	}
	
	public void clearEntities() {
		this.entityStack = new LinkedList<Entity>();
	}
}