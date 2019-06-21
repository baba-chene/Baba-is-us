package com.babachene.logic.data;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		LevelMap map = new LevelMap(10, 10);		
		map.addEntity(6, 5, "baba");
		map.addEntity(7, 5, "textis");
		map.addEntity(5, 8, "textbaba");
		map.addEntity(4, 2, "rock");
		map.addEntity(1, 1, "textbaba");
		map.addEntity(2, 1, "textis");
		map.addEntity(3, 1, "textyou");
		map.moveDown();
		map.moveDown();
		map.moveDown();
		map.moveDown();
		map.moveDown();
		map.moveDown();
		long endTime = System.nanoTime();
		long time = endTime - startTime;
		System.out.println(time/1000000);
	}

}
