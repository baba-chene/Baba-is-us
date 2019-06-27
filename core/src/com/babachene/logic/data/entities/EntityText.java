package com.babachene.logic.data.entities;

import com.babachene.logic.data.LevelMap;

public class EntityText extends Entity {
	
	
	public EntityText(int x, int y, LevelMap map, String typeOfEntity) {
		super(x, y, map,"text");
		this.isPushable = true;
		this.typeOfEntity = "text";
		this.text = typeOfEntity.substring(4);; //Because in the editor we get "textrock" , "textis" ...
		// TODO Auto-generated constructor stub
	}

}
