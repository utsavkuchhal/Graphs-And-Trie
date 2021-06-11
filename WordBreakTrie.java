import java.util.*;

public class RoundRobin {
	public static void main(String args[]) {
		String arr[] = { "mobile", "samsung", "sam", "sung", "ma", "mango", "icecream", "and", "go", "i", "like", "ice",
				"cream" };

		int n = arr.length;
		Trie trie = new Trie();
		for (int i = 0; i < arr.length; i++) {
			trie.insert(arr[i]);
		}

		System.out.print(wordBreak("iiiiiiii", trie) ? "Yes\n" : "No\n");
		System.out.print(wordBreak("", trie) ? "Yes\n" : "No\n");
		System.out.print(wordBreak("ilikelikeimangoiii", trie) ? "Yes\n" : "No\n");
		System.out.print(wordBreak("samsungandmango", trie) ? "Yes\n" : "No\n");
		System.out.print(wordBreak("samsungandmangok", trie) ? "Yes\n" : "No\n");
	}

	static boolean wordBreak(String s, Trie t) {
		if (s.length() == 0) {
			return true;
		}
		for (int i = 1; i <= s.length(); i++) {

			if (t.find(s.substring(0, i)) && wordBreak(s.substring(i), t))
				return true;
		}
		return false;
	}

	static class Trie {
		Node root;

		int count = 0;

		public Trie() {
			this.root = new Node('\0');
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

		void printTrie() {
			Node temp = root;
			printTrieUtil(temp);
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

		public Node(char data) {
			this.data = data;
			this.children = new HashMap<Character, Node>();
			this.terminal = false;
		}
	}
}
