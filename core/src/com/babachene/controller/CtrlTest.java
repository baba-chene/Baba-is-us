package com.babachene.controller;

import com.babachene.logic.data.LevelMap;

public class CtrlTest {
	
	public static LevelMap gimmeLevel() {
		
		LevelMap map = new LevelMap(10, 10);
		
		map.addEntity(2, 4, "keke");
		map.addEntity(5, 5, "textis");
		map.addEntity(5, 8, "textkeke");
		map.addEntity(4, 2, "rock");
		map.addEntity(5, 5, "textis");
		map.addEntity(5, 8, "textkeke");
		map.addEntity(4, 2, "rock");
		map.addEntity(5, 2, "textrock");
		map.addEntity(5, 4, "textpush");
		map.addEntity(5, 7, "textsink");
		map.addEntity(1, 1, "textkeke");
		map.addEntity(2, 1, "textis");
		map.addEntity(3, 1, "textyou");
		map.addEntity(6, 5, "textyou");
		map.addEntity(6, 6, "textrock");
		map.addEntity(4, 7, "water");
		map.addEntity(3, 7, "textwater");
		map.addEntity(7, 2, "wall");
		map.addEntity(7, 4, "textwall");
		map.addEntity(6, 3, "textand");
		map.addEntity(7, 5, "textp1");
		map.addEntity(7, 6, "textp2");
		
		return map;
	}
	
}
