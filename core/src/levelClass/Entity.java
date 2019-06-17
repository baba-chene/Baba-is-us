package levelClass;

public abstract class Entity {

	protected boolean isAlive;
	protected LevelMap map;
	protected Coordinates coordinates;
	protected boolean isPushable;
	protected boolean isSink;
	protected boolean isBlock;
	protected boolean isWin;


	
	public Entity(Coordinates coordinates, LevelMap map) {
		super();
		this.map = map;
		this.coordinates = coordinates;
	}

	public Entity(int x, int y, LevelMap map) {
		super();
		this.map = map;
		this.coordinates = new Coordinates(map, x, y);
	}


	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public boolean isPushable() {
		return isPushable;
	}
	public void setPushable(boolean isPushable) {
		this.isPushable = isPushable;
	}
	public boolean isSink() {
		return isSink;
	}
	public void setSink(boolean isSink) {
		this.isSink = isSink;
	}
	public boolean isStop() {
		return isBlock;
	}
	public void setStop(boolean isStop) {
		this.isBlock = isStop;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
}