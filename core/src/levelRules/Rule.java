package levelRules;


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

		
	}

	private void applyNounRule(Text text, Text noun) {
		
	}
	
}
