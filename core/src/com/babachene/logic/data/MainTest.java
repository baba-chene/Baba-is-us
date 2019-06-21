package com.babachene.logic.data;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		LevelMap map = new LevelMap(20,20);
		map.addEntity(0, 0, "textbaba");
		map.addEntity(0, 1, "textis");
		map.addEntity(0, 2, "textp1");
		map.addEntity(1, 0, "textrock");
		map.addEntity(1, 1, "textis");
		map.addEntity(1, 2, "textp2");
		map.addEntity(2,2,"baba");
		map.addEntity(3,3,"rock");
		map.getRulesUpdater().updateRules();
		map.moveRight(1);
		map.moveLeft(2);
		long endTime = System.nanoTime();
		long time = endTime - startTime;
		System.out.println(time/1000000);
	}

}
