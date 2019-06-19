package levelClass;

import java.util.LinkedList;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(5, 5);
		EntityText entity = new EntityText(0, 0, map, "texttest");
		map.addEntity(entity);
		LevelGroupOfEntities textEntities = LevelMap.findGroup("text");
		EntityText entityTest =  (EntityText) textEntities.getListOfEntities().get(0);
		System.out.println(entityTest.getText());
	}

}
