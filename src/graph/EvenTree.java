/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * EvenTree.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/even-tree
 *
 */

package graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EvenTree {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();		
		Hashtable<Integer, Node> ht = new Hashtable<>(n);
		IntStream.range(0, n).forEach(i -> {
			Node n1 = new Node(i + 1);
			ht.put(i + 1, n1);					
		});
		IntStream.range(0, m).forEach(i -> {
			int v1 = in.nextInt();
			int v2 = in.nextInt();
			Node n1 = ht.get(v1);
			Node n2 = ht.get(v2);
			n1.father = n2;
			n2.addChild(n1);
		});
		int totalCut = 0;
		Node n1 = ht.get(1);

		for (Node nod : n1.childs) totalCut = checkCut(nod, totalCut);
		
		System.out.println(totalCut);
		in.close();
	}

	private static int checkCut(Node nod, int numCuts) {
		if (isEven(countChilds(nod, 0) + 1)) numCuts++;
		if (nod.childs == null) return numCuts;
		if (nod.childs != null) {
			for (Node n : nod.childs) {
				numCuts = checkCut(n, numCuts);
			}
		}
		return numCuts;
	}

	private static boolean isEven(int n) { return n % 2 == 0;}
	
	private static int countChilds(Node nod, int num) {
		if (nod.childs != null) for (Node n : nod.childs) num = countChilds(n, num) + 1;
		return num;
	}

}

class Node {
	public Integer id;
	public List<Node> childs;
	public Node father; // do not need father in this problem
	
	public Node() { }
	public void addChild(Node n1) {
		if (childs == null) childs = new ArrayList<>();
		childs.add(n1);
	}
	public Node(int id) {this.id = id;}
}
