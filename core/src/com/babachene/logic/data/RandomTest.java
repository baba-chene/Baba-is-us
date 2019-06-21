package com.babachene.logic.data;

import java.util.LinkedList;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelMap map = new LevelMap(4,4);
		EntityText entity = new EntityText(0, 0, map,"textrock");
		System.out.println(entity.getId());
	}

}
