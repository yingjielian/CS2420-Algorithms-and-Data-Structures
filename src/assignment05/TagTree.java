package assignment05;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author [ Yingqi Song & Yingjie Lian ]
 * @version [ Oct 24 2017]
 *
 */
public class TagTree {
	private Node root;
	Scanner sc;
	// private ArrayList<Node> child;
	int numChildren = 0;
	int numMaxChildren = 0;

	static class Node {
		public String data;
		private ArrayList<Node> child;

		// Scanner scanner = new Scanner(child);

		public Node(String _data) {
			this.data = _data;
			this.child = new ArrayList<Node>();

		}

	}
/**
 * This method is constructor
 * @param sc
 */
	public TagTree(Scanner sc) {
		this.sc = sc;
		root = parse(sc, sc.next());
		sc.close();

	}
/**
 * This method to check open tag if equal return true
 * otherwise return false 
 * @param tag
 * @return
 */
	public boolean checkOpenTag(String tag) {
		String a = "<";
		String b = "/";
		if ((tag.substring(0, 1)).equals(a)) {
			if (!((tag.substring(1, 2)).equals(b))) {
				return true;
			}
			numChildren++;

		}
		return false;

	}
/**
 * This is the help method ,help you parse a tree/subtree.  
 * It will return a node that is the root of a subtree that you just parsed. 
 * @param sc
 * @param openTag
 * @return 
 */
	private Node parse(Scanner sc, String openTag) {
		String first = openTag;
		String element = sc.next();

		Node kid = new Node(element.substring(0, element.length() - 1));

		String nextToken;

		while (true) {
			nextToken = sc.next();
			if (!checkOpenTag(nextToken)) {
				numMaxChildren++;
				return kid;

			}
			numMaxChildren = 0;

			kid.child.add(parse(sc, nextToken));

		}
	}
/**
 * This is the help method to get height by recursion
 * @param temp
 * @return
 */
	static private int getHeight(Node temp) {

		if (temp.child.size() == 0) {
			return 0;
		}
		int maxHeight = 0;
		for (Node n : temp.child) {
			maxHeight = Math.max(maxHeight, getHeight(n));
		}
		return maxHeight + 1;
	}
/**
 * This method to getHeight of Tree
 * @return number of height 
 */
	public int getHeight() {

		return getHeight(root);
	}
/**
 * This is the help method to getMaxDegree
 * @param temp
 * @return return the number of 
 */
	static private int getMaxDegree(Node temp) {

		if (temp.child.size() == 0) {
			return 0;
		}
		int maxDegree = 0;

		int tempDegree = 0;
		for (Node n : temp.child) {
			tempDegree++;
			maxDegree = Math.max(tempDegree, getMaxDegree(n));
		}
		return maxDegree;
	}
/**
 * This is getMaxDegree method by call help method 
 * @return number of the maxDegree
 */
	public int getMaxDegree() {

		return getMaxDegree(root);
	}
/**
 * This is the help method to print the node names in prefix order (pre-order traversal order)
 * @param temp
 * @return
 */
	static private String outputPrefix(Node temp) {

		if (temp.child.size() == 0) {
			return temp.data;
		}

		String outPut = temp.data;
		for (Node n : temp.child) {
			outPut += " ";

			outPut += outputPrefix(n);

		}

		return outPut;
	}
/**
 * this is the method outputPrefix
 * @return node names in prefix order (pre-order traversal order)
 */
	public String outputPrefix() {

		return outputPrefix(root);
	}
/**
 * This is the help method outputPostfix
 * @param temp
 * @return
 */
	static private String outputPostfix(Node temp) {

		if (temp.child.size() == 0) {
			return temp.data;
		}
		String outPut = "";
		for (Node n : temp.child) {
			outPut += outputPostfix(n);
			outPut += " ";
		}
		return outPut + temp.data;
	}
/**
 * outputPostfix call help method 
 * @return
 */
	public String outputPostfix() {
		return outputPostfix(root);

	}
/**
 * This is the boolean method ,if isBinaryTree return true otherwise return false
 * @return
 */
	public boolean isBinaryTree() {
		if (root.child.size() == 0) {
			return true;
		}
		if (getMaxDegree() <= 2) {
			return true;
		}
		return false;

	}
/**
 * This is the boolean method ,if isTwoTree return true otherwise return false
 * @param temp
 * @return
 */
	public boolean isTwoTree(Node temp) {
		if (!isBinaryTree())
			return false;
		boolean isTwoTree = false;
		if (temp.child.size() == 0 || temp.child.size() == 2) {
			isTwoTree = true;
			for (Node n : temp.child)
				if (!isTwoTree(n)) {
					isTwoTree = false;
					break;
				}
		}

		return isTwoTree;

	}
/**
 * This is the boolean method ,if isTwoTree return true otherwise return false
 * @return
 */
	public boolean isTwoTree() {

		return isTwoTree(root);
	}
/**
 * This is the boolean method ,if isFullBinaryTree return true otherwise return false
 * @return
 */
	public boolean isFullBinaryTree() {
		if (root.child.size() == 0) {
			return true;
		}
		if (isTwoTree() && (numChildren == (int) (Math.pow(2, getHeight() + 1) - 1))) {
			return true;
		}
		return false;
	}
/**
 * This is the help method earches for the node who's name equals the String and returns the depth of that node. 
 *  If the String is not the name of any node, -1 is returned.  If the String is the name of more than one node, 
 * the depth of the node that occurred first in the tag data is returned.
 * @param temp
 * @param data
 * @param level
 * @return
 */
	static private int findDepth(Node temp, String data, int level) {
		int num = -1;

		if ((temp.data).equals(data)) {
			return level;
		}
		if (!((temp.data).equals(data))) {
			for (Node n : temp.child) {
				num = findDepth(n, data, level + 1);
				if (num != -1) {
					return num;
				}
			}
		}
		return -1;
	}
/**
 * This method call the help method 
 * @param data
 * @return a number of your data
 */
	public int findDepth(String data) {

		return findDepth(root, data, 0);
	}

}