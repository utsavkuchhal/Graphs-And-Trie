import java.util.*;

public class RoundRobin {
	public static void main(String[] args) {
		String temp_dictionary[] = { "mobile", "samsung", "sam", "sung", "man", "mango", "icecream", "and", "go", "i",
				"like", "ice", "cream" };
		Trie trie = new Trie();
		for (int i = 0; i < temp_dictionary.length; i++) {
			trie.insert(temp_dictionary[i]);
		}

		if (trie.find("samsu")) {
			System.out.println("found");
		} else {
			System.out.println("Not found");
		}
	}

	static class Trie {
		Node root;

		int count = 0;

		public Trie() {
			this.root = new Node('0');
			count = 0;
		}

		void insert(String s) {
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

			temp.terminal = true;
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

		public Node(char data) {
			this.data = data;
			this.children = new HashMap<Character, Node>();
			this.terminal = false;
		}
	}
}
