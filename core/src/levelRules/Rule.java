package levelRules;

import levelClass.Entity;
import levelClass.LevelGroupOfEntities;
import levelClass.LevelMap;

public class Rule {

	private Subject subject;
	private Attribute attribute;
	private Verb verb;
	private RulesUpdater rulesUpdater;
	
	public Rule(Subject subject, Attribute attribute, Verb verb, RulesUpdater rulesUpdater) {
		super();
		this.subject = subject;
		this.attribute = attribute;
		this.verb = verb;
		this.rulesUpdater = rulesUpdater;
	}
	
	public void applyRules() {
		for (Text text:subject.getSubjectList())
		{
			for (Text noun:attribute.getNounList())
				applyNounRule(text,noun);
			for (Text property:attribute.getPropertyList())
				applyPropertyRule(text,property);
		}
		
	}

	private void applyPropertyRule(Text text, Text property) {
		rulesUpdater.getMap();
		LevelGroupOfEntities entities = LevelMap.findGroup(text.getText());
		if (entities != null) {
		switch(property.getText())
		{
		case("sink"):
			entities.setIsSink(true);
			break;
		case("win"):
			entities.setIsWin(true);
			break;
		case("block"):
			entities.setIsBlock(true);
			break;
		case("push"):
			entities.setIsPush(true);
			break;
		case("you"):
			break;
		}
		}
		
	}

	private void applyNounRule(Text text, Text noun) {
		LevelGroupOfEntities entities = rulesUpdater.getMap().findGroup(text.getText());
		if (entities != null) {
			for (Entity entity : entities.getListOfEntities()) {
				int x = entity.getxPosition();
				int y = entity.getyPosition();
				this.rulesUpdater.getMap().addEntity(x, y, noun.getText());
				this.rulesUpdater.getMap().removeEntity(entity);
			}
		}
	}
	
}
