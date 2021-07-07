import java.io.*;
import java.util.*;

class Template {
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}
			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	static Reader sc = new Reader();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String args[]) throws IOException {
		Reader sc = new Reader();
		int V = inputInt();
		int E = inputInt();
		ArrayList<Integer>[] graph = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < E; i++) {
			int u = inputInt();
			int v = inputInt();
			graph[u - 1].add(v - 1);
			graph[v - 1].add(u - 1);
		}

//		boolean[] vis = new boolean[V];
//		ArrayList<Integer> points = new ArrayList<Integer>();
		int[] color = new int[V];
		if (!checkBipartite(graph, V, color)) {
			println("IMPOSSIBLE");
		} else {
			for (int i = 0; i < color.length; i++) {
				print(color[i] + " ");
			}

		}

		bw.flush();
		bw.close();
	}

	public static boolean checkBipartite(ArrayList<Integer>[] graph, int V, int[] color) {
		for (int i = 0; i < V; i++) {
			if (color[i] == 0) {
				if (!bfsCheck(graph, color, i)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean bfsCheck(ArrayList<Integer>[] graph, int[] color, int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		color[start] = 1;
		while (!q.isEmpty()) {
			int temp = q.remove();

			for (int child : graph[temp]) {
				if (color[child] == 0) {
					if (color[temp] == 1) {
						color[child] = 2;
					} else {
						color[child] = 1;
					}
					q.add(child);
				} else if (color[child] == color[temp]) {
					return false;
				}
			}
		}
		return true;
	}

	public static int inputInt() throws IOException {
		return sc.nextInt();
	}

	public static long inputLong() throws IOException {
		return sc.nextLong();
	}

	public static double inputDouble() throws IOException {
		return sc.nextDouble();
	}

	public static String inputString() throws IOException {
		return sc.readLine();
	}

	public static void print(String a) throws IOException {
		bw.write(a);
	}

	public static void printSp(String a) throws IOException {
		bw.write(a + " ");
	}

	public static void println(String a) throws IOException {
		bw.write(a + "\n");
	}
}
