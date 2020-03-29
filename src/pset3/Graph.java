package pset3;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private int numNodes; // number of nodes in the graph

	private boolean[][] edges;
	// edges[i][j] is true if and only if there is an edge from node i to node j

	// class invariant: fields "edges" is non-null;
	//                  "edges" is a square matrix;
	//                  numNodes is number of rows in "edges"

	public Graph(int size) {
		numNodes = size;

		// your code goes here
		// ...

		// Check don't have illegal arguments (int can't be null)
		if (size < 0) { // Size should be positive
			throw new IllegalArgumentException();
		}

		// Initialize edges
		edges = new boolean[numNodes][numNodes];
		for (boolean[] row : edges) {
			Arrays.fill(row, false);
		}
	}

	public String toString() {
		return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
	}

	public boolean equals(Object o) {
		if (o.getClass() != Graph.class) return false;
		return toString().equals(o.toString());
	}

	public void addEdge(int from, int to) {
		// postcondition: adds a directed edge "from" -> "to" to this graph

		// your code goes here
		//...

		// Check don't have illegal arguments (int can't be null)
		if (from < 0 || to < 0 || from >= numNodes || to >= numNodes) { // Nodes can only be from 0 to numNodes-1
			throw new IllegalArgumentException();
		}

		// Set the entry edges[from][to] to true
		edges[from][to] = true;
	}

	public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
		if (sources == null || targets == null) throw new IllegalArgumentException();

		// postcondition: returns true if (1) "sources" does not contain an illegal node,
		//                                (2) "targets" does not contain an illegal node, and
		//                                (3) for each node "m" in set "targets", there is some
		//                                node "n" in set "sources" such that there is a directed
		//                                path that starts at "n" and ends at "m" in "this"; and
		//                                false otherwise

		// your code goes here
		//...

		// Check if sources or targets are empty sets
		if (sources.isEmpty() || targets.isEmpty()) return false;

		// Check conditions 1 and 2
		if (hasIllegalNodes(sources) || hasIllegalNodes(targets)) return false;

		/*
		 * For each target, iterate over all sources until we find a source that can reach the target, or we run out of
		 * sources. Use BFS to search if the current target node is reachable from the current source node. If we find a
		 * target that is not reachable from any source, return false. If all targets are reachable from a source,
		 * return true.
		 */
		for (int target : targets) {
			boolean targetReachable = false;
			sourcesLoop:
			for (int source : sources) {
				// Initialize the set of visited nodes empty
				Set<Integer> visitedNodes = new HashSet<Integer>();
				// Initialize the queue of the nodes to visit empty
				Queue<Integer> q = new LinkedList<>();
				int visiting;   // Node visiting

				// Visit the source node first
				q.add(source);
				//visitedNodes.add(source);

				// Check if source node is the target
				if (source == target) {
					// The target is reachable from the current source
					targetReachable = true;
					break sourcesLoop;  // Move to the next target
				}

				while(!q.isEmpty()) {
					visiting = q.remove();  // Visit the node in the queue and de-queue it

					// Iterate over all nodes
					for (int i=0; i<numNodes; i++) {
						// Find the adjacent nodes that we have not visited yet (to avoid infinite cycles)
						if (edges[visiting][i] && (!visitedNodes.contains(i))) {
							// Check if the adjacent node is the target we are looking for
							if (i == target) {
								// The target is reachable from the current source
								targetReachable = true;
								break sourcesLoop;  // Move to the next target
							}

							// The adjacent node is not a target
							/* En-queue it if it is not the source node, because the source node was already in the
							 * queue. We didn't add it to the visited nodes, so we could check if there was a cycle that
							 * allowed the source node to reach itself.
							 */
							if (i != source) {
								q.add(i);
							}
							// Add it to the visited nodes set
							visitedNodes.add(i);
						}
					}
				}
			}
			// Check if we found a source that can reach the target
			if (!targetReachable) {
				// No source reaches this target
				return false;
			}
		}

		return true;
	}

	public boolean hasIllegalNodes(Set<Integer> nodes) {
		// postcondition: returns true if there is at least one node outside of the range 0 <= node < numNodes
		//                        false otherwise
		for (int node : nodes) {
			if (node < 0 || node >= numNodes) {
				return true;
			}
		}
		return false;
	}
}
