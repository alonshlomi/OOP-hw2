package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.Node_Comparator;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {

	private graph g;

	@Override
	public void init(graph g) {
		this.g = g;
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		PriorityQueue<node_data> Q = new PriorityQueue<node_data>(Node._Comp);
		Collection<node_data> nodes = g.getV();
		node_data s = g.getNode(src);
		node_data d = g.getNode(dest);

		for (node_data node : nodes) {
			node.setWeight(Double.MAX_VALUE);
			if(node.getKey()==src) node.setWeight(0);
			node.setTag(-1);
			Q.add(node);
		}

	//	s.setWeight(0);

		while (d.getTag() != 1) {
			node_data u = Q.poll();
			Collection<edge_data> u_edges = g.getE(u.getKey());

			if (u_edges != null) {
				for (edge_data edge : u_edges) {
					node_data v = g.getNode(edge.getDest());
					if (v.getTag() != 1) {
						double t = u.getWeight() + edge.getWeight();
						if (v.getWeight() > t) {
							v.setWeight(t);
							v.setInfo(u.getKey()+"");
							PriorityQueue<node_data> tmp = new PriorityQueue<node_data>(Node._Comp);
							while (!Q.isEmpty())
								tmp.add(Q.poll());
							Q = tmp;
							
							
//							if (path != null) {
//								ArrayList<node_data> u_path = new ArrayList<node_data>();
//								if (path.get(v.getKey()) == null) {
//									u_path.add(u);
//									if (path.get(u.getKey()) != null) {
//										u_path.addAll(path.get(u.getKey()));
//									}
//									path.put(v.getKey(), u_path);
//								} else {
//									path.get(v.getKey()).add(u);
//								}
//							}
						}
					}
				}
			}
			u.setTag(1);
		}
		return d.getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data> path = new LinkedList<node_data>();
		shortestPathDist(src, dest);
		node_data curr_dest = g.getNode(dest);
		while(curr_dest.getKey() != src) {
			if(curr_dest.getInfo() == null) return null;
			int prev = Integer.parseInt(curr_dest.getInfo());
			node_data prev_n = g.getNode(prev);
			path.add(prev_n);
			curr_dest = prev_n;
		}
		
		path.add(g.getNode(dest));
		path.sort(Node._Comp);
		return path;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph copy = new DGraph();

		Collection<node_data> nodes = g.getV();
		for (node_data node : nodes) {
			copy.addNode(new Node(node));

			Collection<edge_data> edges = g.getE(node.getKey());
			for (edge_data edge : edges) {
				copy.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
			}
		}
		return copy;
	}

}
