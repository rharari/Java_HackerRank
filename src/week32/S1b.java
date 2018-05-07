/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * S1b.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 *
 */

package week32;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class S1b {
	
	static final int MAXN = 300007;
	
	static final Node r1 = new Node();
	static final Node r2 = new Node();
	static final Node[] lst = new Node[MAXN];
	static int curNode;
	static String s;
	static char[] schar;
	static int ptr;
	static int nextObj;
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int len = in.nextInt();
        //String s = in.next();
        String s = "abcbab";
        int len = s.length();
        
        int[] result = new int[len];
        
        Set<String> set = new HashSet<>();
        schar = s.toCharArray();
        init();
        for (int i = 0; i < len; i++) {
            Node node = insert(i);
            if (node != null) {
            	String s0 = s.substring(node.start, node.end + 1);
            	split(s0, set);
            	result[i] = set.size();
            	System.out.println(node.length + " - start:" + node.start + " - end:" + node.end + " - s0=" + s0);
            }
        }
        
        for (int i = 0; i < len; i++) System.out.println(result[i]);        
	}
	
	static void split(String s, Set<String> r) {
		int len = s.length();
		while (true) {
			if (!r.add(s)) return;
			if (len == 1) return;
			len--;
			s = s.substring(0, len);
		}
	}

	static Node insert(int idx) {
	    int tmp = curNode;
	    while (true) {
	        int curLength = lst[tmp].length;
	        if (idx - curLength >= 1 && schar[idx] == schar[idx-curLength-1]) break;
	        tmp = lst[tmp].suffEdge;
	    }

	    if (lst[tmp].next[schar[idx] - 'a'] != 0) {
	        curNode = lst[tmp].next[schar[idx]-'a'];
	        return null;
	    }

	    ptr++;
	    lst[tmp].next[schar[idx] - 'a'] = ptr;
	    assertInitialized(ptr);
	    lst[ptr].length = lst[tmp].length + 2;
	    lst[ptr].end = idx;
	    lst[ptr].start = idx - lst[ptr].length + 1;
	 
	    tmp = lst[tmp].suffEdge;
	    curNode = ptr;
	    if (lst[curNode].length == 1) {
	        lst[curNode].suffEdge = 2;
	        return lst[ptr];
	    }
	    while (true) {
	        int curLength = lst[tmp].length;
	        if (idx-curLength >= 1 && schar[idx] == schar[idx-curLength-1]) break;
	        tmp = lst[tmp].suffEdge;
	    }
	    lst[curNode].suffEdge = lst[tmp].next[schar[idx]-'a'];
	    return lst[ptr];
	}
	
	private static void assertInitialized(int i) {
		while (i >= nextObj) lst[nextObj++] = new Node();
	}

	static void init() {
	    r1.length = -1; r1.suffEdge = 1;
	    r2.length = 0; r2.suffEdge = 1;
	    lst[1] = r1;
	    lst[2] = r2;
	    nextObj = 3;
	    ptr = 2;
	    curNode = 1;
	}
	
	static class Node {
		int length;
		int start;
		int end;
		int suffEdge;
		int[] next = new int[26];
	}
	
}

