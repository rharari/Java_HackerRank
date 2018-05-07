/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * Test1.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Dec 7, 2017
 *
 */

package amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test1 {

    static List<Integer> lengthEachScene(final List<Character> inputList) {
        final List<Integer> ret = new LinkedList<>();
        boolean start = false;
        int num2 = 0;
        int itmp = 0;
        List<Character> lsttmp = null;
        for (int i = 0; i < inputList.size(); i++) {
        		final Character c = inputList.get(i);
        		if (!start) {
        			num2 = inputList.lastIndexOf(c);
        			itmp = i - 1;
        			if (num2 == i) {
        				ret.add(1);
        			} else {
        				start = true;
            			lsttmp = new LinkedList();
            			lsttmp.add(c);
        			}
        		} else {
        			if (i < num2) {
        				lsttmp.add(c);
        				final int num3 = inputList.lastIndexOf(c);
        				if (num3 > num2) num2 = num3;
        			} else if (!lsttmp.contains(c)) {
        				if (inputList.lastIndexOf(c) > num2) {
        					i = num2;
        					ret.add(num2 - itmp);
        					start = false;
        				} else {
        					lsttmp.add(c);
        				}
        			} else if (i == num2) {
    					i = num2;
    					ret.add(num2 - itmp);
    					start = false;
        			}
        		}
        }
        return ret;
    }

	public static void main(final String[] args) {
	    	// final Character[] c = new Character[] { 'a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h','k', 'l', 'i', 'j'};
	    	final Character[] c = new Character[] { 'a', 'b', 'c'};
	    final List<Integer> lsd = lengthEachScene(Arrays.asList(c));
	    for (final int i : lsd) {
	    		System.out.println(i);
	    }
	}

}
