package levelClass;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5,5);
		map.addEntity(2, 2, "rock");
		map.addEntity(2, 3, "rock");
		map.addEntity(3, 2, "rock");
		map.addEntity(1, 2, "rock");
		map.addEntity(2, 1, "rock");
		map.addEntity(1, 3, "skull");
		map.addEntity(1, 1, "skull");
		map.addEntity(0, 2, "skull");
		map.addEntity(2, 0, "skull");
		map.addEntity(4, 2, "skull");
		map.addEntity(3, 3, "skull");
		map.addEntity(3, 1, "skull");
		map.addEntity(3,1, "rock");
		map.setIsPush("rock", true);
		map.setIsSink("skull", true);
		map.moveRight("rock");
		map.moveUp("rock");
		map.moveLeft("rock");
		map.moveUp("rock");
		EntityRock rock = new EntityRock(2, 2, map);
		map.addEntity(rock);
		map.moveRight(rock);

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
