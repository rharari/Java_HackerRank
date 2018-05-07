/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * https://www.hackerrank.com/contests/w32/challenges/geometric-trick
 * fail!!! give correct result but need optimization
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 *
 */

package week32;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class S1 {

    public static void main(String[] args) {
    	mainTree();
    	if (true) return;
        Scanner in = new Scanner(System.in);
        //int n = in.nextInt();
        //String s = in.next();
        String s = "bccbbbbc";
        int n = s.length();
        int[] result = propertyOfString(s);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");
    }
    
    static int[] propertyOfString(String s){
    	int n = s.length();
        int[] ret = new int[n];
        Set<String> lst = new HashSet<>();
        for (int i = 1; i <= n; i++) {
        	calculate(s.substring(0,  i), lst, i-1);
        	ret[i-1] = lst.size();
        }
        return ret;
    }

	static void calculate(final String s, Set<String> lst, int start) {
		System.out.print("\n\n--->");
	    for (int i = 0; i < s.length(); i++) {
	        calculate(lst,s,i,i+1);
	    }
	    System.out.print("\n-->>");
	    for (int i = 0; i < s.length(); i++) {
	    	calculate(lst,s,i,i);
	    }         
	}

	static void calculate(final Set<String> lst, final String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    	    String s0 = s.substring(i,j+1);
    	    System.out.print(s0 + "[" + i + "]  ");
    	    boolean recursive = !lst.contains(s0);
            lst.add(s0);
            if (recursive && s0.length() > 1) split(s0, lst);
            i--; j++;
      	}
	}
	
	static final int ALPHA = 26;
	static final int MAXN = 100040;
	
	static void split(String s, Set<String> r) {
		while (true) {
			r.add(s);
			s = s.substring(0, s.length() - 1);
			if (r.contains(s) || s.length() == 1) return;
		}
	}
	
	static void mainTree() {
		String line = "bccbbbbc";
		
		schar = line.toCharArray();
		init(line);
		for (int i = 0; i < line.length(); i++) {
			addLetter(i);
		}
		long ans = 0;
		for (int i = ind-1; i > 1; i--) {
			int link = t[i].link;
			t[link].cnt += t[i].cnt;
			ans += t[i].cnt;
		}
		System.out.println("ans --> " + ans);
	}
	
	static String s;
	static char[] schar;
	static Node[] t = new Node[MAXN];
	static int ind;
	static int last;
	static int n;
	
    static int getLink(int cur){
    	int i = n - t[cur].len - 1;
        while(true) {
        	char c1 = schar[n];
        	int i1 = n - t[cur].len - 1;
        	if (i1 < 0) break;
        	char c2 = schar[i1];
        	if (c1 != c2) break;
        	cur = t[cur].link;
        }
        return cur;
    }
    
    static void addLetter(int pos) {
    	n = pos;
    	int let = schar[pos] - 'a'; // TODO
    	last = getLink(last);
    	if (t[last].next[let] == 0) { // TODO
            t[ind].len = t[last].len + 2;
            t[ind].link = t[getLink(t[last].link)].next[let];
            t[ind].half_link = t[getLink(t[last].half_link)].next[let];
            while(t[t[ind].half_link].len * 2 > t[ind].len) {
                t[ind].half_link = t[t[ind].half_link].link;
            }
            t[last].next[let] = ind++;    		
    	}
        last = t[last].next[let];
        t[last].cnt++;
    }
	
	static void init(String string) {
        s = string;
        int len = s.length();
        for(int i = 0;i<len+4;i++) {
        	t[i] = new Node();
        }
        last = 0;
        t[1].len = -1;
        t[0].len = 0;         
        t[0].link = 1;
        t[0].half_link = 1;
        ind = 2;
        n = 0;
	}
	
	static class Node {
		int[] next = new int[ALPHA];
		int len;
		int link;
		int half_link;
		int cnt = 0;
	}

}
