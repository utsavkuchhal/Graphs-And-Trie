import java.util.ArrayList;
import java.util.Scanner;

import com.sun.management.GcInfo;

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
			graph[nbr].add(new Edge(nbr, src, 0));
		}

		boolean[] visited = new boolean[NoOfVertices];
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < NoOfEdges; i++) {
			if (visited[i] == false) {
				ArrayList<Integer> comp = new ArrayList<Integer>();
				GCC(graph, i, comp, visited);
				ans.add(comp);
			}
		}
		
		System.out.println(ans);
	}

	public static void GCC(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
		visited[src] = true;
		comp.add(src);
		for (Edge temp : graph[src]) {
			if (visited[temp.nbr] == false)
				GCC(graph, temp.nbr, comp, visited);
		}
	}

}
