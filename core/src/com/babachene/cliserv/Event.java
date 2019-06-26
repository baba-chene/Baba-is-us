package com.babachene.cliserv;

import java.io.Serializable;

public abstract class Event implements Serializable {

	private static final long serialVersionUID = -2409563161241609396L;
	private int player;
	private final int id;

	public Event(int id) {
		this.id = id;
	}

	public void updateConnection(Server server) {

	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getPlayer() {
		return player;
	}

	public int getId() {
		return id;
	}
}
