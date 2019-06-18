package levelClass;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LevelMap map = new LevelMap(5,5);
		EntityBaba baba = new EntityBaba(4,0,map);
		EntityRock rock1 = new EntityRock(3,0,map);
		EntityRock rock2 = new EntityRock(2,0,map);
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());		
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());
		map.moveUp(baba);
		System.out.println(rock1.getyPosition());

	}

}
