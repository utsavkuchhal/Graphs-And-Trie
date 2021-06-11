import java.util.*;

public class RoundRobin {
	public static void main(String args[]) {
		String arr[] = { "act", "god", "cat", "dog", "tac" };

		int n = arr.length;
		Trie trie = new Trie();

		for (int i = 0; i < arr.length; i++) {
			char[] temp = arr[i].toCharArray();
			Arrays.sort(temp);
			String s = String.valueOf(temp);

			trie.insert(s, i);
		}

		trie.printAnagrams(arr);
	}

	static class Trie {
		Node root;

		int count = 0;

		public Trie() {
			this.root = new Node('\0');
			count = 0;
		}

		void insert(String s, int index) {
			Node temp = root;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (temp.children.containsKey(c)) {
					temp = temp.children.get(c);
				} else {
					Node n = new Node(c);
					temp.children.put(c, n);
					temp = n;
				}
			}

			if (temp.terminal == true) {
				temp.list.add(index);
			} else {
				temp.terminal = true;
				temp.list.add(index);
			}
		}

		void printTrie() {
			Node temp = root;
			printTrieUtil(temp);
		}

		void printAnagrams(String[] arr) {
			Node temp = root;
			printAnagrams(temp, arr);
		}

		void printAnagrams(Node root, String[] arr) {
			if (root.terminal == true) {
				for (int i = 0; i < root.list.size(); i++) {
					System.out.println(arr[root.list.get(i)] + " ");
				}
			}

			for (char c : root.children.keySet()) {
				Node node = root.children.get(c);
				printAnagrams(node, arr);
			}
		}

		void printTrieUtil(Node root) {
			System.out.println("data -> " + root.data + " terminal ->" + root.terminal);

			for (char c : root.children.keySet()) {
				Node node = root.children.get(c);
				printTrieUtil(node);
			}
		}

		boolean find(String s) {
			Node temp = root;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (!temp.children.containsKey(c)) {
					return false;
				} else {
					temp = temp.children.get(c);

				}
			}
			return temp.terminal;
		}
	}

	static class Node {
		char data;
		HashMap<Character, Node> children;
		boolean terminal;
		List<Integer> list;

		public Node(char data) {
			this.data = data;
			this.list = new ArrayList<>();
			this.children = new HashMap<Character, Node>();
			this.terminal = false;
		}
	}
}
