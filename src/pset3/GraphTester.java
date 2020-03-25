package pset3;

import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.Set;
import org.junit.Test;

public class GraphTester {
	// tests for method "addEdge" in class "Graph"
	@Test
	public void tae0() {
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		System.out.println(g);
		assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
	}

	// your tests for method "addEdge" in class "Graph" go here

	// you must provide at least 4 test methods;
	// each test method has at least 1 invocation of addEdge;
	// each test method creates exactly 1 graph
	// each test method creates a unique graph w.r.t. "equals" method
	// each test method has at least 1 test assertion;
	// your test methods provide full statement coverage of your
	// implementation of addEdge and any helper methods
	// no test method directly invokes any method that is not
	// declared in the Graph class as given in this homework

	// ...

	@Test
	public void tae1() {
		// Test we can't add negative nodes in the `to` argument (illegal argument exception)
		Graph g = new Graph(3);
		boolean exceptionThrown = false;

		try {
			g.addEdge(0, -1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		assertEquals(g.toString(), "numNodes: 3\nedges: [[false, false, false], [false, false, false], "
				+ "[false, false, false]]");
		//System.out.println(g);

		// Test we can't add negative nodes in the `from` argument (illegal argument exception)
		g = new Graph(3);
		exceptionThrown = false;

		try {
			g.addEdge(-2, 0);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		assertEquals(g.toString(), "numNodes: 3\nedges: [[false, false, false], [false, false, false], "
				+ "[false, false, false]]");
		//System.out.println(g);
	}

	@Test
	public void tae2() {
		// Test we can't add nodes >= numNodes in the `to` argument (illegal arguments)
		Graph g = new Graph(4);
		boolean exceptionThrown = false;

		try {
			g.addEdge(0, 4);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		assertEquals(g.toString(), "numNodes: 4\nedges: [[false, false, false, false], "
				+ "[false, false, false, false], [false, false, false, false], "
				+ "[false, false, false, false]]");
		//System.out.println(g);

		// Test we can't add nodes >= numNodes in the `from` argument (illegal arguments)
		g = new Graph(4);
		exceptionThrown = false;

		try {
			g.addEdge(5, 0);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		assertEquals(g.toString(), "numNodes: 4\nedges: [[false, false, false, false], "
				+ "[false, false, false, false], [false, false, false, false], "
				+ "[false, false, false, false]]");
		//System.out.println(g);
	}

	@Test
	public void tae3() {
		// Test we can add nodes that point to themselves
		Graph g = new Graph(1);
		g.addEdge(0, 0);
		assertEquals(g.toString(), "numNodes: 1\nedges: [[true]]");
	}

	@Test
	public void tae4() {
		// Test we can add cycles
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		assertEquals(g.toString(), "numNodes: 3\nedges: [[false, true, false], [true, false, true], "
				+ "[true, false, false]]");
	}

	@Test
	public void tae5() {
		// Adding the same edges to empty graphs creates the same graph every time
		Graph g1 = new Graph(3);
		g1.addEdge(0, 1);
		g1.addEdge(1, 0);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		Graph g2 = new Graph(3);
		g2.addEdge(0, 1);
		g2.addEdge(1, 0);
		g2.addEdge(1, 2);
		g2.addEdge(2, 0);
		assertEquals(g1, g2);
		assertEquals(g2, g1);
	}

	// TODO: Search for other interesting cases to test `addEdge()`

	// tests for method "reachable" in class "Graph"
	@Test
	public void tr0() {
		Graph g = new Graph(1);
		Set<Integer> nodes = new TreeSet<Integer>();
		nodes.add(0);
		assertTrue(g.reachable(nodes, nodes));
	}

	// your tests for method "reachable" in class "Graph" go here


	// you must provide at least 6 test methods;
	// each test method must have at least 1 invocation of reachable;
	// each test method must have at least 1 test assertion;
	// at least 2 test methods must have at least 1 invocation of addEdge;
	// your test methods must provide full statement coverage of your
	// implementation of reachable and any helper methods
	// no test method directly invokes any method that is not
	// declared in the Graph class as given in this homework

	// ...

	@Test
	public void tr1() {
		// Test if there is not a path between 2 nodes, you cannot reach one from the other
		Graph g = new Graph(2);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(1);
		assertFalse(g.reachable(sources, targets));
	}

	@Test
	public void tr2() {
		// Test if there is a path between 2 nodes, you can reach one from the other
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(1);
		assertTrue(g.reachable(sources, targets));
	}

	@Test
	public void tr3() {
		// Test that providing illegal nodes in the sources set returns false
		Graph g = new Graph(1);

		// Null set
		boolean exceptionThrown = false;
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		targets.add(0);
		try {
			g.reachable(null, targets);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);

		// Empty set
		assertFalse(g.reachable(sources, targets));

		// Negative illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(-1);
		targets.add(0);
		assertFalse(g.reachable(sources, targets));

		// Too large illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		targets.add(0);
		assertFalse(g.reachable(sources, targets));
	}

	@Test
	public void tr4() {
		// Test that providing illegal nodes in the targets set returns false
		Graph g = new Graph(1);

		// Null set
		boolean exceptionThrown = false;
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		try {
			g.reachable(sources, null);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);

		// Empty set
		assertFalse(g.reachable(sources, targets));

		// Negative illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		targets.add(0);
		targets.add(-1);
		sources.add(0);
		assertFalse(g.reachable(sources, targets));

		// Too large illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		targets.add(0);
		targets.add(1);
		sources.add(0);
		assertFalse(g.reachable(sources, targets));
	}

	@Test
	public void tr5() {
		// Test that providing illegal nodes in the sources and targets sets returns false
		Graph g = new Graph(1);

		// Null set
		boolean exceptionThrown = false;
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		try {
			g.reachable(null, null);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);

		// Empty set
		assertFalse(g.reachable(sources, targets));

		// Negative illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(-1);
		targets.add(0);
		targets.add(-1);
		assertFalse(g.reachable(sources, targets));

		// Too large illegal node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		targets.add(0);
		targets.add(1);
		assertFalse(g.reachable(sources, targets));
	}

	@Test
	public void tr6() {
		// Test paths with self-cycles and cycles
		// Test paths with self-cycles
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 2);

		// Self cycle in node 2 does not interfere with 0 being able to reach every node
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(0);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));

		// Self cycle in node 2 does not interfere with 2 being able to reach itself
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(2);
		targets.add(2);
		assertTrue(g.reachable(sources, targets));

		// Test path with cycles
		g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 0);

		// Cycle in nodes (0, 1, 2, 0) does not interfere with 0 being able to reach every node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(0);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));
	}

	@Test
	public void tr7() {
		// Test multiple sources to multiple targets
		Graph g = new Graph(5);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 4);

		// Test nodes 0 and 1 can reach every node in the graph
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		targets.add(0);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));

		// Test nodes 3 and 4 cannot reach nodes 0, 1 or 2 in the graph
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(3);
		sources.add(4);
		targets.add(0);
		targets.add(1);
		targets.add(2);
		assertFalse(g.reachable(sources, targets));

		// Test nodes 0, 2 and 4 cannot reach nodes 1 or 3 in the graph
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(2);
		sources.add(4);
		targets.add(1);
		targets.add(3);
		assertFalse(g.reachable(sources, targets));
	}

	@Test
	public void tr8() {
		// Test graphs without edges
		Graph g = new Graph(4);

		// Without edges, all nodes are reachable from themselves
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		sources.add(2);
		sources.add(3);
		targets.add(0);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(0);
		assertTrue(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(1);
		targets.add(1);
		assertTrue(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(2);
		targets.add(2);
		assertTrue(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(3);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));

		// Without edges, no node is reachable from a different node
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(1);
		sources.add(2);
		sources.add(3);
		targets.add(0);
		assertFalse(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(2);
		sources.add(3);
		targets.add(1);
		assertFalse(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		sources.add(3);
		targets.add(2);
		assertFalse(g.reachable(sources, targets));
		sources = new TreeSet<Integer>();
		targets = new TreeSet<Integer>();
		sources.add(0);
		sources.add(1);
		sources.add(2);
		targets.add(3);
		assertFalse(g.reachable(sources, targets));
	}

	// TODO: Search for other interesting cases to test `reachable()`
}
