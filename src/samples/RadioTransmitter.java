/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * RadioTransmitter.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * 
 * https://www.hackerrank.com/challenges/hackerland-radio-transmitters
 *
 */

package samples;

import java.util.Arrays;
import java.util.Scanner;

public class RadioTransmitter {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for(int x_i=0; x_i < n; x_i++){
            x[x_i] = in.nextInt();
        }
        go(n,k,x);
        in.close();
    }
    
	private static void go(int n, int k, int[] lst) {
		Arrays.sort(lst);
		int[] ctrl = new int[]{-1, 1, -1, 0};
		
		Arrays.stream(lst).forEachOrdered(v ->{
			if (v > ctrl[2]) {
				if (ctrl[1] == 0) {
					if (v == ctrl[0]) {
						ctrl[0] = v + k; ctrl[1] = 1;
					} else if (v > ctrl[0]) {
						ctrl[0] = ctrl[2] + k;
						if (v > ctrl[0]) {
							ctrl[3]++;
							ctrl[0] = v + k;
						} else {
							ctrl[1] = 1;
						}
					}
				} else {
					if (v > ctrl[0]) {
						ctrl[3]++; 
						ctrl[1] = 0;
						ctrl[0] = v + k;
					}
				}
			}
			ctrl[2] = v;	
		});
		System.out.println(ctrl[3]);
	}

}
