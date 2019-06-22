package com.babachene.logic.rules;

public class Text {

	private RulesUpdater rulesUpdater;
	private int xPosition;
	private int yPosition;
	private final String text;
	protected boolean isNoun;
	protected boolean isLogic;
	protected boolean isProperty;
	protected boolean isVerb;
	
	
	
	public Text(int xPosition, int yPosition, String text, RulesUpdater rulesUpdater) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.text = text;
		this.rulesUpdater = rulesUpdater;
		this.isLogic = false;
		this.isVerb = false;
		this.isProperty = false;
		this.isNoun = false;
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public String getText() {
		return text;
	}
	
	
	//These methods gives the neighbours of the texts, and return itself if it doesn't exist
	public Text findHPrevious()
	{
		if (this.yPosition >0)
		{
			return this.rulesUpdater.getText(xPosition, yPosition-1);
		}
		return this;
	}
	
	public Text findVPrevious()
	{
		if (this.xPosition >0)
		{
			return this.rulesUpdater.getText(xPosition-1, yPosition);
		}
		return this;
	}
	
	public Text findHNext()
	{
		if (this.yPosition < this.rulesUpdater.getyLength()-1 )
		{
			return this.rulesUpdater.getText(xPosition, yPosition+1);
		}
		return this;
	}
	
	public Text findVNext()
	{
		if (this.xPosition < this.rulesUpdater.getxLength()-1 )
		{

			return this.rulesUpdater.getText(xPosition+1, yPosition);
		}
		return this;
	}
	
}
