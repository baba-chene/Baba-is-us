package levelClass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LevelGroupOfEntities {
	
	/* Basically is an list of entities of the same type. They are mainly so that the player can control different entities
	 * of the map at the same time. It also allows to manage the rules easily, if a rules changes like for example "rock is push",
	 * we just have to apply the rule to the group of entity and it will set all the rocks to isPush." 
	 */
	private LevelMap map;
	private String typeOfEntities;
	private int numberOfEntities;
	private LinkedList<Entity> listOfEntities;
	private LevelPlayer player;

	public LevelGroupOfEntities(String typeOfEntities, LevelMap map) {
		super();
		this.map= map;
		this.typeOfEntities = typeOfEntities; //There is only one type of entity allowed in a Group of entities
		this.numberOfEntities = 0;
		this.listOfEntities = new LinkedList<Entity>(); //Contains the entities of the group
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
	
	//The following methods set the attributes isWin, isPush ... to true or false to all the entities of the group.
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
	
	//The following methods move all the entities of the group if it's possible.
	
	public void moveLeft() {
		Collections.sort(listOfEntities,new xPositionComparator());
		for (Entity e : listOfEntities)
		{
			this.map.moveLeft(e);
		}
	}
	
	public void moveRigth() {
		Collections.sort(listOfEntities,new yPositionComparator());
		Collections.reverse(listOfEntities);
		for (Entity e : listOfEntities)
		{
			this.map.moveRigth(e);
		}
	}
	
	public void moveUp() {
		Collections.sort(listOfEntities,new xPositionComparator());
		for (Entity e : listOfEntities)
		{
			this.map.moveUp(e);
		}
	}
	
	public void moveDown() {
		Collections.sort(listOfEntities,new xPositionComparator());
		Collections.reverse(listOfEntities);
		for (Entity e : listOfEntities)
		{
			this.map.moveDown(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	
}