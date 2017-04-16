/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaBitSet.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-bitset
 *
 */

package samples.java;

import java.util.BitSet;
import java.util.Scanner;

public class JavaBitSet {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int m = sc.nextInt();
    	BitSet b1 = new BitSet(n);
    	BitSet b2 = new BitSet(n);
    	while (m > 0) {
    		String op = sc.next();
    		int i1 = sc.nextInt();
    		int i2 = sc.nextInt();
    		if (op.equals("AND")) {
    			if (i1 == 1) {
    				b1.and(b2);
    			} else {
    				b2.and(b1);
    			}
    		} else if (op.equals("OR")) {
    			if (i1 == 1) {
    				b1.or(b2);
    			} else {
    				b2.or(b1);
    			}
    		} else if (op.equals("XOR")) {
    			if (i1 == 1) {
    				b1.xor(b2);
    			} else {
    				b2.xor(b1);
    			}
    		} else if (op.equals("SET")) {
    			if (i1 == 1) {
    				b1.set(i2);
    			} else {
    				b2.set(i2);
    			}
    		} else if (op.equals("FLIP")) {
    			if (i1 == 1) {
    				b1.flip(i2);
    			} else {
    				b2.flip(i2);
    			}
    		}
    		System.out.println(b1.cardinality() + " " + b2.cardinality());
    	}
    	sc.close();
    }
	
}
