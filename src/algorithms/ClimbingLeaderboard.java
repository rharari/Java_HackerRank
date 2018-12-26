/**
 * HackerRank
 * ClimbingLeaderboard.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date 18 Dec 2018
 *
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
 *
 */

package algorithms;

public class ClimbingLeaderboard {

	// Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int position = 1;
		int lenScore = scores.length;
		int iAlice = alice.length - 1;
		int rank[] = new int[alice.length];
		int i = 0;
		int currRank = iAlice;
		while (true) {
			int score = scores[i];
			if (alice[iAlice] >= score) {
				rank[currRank--] = position;
				iAlice--;
			} else {
				position++;
				while (++i < lenScore && scores[i] == score);
			}
			if (iAlice == -1 || i == lenScore) break;
		}
		while (iAlice-- > -1) rank[currRank--] = position;
		return rank;
	}

	public static void main(String[] args) {
		int scores[] = {100,100,50,40,40,20,10};
		int alice[] = {5,25,50,120};
		int ret[] = climbingLeaderboard(scores, alice);
		for (int i : ret) {
			System.out.println(i);
		}
	}

}
