package levelRules;

import levelClass.LevelMap;

public class RuleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(6, 6);
		map.addEntityText(0, 2, "textrock");
		map.addEntityText(1, 2, "textand");
		map.addEntityText(2, 2, "textwall");
		map.addEntityText(3, 2, "textis");
		map.addEntityText(4, 2, "textrock");
		map.getRulesUpdater().updateRules();
	}

}
