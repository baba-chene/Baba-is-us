package com.babachene.userinput;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.KeepAliveUpdate;
import com.babachene.cliserv.Update;
import com.babachene.logic.Logic;

public class MockLogic implements Logic {

	@Override
	public Update processEvent(Event event) {
		return new KeepAliveUpdate();
	}

	@Override
	public void processUpdate(Update update) {
		
	}

}
