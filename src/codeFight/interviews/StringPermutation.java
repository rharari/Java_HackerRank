/**
 * Technique TI Ltd - Brazil
 * StringPermutation.java
 * 
 * string permutations @ codefights
 * 
 * https://codefights.com/interview/fwMMv4mASRuhxPzcP/topics/combinatorics/description
 * 
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * 
 */

package codeFight.interviews;

import java.util.ArrayList;
import java.util.Hashtable;

public class StringPermutation {

	public static void main(String[] args) {
		StringPermutation o = new StringPermutation();
		String[] result = o.stringPermutations("abc");
		for (String s : result) System.out.println(s);
	}
	
	String[] stringPermutations(String s) {
		Hashtable<String, String> ht = new Hashtable<>();
		char[] lst = s.toCharArray();
		doPermutations()
		
		/**
perms([]) -> [[]];
perms(L)  -> [[H|T] || H <- L, T <- perms(L--[H])].
		 */
		
	}
	
}
