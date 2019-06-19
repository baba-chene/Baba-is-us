package levelClass;

import java.util.LinkedList;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> list = new LinkedList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		for (String s:list)
			System.out.println(s);
		String test = list.pop();
		System.out.println(test);
	}

}
