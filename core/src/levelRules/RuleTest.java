package levelRules;

import levelClass.LevelMap;

public class RuleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(6, 6);
		map.addEntityText(2, 0, "textflag");
		map.addEntityText(2, 1, "textis");	
		map.addEntityText(2, 2, "textwin");
		map.addEntity(4, 4, "rock");
		map.addEntity(0,0,"flag");
		map.getRulesUpdater().updateRules();

	}

}
