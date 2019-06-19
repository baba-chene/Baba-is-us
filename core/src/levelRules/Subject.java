package levelRules;

import java.util.LinkedList;

public class Subject {

	private LinkedList<Term> subjectList;
	
	public Subject() {
		this.subjectList = new LinkedList<Term>();
	}
	
	public void addSubject (Term term) {
		this.subjectList.add(term);
	}
	
	public void removeSubject (Term term) {
		this.subjectList.remove(term);
	}
}
