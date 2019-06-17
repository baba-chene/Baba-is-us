package levelClass;

public abstract class LevelObject {

	private boolean isAlive;
	private LevelMap map;
	private ObjectCoordinates objectCoordinates;
	private boolean isPushable;
	private boolean isSink;
	private boolean isStop;
	private boolean isWin;


	public LevelObject(ObjectCoordinates objectCoordinates, boolean isWin, LevelMap map) {
		super();
		this.map = map;
		this.objectCoordinates = objectCoordinates;
		this.isWin = isWin;
	}

	public LevelObject(int x, int y, boolean isWin, LevelMap map) {
		super();
		this.map = map;
		this.objectCoordinates = new ObjectCoordinates(map, x, y);
		this.isWin = isWin;
	}


	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public ObjectCoordinates getObjectCoordinates() {
		return objectCoordinates;
	}
	public void setObjectCoordinates(ObjectCoordinates objectCoordinates) {
		this.objectCoordinates = objectCoordinates;
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
		return isStop;
	}
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
}