/**
 * Technique TI Ltd - Brazil
 * HackerRank
 *
 *
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 *
 */

package week32;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class S2 {
	
	public static void main(String[] args) {
		TreeSet<Box> boxes = new TreeSet<Box>();
        Scanner in = new Scanner(System.in);
        // n,m 1..100
        int n = in.nextInt(); // colors
        int m = in.nextInt(); // number of boxes
        int[] a = new int[n]; // number of balls of each color
        int[] c = new int[m]; // box capacity in # of balls
        int[][] b = new int[n][m]; // qtdy gain of candies per box
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        for (int i = 0; i < m; i++) c[i] = in.nextInt();
        for (int i = 0; i < n; i++) {
        	int[] gain = new int[m];
        	for (int j = 0; j < m; j++) {
        		b[i][j] = in.nextInt();
        	}        	
        }
        
        int total = 0;
        
        int totalBalls = Arrays.stream(a).sum();
        while (totalBalls > 0) {
        	for (int ball = 0; ball < n; ball++) {
        		int qtd = a[ball];
        		if (qtd > 0) {
        			while (true) {
	        			int[] gain = b[ball];
	        			int j = findMax(gain);
	        			if (j == -1) break;
        			}
        		}
        	}
        }

	}
	
	static int findMax(int[] lst) {
		int max = -100;
		int pos = -1;
		for (int i = 0; i < lst.length; i++) {
			if (max < lst[i]) {
				max = lst[i]; pos = i;
			}
		}
		return pos;
	}
	
	class Ball {
		int color;
		int qtd;
	}
	
	class Box implements Comparator<Box> {
		public Box(int id, int capacity, int[] gain) {
			this.id = id;
			this.capacity = capacity;
			this.gain = gain;
			setMaxGain();
		}
		
		int id;
		int capacity;
		int[] gain; // 0..n	per color
		int maxGain;
		
		void setMaxGain() {
			maxGain = Arrays.stream(gain).max().getAsInt();
		}
		
		
		@Override
		public int compare(Box o1, Box o2) {
			int i1 = o1.capacity > 0 ? 1 : 0;
			int i2 = o1.capacity > 0 ? 1 : 0;
			return o1.maxGain * i1 - o2.maxGain * i2;
		}
		
		
	}

}
