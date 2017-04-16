/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaStack.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-stack
 *
 */

package samples.java;

import java.util.Scanner;
import java.util.Stack;

public class JavaStack {

   public static void main(String []argh) {
      Scanner sc = new Scanner(System.in);
      while (sc.hasNext()) {
         String input=sc.next();
         Stack<Character> st = new Stack<Character>();
         char[] lst = input.toCharArray();
         try {
	         for (char c : lst) {
	        	 if (c == '}' || c == ')' || c == ']') {
	        		 check(st, c);
	        	 } else {
	        		 st.add(c);	        		 
	        	 }
	         }
	         System.out.println(st.size() == 0);
         } catch (Exception e) {
        	 System.out.println(false);  	 
         }
      }
      sc.close();
   }

	private static void check(Stack<Character> st, char c) throws Exception {
		char c1 = st.pop();
		char c2 = c == '}' ? '{' : c == ']' ? '[' : '(';
		if (c1 != c2) throw new Exception("oops");
	}
	
}
