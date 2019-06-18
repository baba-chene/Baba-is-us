package levelClass;

public class EntityText extends Entity {

	public EntityText(int x, int y, LevelMap map) {
		super(x, y, map,"Text");
		this.isPushable = true;
		this.map.addEntity(x, y, this);
		// TODO Auto-generated constructor stub
	}
	

	
}
