package levelClass;

import java.util.LinkedList;

public class LevelMap {

	/* The map in the game controls everything in the level. As it has an overall view of the available tiles of the map, it gives
	 * the possibility to add/remove an entity in the level, but also to move the entities. 
	 */
	private int xLength;
	private int yLength;	
	private int numberOfGroupEntities;
	private static LinkedList<LevelGroupOfEntities> mapEntities; //All the existing group of entities in the map, initially there is only the group of empty entities.
	private LinkedList<String> existingGroups; //Allows to fast-check if a group of entities already exists, to ensure we never have 2 groups of rocks for example.
	private LevelMapCase[][] mapMatrix;

	public LevelMap(int xLength, int yLength) { //Creates a map of dimension xLength*yLength with cases filled with "empty".
		super();
		this.xLength = xLength;
		this.yLength = yLength;
		this.mapEntities = new LinkedList<LevelGroupOfEntities>();
		this.existingGroups = new LinkedList<String>(); 
		this.numberOfGroupEntities = 0;
		mapMatrix = new LevelMapCase[xLength][yLength];
		for (int i = 0;i< xLength; i++) {
			for (int j = 0;j< yLength; j++) {
				mapMatrix[i][j] = new LevelMapCase(i, j, this);			//Initially we fill the mapCases with emptyEntities
				this.addEntity(i, j, new EntityEmpty(i, j, this));
			}
	}

	}

	public LinkedList<LevelGroupOfEntities> getMapEntities() {
		return mapEntities;
	}
	public void setMapEntities(LinkedList<LevelGroupOfEntities> mapEntities) {
	}
	public int getxLength() {
		return xLength;
	}
	public int getyLength() {
		return yLength;
	}

	public boolean isFree (int x, int y) {	//isFree means that an entity can move on this case (there can already be another entity).
		return mapMatrix[x][y].isFree();
	}
	
	public LevelMapCase[][] getMapMatrix() {
		return mapMatrix;
	}
	
	private void moveRightMultipleEntities(LinkedList<Entity> list) { //Moves to the right all the entities that are able to do so.
		int n = list.size();
		for(int i = 0; i<n;i++){
			Entity entity = list.get(i);
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			entity.setyPosition(y+1);
			mapMatrix[x][y].removeEntity(entity);
			mapMatrix[x][y+1].addEntity(entity);
		}
	}
	public void moveRight(Entity entity) {							//Same method than the one below with more convenient arguments
		LinkedList<Entity> list = new LinkedList<Entity>();
		list.add(entity);
		moveRight(entity.getxPosition(),entity.getyPosition(),list);
	}
	
