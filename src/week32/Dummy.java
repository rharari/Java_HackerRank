/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * dummy.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date May 21, 2017
 *
 */

package week32;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Dummy {

    private static byte[] concat(byte[]... arrays) {
	    int length = 0;
	    for (byte[] array : arrays) {
	      length += array.length;
	    }
	    byte[] result = new byte[length];
	    int pos = 0;
	    for (byte[] array : arrays) {
	      System.arraycopy(array, 0, result, pos, array.length);
	      pos += array.length;
	    }
	    return result;
	}
	
	public static void main(String[] args) throws Exception {
		String s = "1234567890";
		byte bb1 = 'a';
		byte bb2 = 'b';
		byte[] bb3 = {bb1, bb2};
		while (s.length() < 50000) s += s;
		Set<byte[]> set = new LinkedHashSet<>();
		if (s.length() > 50000) s = s.substring(0, 50000);
		
		System.out.println("start");
		long l = System.currentTimeMillis();
		byte[] c = s.getBytes("US-ASCII");
		System.out.println(System.currentTimeMillis() - l);
		for (int i = 0; i < s.length(); i++) {
			byte[] b1 = Arrays.copyOf(c, i);
			concat(new byte[]{'a'}, b1);
			//set.add(b1);
			//if (!set.contains(b1)) System.out.println("err");
		}
		System.out.println(System.currentTimeMillis() - l);
		

	}

}
