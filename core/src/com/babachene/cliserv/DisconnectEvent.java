package com.babachene.cliserv;

public class DisconnectEvent extends Event {

	private static final long serialVersionUID = -9114839406130216808L;

	public DisconnectEvent() {
		super(01);
	}
	
	public void updateConnection(Server server) {
        server.disconnect();
    }
}
