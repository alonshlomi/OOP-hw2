package dataStructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DGraph implements graph {

	private int num_of_nodes, num_of_edges;
	private LinkedHashMap<Integer, node_data> nodes;
	private LinkedHashMap<Integer, LinkedHashMap<Integer, edge_data>> edges;

	public void print() {
		
		System.out.println(nodes.keySet());
		for (int keySrc : this.edges.keySet()) {
			String ans = "";
			ans += "src:" + keySrc + "-";
			ans += this.getE(keySrc).toString();
			System.out.println(ans);
		}
		System.out.println("num_of_nodes: " + num_of_nodes + ",num_of_edges: " + num_of_edges);
	}

	public DGraph(int num) {
		this();
		for (int i = 0; i <= num; i++) {
			node_data new_node = new Node();
			addNode(new_node);
		}
	}

	public DGraph() {
		nodes = new LinkedHashMap<Integer, node_data>();
		edges = new LinkedHashMap<Integer, LinkedHashMap<Integer, edge_data>>();
		num_of_edges = 0;
		num_of_nodes = 0;
	}
	

	@Override
	public node_data getNode(int key) {
		return nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return edges.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		nodes.put(n.getKey(), n);
		num_of_nodes++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		node_data s = nodes.get(src);
		node_data d = nodes.get(dest);
		edge_data edge = new Edge(s, d, w);
		LinkedHashMap<Integer, edge_data> tmp_edge;
		if (edges.get(src) == null) {
			tmp_edge = new LinkedHashMap<Integer, edge_data>();
		} else {
			tmp_edge = edges.get(src);
		}
		tmp_edge.put(dest, edge);
		edges.put(src, tmp_edge);
		num_of_edges++;
	}

	@Override
	public Collection<node_data> getV() {
		Collection<node_data> vertex = nodes.values();
		return vertex;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if(edges.get(node_id) == null) return null;
		return edges.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		if (edges.get(key) != null)
			num_of_edges--;
		edges.remove(key);

		Collection<LinkedHashMap<Integer, edge_data>> tmp = edges.values();
		Iterator<LinkedHashMap<Integer, edge_data>> it = tmp.iterator();

		while (it.hasNext()) {
			LinkedHashMap<Integer, edge_data> curr = it.next();
			if (curr.get(key) != null)
				num_of_edges--;
			curr.remove(key);
		}

		if (nodes.get(key) != null)
			num_of_nodes--;
		return nodes.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (edges.get(src) != null && edges.get(src).get(dest) != null)
			num_of_edges--;
		return edges.get(src).remove(dest);
	}

	@Override
	public int nodeSize() {
		return num_of_nodes;
	}

	@Override
	public int edgeSize() {
		return num_of_edges;
	}

	@Override
	public int getMC() {
		return 0;
	}

}
