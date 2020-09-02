import java.util.ArrayList;
import java.util.Scanner;


//  In this solution edge is vertice and vertice is edge Sorry, My mistake
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
		int NoOfEdges = scn.nextInt();

		ArrayList<Edge>[] graph = new ArrayList[NoOfEdges];

		for (int i = 0; i < NoOfEdges; i++) {
			graph[i] = new ArrayList<>();
		}

		int NoOfVertices = scn.nextInt();

		for (int i = 0; i < NoOfVertices; i++) {
			int src = scn.nextInt();
			int nbr = scn.nextInt();
			graph[src].add(new Edge(src, nbr, 0));
		}

		int src = 2;
		int dst = 3;
		boolean[] visited = new boolean[NoOfVertices];
		PrintPath(graph, src, dst, visited, src +"");
	}

	public static void PrintPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited, String ans) {
		if (src == dst) {
			System.out.println(ans);
			return;
		}

		visited[src] = true;
		for (Edge temp : graph[src]) {
			if (visited[temp.nbr] == false)
				PrintPath(graph, temp.nbr, dst, visited, ans + " " +  temp.nbr);
		}
		visited[src] = false;
	}

}
