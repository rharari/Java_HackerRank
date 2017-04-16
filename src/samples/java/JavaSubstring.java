/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaSubstring.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-substring
 *
 */

package samples.java;

import java.util.Scanner;

public class JavaSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        System.out.println(S.substring(start, end));
        in.close();
    }
}
