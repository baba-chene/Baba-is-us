package levelRules;


public class Rule {

	private Subject subject;
	private Attribute attribute;
	private Verb verb;
	
	public Rule(Subject subject, Attribute attribute, Verb verb) {
		super();
		this.subject = subject;
		this.attribute = attribute;
		this.verb = verb;
	}
	
}
