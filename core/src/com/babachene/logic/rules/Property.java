package com.babachene.logic.rules;

public class Property extends Term {

	public Property(int xPosition, int yPosition, String text, RulesUpdater rulesUpdater) {
		super(xPosition, yPosition, text, rulesUpdater);
		this.isProperty = true;
		// TODO Auto-generated constructor stub
	}

}
