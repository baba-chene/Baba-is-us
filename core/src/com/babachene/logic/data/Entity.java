package com.babachene.logic.data;

import com.babachene.Baba;
import com.babachene.gui.renderer.RenderableEntity;

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
	protected String text;








	public Entity(int x, int y, LevelMap map,String typeOfEntity) {
		super();
		this.map = map;
		this.text ="";
		this.xPosition = x;
		this.yPosition = y;
		this.typeOfEntity =typeOfEntity;

		
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
		return null;
	}
	
	@Override
	public short getId() {
		// TODO Auto-generated method stub
		String switchString = new String();
		switchString = this.typeOfEntity + this.text;
		System.out.println(switchString);
		switch(switchString) {
		case("baba"):
			return Baba.BABA;
		case("rock"):
			return Baba.ROCK;
		case("skull"):
			return Baba.SKULL;
		case("flag"):
			return Baba.FLAG;
		case("lava"):
			return Baba.LAVA;
		case("wall"):
			return Baba.WALL;
		case("water"):
			return Baba.WATER;
		case("textbaba"):
			return Baba.TXT_BABA;
		case("textrock"):
			return Baba.TXT_ROCK;
		case("textskull"):
			return Baba.TXT_SKULL;
		case("textflag"):
			return Baba.TXT_FLAG;
		case("textlava"):
			return Baba.TXT_LAVA;
		case("textwall"):
			return Baba.TXT_WALL;
		case("textwater"):
			return Baba.TXT_WATER;
		case("textis"):
			return Baba.TXT_IS;
		case("texthas"):
			return Baba.TXT_HAS;
		case("textand"):
			return Baba.TXT_AND;
		case("textpush"):
			return Baba.TXT_PUSH;
		case("textsink"):
			return Baba.TXT_SINK;
		case("textblock"):
			return Baba.TXT_BLOCK;
		case("textyou"):
			return Baba.TXT_YOU;
		case("textp1"):
			return Baba.TXT_P1;
		case("textp2"):
			return Baba.TXT_P2;
		case("textwin"):
			return Baba.TXT_WIN;
		
		}
		return 0;
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
		return false;
		
	}@Override
	public boolean hasVaration() {
		// TODO Auto-generated method stub
		return false;
	}
	
}