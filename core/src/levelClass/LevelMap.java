package levelClass;

import java.util.LinkedList;
import java.util.LinkedList;

public class LevelMap {

	private int xLength;
	private int yLength;	
	private LinkedList<LevelGroupOfEntities> mapEntities;
	private LevelMapCase[][] mapMatrix;

	public LevelMap(int xLength, int yLength) { //Creates a map of dimension xLength*yLength with cases filled with "empty".
		super();
		this.xLength = xLength;
		this.yLength = yLength;
		mapMatrix = new LevelMapCase[xLength][yLength];
		for (int i = 0;i< xLength; i++) {
			for (int j = 0;j< yLength; j++) {
				mapMatrix[i][j] = new LevelMapCase(i, j, this);
			}
	}
		this.mapEntities = new LinkedList<LevelGroupOfEntities>();
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

	public boolean isFree (int x, int y) {
		return mapMatrix[x][y].isFree();
	}
	
	public LevelMapCase[][] getMapMatrix() {
		return mapMatrix;
	}
	
	public void moveRigthMultipleEntities(LinkedList<Entity> list) {
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
	public void moveRigth(Entity entity) {
		LinkedList<Entity> list = new LinkedList<Entity>();
		list.add(entity);
		moveRigth(entity.getxPosition(),entity.getyPosition(),list);
	}
	
	public void moveRigth(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (y < this.yLength -1)
		{
			if(mapMatrix[x][y+1].isFree()) {
				moveRigthMultipleEntities(entitiesMoved);
				}
			if(mapMatrix[x][y+1].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x][y+1].getPushableEntityList());
				moveRigth(x,y+1,entitiesMoved);
			}
				
		}
		
	}
	
	public void moveUpMultipleEntities(LinkedList<Entity> list) {
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
	
	public void moveUp(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (x > 0)
		{
			if(mapMatrix[x -1][y].isFree()) {
				moveUpMultipleEntities(entitiesMoved);
				}
			if(mapMatrix[x-1][y].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x-1][y].getPushableEntityList());
				moveUp(x-1,y,entitiesMoved);
			}
				
		}
		
	}
	
	public void moveDownMultipleEntities(LinkedList<Entity> list) {
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
	
	public void moveDown(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (x < xLength -1)
		{
			if(mapMatrix[x+1][y].isFree()) {
				moveDownMultipleEntities(entitiesMoved);
				}
			if(mapMatrix[x+1][y].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x+1][y].getPushableEntityList());
				moveDown(x+1,y,entitiesMoved);
			}
				
		}
		
	}
	
	public void moveLeftMultipleEntities(LinkedList<Entity> list) {
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
	
	public void moveLeft(int x, int y, LinkedList<Entity> list) {
		LinkedList<Entity> entitiesMoved = list;
		if (y > 0)
		{
			if(mapMatrix[x][y-1].isFree()) {
				moveLeftMultipleEntities(entitiesMoved);
				}
			if(mapMatrix[x][y-1].containsPushableEntity()) {
				entitiesMoved.addAll(mapMatrix[x][y-1].getPushableEntityList());
				moveLeft(x,y-1,entitiesMoved);
			}
				
		}
		
	}


}