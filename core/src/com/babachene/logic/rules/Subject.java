package com.babachene.logic.rules;

import java.util.LinkedList;

public class Subject {

	private LinkedList<Text> subjectList;
	

	public Subject() {
		this.subjectList = new LinkedList<Text>();
	}
	
	public void addSubject (Text text) {
		this.subjectList.add(text);
	}
	
	public void removeSubject (Text text) {
		this.subjectList.remove(text);
	}
	
	public boolean isEmpty() {
		return subjectList.isEmpty();
	}
	public LinkedList<Text> getSubjectList() {
		return subjectList;
	}

}
