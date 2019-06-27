package com.babachene.cliserv;

public class InputUpdate extends Update {
	
	private static final long serialVersionUID = -8465728544238652482L;
	
	private final int player;
	public final boolean updated;
	
	public final byte eventType;
	
	public InputUpdate(byte type, int player, boolean updated) {
		super(20);
		this.player = player;
		this.updated = updated;
		eventType = type;
	}

	public int getPlayer() {
		return player;
	}
	
	public static final byte
		MOVE_LEFT=0,
		MOVE_RIGHT=1,
		MOVE_UP=2,
		MOVE_DOWN=3,
		
		Z_REQUEST=4,
		Z_REQUEST_STOP=5,
		
		RESET_REQUEST=6,
		RESET_REQUEST_STOP=7,
		
		WAIT=8;
}
