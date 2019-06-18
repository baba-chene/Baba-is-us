package com.babachene.cliserv;

public class DisconnectEvent extends Event {

	private static final long serialVersionUID = -9114839406130216808L;

	public void updateConnection(Server server) {
        server.disconnect();
    }
}
