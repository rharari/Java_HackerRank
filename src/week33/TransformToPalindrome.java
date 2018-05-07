/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * PatternCount.java
 * https://www.hackerrank.com/contests/w33/challenges/transform-to-palindrome
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Jun 14, 2017
 *
 */

package week33;

import java.util.Scanner;

public class TransformToPalindrome {
    private static int[] arr;  

    private static void initialize(int n) {
    	arr = new int[n + 1];
    	for (int i = 0; i <= n; i++) arr[i] = i;
    }
    
    private static int find(int pos, int posstart) {
    	if (arr[pos] == pos) {
    		arr[posstart] = pos; 
    		return pos;
    	}
    	return find(arr[pos], posstart);
    }
    
	private static int maxPalindromeSize(int[] lst){
		int size = lst.length;
		int [][]matrix = new int[size][size]; 
		for (int i = 0; i < size; i++) matrix[i][i] = 1;
		
		for (int sublen = 2; sublen <= size; sublen++){
			for (int i = 0; i <= matrix.length - sublen; i++){
				int j = i + sublen - 1;
				if (lst[i] == lst[j]){
					matrix[i][j] = sublen==2 ? 2 : matrix[i+1][j-1] + 2;
				} else{
					matrix[i][j] = Math.max(matrix[i+1][j], matrix[i][j-1]);
				}
			}
		}
		return matrix[0][matrix.length-1];
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        initialize(in.nextInt());
        int count = in.nextInt();
        int m = in.nextInt();
        while (--count >= 0) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (p > q) {
            	link(q, p);
            } else if (p < q) {
            	link(p, q);
            }
        }
        int[] lst = new int[m];
        while (--m >= 0) {
        	int k = in.nextInt();
        	lst[m] = find(k, k);
        }
        System.out.println(maxPalindromeSize(lst));
    }

	private static void link(int x, int y) {
		while (true) {
			if (arr[y] == y || arr[y] == x) {
				arr[y] = x;
				break;
			}
			y = arr[y];
			if (y < x) {
				int x1 = x;
				x = y; y = x1;
			}
		}
	}
}
