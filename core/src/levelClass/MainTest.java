package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
	//	EntityBaba baba = new EntityBaba(2,2,map);
		map.addEntity(2, 2, "rock");
		String test = "textrock";
		System.out.println(test.substring(4));
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
