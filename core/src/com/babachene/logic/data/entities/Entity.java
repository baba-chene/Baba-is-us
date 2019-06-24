package com.babachene.logic.data.entities;

import com.babachene.gui.renderer.RenderableEntity;
import com.babachene.logic.data.Direction;
import com.babachene.logic.data.LevelMap;

public abstract class Entity implements RenderableEntity {

	/* An entity in the game represents an object that has some properties. The entity can be several things,
	 * like a rock, Baba, a wall, empty or even some text. The properties are the ability to be pushed, to make
	 * a map case blocking the players moves. 
	 */
	protected String typeOfEntity; //Rock, Baba, Wall..
	protected LevelMap map;
	protected int xPosition;
	protected int yPosition;
	protected boolean isPushable;
	protected boolean isSink;
	protected boolean isBlock;
	protected boolean isWin;
	protected boolean isKill;
	protected String text;
	protected Direction direction;







	public Entity(int x, int y, LevelMap map,String typeOfEntity) {
		super();
		this.map = map;
		this.text ="";
		this.xPosition = x;
		this.yPosition = y;
		this.typeOfEntity =typeOfEntity;
		this.direction = Direction.EAST;
		
	}

	public String getText() {
		return text;
	}
	


	public boolean isPushable() {
		return isPushable;
	}
	public void setPushable(boolean isPushable) {
		this.isPushable = isPushable;
		this.map.getMapCase(xPosition, yPosition).updateContainsPushable();
	}
	public boolean isSink() {
		return isSink;
	}
	public void setSink(boolean isSink) {
		this.isSink = isSink;
		this.map.getMapCase(xPosition, yPosition).updateIsSink();
	}
	public boolean isBlock() {
		return isBlock;
	}
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
		this.map.getMapCase(xPosition, yPosition).updateIsFree();

	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}



	public int getxPosition() {
		return xPosition;
	}



	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}



	public int getyPosition() {
		return yPosition;
	}



	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public String getTypeOfEntity() {
		return typeOfEntity;
	}
	
	@Override
	public Object getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}
	
	@Override
	public String getId() {
		return this.typeOfEntity + this.text;
	}
	
	@Override
	public Object getVaration() {
		// TODO Auto-generated method stub
		return null;
		
	}@Override
	public int getX() {
		// TODO Auto-generated method stub
		return getyPosition();
		
	}@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (map.getxLength()- 1 - this.getxPosition());
		
	}@Override
	public boolean hasDirection() {
		// TODO Auto-generated method stub
		return true;
		
	}@Override
	public boolean hasVaration() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isKill() {
		return isKill;
	}

	public void setKill(boolean isKill) {
		this.isKill = isKill;
	}

	
	
	
	
	
}