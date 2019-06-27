package com.babachene.logic;

import com.babachene.cliserv.Update;

/**
 * Sent by the server to inform the client about the level map that
 * the game should load and play.
 * @author jeremy
 *
 */
public class LevelUpdate extends Update {
	
	private static final long serialVersionUID = 5895346999114013724L;
	public final String name;
	
	public LevelUpdate(String name) {
		super(10); // FIXME dat sh**** id
		this.name = name;
	}
	
	
}
