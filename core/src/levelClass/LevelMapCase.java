package levelClass;

public class LevelMapCase {

	private int xPosition;
	private int yPosition;
	private boolean isFree;
	private Entity entity;

	public LevelMapCase(int x, int y, LevelMap map) {
		this.xPosition = x;
		this.yPosition = y;
		this.isFree = true;
		this.entity = new EntityEmpty(xPosition, yPosition, map);
	}
	public boolean isFree() {
		return isFree;
	}

	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}


}