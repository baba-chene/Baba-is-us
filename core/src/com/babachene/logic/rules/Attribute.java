package com.babachene.logic.rules;

import java.util.LinkedList;

public class Attribute {

	private LinkedList<Text> nounList;
	private LinkedList<Text> propertyList;
	
	public Attribute() {
		this.nounList = new LinkedList<Text>();
		this.propertyList = new LinkedList<Text>();
	}
	
	public void addNoun(Text text) {
		this.nounList.add(text);
	}
	
	public void removeNoun (Text text) {
		this.nounList.remove(text);
	}
	
	public void addProperty (Text text) {
		this.propertyList.add(text);
	}
	
	public void removeProperty(Text text) {
		this.propertyList.remove(text);
	}
	
	public boolean isEmpty() {
		return (nounList.isEmpty()&&propertyList.isEmpty());
	}

	public LinkedList<Text> getNounList() {
		return nounList;
	}

	public LinkedList<Text> getPropertyList() {
		return propertyList;
	}
	
}
