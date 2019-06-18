package levelClass;

public class EntityLava extends Entity {

	public EntityLava(int x, int y, LevelMap map) {
		super(x, y, map,"Lava");
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}

}
