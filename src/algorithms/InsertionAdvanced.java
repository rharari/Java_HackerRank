/**
 * HackerRank
 * Insertion Sort Advanced Analysis
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 *
 * https://www.hackerrank.com/challenges/insertion-sort/problem
 *
 */

package algorithms;

import java.util.Scanner;

public class InsertionAdvanced {

	static final int MAX_SIZE = 10000000;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			long[] x = new long[n];
			for(int j=0; j < n; j++){
				x[j] = in.nextLong();
			}
			System.out.println(sort(x));
		}
	}

	private static long query(long[] arr, long i) {
		long i0 = i;
		long s = 0;
		while (i > 0) {
			s += arr[(int)i];
			i = i & i - 1;
		}
		while (i0 < MAX_SIZE) {
			arr[(int)i0] += 1;
			i0 += i0 & -i0;
		}
		return s;
	}

	private static long sort(long[] arr) {
		long tmp[] = new long[MAX_SIZE];
		long total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += i - query(tmp, arr[i]);
		}
		return total;
	}

}
