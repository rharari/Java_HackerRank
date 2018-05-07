/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * BonnieClyde.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Jun 16, 2017
 * 
 * TODO: timeout
 *
 */

package week33;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class BonnieClyde {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) g.addTwoWayVertex(in.nextInt(), in.nextInt());

        for (int i = 0; i < q; i++) {
        	Integer b = in.nextInt();
        	Integer c = in.nextInt();
        	Integer s = in.nextInt();
        	LinkedList<Integer> visited = new LinkedList<Integer>();
        	
        	if (b == c && c == s) {
        		System.out.println("YES");
        	} else if (b == s) {
            	LinkedList<LinkedList<Integer>> lst = new LinkedList<>();
            	boolean[] marked = new boolean[n + 1];
            	marked[c] = true;
            	visited.add(c);
            	try {
            		depthFirst(g, visited, s, marked, lst, null, true);
            		System.out.println("NO");
            	} catch (Exception e) {
            		System.out.println(e.getMessage());
            	}
        	} else if (c == s) {
            	LinkedList<LinkedList<Integer>> lst = new LinkedList<>();
            	boolean[] marked = new boolean[n + 1];
            	marked[b] = true;
            	visited.add(b);
            	try {
            		depthFirst(g, visited, s, marked, lst, null, true);
            		System.out.println("NO");
            	} catch (Exception e) {
            		System.out.println(e.getMessage());
            	}        		
        	} else {
	            try {
	            	LinkedList<LinkedList<Integer>> lst = new LinkedList<>();
	            	boolean[] marked = new boolean[n + 1];
	            	marked[b] = true;
	            	marked[c] = true;
	            	visited.add(b);
	            	depthFirst(g, visited, s, marked, lst, null, false);
	            	if (lst.size() == 0) {
	            		System.out.println("NO");
	            	} else {
		            	marked = new boolean[n + 1];
		            	marked[c] = true;
		            	marked[b] = true;
		            	visited = new LinkedList<Integer>();
		            	visited.add(c);
		            	LinkedList<LinkedList<Integer>> lst1 = new LinkedList<>();
		            	depthFirst(g, visited, s, marked, lst1, lst, false);
		            	System.out.println("NO");
	            	}
	            } catch (Exception e) {
	            	System.out.println(e.getMessage());
	            }
        	}
        }
        in.close();
	}

    private static void depthFirst(Graph graph, LinkedList<Integer> visited, Integer end, boolean[] marked, LinkedList<LinkedList<Integer>> lst, LinkedList<LinkedList<Integer>> lst2, boolean findOne) throws Exception {
        LinkedList<Integer> nodes = graph.adjacentNodes(visited.getLast());
        if (nodes == null) return;
        for (Integer node : nodes) {
            if (marked[node]) {
            	continue;
            } else if (node == end) {
            	if (findOne) throw new Exception("YES");
            	//visited.add(node);
                if (lst2 == null) {
                	lst.add((LinkedList<Integer>)visited.clone());
                } else {
                	for (LinkedList<Integer> l : lst2) {
                		if (Collections.disjoint(l, visited)) throw new Exception("YES");
                	}
                }
                //visited.removeLast();
            } else {
                visited.addLast(node);
                marked[node] = true;
	            depthFirst(graph, visited, end, marked, lst, lst2, findOne);
	            visited.removeLast();
            }
        }
    }

    private static void printPath(LinkedList<Integer> visited) {
        for (Integer node : visited) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}


class Graph {
	
	private LinkedList<Integer>[] map;
	public Graph(int n) {
		map = new LinkedList[n + 1];
	}
	public boolean found = false;
	
    //private Map<Integer, LinkedHashSet<Integer>> map = new HashMap<Integer, LinkedHashSet<Integer>>();
    
    public void addEdge(Integer node1, Integer node2) {
    	if (map[node1] == null) map[node1] = new LinkedList<>();
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