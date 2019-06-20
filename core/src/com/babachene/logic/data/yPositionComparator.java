package com.babachene.logic.data;
import java.util.Comparator;

public class yPositionComparator implements Comparator<Entity> {
/*
 * Simply allows to sort entities by their xPosition, to prevent some bugs.
 */
	@Override
	public int compare(Entity e1, Entity e2) {
		// TODO Auto-generated method stub
		return e1.getyPosition()-(e2.getyPosition());
	}

}
