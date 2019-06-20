package com.babachene.logic.data;

import java.util.Collections;
import java.util.LinkedList;

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
	private boolean isYou;

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
	public void setIsBlock(boolean value) {             
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setBlock(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsFree(); //we update the properties of the map case accordingly.
		}
	}
	
	public void setIsYou (boolean value) {
		this.isYou=value;
	}
	
	public boolean IsYou () {
		return this.isYou;
	}
	public void setIsPush(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setPushable(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateContainsPushable();
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsFree();
		}
	}
	
	public void setIsSink(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setSink(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsSink();
		}
	}
	
	public void setIsWin(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setWin(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsWin();

		}
	}
	
	public void setAllFalse() {
		this.setIsBlock(false);
		this.setIsPush(false);
		this.setIsSink(false);
		this.setIsWin(false);
		this.setIsYou(false);
	}
	

	
	//The following methods move all the entities of the group if it's possible.
	
	public void moveLeft() {
		Collections.sort(listOfEntities,new yPositionComparator());
		Collections.reverse(listOfEntities);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveLeft(e);
		}
	}
	
	public void moveRight() {
		Collections.sort(listOfEntities,new yPositionComparator());
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveRight(e);
		}
	}
	
	public void moveUp() {
		Collections.sort(listOfEntities,new xPositionComparator());
		Collections.reverse(listOfEntities);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveUp(e);
		}
	}
	
	public void moveDown() {
		Collections.sort(listOfEntities,new xPositionComparator());
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveDown(e);
		}
		
	}
	
	
	
	public void updateWin() {
		for(Entity entity:listOfEntities)
			if (this.map.getMapCase(entity.getxPosition(), entity.getyPosition()).isWin())
				System.out.println("Congratulations ! You won");
	}
	
	
	
	
	
	
	

	
}