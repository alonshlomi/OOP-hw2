package dataStructure;

import java.util.Comparator;

public class Node_Comparator implements Comparator<node_data> {
	
	@Override
	public int compare(node_data o1, node_data o2) {
		double ans = o1.getWeight() - o2.getWeight();
		if(ans > 0) {
			return 1;
		} else if(ans == 0) {
			return 0;
		} else {
			return -1;
		}
	}

}
