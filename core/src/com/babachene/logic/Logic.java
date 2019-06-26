package com.babachene.logic;

import com.babachene.cliserv.Event;
import com.babachene.cliserv.Update;
import com.babachene.logic.data.LevelMap;

public interface Logic {

    public Update processEvent(Event event);
    public void processUpdate(Update update);
    public LevelMap getLevelMap();
}