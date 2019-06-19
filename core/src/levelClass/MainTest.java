package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
	//	EntityBaba baba = new EntityBaba(2,2,map);
		map.addEntity(2, 2, "rock");
		map.addEntity(2, 3, "rock");
		map.addEntity(3, 2, "rock");
		map.moveRigth("rock");
		map.moveUp("rock");
		map.moveLeft("rock");
		map.moveDown("rock");
/*		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());		
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());*/

	}

}
