import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;

class DGraphTest {

	static DGraph graph,empty_graph;
	final static int NUM_OF_NODES = 10;
	static int rand_key1,rand_key2,rand_key3;

	@BeforeAll
	static void init() {
		rand_key1 = (int) (Math.random() * NUM_OF_NODES);
		rand_key2 = (int) (Math.random() * NUM_OF_NODES);
		rand_key3 = (int) (Math.random() * NUM_OF_NODES);
		
		graph = new DGraph(NUM_OF_NODES);
		graph.connect(rand_key1, rand_key2, 50);
		graph.connect(rand_key1, rand_key3, 60);
		graph.connect(rand_key2, rand_key3, 70);

		
		empty_graph = new DGraph();	
		
	}

	@Test
	void testGetNode() {	
		node_data actual_node1 = graph.getNode(rand_key1);
		node_data actual_node2 = graph.getNode(rand_key2);
		node_data actual_node3 = graph.getNode(rand_key3);
		node_data null_node = empty_graph.getNode(rand_key1);

		
		assertEquals(rand_key1, actual_node1.getKey());
		assertEquals(rand_key2, actual_node2.getKey());
		assertEquals(rand_key3, actual_node3.getKey());
		assertNull(null_node);

	}

	@Test
	void testGetEdge() {
		edge_data e0 = graph.getEdge(rand_key1, rand_key2);
		edge_data e1 = graph.getEdge(rand_key2, rand_key3);
		
		edge_data null_e = empty_graph.getEdge(rand_key1, rand_key2);
		
		assertEquals(rand_key1, e0.getSrc());
		assertEquals(rand_key2, e0.getDest());
		assertEquals(rand_key2, e1.getSrc());
		assertEquals(rand_key3, e1.getDest());
		assertNull(null_e);
	
	}

	@Test
	void testAddNode() {
		Node new_node = new Node();
		graph.addNode(new_node);
		node_data get_node = graph.getNode(new_node.getKey());
		
		assertEquals(new_node,get_node);
	}

	@Test
	void testConnect() {
		
	
	}

	@Test
	void testGetV() {
		fail("Not yet implemented");
	}

	@Test
	void testGetE() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveNode() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveEdge() {
		fail("Not yet implemented");
	}

	@Test
	void testNodeSize() {
		fail("Not yet implemented");
	}

	@Test
	void testEdgeSize() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMC() {
		fail("Not yet implemented");
	}

}
