package levelClass;

public class EntityRock extends Entity {

	public EntityRock(int x, int y, LevelMap map) {
		super(x, y, map);
		this.isPushable = true;
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}
	

}
