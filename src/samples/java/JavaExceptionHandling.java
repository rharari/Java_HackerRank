/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaExceptionHandling.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Apr 14, 2017
 *
 */

package samples.java;

import java.util.Scanner;

public class JavaExceptionHandling {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	try {
	    	int n = sc.nextInt();
	    	int m = sc.nextInt();
	    	System.out.println((int) (n / m));
    	} catch (java.lang.ArithmeticException e1) {
    		System.out.println("java.lang.ArithmeticException: / by zero");
    	} catch (Exception e) {
    		System.out.println(e.getClass().getName());
    	}
    	sc.close();
    }
}
