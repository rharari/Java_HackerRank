/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * Test2.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Dec 7, 2017
 *
 */

package amazon;

import java.util.LinkedList;
import java.util.List;

public class Test2 {

    public static void main(final String[] args) {
		final String str = "awaglknagawunagwkwagl"; // 4
    		// final String str = "abcbcd";
		final List<String> lst = subStringsKDist(str, 4);
		for (final String s : lst) {
			System.out.print(s + ", ");
		}
	}

	public static List<String> subStringsKDist(final String inputStr, final int num) {
		final LinkedList<String> lst = new LinkedList<>();
		if (inputStr == null || inputStr.length() == 0) return lst; // return empty lst when inputstr is empty
		if (num == 0) return lst; // return empty lst when num = 0
		final int len = inputStr.length();
		final char[] lstChar = inputStr.toCharArray();
		for (int i = 0; i < len - num + 1; i++) {
			final int[] arr = new int[256]; // TODO: only ascii range chr(0) -> chr(256) - can broke if using large chars sets
			boolean dupl = false;
			for (int j = 0; j < num; j++) {
				final int idx = lstChar[i + j];
				if (arr[idx] == 1) {
					dupl = true; break;
				}
				arr[idx] = 1;
			}
			if (! dupl) {
				final String s = inputStr.substring(i, i + num);
				if (!lst.contains(s)) lst.add(s);
			}
		}
        return lst;
    }

}
