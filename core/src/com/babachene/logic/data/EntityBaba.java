package com.babachene.logic.data;

import com.babachene.Baba;

public class EntityBaba extends Entity {

	public EntityBaba(int x, int y, LevelMap map) {
		super(x, y, map,"baba");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public short getId() {
		return Baba.BABA;
	}

}
