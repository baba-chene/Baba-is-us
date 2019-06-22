package com.babachene.logic.rules;

import java.util.LinkedList;

import com.babachene.logic.data.EntityText;
import com.babachene.logic.data.LevelGroupOfEntities;
import com.babachene.logic.data.LevelMap;

public class RulesUpdater {

	private LinkedList<Rule> ruleList;
	private LinkedList<Subject> subjectList;
	private LinkedList<Attribute> attributeList;
	private LinkedList<Verb> verbList;
	private LevelMap map;
	private int xLength;
	private int yLength;
	private Text emptyText;
	private Text[][] textTab;


	public RulesUpdater(LevelMap map) {
		this.ruleList = new LinkedList<Rule>();
		this.map = map;
		this.xLength = map.getxLength();
		this.yLength = map.getyLength();
		this.verbList = new LinkedList<Verb>();
		this.attributeList = new LinkedList<Attribute>();
		this.subjectList = new LinkedList<Subject>();
		this.emptyText = new Text(0,0,"",this);
		this.textTab = new Text[xLength][yLength];
		for (int i = 0; i< xLength;i++) {
			for (int j =0; j< yLength; j++)
				textTab[i][j] = new Text(i,j,"",this);
		}
		this.updateTab();
		
	}
	
	public boolean updateTab()
	{
		this.verbList = new LinkedList<Verb>();
		LevelGroupOfEntities textEntities = LevelMap.findGroup("text");
		for (int i =0; i <this.xLength;i++) {
			for(int j =0; j<this.yLength;j++)
				this.textTab[i][j] = this.emptyText;
		}
		if (textEntities == null)
			return false;
		for (int i =0; i<textEntities.getNumberOfEntities();i++) {
			EntityText entity = (EntityText) textEntities.getListOfEntities().get(i);
			String text = entity.getText();
			int x = entity.getxPosition();
			int y = entity.getyPosition();
			switch(text) { //Ok I know but I don't see another way to do it ..
			case "is":
			case "has":
				Verb verb = new Verb(x,y,text,this);
				this.verbList.add(verb);
				this.textTab[x][y] = verb;
				break;
			case "rock":
			case "wall":
			case "baba":
			case "skull":
			case "lava":
			case "water":
			case "flag":
			case "grass":
				Noun noun = new Noun(x,y,text,this);
				this.textTab[x][y] = noun;
				break;
			case "and":
			case "not":
				LogicWord logic = new LogicWord(x, y, text,this);
				this.textTab[x][y] = logic;
				break;
			case "sink":
			case "push":
			case "block":
			case "you":
			case "win":
			case "p1":
			case "p2":
				Property property = new Property(x,y,text,this);
				this.textTab[x][y] = property;
				break;
			}
		}
		return true;
	}
	
	public int getxLength() {
		return xLength;
	}

	public int getyLength() {
		return yLength;
	}
	
	public void findHRule(Verb verb) {
		Subject subject = new Subject();
		Text previous = verb.findHPrevious();
		// We find all the subjects that concern the verb (ex : Baba And Rock is ... => subjectList = ["Baba","Rock"])
		while(previous.isNoun)
		{
			subject.addSubject(previous);
			if (!previous.findHPrevious().getText().equalsIgnoreCase("and"))
				break;
			previous = previous.findHPrevious().findHPrevious(); //On saute la case qui contient le and
		}
		// We do the same for the attributes (ex ... is Sink and Rock => nounList = ["Rock"], propertyList = ["sink"])
		Attribute attribute = new Attribute();
		Text next = verb.findHNext();
			while(next.isProperty||next.isNoun) {
				if (next.isProperty)
					attribute.addProperty(next);
				if (next.isNoun)
					attribute.addNoun(next);
				if(!next.findHNext().getText().equalsIgnoreCase("and"))
					break;
				next = next.findHNext().findHNext();
			}
		if (!subject.isEmpty() && !attribute.isEmpty())
		{
			ruleList.add(new Rule(subject, attribute, verb, this));
		}
	}
	public void findVRule(Verb verb) {
		Subject subject = new Subject();
		Text previous = verb.findVPrevious();
		// We find all the subjects that concern the verb (ex : Baba And Rock is ... => subjectList = ["Baba","Rock"])
		while(previous.isNoun)
		{
			subject.addSubject(previous);
			if (!previous.findVPrevious().getText().equalsIgnoreCase("and"))
				break;
			previous = previous.findVPrevious().findVPrevious(); //On saute la case qui contient le and
		}
		// We do the same for the attributes (ex ... is Sink and Rock => nounList = ["Rock"], propertyList = ["sink"])
		Attribute attribute = new Attribute();
		Text next = verb.findVNext();
			while(next.isProperty||next.isNoun) {
				if (next.isProperty)
					attribute.addProperty(next);
				if (next.isNoun)
					attribute.addNoun(next);
				if(!next.findVNext().getText().equalsIgnoreCase("and"))
					break;
				next = next.findVNext().findVNext();
			}
		if (!subject.isEmpty() && !attribute.isEmpty())
		{
			ruleList.add(new Rule(subject, attribute, verb, this));
		}
	}
	private void updateHRules(){
		for (Verb verb : verbList) {
			findHRule(verb);
		}
	}
	private void updateVRules() {
		for (Verb verb : verbList) {
			findVRule(verb);
	}
}
	public void updateRules() {
		this.updateTab();
		this.ruleList = new LinkedList<Rule>();
		updateHRules();
		updateVRules();
		resetProperties();
		for(Rule rule:ruleList)
			rule.applyRules();
		//add here the method that executes the rules of ruleList
	}
	
	public void updateIsWin() {
		LinkedList<LevelGroupOfEntities> youEntities = this.map.findYou();
		for (LevelGroupOfEntities entities:youEntities)
			entities.updateWin();
	}
	
	public void resetProperties() {
		for (LevelGroupOfEntities entities:map.getMapEntities())
		{
			if (!entities.getTypeOfEntities().equalsIgnoreCase("text")) //On ne touche pas aux propriétés des textes
			{
				entities.setAllFalse();
			}
		}
	}
	public Text getText(int x, int y) {
		return this.textTab[x][y];
	}

	public LevelMap getMap() {
		return map;
	}
	
	
	
}
