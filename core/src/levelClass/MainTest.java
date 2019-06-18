package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
	//	EntityBaba baba = new EntityBaba(2,2,map);
		EntityRock rock0 = new EntityRock(2,2,map);
		EntityRock rock1 = new EntityRock(3,2,map);
		EntityRock rock2 = new EntityRock(2,3,map);
		EntityRock rock3 = new EntityRock(1,2,map);
		EntityRock rock4 = new EntityRock(2,1,map);
		map.moveRigth("Rock");
		map.moveUp("Rock");
		map.moveLeft("Rock");
		map.moveDown("Rock");
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