	private boolean moveRight(int x, int y, LinkedList<Entity> list) {  //Recursively finds the pushable entities that needs to move.
		LinkedList<Entity> entitiesMoved = list;
		if (y < this.yLength -1)
		{
			if(mapMatrix[x][y+1].isFree()) {
				moveRightMultipleEntities(entitiesMoved);
				return true;
				}
			if(mapMatrix[x][y+1].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x][y+1].getPushableEntityList());
				moveRight(x,y+1,entitiesMoved);
			}
				
		}
		return false;
	}
	
	private void moveUpMultipleEntities(LinkedList<Entity> list) {
		int n = list.size();
		for(int i = 0; i<n;i++){
			Entity entity = list.get(i);
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			entity.setxPosition(x-1);
			mapMatrix[x][y].removeEntity(entity);
			mapMatrix[x-1][y].addEntity(entity);
		}
	}
	public void moveUp(Entity entity) {
		LinkedList<Entity> list = new LinkedList<Entity>();
		list.add(entity);
		moveUp(entity.getxPosition(),entity.getyPosition(),list);
	}
	
	private boolean moveUp(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (x > 0)
		{
			if(mapMatrix[x -1][y].isFree()) {
				moveUpMultipleEntities(entitiesMoved);
				return true;
				}
			if(mapMatrix[x-1][y].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x-1][y].getPushableEntityList());
				moveUp(x-1,y,entitiesMoved);
			}
				
		}
		return false;
	}
	
	private void moveDownMultipleEntities(LinkedList<Entity> list) {
		int n = list.size();
		for(int i = 0; i<n;i++){
			Entity entity = list.get(i);
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			entity.setxPosition(x+1);
			mapMatrix[x][y].removeEntity(entity);
			mapMatrix[x+1][y].addEntity(entity);
		}
	}
	public void moveDown(Entity entity) {
		LinkedList<Entity> list = new LinkedList<Entity>();
		list.add(entity);
		moveDown(entity.getxPosition(),entity.getyPosition(),list);
	}
	
	private boolean moveDown(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (x < xLength -1)
		{
			if(mapMatrix[x+1][y].isFree()) {
				moveDownMultipleEntities(entitiesMoved);
				return true;
				}
			if(mapMatrix[x+1][y].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x+1][y].getPushableEntityList());
				moveDown(x+1,y,entitiesMoved);
			}
				
		}
		return false;
	}
	
	private void moveLeftMultipleEntities(LinkedList<Entity> list) {
		int n = list.size();
		for(int i = 0; i<n;i++){
			Entity entity = list.get(i);
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			entity.setyPosition(y-1);
			mapMatrix[x][y].removeEntity(entity);
			mapMatrix[x][y-1].addEntity(entity);
		}
	}
	public void moveLeft(Entity entity) {
		LinkedList<Entity> list = new LinkedList<Entity>();
		list.add(entity);
		moveLeft(entity.getxPosition(),entity.getyPosition(),list);
	}
	
	private boolean moveLeft(int x, int y, LinkedList<Entity> list) {                 
		LinkedList<Entity> entitiesMoved = list;
		if (y > 0)
		{
			if(mapMatrix[x][y-1].isFree()) {
				moveLeftMultipleEntities(entitiesMoved);
				return true;
				}
			if(mapMatrix[x][y-1].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x][y-1].getPushableEntityList());
				moveLeft(x,y-1,entitiesMoved);
			}
				
		}
		return false;
	}
	
	public void addEntity(Entity entity)
	{
		addEntity(entity.getxPosition(),entity.getyPosition(),entity);
	}
	
	public void addEntity(int x, int y, Entity entity) {
		this.mapMatrix[x][y].addEntity(entity);										//First we add the entity to the corresponding map case
		String entityType = entity.getTypeOfEntity();
		if (!existingGroups.contains(entityType))									//Then we add the entity to the corresponding group of entities. If the group doesn't exist we create one.
		{																			
			LevelGroupOfEntities group = new LevelGroupOfEntities(entityType,this);
			group.addEntity(entity);
			this.numberOfGroupEntities ++;
			this.mapEntities.add(group);
			this.existingGroups.add(entityType);
		}
		else
		{
			findGroup(entityType).addEntity(entity);
		}
	}
	
	public void removeEntity(Entity entity) {
		String entityType = entity.getTypeOfEntity();
		int x = entity.getxPosition();
		int y = entity.getyPosition();
		this.mapMatrix[x][y].removeEntity(entity);  					  //First we remove the entity from the mapCase.
		int i = 0;	
		findGroup(entityType).removeEntity(entity);					  //We remove it from the group.
	}
	
	public void addEntity(int x, int y, String typeOfEntity)			  //Easy to use method to add an entity to the map.
	{
		switch(typeOfEntity)
		{
		case "baba":
			addEntity(x,y,new EntityBaba(x,y,this));
			break;
		case "empty":
			addEntity(x,y,new EntityEmpty(x,y,this));
			break;
		case "wall":
			addEntity(x,y,new EntityWall(x,y,this));
			break;
		case "water":
			addEntity(x,y,new EntityWater(x,y,this));
			break;
		case "lava":
			addEntity(x,y,new EntityLava(x,y,this));
			break;
		case "rock":
			addEntity(x,y,new EntityRock(x,y,this));
			break;
		case "skull":
			addEntity(x,y,new EntitySkull(x,y,this));
			break;
		default:
			addEntity(x,y,new EntityText(x,y,this,typeOfEntity));
		}
	}
	
	public LevelMapCase getMapCase(int x, int y) {
		return this.mapMatrix[x][y];
	}
	
	public void moveRight(String s) {								//If s = "rock", moves all the rocks of the map to the right.
		if (existingGroups.contains(s)) {
			int i = 0;
			while (mapEntities.get(i).getTypeOfEntities() != s) {   //Looking for the good group of entities.
				i++;
			}
			mapEntities.get(i).moveRight();
		}
			
	}
	public void moveLeft(String s) {
		if (existingGroups.contains(s)) {
			int i = 0;
			while (mapEntities.get(i).getTypeOfEntities() != s) {
				i++;
			}
			mapEntities.get(i).moveLeft();
		}
			
	}
	public void moveUp(String s) {
		if (existingGroups.contains(s)) {
			int i = 0;
			while (mapEntities.get(i).getTypeOfEntities() != s) {
				i++;
			}
			mapEntities.get(i).moveUp();
		}
			
	}
	public void moveDown(String s) {
		if (existingGroups.contains(s)) {
			int i = 0;
			while (mapEntities.get(i).getTypeOfEntities() != s) {
				i++;
			}
			mapEntities.get(i).moveDown();
		}
			
	}

	public static LevelGroupOfEntities findGroup(String s) {
		int i =0;
		while(mapEntities.get(i).getTypeOfEntities()!= s)
			i++;
		if (i!= mapEntities.size())
			return mapEntities.get(i);
		else 
			return null;
	}
	
	public void setIsWin(String typeOfEntities, boolean value)
	{
		findGroup(typeOfEntities).setIsWin(value);
	}
	
	public void setIsBlock(String typeOfEntities, boolean value)
	{
		findGroup(typeOfEntities).setIsBlock(value);
	}
	
	public void setIsSink(String typeOfEntities, boolean value)
	{
		findGroup(typeOfEntities).setIsSink(value);
	}
	
	public void setIsPush(String typeOfEntities, boolean value)
	{
		findGroup(typeOfEntities).setIsPush(value);
	}
}