package levelClass;

public class LevelMapCase {

	private int xPosition;
	private int yPosition;
	private boolean isFree;
	private LevelObject object;

	public LevelMapCase(int x, int y, LevelMap map) {
		this.xPosition = x;
		this.yPosition = y;
		this.isFree = true;
		this.object = new ObjectEmpty(xPosition, yPosition, false, map);
	}
	public boolean isFree() {
		return isFree;
	}
	public void setObject(boolean isFree) {
	}
	public LevelObject getObject() {
		return object;
	}
	public void setObject(LevelObject object) {
		this.object = object;
	}


}