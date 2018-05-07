/**
 * HackerRank challenge
 * TreeHeightBinaryTree.java
 *
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Apr 1, 2018
 *
 */

package data_structure;

import java.util.Scanner;

class Node {
    Node left;
    Node right;
    int data;

    Node(final int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class TreeHeightBinaryTree {
	static int calc(final Node n1, final int sum) {
		if (n1 == null) return sum;
		final int left = calc(n1.left, sum + 1);
		final int right = calc(n1.right, sum + 1);
		return left > right ? left : right;
	}

	static int height(final Node root) {
      	final int left = calc(root.left, 0);
		final int right = calc(root.right, 0);
		return left > right ? left : right;
    }

	public static Node insert(final Node root, final int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(final String[] args) {
    		// sample data: 7 3 2 1 5 4 6 7 -> result: 3
        final Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            final int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        final int height = height(root);
        System.out.println(height);
    }
}