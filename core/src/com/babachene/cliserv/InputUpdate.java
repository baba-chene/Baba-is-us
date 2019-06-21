package com.babachene.cliserv;

public class InputUpdate extends Update {
	
	private static final long serialVersionUID = -8465728544238652482L;

	/** A byte to store some data. The name is yet to adapt. */
	public final byte additionalData;
	
	public final byte eventType;
	
	public InputUpdate(byte type, byte data) {
		super(20);
		additionalData = data;
		eventType = type;
	}
	
	public static final byte
		MOVE_LEFT=0,
		MOVE_RIGHT=1,
		MOVE_UP=2,
		MOVE_DOWN=3,
		
		Z_REQUEST=4,
		Z_REQUEST_STOP=5,
		
		RESET_REQUEST=6,
		RESET_REQUEST_STOP=7;
}
