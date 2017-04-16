/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * IceCreamParlor.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/icecream-parlor
 *
 */

package samples;

import java.util.Hashtable;
import java.util.Scanner;

public class IceCreamParlor {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) go(in);     
        in.close();
    }
	
    private static void go(Scanner in) {
    	boolean bypass = false;
        int n = in.nextInt();
        int f = in.nextInt();
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        for (int i = 0; i < f; i++) {
        	Integer c = in.nextInt();
        	if (!bypass && c < n) {
        		Integer dif = n - c;
        		Integer c2 = ht.get(dif);
        		if (c2 != null) {
        			print(c2, i + 1);
        			bypass = true; ht = null;
        		} else if (ht.get(c) == null) {
        			ht.put(c, i + 1);
        		}
        	}        	
        }
    }

	private static void print(int i1, int i2) {
		if (i1 > i2) System.out.println(i2 + " " + i1);
			else System.out.println(i1 + " " + i2);
	}
    
}
