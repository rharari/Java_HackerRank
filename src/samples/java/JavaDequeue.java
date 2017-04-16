/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * JavaDequeue.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * https://www.hackerrank.com/challenges/java-dequeue
 *
 */

package samples.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;

public class JavaDequeue {

    public static void main(String[] args) {
        int max = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Deque<Integer> deque = new ArrayDeque<Integer>(m);
        HashSet<Integer> hash = new HashSet<Integer>(m);
        boolean ok = false;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.addLast(num);
            hash.add(num);
            if (ok || i >= m-1) {
            	int max2 = hash.size();
            	max = max > max2 ? max : max2;
            	if (max == m) break;
        		int k = deque.removeFirst();
        		if (!deque.contains(k)) hash.remove(k);
        		ok = true;
            }
        }
        System.out.println(max);
        in.close();
    }

}
