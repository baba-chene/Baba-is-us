package levelClass;

public class EntityWater extends Entity {

	public EntityWater(int x, int y, LevelMap map) {
		super(x, y, map,"Water");
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}

}
