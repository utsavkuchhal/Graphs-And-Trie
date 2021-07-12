import java.util.*;

public class BridgesInGraph {
	private int V;

	private LinkedList<Integer> adj[];
	int time = 0;
	static final int NIL = -1;

	BridgesInGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	public static void main(String[] args) {
		System.out.println("Bridges in first graph ");
		BridgesInGraph g1 = new BridgesInGraph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		g1.bridge();
		System.out.println();

		System.out.println("Bridges in Second graph");
		BridgesInGraph g2 = new BridgesInGraph(4);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.bridge();
		System.out.println();

		System.out.println("Bridges in Third graph ");
		BridgesInGraph g3 = new BridgesInGraph(7);
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		g3.addEdge(1, 6);
		g3.addEdge(3, 5);
		g3.addEdge(4, 5);
		g3.bridge();
	}

	private void bridge() {
		boolean[] visited = new boolean[V];
		int[] low = new int[V];
		int[] disc = new int[V];

		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				bridgeUtil(visited, low, disc, 0, -1, 0);
			}
		}
	}

	private void bridgeUtil(boolean[] visited, int[] low, int[] tim, int timer, int parent, int start) {
		visited[start] = true;
		tim[start] = low[start] = timer++;

		for (int i : adj[start]) {
			if (i == parent) {
				continue;
			}

			if (!visited[i]) {
				bridgeUtil(visited, low, tim, timer, start, i);
				low[start] = Math.min(low[start], low[i]);

				if (low[i] > tim[start]) {
					System.out.println(i + " " + start);
				}
			} else {
				low[start] = Math.min(low[start], low[i]);

			}

		}

	}

}
