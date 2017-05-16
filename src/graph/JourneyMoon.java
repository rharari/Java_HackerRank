/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JourneyMoon.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/journey-to-the-moon
 *
 */

package graph;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class JourneyMoon {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        Hashtable<Integer, Integer> hp = new Hashtable<>(n);
        int grp = 0;
        while (p-- > 0) {
        	int x1 = in.nextInt();
        	int x2 = in.nextInt();
        	Integer i1 = hp.get(x1);
        	Integer i2 = hp.get(x2);
        	int sum = 0;
        	if (i1 == null && i2 == null) {
        		i1 = grp++;
        		hp.put(x1, grp); sum++;
        		hp.put(x2, grp); sum++;
        	} else if (i1 == null) {
        		hp.put(x1, i2); sum++;
        		i1 = i2;
        	} else if (i2 == null) {
        		hp.put(x1, i1); sum++;
        	}
        	Integer i0 = ht.get(i1);
        	if (i0 == null) i0 = 0;
        	ht.put(i1, i0+sum);
        }
        
        BigInteger total = ht.entrySet().stream().map(Map.Entry::getValue).map(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
        System.out.println(total);
        
        in.close();
	}
	
}
