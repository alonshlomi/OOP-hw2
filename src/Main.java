import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import algorithms.Graph_Algo;
import dataStructure.*;
import gui.Graph_GUI;
import utils.Range;

public class Main {

	public static void main(String[] args) {
		DGraph g = new DGraph(5);
		Graph_Algo ga = new Graph_Algo();
		Graph_GUI gg = new Graph_GUI(g);
		ga.init(g);
		g.connect(1, 0, 10);
	//	g.connect(0, 2, 20);
		g.connect(5, 1, 25);
	//	g.connect(1, 4, 7);
		g.connect(2, 3, 30);
		g.connect(3, 4, 4);
		g.connect(4, 5, 2);
	//	g.connect(5, 0, 1);
		System.out.println(g);
		int src = 5;
		int dst = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(5);
		list.add(1);
		list.add(0);
		System.out.println(ga.shortestPathDist(src, dst));
		System.out.println(ga.shortestPath(src, dst));
		System.out.println(ga.TSP(list));
//		g.removeNode(1);
		



		


		
	}

}
