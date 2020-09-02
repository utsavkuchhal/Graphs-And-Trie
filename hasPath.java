import java.util.ArrayList;
import java.util.Scanner;
//  In this solution edge is vertice and vertice is edge Sorry, My mistake
public class Practise7 {

	static class Edge {
		int src;
		int wt;
		int nbr;

		public Edge(int src, int dst) {
			this.src = src;
			this.wt = wt;
			this.nbr = nbr;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();

		ArrayList<Edge>[] graph = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		

		boolean[] visited = new boolean[n];
		boolean hasPath = hasPath(graph, 1, 3, visited);
	}

	public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited) {
		if (src == dst) {
			return true;
		}

		visited[src] = true;
		for (Edge temp : graph[src]) {
			boolean nbrhasPath = hasPath(graph, temp.nbr, dst, visited);
			if (nbrhasPath) {
				return true;
			}
		}
		return false;
	}

}
