package com.babachene.logic.rules;

import java.util.LinkedList;

import com.babachene.logic.data.LevelGroupOfEntities;
import com.babachene.logic.data.LevelMap;
import com.babachene.logic.data.entities.Entity;

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
	
	public void applyRulesH() {
		for (Text text:subject.getSubjectList())
		{
			applyNounRule(text,attribute.getNounList());
			for (Text property:attribute.getPropertyList())
				applyPropertyRuleH(text,property);
		}
		
	}
	
	public void applyRulesV() {
		for (Text text:subject.getSubjectList())
		{
			applyNounRule(text,attribute.getNounList());
			for (Text property:attribute.getPropertyList())
				applyPropertyRuleV(text,property);
		}
		
	}
	

	private void applyPropertyRuleH(Text text, Text property) {
		rulesUpdater.getMap();
		if(text.getText().equalsIgnoreCase("hug"))
			applyHugRules(property);
		LevelGroupOfEntities entities = LevelMap.findGroup(text.getText());
		if (entities != null) {
		if (this.verb.getText().equalsIgnoreCase("is")){
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
			entities.setIsYou(true);
			break;
		case("p1"):
			entities.setIsPlayer1(true);
			break;
		case("p2"):
			entities.setIsPlayer2(true);
			break;
		case("kill"):
			entities.setIsKill(true);
			break;
		case("move"):
			entities.setMoveH(true);
			break;
		}
		}
		}
	}
	private void applyPropertyRuleV(Text text, Text property) {
		rulesUpdater.getMap();
		if(text.getText().equalsIgnoreCase("hug"))
			applyHugRules(property);
		LevelGroupOfEntities entities = LevelMap.findGroup(text.getText());
		if (entities != null) {
		if (this.verb.getText().equalsIgnoreCase("is")){
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
			entities.setIsYou(true);
			break;
		case("p1"):
			entities.setIsPlayer1(true);
			break;
		case("p2"):
			entities.setIsPlayer2(true);
			break;
		case("kill"):
			entities.setIsKill(true);
			break;
		case("move"):
			entities.setMoveV(true);
			break;
		}
		}
		}
	}
	
	private void applyHugRules(Text property) {
		if (this.verb.getText().equalsIgnoreCase("is")) {
			if(property.getText().equalsIgnoreCase("win"))
				rulesUpdater.getMap().getHuggedEntities().setWin(true);
		}
	}
	

	private void applyNounRule(Text text, LinkedList<Text> nounList) {
		rulesUpdater.getMap();
		if (!nounList.isEmpty()) {
		LevelGroupOfEntities entities = LevelMap.findGroup(text.getText());
		if (entities != null) {
			LinkedList<Entity> list = entities.getListOfEntities();
			int n = list.size();
			for (int i = n-1; i> -1; i--) {
				Entity entity = list.get(i);
				int x = entity.getxPosition();
				int y = entity.getyPosition();
				for (Text noun : nounList)
					this.rulesUpdater.getMap().addEntity(x, y, noun.getText());
				this.rulesUpdater.getMap().getMapUpdateQueue().pushRemovedEntity(entity); 
				this.rulesUpdater.getMap().removeEntity(entity);
			}
		}
	}}
	
}
