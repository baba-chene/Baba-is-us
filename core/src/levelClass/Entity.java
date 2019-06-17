package levelClass;

public abstract class Entity {

	protected boolean isAlive;
	protected LevelMap map;
	protected int xPosition;
	protected int yPosition;
	protected boolean isPushable;
	protected boolean isSink;
	protected boolean isBlock;
	protected boolean isWin;


	public Entity(int x, int y, LevelMap map) {
		super();
		this.map = map;
		this.xPosition = x;
		this.yPosition = y;
	}



	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
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
	public boolean isBlock() {
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
}