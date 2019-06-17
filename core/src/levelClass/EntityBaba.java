package levelClass;

public class EntityBaba extends Entity {

	public EntityBaba(int x, int y, LevelMap map) {
		super(x, y, map);
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}
	

}
