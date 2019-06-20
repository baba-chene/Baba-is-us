package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
		map.addEntity(0, 0, "textbaba");
		map.addEntity(0, 1, "textis");
		map.addEntity(0, 2, "textyou");
		map.addEntity(2,2,"baba");
		map.getRulesUpdater().updateRules();
		map.moveLeft();
		map.moveDown();
		map.moveRight();
		map.moveUp();
	}

}
