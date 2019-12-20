import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Range;

public class Main {

	public static void main(String[] args) {
		DGraph g = new DGraph(5);
		g.connect(0, 1, 10);
		g.connect(0, 2, 20);
		g.connect(1, 3, 25);
		g.connect(1, 4, 7);
		g.connect(2, 4, 30);
		g.connect(3, 5, 4);
		g.connect(4, 5, 2);
	//	g.connect(5, 0, 1);
		Graph_Algo algo = new Graph_Algo();
		algo.init(g);
		System.out.println(algo.shortestPath(0, 2));
		System.out.println(algo.shortestPathDist(0, 2));
		System.out.println(g.getV());
	//	System.out.println(g.getV());


		
	}

}
