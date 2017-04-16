/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaException2.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-exception-handling
 *
 */

package samples.java;

import java.util.Scanner;

class MyCalculator {
	long power(int i, int j) throws Exception {
		if (i < 0 || j < 0) throw new Exception("n and p should be non-negative");
		return (long)Math.pow(i, j);
	}
}

public class JavaException2 {
	
	 public static void main(String[] args) {
		  Scanner in = new Scanner(System.in);
		  while ( in .hasNextInt()) {
		   int n = in .nextInt();
		   int p = in .nextInt();
		   MyCalculator my_calculator = new MyCalculator();
		   try {
		    System.out.println(my_calculator.power(n, p));
		   } catch (Exception e) {
		    System.out.println(e);
		   }
		   in.close();
		  }
	}
}
