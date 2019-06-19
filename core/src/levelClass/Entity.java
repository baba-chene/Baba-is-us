package levelClass;

public abstract class Entity {

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




	public String getText() {
		return text;
	}



	public Entity(int x, int y, LevelMap map,String typeOfEntity) {
		super();
		this.map = map;
		this.text ="";
		this.xPosition = x;
		this.yPosition = y;
		this.typeOfEntity =typeOfEntity;

		
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
	
}