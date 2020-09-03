import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.sun.management.GcInfo;
import com.sun.tools.javac.resources.compiler;

public class Practise7 {

	static class Edge {
		int src;
		int wt;
		int nbr;

		public Edge(int src, int nbr, int wt) {
			this.src = src;
			this.wt = wt;
			this.nbr = nbr;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scn = new Scanner(System.in);
		int NoOfVertices = scn.nextInt();

		ArrayList<Edge>[] graph = new ArrayList[NoOfVertices];

		for (int i = 0; i < NoOfVertices; i++) {
			graph[i] = new ArrayList<>();
		}

		int NoOfEdges = scn.nextInt();

		for (int i = 0; i < NoOfEdges; i++) {
			int src = scn.nextInt();
			int nbr = scn.nextInt();
			graph[src].add(new Edge(src, nbr, 0));
//			graph[nbr - 1].add(new Edge(nbr - 1, src - 1, 0));
		}

//		HashSet<Integer> visited = new HashSet<Integer>();
//		HamiltonianPathCycle(graph, 0, 0 + " ", visited, 0);
		BFS(graph, 2);
	}

	public static void BFS(ArrayList<Edge>[] graph, int src) {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(src);
		boolean[] visited = new boolean[graph.length];
		while (!queue.isEmpty()) {
			int rem = queue.removeFirst();
			if (visited[rem] == true) {
				continue;
			} else {
				visited[rem] = true;
				System.out.print(rem + " ");
				for (Edge e : graph[rem]) {
					if (visited[e.nbr] == false) {
						queue.add(e.nbr);
					}
				}
			}
		}
	}
}
