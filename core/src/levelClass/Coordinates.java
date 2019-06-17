package levelClass;

public class Coordinates {

	private LevelMap map;
	private int xPosition;
	private int yPosition;

	public Coordinates(LevelMap map, int xPosition, int yPosition) {
		super();
		this.map = map;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}


	public LevelMap getMap() {
		return map;
	}
	public void setMap(LevelMap map) {
		this.map = map;
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

	public void moveLeft()
	{
		if (xPosition>0 && map.isFree(xPosition-1, yPosition)) 
			xPosition -=1;
	}

	public void moveRigth()
	{
		if (xPosition< map.getxLength()-1 && map.isFree(xPosition+1, yPosition))
			xPosition +=1;
	}

	public void moveUp()
	{
		if (yPosition> 0 && map.isFree(xPosition, yPosition-1))
			yPosition -=1;
	}

	public void moveDown()
	{
		if (yPosition<map.getyLength() && map.isFree(xPosition, yPosition+1))
			yPosition +=1;
	}

}