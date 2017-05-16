/**
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date May 11, 2017
 * given an array of array of string, print the repeated elements in all rows, ex:
 *  [
 *  	[ a, b, c, d, e, f],
 *  	[ a, b, b, c, d],
 *  	[ a, a, a, a, b],
 *  	[ a, b, j, k ]
 *  ]
 *  
 *  will print a and b since all rows contains those elements
 *
 */

package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class RepeatedWords {

	static String[] getBigArray(String prefix) {
		String[] ret = new String[10000]; // 10^4 elements
		for (int i = 0; i < 10000; i++) ret[i] = prefix + (int)(Math.random() * 5000);
		ret[0] = "a"; ret[5000] = "b"; ret[5001]="b"; ret[9999] = "c";
		return ret;
	}
	
	static int currentLine = 0;
	static int totalAdd = 0;
	
	public static void main(String[] args) {
		// ** prepare test data **
		LinkedList<String[]> lst = new LinkedList<>();
		lst.add(getBigArray("first "));
		String[] bigarray = getBigArray("others ");
		for (int i = 0; i < 100000; i++) lst.add(bigarray); // 10^5 elements
		// ** expected results -> a,b,c

		long l = System.currentTimeMillis();

		// ** start algorithm ***
		int numlines = lst.size();
		String[] line0 = lst.removeFirst();
		HashMap<String, Wrapper> ht = new HashMap<>(line0.length);
		for (String s : line0) if (ht.get(s) == null) ht.put(s, new Wrapper(s));
		lst.parallelStream().forEach(line1 -> {
			int s0 = ht.size();
			int s1 = line1.length;
			// ht1 is a tmp map to avoid repeated word in same row
			Hashtable<String, String> ht1 = new Hashtable<>(s0 > s1 ? s1 : s0);
			Arrays.asList(line1).parallelStream().forEach(s -> {
				Wrapper w = ht.get(s);
				if (w != null && !ht1.containsKey(s)) { w.add(); ht1.put(s, s); }					
			});
		});
		ht.forEach((key, w) -> {if (w.cnt == numlines) System.out.println(key);});
		System.out.println("# elapsed time " + (System.currentTimeMillis() - l) / 1000.0 + " seconds");
	}

}

class Wrapper {
	public Wrapper(String word) {
		this.word = word;
	}
	synchronized void add() {cnt++;};
	
	public String word;
	public int cnt = 1;
}

