package com.babachene.cliserv;

public class DisconnectUpdate extends Update {

	private static final long serialVersionUID = 1334972505409275920L;

	public void updateConnction(Client client) {
        client.disconnect();
    }
}
