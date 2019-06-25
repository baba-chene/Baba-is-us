package com.babachene.logic.data;

import java.util.Collections;
import java.util.LinkedList;

import com.babachene.logic.data.entities.Entity;

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
	private boolean isPlayer1;
	private boolean isPlayer2;
	private boolean isMoveH;
	private Direction hDirection;
	private boolean isMoveV;
	private Direction vDirection;

	public LevelGroupOfEntities(String typeOfEntities, LevelMap map) {
		super();
		this.hDirection = Direction.EAST;
		this.vDirection = Direction.SOUTH;
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
	
	
	
	public boolean isPlayer1() {
		return isPlayer1;
	}

	public void setIsPlayer1(boolean value) {
		this.isPlayer1 = value;
	}

	public boolean isPlayer2() {
		return isPlayer2;
	}

	public void setIsPlayer2(boolean isPlayer2) {
		this.isPlayer2 = isPlayer2;
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
	public void setIsKill(boolean value) {
		if (!this.typeOfEntities.equalsIgnoreCase("empty")) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setKill(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsKill();

		}
		}
	}
	public void setAllFalse() {
		this.setIsBlock(false);
		this.setIsPush(false);
		this.setIsSink(false);
		this.setIsWin(false);
		this.setIsYou(false);
		this.setIsPlayer1(false);
		this.setIsPlayer2(false);
		this.setIsKill(false);
		this.setMoveH(false);
		this.setMoveV(false);
	}
	

	
	//The following methods move all the entities of the group if it's possible.
	
	public void moveLeft() {
		Collections.sort(listOfEntities,new yPositionComparator());
		Collections.reverse(listOfEntities);
		updateMake();
		this.setDirection(Direction.WEST);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveLeft(e);
		}
	}
	
	public void moveRight() {
		Collections.sort(listOfEntities,new yPositionComparator());
		updateMake();
		this.setDirection(Direction.EAST);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveRight(e);
			
		}
	}
	
	public void moveUp() {
		Collections.sort(listOfEntities,new xPositionComparator());
		Collections.reverse(listOfEntities);
		updateMake();
		this.setDirection(Direction.NORTH);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveUp(e);
		}
	}
	
	public void moveDown() {
		Collections.sort(listOfEntities,new xPositionComparator());
		updateMake();
		this.setDirection(Direction.SOUTH);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveDown(e);
		}
		
	}
	
	
	
	public void updateWin() {
		for(Entity entity:listOfEntities)
			if (this.map.getMapCase(entity.getxPosition(), entity.getyPosition()).isWin()) {
				System.out.println("Congratulations ! You won");
				return;}		
	}
	
	
	public void setDirection(Direction direction) {
		for (Entity entity : this.listOfEntities) {
			entity.setDirection(direction);
		}
	}

	public boolean isMoveH() {
		return isMoveH;
	}

	public void setMoveH(boolean isMoveH) {
		this.isMoveH = isMoveH;
	}
	public boolean isMoveV() {
		return isMoveV;
	}

	public void setMoveV(boolean isMoveV) {
		this.isMoveV = isMoveV;
	}
	
	public void updateMove() {
		updateMoveH();
		updateMoveV();
	}
	
	
	private void updateMoveH() {
		if(isMoveH)
		{
			Collections.sort(listOfEntities, new yPositionComparator());
			int yMax = listOfEntities.get(numberOfEntities-1).getyPosition();
			int yMin = listOfEntities.get(0).getyPosition();
			if (yMax == map.getyLength()-1 && yMin == 0)
				return;
			if(yMax == map.getyLength() -1)
				this.hDirection = Direction.WEST;
			if(yMax < map.getyLength() -1 && !map.getMapCase(listOfEntities.get(numberOfEntities-1).getxPosition(), yMax +1).isFree())
				this.hDirection = Direction.WEST;
			if(yMin == 0)
				this.hDirection = Direction.EAST;
			if(yMin >0 && !map.getMapCase(listOfEntities.get(0).getxPosition(), yMin -1).isFree())
				this.hDirection = Direction.EAST;
			switch(hDirection) {
			case WEST:
				this.moveLeft();
				break;
			case EAST:
				this.moveRight();
				break;
			}
		}
	}
	
	private void updateMoveV() {
		if(isMoveV)
		{
			Collections.sort(listOfEntities, new xPositionComparator());
			int xMax = listOfEntities.get(numberOfEntities-1).getxPosition();
			int xMin = listOfEntities.get(0).getxPosition();
			if (xMax == map.getxLength()-1 && xMin == 0)
				return;
			if(xMax == map.getxLength() -1)
				this.vDirection = Direction.NORTH;
			if(xMin == 0)
				this.vDirection = Direction.SOUTH;
			switch(vDirection) {
			case NORTH:
				this.moveUp();
				break;
			case SOUTH:
				this.moveDown();
				break;
			}	
		}
	}
	public void updateMake(){
			if(!this.getTypeOfEntities().equalsIgnoreCase("empty")) {
			for (Entity entity : this.getListOfEntities()) {
				for(String s : entity.getMakeEntity()) {
					map.addEntity(entity.getxPosition(), entity.getyPosition(), s);
				}
			}
		}
	}

	
}