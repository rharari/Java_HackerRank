/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaRegex2.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/duplicate-word
 *
 */

package samples.java;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegex2 {

    public static void main(String[] args) {
        String regex = "\\b(\\w+)(\\b\\W+\\b\\1\\b)*";
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());
        
        while (numSentences-- > 0) {
            String input = in.nextLine();
            
            Matcher m = p.matcher(input);
            
            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
            	String s = m.group();
            	if (s.length() > 0) {
            		int i1 = s.indexOf(' ');
            		if (i1 > -1) {
		            	String s1 = s.substring(0, i1);
		                input = input.replaceAll(s, s1);
            		}
            	}
            }
            
            // Prints the modified sentence.
            System.out.println(input);
        }
        
        in.close();
    }
	
}
