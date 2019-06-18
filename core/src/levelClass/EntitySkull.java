package levelClass;

public class EntitySkull extends Entity {

	public EntitySkull(int x, int y, LevelMap map) {
		super(x, y, map,"Skull");
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}

}
