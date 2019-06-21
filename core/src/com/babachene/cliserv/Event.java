package com.babachene.cliserv;

import java.io.Serializable;

public abstract class Event implements Serializable {

	private static final long serialVersionUID = -2409563161241609396L;
	private int source;
	private final int id;
	
	public Event(int id) {
		this.id = id;
	}

	public void updateConnection(Server server) {

    }
	
	public void setSource(int source) {
		this.source = source;
	}

    public int getSource() {
    	return source;
    }
    
    public int getId() {
    	return id;
    }
}
