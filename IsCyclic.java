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
		int t = scn.nextInt();
		while (t > 0) {
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
				graph[nbr].add(new Edge(nbr, src, 0));
			}

			boolean[] visited = new boolean[NoOfVertices];
			boolean check = false;
			for (int i = 0; i < NoOfVertices; i++) {
				if (visited[i] == false) {
					boolean isCyclic = isCyclic(graph, i, visited);
					if (isCyclic) {
						System.out.println(1);
						check = true;
					}
				}
			}
			if (!check) {
				System.out.println(0);
			}
			t--;
		}
	}

	public static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(src);
		while (!queue.isEmpty()) {
			int rem = queue.removeFirst();
			if (visited[rem] == true) {
				return true;
			}

			visited[rem] = true;
			for (Edge e : graph[rem]) {
				if (visited[e.nbr] == false) {
					queue.add(e.nbr);
				}
			}
		}
		return false;
	}
}
