/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * GeometricTrick.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date May 19, 2017
 * 
 * TODO: timeout, failed!!!
 *
 */

package week32;

import java.util.LinkedList;
import java.util.Scanner;

public class GeometricTrick {

	public static void main(String[] args) {
		int total = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        char[] lst = s.toCharArray();
        for (int i = 0; i < n; i++) {
        	if (lst[i] == 'b') {
        		int j = i + 1;
        		long square = (long)j * j;
        		LinkedList<Integer> l = factors(square, j, n);
                for (int k = 0; k < l.size(); k++) {
                	int num1 = l.get(k).intValue();

                	int num2 = (int)(square/num1);
            		if (num1 <= n && num2 <= n && num2 > 0) {
                		char c1 = lst[num1-1];
                		char c2 = lst[num2-1];
                		if (c1 == 'a' && c2 == 'c') total++;
                			else if (c1 == 'c' && c2 == 'a') total++;
            		}
                	
                }
        	}        	
        };
        System.out.println(total);
        in.close();
	}
	
	static LinkedList<Integer> factors(long k, int j, int n) {
		LinkedList<Integer> ret = new LinkedList<>();
	    for(int i=j-1; i >= 1; i--) {
	    	if (k % i == 0) {
		    	if (k/i <= n) ret.add(i);
	    	}
	    }
	    return ret;
	}
	
}
