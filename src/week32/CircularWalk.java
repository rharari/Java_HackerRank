/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * CircularWalk.java
 * https://www.hackerrank.com/contests/w32/challenges/circular-walk
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date May 18, 2017
 *
 */

package week32;

import java.util.LinkedList;
import java.util.Scanner;

public class CircularWalk {
	
	public static void main(String[] args) {
		System.out.println(find());
	}
	
	private static int find() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int px = in.nextInt();
        int py = in.nextInt();
        if (px == py) {
        	return 0;
        }
        int r0 = in.nextInt();
        int g = in.nextInt();
        int seed = in.nextInt();
        int p = in.nextInt();
		int[] lst = new int[n];
		lst[0] = r0;
		for (int i = 1; i < lst.length; i++) {
			int r1 = (r0 * g + seed) % p;
			lst[i] = r1;
			r0 = r1;
		}
		LinkedList<Wrapper> ll = new LinkedList<>();
		ll.addLast(new Wrapper(px, 0, true, true));		
		int timer = 0;
		while (true) {
			if (ll.size() == 0) return -1;
			Wrapper w = ll.removeFirst();
			px = w.pos;
			timer = w.timer + 1;
			int dl = lst[px];
			lst[px] = -1;
			int s0 = px - dl;
			int max = 0;
			int max2 = 0;
			int delta = 0;
			int delta2 = 2 * dl;
			int posX = 0;
			int posX2 = 0;
			if (w.left) {
				for (int i = s0; i < px; i++) {
					int pos = getPos(i, n);
					if (pos == py) return timer;
					int lpos = lst[pos];
					if (lpos > 0) {
						int dis = lpos - delta;
						boolean b = false;
						if (dis > max) {
							posX = pos;
							max = dis;
							b = true;
						} 
						dis = lpos - delta2;
						if (dis > max2 && dis > 0) {
							posX2 = pos;
							max2 = dis;
							b = true;
						} 
						if (!b) lst[pos] = -1;
					}
					delta2++;
					delta++;
				}
				if (max > 0) ll.addLast(new Wrapper(posX, timer, true, false));
				if (max2 > 0) {
					if (posX2 != posX) {
						ll.addLast(new Wrapper(posX2, timer, false, true));
					} else {
						ll.getLast().right = true;
					}
				}
			}
			
			// right side
			s0 = px + dl;
			max = max2 = 0;
			delta = 0;
			delta2 = 2 * dl;
			int posR = 0;
			int posR2 = 0;
			if (w.right) {
				for (int i = s0; i > px; i--) {
					int pos = getPos(i, n);
					if (pos == py) return timer;
					int lpos = lst[pos];
					if (lpos > 0) {
						int dis = lpos - delta;
						boolean b = false;
						if (dis > max && dis > 0) {
							posR = pos;
							max = dis;
							b = true;
						} 
						dis = lpos - delta2;
						if (dis > max2) {
							posR2 = pos;
							max2 = dis;
							b = true;
						}
						if (!b)	lst[pos] = -1;
					}
					delta2++;
					delta++;
				}
				if (max > 0) ll.addLast(new Wrapper(posR, timer, false, true)); 
				if (max2 > 0) {
					if (posR == posR2) {
						ll.getLast().right = true;
					} else {
						ll.addLast(new Wrapper(posR2, timer, true, false));
					}					
				}
			}
		}
	}
	
	private static int getPos(int x, int n) {
		if (x < 0) return n + x;
		if (x >= n) return x - n;
		return x;
	}
	
	static class Wrapper {
		public Wrapper(int pos, int timer) {
			this.pos = pos;
			this.timer = timer;
		}
		public Wrapper(int pos, int timer, boolean left, boolean right) {
			this.pos = pos;
			this.timer = timer;
			this.left = left;
			this.right = right;
		}
		boolean left = false;
		boolean right = false;
		int timer;
		int pos;
	}
	
}


