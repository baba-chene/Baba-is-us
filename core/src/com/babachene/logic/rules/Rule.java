package com.babachene.logic.rules;

import java.util.LinkedList;

import com.babachene.logic.data.HuggedEntities;
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
		if (this.verb.getText().equalsIgnoreCase("make"))
		{
			switch(property.getText()) {
			case "empty":
			case "rock":
			case "wall":
			case "baba":
			case "keke":
			case "skull":
			case "lava":
			case "water":
			case "flag":
			case "grass":
			case "hug":
			case "lego":
			case "love":
			case "palm":
			case "bush":
			case "box" :
			case "tree":
				makeHug(property.getText());
			}
		}
	}
	
	public void makeHug(String s){
		HuggedEntities hug = rulesUpdater.getMap().getHuggedEntities();
		for (int i =0; i< hug.getSize(); i++) {
			rulesUpdater.getMap().addEntity(hug.getxHugged().get(i), hug.getyHugged().get(i), s);
		}
	}
	

	private void applyNounRule(Text text, LinkedList<Text> nounList) {
		rulesUpdater.getMap();
		if (!nounList.isEmpty()) {
			if(text.getText().equalsIgnoreCase("hug"));{
				for (Text s :nounList)
					makeHug(s.getText());
			}
		LevelGroupOfEntities entities = LevelMap.findGroup(text.getText());
		if (entities != null) {
			LinkedList<Entity> list = entities.getListOfEntities();
			int n = list.size();
			if(this.verb.getText().equalsIgnoreCase("is")) {
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
			if(this.verb.getText().equalsIgnoreCase("make")) {
			for (Entity entity : list) {
				for (Text noun : nounList) {
					System.out.println(noun.getText());
					if (!entity.getMakeEntity().contains(noun.getText()))
					entity.addMakeEntity(noun.getText());}
				}
					
			}
		}
		
	}}
	
}
