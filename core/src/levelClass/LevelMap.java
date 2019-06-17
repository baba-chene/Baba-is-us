package levelClass;

import java.util.ArrayList;

public class LevelMap {

	private int xLength;
	private int yLength;	
	private ArrayList<LevelGroupOfObjects> mapObjects;
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
		this.mapObjects = new ArrayList<LevelGroupOfObjects>();
	}

	public ArrayList<LevelGroupOfObjects> getMapObjects() {
		return mapObjects;
	}
	public void setMapObjects(ArrayList<LevelGroupOfObjects> mapObjects) {
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

}