package levelClass;

import java.util.LinkedList;
import java.util.LinkedList;

public class LevelGroupOfEntities {
	private String typeOfEntities;
	private int numberOfEntities;
	private LinkedList<Entity> listOfEntities;
	private LevelPlayer player;

	public LevelGroupOfEntities(String typeOfEntities) {
		super();
		this.typeOfEntities = typeOfEntities;
		this.numberOfEntities = 0;
		this.listOfEntities = new LinkedList<Entity>();
		player = new LevelPlayer();
	}

	public LevelPlayer getPlayer() {
		return player;
	}

	public void setPlayer(LevelPlayer player) {
		this.player = player;
	}

	public String getTypeOfEntities() {
		return typeOfEntities;
	}

	public int getNumberOfEntities() {
		return numberOfEntities;
	}

	public LinkedList<Entity> getListOfEntities() {
		return listOfEntities;
	}

	public LinkedList<Entity> getEntitiesList()
	{
		LinkedList<Entity> coordinatesList = new LinkedList<Entity>();
		return null;
	}
	
	public void removeEntity(Entity entity) {
		if (listOfEntities.contains(entity)) {
			listOfEntities.remove(entity);
			this.numberOfEntities-=1;
		}
			
	}
	
	public void addEntity(Entity entity) {
		if (entity.getTypeOfEntity().equalsIgnoreCase(this.typeOfEntities)){ //We double check that we're adding an entity that has the good type for the group
			this.numberOfEntities+=1;
			this.listOfEntities.add(entity);
		}
	}
	
	public void groupIsBlock() {
		for (Entity e :listOfEntities) {
			e.setBlock(true);
		}
	}
	
	public void groupIsPushable() {
		for (Entity e :listOfEntities) {
			e.setPushable(true);
		}
	}
	
	public void groupIsSink() {
		for (Entity e :listOfEntities) {
			e.setSink(true);
		}
	}
	
	public void groupIsWin() {
		for (Entity e :listOfEntities) {
			e.setWin(true);
		}
	}
	
	public void groupIsNotBlock() {
		for (Entity e :listOfEntities) {
			e.setBlock(false);
		}
	}
	
	public void groupIsNotPushable() {
		for (Entity e :listOfEntities) {
			e.setPushable(false);
		}
	}
	
	public void groupIsNotSink() {
		for (Entity e :listOfEntities) {
			e.setSink(false);
		}
	}
	
	public void groupIsNotWin() {
		for (Entity e :listOfEntities) {
			e.setWin(false);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}