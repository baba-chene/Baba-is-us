package levelRules;

import levelClass.LevelMap;

public class RuleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(6, 6);
		map.addEntityText(2, 2, "textrock");
		map.addEntityText(2, 3, "textis");	
		map.addEntityText(2, 4, "textpush");
		map.addEntity(4, 4, "rock");
		map.addEntity(3, 4, "baba");
		map.getRulesUpdater().updateRules();
		map.moveDown("baba");
		map.moveDown("baba");
		map.getRulesUpdater().updateRules();
	}

}
