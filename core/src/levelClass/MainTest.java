package levelClass;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LevelMap map = new LevelMap(10,10);
		EntityBaba baba = new EntityBaba(0, 0, map);
		EntityRock rock = new EntityRock(0,1,map);
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());		
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());
		map.moveRigth(baba);
		System.out.println(rock.getyPosition());

	}

}
