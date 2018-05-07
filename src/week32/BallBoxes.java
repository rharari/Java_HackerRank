/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * BallBoxes.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date May 22, 2017
 *
 */

package week32;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class BallBoxes {

	public static void main(String[] args) {
		
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] balls = new int[n];
        int m = in.nextInt();
        Capacity[] capacity = new Capacity[m];
        Capacity[] exceed = new Capacity[m];
        int[][] values = new int[n][m];
        
        int totalballs = 0;
        int total = 0;
        
        for (int i = 0; i < n; i++) {
        	balls[i] = in.nextInt();
        	totalballs += balls[i];
        }
        for (int i = 0; i < m; i++) {
        	capacity[i] = new Capacity(in.nextInt());
        	exceed[i] = new Capacity(0);
        }
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		values[i][j] = in.nextInt();
        	}
        }

        // mount boxes
        LinkedList<Box> boxes = new LinkedList<>();
        LinkedList<Box> boxesfull = new LinkedList<>();
        for (int j = 0; j < m; j++) {
        	Box next = null;
        	Box prev = null;
        	for (int i = 0; i < n; i++) {
            	Box box = new Box(j + " , " + i);
            	if (prev != null) prev.next = box;
            	box.capacity = capacity[j];
            	box.exceed = exceed[j];
        		box.value = values[i][j];
        		box.idball = i;
        		box.prev = prev;
        		if (box.value > 0) {
        			boxes.add(box);
        		} else {
        			boxesfull.add(box);
        			box.used = true;
        		}
            	prev = box;
        	}
        }
        
        Collections.sort(boxes);
        while(true) {
        	if (totalballs == 0) break;
        	if (boxes.size() == 0) break;
        	LinkedList<Box> tmplst = new LinkedList<>();
        	Box nxt = boxes.removeFirst();
        	int value = nxt.getValue();
        	if (value <= 0) break;
        	tmplst.add(nxt);
        	while (true) {
        		if (boxes.size() == 0 || boxes.get(0).getValue() != value) break;
        		tmplst.add(boxes.removeFirst());
        	}
        	nxt = getNextBox(tmplst, balls);
        	total += nxt.value;
        	totalballs--;
        	balls[nxt.idball]--;
        	boolean sort = false;
        	if (nxt.capacity.size > 0) {
        		nxt.capacity.size--;
        	} else {
        		nxt.exceed.size++;
        		sort = true;
        	}
        	nxt.used = true;
        	boxesfull.add(nxt);
        	
        	if (tmplst.size() > 0) {
        		boxes.addAll(0, tmplst);
        	}
        	
        	// no more balls of this color
        	if (balls[nxt.idball] == 0) {
        		Box tmp = nxt;
        		while (tmp.prev != null) tmp = tmp.prev;
        		while (true) {
        			if (tmp.next == null) break;
        			tmp = tmp.next;
        			if (!tmp.used) {
        				boxes.remove(tmp);
        				boxesfull.add(tmp);
        				tmp.used = true;
        				sort = true;
        			}
        		}
        	}
        	
        	if (sort) Collections.sort(boxes);
        }
        
        int penalties = 0;
        for (Capacity c : exceed) {
        	penalties += c.size * c.size;
        }
        
        System.out.println(total - penalties);
        
        in.close();
	}
	
	// TODO: does not provide the optimal result - use greedy algorithm and test all possibilities avoiding local minimum!
	private static Box getNextBox(LinkedList<Box> tmplst, int[] balls) {
		if (tmplst.size() == 1) return tmplst.removeFirst();
		int max = -1;
		int pos = -1;
		LinkedList<Integer> ll = new LinkedList<>();
		// max balls
		for (int i = 0; i < tmplst.size(); i++) {
			Box box = tmplst.get(i);
			int qtd = balls[box.idball];
			if (qtd > max) {
				pos = i;
				max = qtd;
				ll.clear(); ll.add(pos);
			} else if (qtd == max) {
				ll.add(i);
			}
		}
		if (ll.size() == 1 || max > 0) return tmplst.remove(ll.getFirst().intValue());
		
		// if more than one with same balls, retrieve with the min of the max box value
		pos = -1;
		int min = 0;
		for (int i : ll) {
			Box box = tmplst.get(i);
			int v = box.getMaxValueUnusedColor();
			if (v > 0 && v < min) {
				pos = i;
				min = v;
			}
		}
		if (pos == -1) pos = 0;
		return tmplst.remove(pos);
	}

	static class Capacity {
		public Capacity(int i) {
			this.size = i;
		}
		public int size;
	}
	
	static class Box implements Comparable<Box> {
		public Box(String id) {
			this.id = id;
		}
		public int getMaxValueUnusedColor() {
			int v = 0;
			Box b = next;
			while (true) {
				if (b == null) return v;
				if (!b.used && b.getValue() > v) v = b.getValue();
				b = b.next;
			}
		}
		
		public String id;
		public int idball;
		public Capacity capacity;
		public Capacity exceed;
		public int value;
		
		public Box next = null; // same color
		public boolean used = false;
		public Box prev = null;
		
		// valor hipotetico
		public int getValue() {
			if (capacity.size == 0) {
				int extra = exceed.size + 1;
				return value - extra * extra + exceed.size * exceed.size;	
			} else {
				return value;
			}
		}
		
		// (value - penalty) * 100 + capacity
		@Override
		public int compareTo(Box o) {
			return ( (o.getValue() * 1000 + o.capacity.size) 
					- 
					 (getValue() * 1000 + capacity.size)
					 );
		}
		
	}
	
}
