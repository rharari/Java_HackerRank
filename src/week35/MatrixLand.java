/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * MatrixLand.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Nov 16, 2017
 *
 */

package week35;

import java.util.Scanner;

public class MatrixLand {

    static int posi = -1;
	static int posf = -1;

	static long getMax(final short[] lst, int pos, final int direction) {
		long max = 0;
		long curr = 0;
		posf = pos;
		final int len = lst.length;
		pos += direction;
		while (pos > -1 && pos < len) {
			final short v = lst[pos];
			curr += v;
			if (curr >= max) {
				max = curr;
				posf = pos;
			}
			pos += direction;
		}
		return max;
	}

	static long kadane(final short[] array0, final short[] array, final int pos0, final int pos1) {
		long max_ending_here = 0;
		long max_so_far = 0;
		posi = 0;
        posf = -1;
		int i = 0;
		boolean reset = true;
		long v0 = 0;
        int posmax = -1;
        long max = 0;
        for (final short v : array) {
	        	if (array0 != null && (i < pos0 || i > pos1)) {
	        		v0 += array0[i];
	        	} else {
	        		v0 = 0;
	        	}
	        final long sum = v + v0;
	        max_ending_here = max_ending_here + sum;
	        if (max < sum) {
	            max = sum;
	            posmax = i;
	        }
	        if (max_ending_here < 0) {
	            	max_ending_here = 0;
	            	reset=true;
	        }
	        if (max_so_far <= max_ending_here) {
	            	max_so_far = max_ending_here;
	            	posf = i;
	            	if (reset) {
	            		posi = i;
	            		reset = false;
	            	}
	        	}
	        i++;
        }
       if (posf == -1) {
           posf = posmax;
           posi = posmax;
           return max;
       }
       return max_so_far;
	}

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        final short[][] A = new short[n][m];
        for (int A_i = 0; A_i < n; A_i++) {
            for (int A_j = 0; A_j < m; A_j++) {
                A[A_i][A_j] = in.nextShort();
            }
        }
        final long result = matrixLand(A, n);
        System.out.println(result);
        in.close();
    }

    static long matrixLand(final short[][] array, final int n) {
		final int x = 0;
		long v = getMax(array[0], x, -1) + array[0][x];
		int pos0 = posf;
		v += getMax(array[0], x, 1);
		int pos1 = posf;

		for (int j = 1; j < n; j++) {
			long v0 = kadane(array[j - 1], array[j], pos0, pos1);
			if (posi > pos1) {
				v0 = getMax(array[j], pos1, -1) + sum(array[j-1], pos1 + 1, posi);
				pos0 = pos1;
				pos1 = posf;
				v0 += getMax(array[j], pos0, 1);
			} else if (posf < pos0) {
				v0 = getMax(array[j], pos1, -1) + sum(array[j-1], posf, pos0 - 1);
				pos1 = pos0;
				pos0 = posi;
			} else {
				System.out.println("posi=" + posi + " - posf=" + posf + "  -- pos0=" + pos0 + " - pos1=" + pos1);
				final int px = posi > pos0 ? posi : posf > pos1 ? posf : pos1;
				v0 = getMax(array[j], px, -1) + array[j][px];
				pos0 = posf;
				v0 += getMax(array[j], px, 1);
				pos1 = posf;
			}
			v += v0;
		}
        return v;
    }

	private static long sum(final short[] s, final int i0, final int i1) {
		long total = 0;
		for (int i = i0; i <= i1; i++) total += s[i];
		return total;
	}

}
