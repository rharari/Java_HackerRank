/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * SpecialSubtree.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * 
 * https://www.hackerrank.com/challenges/primsmstsub
 *
 */

package samples;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class SpecialSubtree {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Hashtable<Integer, Node> ht = new Hashtable<Integer, Node>();
        for (int i = 0; i < m; i++) {
        	int n1 = in.nextInt();
        	int n2 = in.nextInt();
        	int c = in.nextInt();
        	Node no = ht.get(n1);
        	if (no == null) {
        		ht.put(n1, new Node(n1, n2, c));
        	} else {
        		no.add(n2, c);
        	}
        	no = ht.get(n2);
        	if (no == null) {
        		ht.put(n2, new Node(n2, n1, c));
        	} else {
        		no.add(n1, c);
        	}
        }
        in.nextInt(); // start
        int sum = 0;
        for (int i = 1; i <= n; i++) {
        	Node no = ht.get(i);
        	sum += no.getMin();
        }
        in.close();
        System.out.println(sum);
    }
    
    static class Node {
    	int n1;
    	Hashtable<Integer, Integer> nodes = new Hashtable<>();
    	public Node() {};
    	public Node(int n1, int n2, int c) {
    		this.n1 = n1;
    		add(n2, c);
    	}
    	public void add(int n2, int c) {
    		Integer c0 = nodes.get(n2);
    		if (c0 == null || c0 > c) nodes.put(n2, c);
    	}
    	public int getMin() {
    		return Collections.min(nodes.values());
    	}
    }

}
