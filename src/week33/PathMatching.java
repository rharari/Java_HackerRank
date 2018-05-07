/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * PathMatching.java
 * 
 * https://www.hackerrank.com/contests/w33/challenges/path-matching
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Jun 19, 2017
 *
 */

package week33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PathMatching {

	static char[] lst;
	static AhoCorasick ahoCorasick;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		String s = in.next();
		lst = (" " + s).toCharArray();
		String p = in.next();
		Graph2 g = new Graph2(n);

		ahoCorasick = new AhoCorasick(100000);
		ahoCorasick.addString(p);

		for (int i = 0; i < n - 1; i++) g.addTwoWayVertex(in.nextInt(), in.nextInt());

		for (int i = 0; i < q; i++) {
			g.found = false;
			Integer u = in.nextInt();
			Integer v = in.nextInt();
			if (u == v) {
				if (p.equals(lst[v] + "")) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
			} else {
				LinkedList<Integer> visited = new LinkedList<>();
				boolean[] marked = new boolean[n + 1];
				marked[u] = true;
				visited.add(u);
				depthFirst(g, visited, v, marked);
			}
		}
	}

	static void print(LinkedList<Integer> visited) {
		int size = visited.size();
		char[] sb = new char[size];
		int j = 0;
		for (Integer i : visited) sb[j++] = lst[i];

		int node = 0;
		List<Integer> positions = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			node = ahoCorasick.transition(node, sb[i]);
			if (ahoCorasick.nodes[node].leaf)
				positions.add(i);
		}
		System.out.println(positions.size());
	}

	private static void depthFirst(Graph2 graph, LinkedList<Integer> visited, Integer end, boolean[] marked) {
		LinkedList<Integer> nodes = graph.adjacentNodes(visited.getLast());
		if (nodes == null) return;
		for (Integer node : nodes) {
			if (marked[node]) {
				continue;
			} else if (node == end) {
				visited.add(node);
				print(visited);
				graph.found = true;
				return;
			} else {
				visited.addLast(node);
				marked[node] = true;
				depthFirst(graph, visited, end, marked);
				if (graph.found) return;
				visited.removeLast();
			}
		}
	}
}

class AhoCorasick {

	static final int ALPHABET_SIZE = 26;

	Node[] nodes;
	int nodeCount;

	public static class Node {
		int parent;
		char charFromParent;
		int suffLink = -1;
		int[] children = new int[ALPHABET_SIZE];
		int[] transitions = new int[ALPHABET_SIZE];
		boolean leaf;

		{
			Arrays.fill(children, -1);
			Arrays.fill(transitions, -1);
		}
	}

	public AhoCorasick(int maxNodes) {
		nodes = new Node[maxNodes];
		nodes[0] = new Node();
		nodes[0].suffLink = 0;
		nodes[0].parent = -1;
		nodeCount = 1;
	}

	public void addString(String s) {
		int cur = 0;
		for (char ch : s.toCharArray()) {
			int c = ch - 'a';
			if (nodes[cur].children[c] == -1) {
				nodes[nodeCount] = new Node();
				nodes[nodeCount].parent = cur;
				nodes[nodeCount].charFromParent = ch;
				nodes[cur].children[c] = nodeCount++;
			}
			cur = nodes[cur].children[c];
		}
		nodes[cur].leaf = true;
	}

	public int suffLink(int nodeIndex) {
		Node node = nodes[nodeIndex];
		if (node.suffLink == -1)
			node.suffLink = node.parent == 0 ? 0 : transition(suffLink(node.parent), node.charFromParent);
		return node.suffLink;
	}

	public int transition(int nodeIndex, char ch) {
		int c = ch - 'a';
		Node node = nodes[nodeIndex];
		if (node.transitions[c] == -1)
			node.transitions[c] = node.children[c] != -1 ? node.children[c]
					: (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
		return node.transitions[c];
	}

}

class Graph2 {
	private LinkedList<Integer>[] map;

	public Graph2(int n) {
		map = new LinkedList[n + 1];
	}
	public boolean found = false;
	public void addEdge(Integer node1, Integer node2) {
		if (map[node1] == null)
			map[node1] = new LinkedList<>();
		map[node1].add(node2);
	}

	public void addTwoWayVertex(Integer n1, Integer n2) {
		addEdge(n1, n2);
		addEdge(n2, n1);
	}

	public LinkedList<Integer> adjacentNodes(Integer last) {
		return map[last];
	}

}
