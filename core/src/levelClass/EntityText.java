package levelClass;

public class EntityText extends Entity {
	
private String typeOfText ;
	
	public EntityText(int x, int y, LevelMap map) {
		super(x, y, map,"text");
		this.isPushable = true;
		this.typeOfText = "";
		// TODO Auto-generated constructor stub
	}
	
	public EntityText(int x, int y, LevelMap map, String typeOfText) {
		super(x, y, map,"text");
		this.isPushable = true;
		this.typeOfText = typeOfText.substring(4);;
		// TODO Auto-generated constructor stub
	}
	
	
}
