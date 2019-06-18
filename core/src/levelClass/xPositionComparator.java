package levelClass;
import java.util.Comparator;

public class xPositionComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity e1, Entity e2) {
		// TODO Auto-generated method stub
		return e1.getxPosition()-(e2.getxPosition());
	}

}
