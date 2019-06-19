package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
	//	EntityBaba baba = new EntityBaba(2,2,map);
		map.addEntity(0, 0, "rock");
		map.addEntity(0, 1, "rock");
		map.addEntity(1, 0, "rock");
		EntitySkull skull = new EntitySkull(0, 2, map);
		EntitySkull skull2 = new EntitySkull(0, 3, map);
		EntitySkull skull3 = new EntitySkull(1, 1,map);
		skull.setSink(true);
		skull2.setSink(true);
		skull3.setSink(true);
		map.addEntity(skull3);
		map.addEntity(skull);
		map.addEntity(skull2);
		map.moveRigth("rock");
		map.moveRigth("rock");
		map.moveRigth("rock");
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
