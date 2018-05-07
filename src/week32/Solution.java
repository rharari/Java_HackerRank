/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * Eertree.java
 * 
 * https://www.hackerrank.com/contests/w32/challenges/special-substrings
 * 
 * failed- need optimization
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 *
 */

package week32;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static final int INDEX_EMPTY_STRING = 1;
	public static final int MAX_SIZE = 300007;

	private char[] string;
	private PNode[] tree = new PNode[MAX_SIZE];
	int treeSize = 0;
	private int curLngPalindSuffixNodeIdx;
	static final Set<String> set = new LinkedHashSet<>(500000);

	private void initTree() {
		ImgNode imgStr = new ImgNode();
		tree[imgStr.getIndex()] = imgStr;
		treeSize++;
		PNode emptyString = new EmptyStringPNode(imgStr);
		tree[emptyString.getIndex()] = emptyString;
		treeSize++;
		curLngPalindSuffixNodeIdx = emptyString.getIndex();
	}

	public void addLetter(int letraIndex) {
		Insertion insert = new Insertion(letraIndex);
		PNode lngPrefix = getLongestPalindromePrefixForNextPNode(insert);
		if (isDuplicatePalindrome(insert.letra, lngPrefix)) {
			curLngPalindSuffixNodeIdx = lngPrefix.getOutgoingNodes().get(insert.letra).getIndex();
			return;
		}
		addNewPNode(lngPrefix, insert);
	}

	private void addNewPNode(PNode lngPrefix, Insertion insert) {
		int nextNodeIndex = treeSize;
		PNode newNode = new PNode(nextNodeIndex);
		if (lngPrefix.isImgNode()) {
			String s = "" + insert.letra;
			addWord(s);
			newNode.setLabel(s);
		} else {
			String s = insert.letra + lngPrefix.getLabel() + insert.letra;
			addWord(s);
			newNode.setLabel(s);
		}
		
		tree[treeSize]=newNode;
		treeSize++;

		lngPrefix.getOutgoingNodes().put(insert.letra, newNode);

		if (lngPrefix.isImgNode()) {
			newNode.setLongestPalindromeSuffix(tree[INDEX_EMPTY_STRING]);
		} else {
			PNode suffixForNewNode = getLngSuffixForNewNode(insert, lngPrefix);
			newNode.setLongestPalindromeSuffix(suffixForNewNode);
		}

		curLngPalindSuffixNodeIdx = newNode.getIndex();
	}

	private boolean isDuplicatePalindrome(char letra, PNode lngSuffixForNextPalindrome) {
		return lngSuffixForNextPalindrome.getOutgoingNodes().containsKey(letra);
	}

	private PNode getLongestPalindromePrefixForNextPNode(Insertion insert) {
		PNode lngPrefix = tree[curLngPalindSuffixNodeIdx];
		while (needTraversing(insert, lngPrefix)) lngPrefix = lngPrefix.getLngSuffix();
		return lngPrefix;
	}

	private PNode getLngSuffixForNewNode(Insertion insert, PNode lngPrefix) {
		PNode suffixForNewNode = lngPrefix.getLngSuffix();
		while (needTraversing(insert, suffixForNewNode)) {
			suffixForNewNode = suffixForNewNode.getLngSuffix();
		}
		suffixForNewNode = suffixForNewNode.getOutgoingNodes().get(insert.letra);
		return suffixForNewNode;
	}

	private boolean needTraversing(Insertion insert, PNode currentSuffix) {
		if (currentSuffix.isImgNode()) return false;
		int idxSuffix = insert.letraIndex - currentSuffix.getLength() - 1;
		return (idxSuffix < 0 || insert.letra != string[idxSuffix]);
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int[] result = propertyOfString(s, n);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        in.close();
    }

	public static int[] propertyOfString(String str, int n) {
		int[] ret = new int[str.length()];
		Solution o = new Solution();
		o.string = str.toCharArray();
		o.initTree();
		for (int i = 0; i < o.string.length; i++) {
			o.addLetter(i);
			ret[i] = set.size();
		}
		return ret;
	}

	private static void addWord(String label) {
		for (int i = label.length(); i > 0; i--) {
			if (!set.add(label.substring(0,  i))) return;
		}
	}

	class Insertion {
		final char letra;
		final int letraIndex;
		public Insertion(int letraIndex) {
			this.letraIndex = letraIndex;
			this.letra = string[letraIndex];
		}
	}

	class PNode {
		String label;
		int labelLen;
		int index;
		PNode lngSuffix;
		Map<Character, PNode> outgoingNodes = new HashMap<>();
		
		protected PNode(int index) {
			this.index = index;
		}
		public boolean isImgNode() {
			return false;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getLength() {
			return labelLen;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
			this.labelLen = label.length();
		}
		public PNode getLngSuffix() {
			return lngSuffix;
		}
		public void setLongestPalindromeSuffix(PNode lngSuffix) {
			this.lngSuffix = lngSuffix;
		}
		public Map<Character, PNode> getOutgoingNodes() {
			return outgoingNodes;
		}
		public void setOutNodes(Map<Character, PNode> outgoingNodes) {
			this.outgoingNodes = outgoingNodes;
		}
	}

	class EmptyStringPNode extends PNode {
		public static final int INDEX_EMPTY_STRING = 1;
		public EmptyStringPNode(ImgNode ImgNode) {
			super(INDEX_EMPTY_STRING);
			setLongestPalindromeSuffix(ImgNode);
			setLabel("");
		}
	}

	class ImgNode extends PNode {
		private static final int IDX_IMG_STR = 0;
		public ImgNode() {
			super(IDX_IMG_STR);
		}
		public String getLabel() {
			return "-1";
		}
		public PNode getLngSuffix() {
			return this;
		}
		public int getLength() {
			return -1;
		}
		public boolean isImgNode() {
			return true;
		}
	}

}
