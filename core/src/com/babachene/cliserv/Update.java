package com.babachene.cliserv;

import java.io.Serializable;

public abstract class Update implements Serializable {

	private static final long serialVersionUID = -2445423896992172199L;
	private int id;
	
	public Update(int id) {
		this.id = id;
	}
	
	public void updateConnection(Client client) {
		
    }
	
	public int getId() {
		return id;
	}
	
}
