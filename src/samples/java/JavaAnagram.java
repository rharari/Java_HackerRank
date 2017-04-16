/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaAnagram.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-anagrams
 *
 */

package samples.java;

import java.util.Arrays;
import java.util.Scanner;

public class JavaAnagram {

    static boolean isAnagram(String a, String b) {
    	char[] la = a.toLowerCase().toCharArray();
    	char[] lb = b.toLowerCase().toCharArray();
        Arrays.sort(la); Arrays.sort(lb);
        return Arrays.toString(la).equals(Arrays.toString(lb));
    }
	
    public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    String a = scan.next();
	    String b = scan.next();
	    scan.close();
	    boolean ret = isAnagram(a, b);
	    System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
