package com.babachene.logic.data;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		LevelMap map = new LevelMap(10, 10);		
		map.addEntity(6, 5, "baba");
		map.addEntity(6, 6, "water");
		map.addEntity(1, 1, "textbaba");
		map.addEntity(2, 1, "textis");
		map.addEntity(3, 1, "textyou");
		map.addEntity(1, 2, "textwater");
		map.addEntity(2, 2, "textis");
		map.addEntity(3, 2, "textsink");
		map.moveLeft();
		map.undo();
		map.moveLeft();
		map.moveRight();
		map.moveRight();
		map.undo();
		map.moveDown();
		map.moveDown();
		long endTime = System.nanoTime();
		map.undo();
		map.undo();
		map.undo();
		map.undo();
		map.undo();
		map.undo();

		long time = endTime - startTime;
		System.out.println(time/1000000);
	}

}
