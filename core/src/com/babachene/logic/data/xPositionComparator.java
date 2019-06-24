package com.babachene.logic.data;
import java.util.Comparator;

import com.babachene.logic.data.entities.Entity;

public class xPositionComparator implements Comparator<Entity> {
	/*
	 * Simply allows to sort entities by their xPosition, to prevent some bugs.
	 */

	@Override
	public int compare(Entity e1, Entity e2) {
		// TODO Auto-generated method stub
		return e1.getxPosition()-(e2.getxPosition());
	}

}
