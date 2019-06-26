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
	private boolean isUndoing;
	private boolean isPlayer2;


	public LevelGroupOfEntities(String typeOfEntities, LevelMap map) {
		super();
		this.isUndoing = false;
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
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsFree(); 
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsBlock(); //we update the properties of the map case accordingly.//we update the properties of the map case accordingly.
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
	
	public void setHas(boolean value) {
		if (!this.typeOfEntities.equalsIgnoreCase("empty")) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setHasEntity(value);
			if (value == false)
			e.clearHasType();
		}
			
		}
	}
	
	public void addHasEntityType(String s) {
		if (!this.typeOfEntities.equalsIgnoreCase("empty")) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.getHasEntityType().add(s);
		}
		}
	}
	
	public void setIsOpen(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setOpen(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsOpen();

		}	}
	
	public void setIsShut(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setShut(value);
			this.map.getMapCase(e.getxPosition(),e.getyPosition()).updateIsShut();

		}	}
	
	public void setMoveH(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setMoveH(value);}
	}
	
	public void setMoveV(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setMoveV(value);}
	}
	
	public void setIsSlide(boolean value) {
		for (int i = listOfEntities.size()-1; i>-1;i--) {
			Entity e = listOfEntities.get(i);
			e.setSlide(value);}
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
		this.setHas(false);
		this.setMakeFalse();
		this.setIsOpen(false);
		this.setIsShut(false);
		this.setIsSlide(false);
	}
	private void setMakeFalse() {
		for (Entity entity : listOfEntities)
			entity.setMakeEntity(new LinkedList<String>());
	}
	

	
	//The following methods move all the entities of the group if it's possible.
	
	public void moveLeft() {
		Collections.sort(listOfEntities,new yPositionComparator());
		Collections.reverse(listOfEntities);
		updateMake();
		if(!isUndoing)
		this.setDirection(Direction.WEST);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveLeft(e);
		}
		updateCanMove();
	}
	
	public void moveRight() {
		Collections.sort(listOfEntities,new yPositionComparator());
		updateMake();
		if(!isUndoing)
		this.setDirection(Direction.EAST);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveRight(e);
			
		}
		updateCanMove();
	}
	
	public void moveUp() {
		Collections.sort(listOfEntities,new xPositionComparator());
		Collections.reverse(listOfEntities);
		updateMake();
		if(!isUndoing)
		this.setDirection(Direction.NORTH);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveUp(e);
		}
		updateCanMove();
	}
	
	public void moveDown() {
		Collections.sort(listOfEntities,new xPositionComparator());
		updateMake();
		if(!isUndoing)
		this.setDirection(Direction.SOUTH);
		for (int i =numberOfEntities -1; i>-1 ;i--) //We go backward just in case blocks are destroyed.
		{
			Entity e = listOfEntities.get(i);
			this.map.moveDown(e);
		}
		updateCanMove();

	}
	
	public void updateCanMove() {
		for (int i = 0; i< map.getxLength(); i++)
			for(int j = 0; j< map.getyLength();j++)
				map.getMapCase(i, j).updateCanMove();
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


	
	public void updateMove() {
		updateMoveH();
		updateMoveV();
	}
	
	
	private void updateMoveH() {
		for (int i = numberOfEntities-1; i>-1; i--) {
			Entity entity = listOfEntities.get(i);
		if(entity.isMoveH())
		{
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			if(y == map.getyLength() -1)
				entity.sethDirection(Direction.WEST);
			if(!map.getMapCase(x, y).isCanMoveRight())
				entity.sethDirection(Direction.WEST);
			if(y == 0)
				entity.sethDirection(Direction.EAST);
			if(!map.getMapCase(x,y).isCanMoveLeft())
				entity.sethDirection (Direction.EAST);
			switch(entity.gethDirection()) {
			case WEST:
				map.moveLeft(entity);
				break;
			case EAST:
				map.moveRight(entity);
				break;
			}
		}
		}
	}
	
	private void updateMoveV() {
		for (int i = numberOfEntities-1; i>-1; i--) {
			Entity entity = listOfEntities.get(i);
		if(entity.isMoveV())
		{
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			if(x == map.getxLength() -1)
				entity.setvDirection(Direction.NORTH);
			if(!map.getMapCase(x, y).isCanMoveDown())
				entity.setvDirection(Direction.NORTH);
			if(x == 0)
				entity.setvDirection(Direction.SOUTH);
			if(!map.getMapCase(x,y).isCanMoveUp())
				entity.setvDirection (Direction.SOUTH);
			switch(entity.getvDirection()) {
			case SOUTH:
				map.moveDown(entity);
				break;
			case NORTH:
				map.moveUp(entity);
				break;
			}
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

	public void setUndoing(boolean isUndoing) {
		this.isUndoing = isUndoing;
	}

	public void updateSlide() {
		for(int i = numberOfEntities-1; i>-1; i--) {
			Entity entity = this.listOfEntities.get(i);
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			map.getMapCase(x, y).updateIsKill();
			map.getMapCase(x, y).updateIsSink();
			if (!entity.isSlide()) {
			if (map.getMapCase(x, y).isSlide() && !(entity.getTypeOfEntity().equalsIgnoreCase("empty"))) {
				switch(entity.getDirection())
				{
				case NORTH:
					map.moveUp(entity);
					if (map.getMapCase(x, y).isCanMoveUp())
					updateSlide();
					break;
				case SOUTH:
					map.moveDown(entity);
					if (map.getMapCase(x, y).isCanMoveDown())

					updateSlide();

					break;
				case EAST:
					map.moveRight(entity);
					if (map.getMapCase(x, y).isCanMoveRight())

					updateSlide();

					break;
				case WEST:
					map.moveLeft(entity);
					if (map.getMapCase(x, y).isCanMoveLeft())
					updateSlide();

					break;
				}
			}
			}
		}
		
	}
	
}