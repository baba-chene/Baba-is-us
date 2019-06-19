package levelRules;

public abstract class Text {

	private int xPosition;
	private int yPosition;
	private final String text;
	
	
	
	public Text(int xPosition, int yPosition, String text) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.text = text;
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
	
	
}
