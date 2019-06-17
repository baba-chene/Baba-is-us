package levelClass;

public class LevelPlayer {
	private LevelMap map;
	private boolean isAlive;
	private ObjectCoordinates playerCoordinates;
	private LevelGroupOfObjects objectsPlayed; //Defines the objects that you actually play -> Can be Baba,
										   //a Wall, a Rock, but can also be multiple walls...
}