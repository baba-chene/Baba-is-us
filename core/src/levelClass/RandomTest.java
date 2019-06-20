package levelClass;

import java.util.LinkedList;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
		EntityBaba baba = new EntityBaba(2,1,map);
		EntityRock rock = new EntityRock(2,2,map);
		map.addEntity(rock);
		map.addEntity(baba);
		map.setIsPush("rock",true);
		map.moveRight(baba);
	}

}
