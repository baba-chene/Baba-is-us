package levelClass;

public class EntityWall extends Entity {

	public EntityWall(int x, int y, LevelMap map) {
		super(x, y, map,"Wall");
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}

}
