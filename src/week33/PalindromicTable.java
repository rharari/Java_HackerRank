/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * 
 * https://www.hackerrank.com/contests/w33/challenges/palindromic-table
 * PalindromicTable.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Jun 15, 2017
 *
 */

package week33;

import java.util.Scanner;

public class PalindromicTable {

    public static void main(String[] args) {
    	int[][] table;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        table = new int[n][m];
        for(int table_i = 0; table_i < n; table_i++){
            for(int table_j = 0; table_j < m; table_j++){
                table[table_i][table_j] = in.nextInt();
            }
        }
        findMaxSubMatrix(table);
        in.close();
    }
    
    /**
     * will be a valid palindrome if and only if
     * - there is 0 or 1 element with odd quantity
     * - there is at least 1 pair of element greater than zero
     */
    private static int check(int[] arr) {
    	int numZeros = arr[0];
    	int total = numZeros;
    	boolean hasOdd = numZeros % 2 != 0;
    	boolean hasPair = false;
    	for (int i = 1; i < 10; i++) {
    		int qtd = arr[i];
    		if (qtd % 2 != 0) {
    			if (hasOdd) return 0;
    			hasOdd = true;
    		}
    		if (qtd > 1) hasPair = true;
    		total += qtd;
    	}
    	if (!hasPair) return total == 1 ? 1 : 0;
    	return total;
    }
    

    // brute force
    public static void findMaxSubMatrix(int[][] a) {
        int cols = a[0].length;
        int rows = a.length;
        int currentResult;
        int maxSum = 0;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        for (int leftCol = 0; leftCol < cols; leftCol++) {
        	if ( (rows) * (cols - leftCol) > maxSum) {
	            for (int rightCol = cols-1; rightCol >= leftCol; rightCol--) {
	            	if ( (rows) * (rightCol - leftCol + 1) > maxSum) {
		            	for (int topRow = 0; topRow < rows; topRow++) {
		            		if ( (rows - topRow) * (rightCol - leftCol + 1) > maxSum) {
		            			int[] tmp = new int[10];
			            		for (int bottomRow = topRow; bottomRow < rows; bottomRow++) {
			            			if ( (bottomRow - topRow + 1) * (rightCol - leftCol + 1) > maxSum) {
					                    for (int j = leftCol; j <= rightCol; j++) tmp[a[bottomRow][j]]++;
				                        currentResult = check(tmp);
				                        if (currentResult > maxSum) {
				                        	maxSum = currentResult;
				                        	left = leftCol;right = rightCol;
				                        	top = topRow; bottom = bottomRow;
				                        }
			            			} else for (int j = leftCol; j <= rightCol; j++) tmp[a[bottomRow][j]]++;
			            		}
		            		} 
		            	}
	            	} 
	            }
        	}
        }
        if (maxSum > 0) {
	        System.out.println(maxSum);
	        System.out.println(top + " " + left + " " + bottom + " " + right);
        } else {
        	System.out.println("1\n0 0 0 0");
        }
    }
	
}
