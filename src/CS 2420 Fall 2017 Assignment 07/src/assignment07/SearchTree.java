package assignment07;

import java.util.*;

/**
 * <p>
 * A generic implementation of a binary search tree. The search tree stores
 * references to data objects. This search tree allows and keeps multiple
 * 'equal' data objects with different or identical references. (This search
 * tree is suitable for implementing multimaps.)
 * </p>
 * 
 * <p>
 * When the search tree is constructed, a comparator must be provided for
 * comparing data values.
 * </p>
 * 
 * <p>
 * Data objects must be immutable (with respect to comparisons.)
 * </p>
 * 
 * <p>
 * Students will adapt this code so that the search tree implementation uses an
 * AVL tree data structure. This will guarantee O(lg(n)) performance for add,
 * find, and remove operations. (The default implementation only guarantees O(n)
 * performance).
 * </p>
 * 
 * @author Peter Jensen and (Yingjie Lian & Yingqi Song)
 * @version November 18, 2017
 */
public class SearchTree<E> {
	/* Fields - students may not add fields */

	private Node<E> root;
	private int size;

	private Comparator<E> comparator;
	private boolean keepBalanced;

	/*
	 * Comparison count is an internal detail. I've set it to package level
	 * visibility.
	 */

	static int comparisonCount; // I didn't bother with a count()
	// method this time.

	/*
	 * Methods - students may add private helper methods, but must not remove or
	 * change the headers or contracts of the public methods.
	 */

	/**
	 * <p>
	 * Constructs an empty search tree. The caller must provide a comparator
	 * object for comparing data values of type E (whatever type that is).
	 * </p>
	 * 
	 * @param comparator
	 *            a comparator for comparing data values
	 * @param keepBalanced
	 *            if true, the tree will be an AVL tree
	 */
	public SearchTree(Comparator<E> comparator, boolean keepBalanced) {
		// Save the comparator for future use.

		this.comparator = comparator;
		this.keepBalanced = keepBalanced;

		// These would normally be the defaults. I'm setting
		// these explicitly for clarity. The tree starts empty,
		// and an empty tree has no nodes.

		this.root = null;
		this.size = 0;
	}

	/**
	 * <p>
	 * Returns the number of entries in this search tree.
	 * </p>
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <p>
	 * Returns the height of this tree (-1 if the tree is empty).
	 * </p>
	 *
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @return the height of this tree
	 */
	int getHeight() {
		return root == null ? -1 : root.height;
	}

	/**
	 * <p>
	 * Performs a pre-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitPreOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitPreOrder(v);
	}

	/**
	 * <p>
	 * Performs an in-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitInOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitInOrder(v);
	}

	/**
	 * <p>
	 * Performs a post-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitPostOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitPostOrder(v);
	}

	/**
	 * <p>
	 * Returns a list of all data object references stored in this binary search
	 * tree that are equal to the target data object. The list may be empty.
	 * Equality is determined using the tree's comparator on each data object.
	 * </p>
	 * 
	 * <p>
	 * Complexity is O(lg(size)) for single matches. For multiple matches, the
	 * complexity will be the larger of O(lg(size)) and the match count.
	 * </p>
	 * 
	 * @param target
	 *            the data to find in the tree
	 * @return a list of matching data objects
	 */
	public List<E> find(E target) {
		List<E> results = new ArrayList<E>();

		// If the root is not null, use a recursive helper
		// function to find the matching data.

		if (root != null)
			find(root, target, results);

		return results;
	}

	/**
	 * <p>
	 * Recursively finds data in this subtree that matches the target, and adds
	 * matching data object references to the results list.
	 * </p>
	 * 
	 * @param current
	 *            a root node of some subtree
	 * @param target
	 *            a data value to search for
	 * @param results
	 *            the list to add data values to
	 * @throws NullPointerException
	 *             the target is null or the current node is null
	 */
	private void find(Node<E> current, E target, List<E> results) {
		int compareResults = comparator.compare(current.data, target);
		comparisonCount++;

		if (compareResults == 0)
			results.add(current.data);

		if (compareResults <= 0 && current.right != null)
			find(current.right, target, results);

		if (compareResults >= 0 && current.left != null)
			find(current.left, target, results);
	}

	/**
	 * <p>
	 * Adds the given data object to this search tree. The data object must not
	 * be null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param data
	 *            any non-null data object.
	 * @throws IllegalArgumentException
	 *             if the data is null
	 */
	public void add(E data) {
		if (data == null)
			throw new IllegalArgumentException();

		// Keep the size up-to-date.

		size++;

		// If the tree is empty, just make a node and bail out.

		if (root == null) {
			root = new Node<E>(data); // Height is 0, OK.
			return;
		}

		// Locate a node with a free child slot for this data.
		// (Non-recursive, no specific reason.)

		Node<E> current = root;

		while (true) {
			int compareResults = comparator.compare(current.data, data);
			comparisonCount++;

			// Does it belong to the left?

			if (compareResults > 0) {
				// Is the left empty?
				if (current.left == null) {
					// Yes, add it there.
					current.left = new Node<E>(data);
					current.left.parent = current;
					updateNode(current);
					return;
				} else
					current = current.left; // No, go deeper.
			} else // It belongs to the right. Is the right empty?
				if (current.right == null) {
					// Yes, add it there.
					current.right = new Node<E>(data);
					current.right.parent = current;
					updateNode(current);
					return;
				} else
					current = current.right; // No, go deeper.
		}
	}

	/**
	 * <p>
	 * Removes the first (in traversal order) data entry that equals the target.
	 * A reference to the removed data entry is returned. The target must not be
	 * null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param targetValue
	 *            any non-null data object value
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the tree is nonempty and the target is null
	 */
	public E removeValue(E targetValue) {
		// Locate the node with matching data value.

		Node<E> current = root;
		Node<E> match = null;
		while (current != null) {
			int compareResults = comparator.compare(current.data, targetValue);
			comparisonCount++;

			if (compareResults == 0)
				match = current; // Found one, but look for earlier ones

			if (compareResults <= 0)
				current = current.right;
			else
				current = current.left;
		}

		if (match == null)
			return null; // Not found.

		// Remove it. (Use a helper function to finish the work.)

		return remove(match);
	}

	/**
	 * <p>
	 * Removes the data in specified node from the search tree, returns a
	 * reference to the data in the node. Note that the node reference may
	 * remain in the tree, but the data in the node will be removed. (Other data
	 * may be copied into this node during the removal process.) The tree size
	 * and all changed heights are updated.
	 * </p>
	 * 
	 * @param doomed
	 *            the node containing data to remove
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the node is null
	 */
	private E remove(Node<E> doomed) {
		// In cases where the node has zero or one children,
		// unlink the node and return the data.

		if (doomed.left == null || doomed.right == null) {
			// Update the size.
			size--;
			// Get a reference to the only child.

			Node<E> soleChild; // Might be null after initialization.

			if (doomed.right == null)
				soleChild = doomed.left;
			else
				soleChild = doomed.right;

			// Point the node's parent to this only child.

			if (doomed.parent != null) {
				if (doomed.parent.left == doomed)
					doomed.parent.left = soleChild;
				else
					doomed.parent.right = soleChild;
			} else
				root = soleChild;

			// Point the child to the parent of the removed node.

			if (soleChild != null)
				soleChild.parent = doomed.parent;

			// The node is now unlinked. Update the heights
			// in the parent and return the data.

			updateNode(doomed.parent);

			return doomed.data;
		}

		// The node has two children. Instead of removing
		// the node, just copy up data from deeper in the
		// tree (and then remove that deeper node).

		// Locate the immediate predecessor data in the tree.

		Node<E> predecessor = doomed.left;
		while (predecessor.right != null)
			predecessor = predecessor.right;

		// Remove it, but store its data in this node.
		// Finally, return the removed data.
		// (Note that this will decrease size for us.)

		return doomed.data = remove(predecessor);
	}

	/**
	 * <p>
	 * Updates (fixes) the height or balance of the subtree rooted at the
	 * specified node (current). It is assumed that the node may be currently
	 * incorrect -- that the height and/or balance of the node is wrong.
	 * </p>
	 * 
	 * <p>
	 * When a child node changes its height or balance, the parent's height or
	 * balance may need to change as well. This method is designed to simplify
	 * that process. After a child has changed, call this method on the parent
	 * to ensure the parent has the correct height or balance.
	 * </p>
	 * 
	 * <p>
	 * Note that this method may initiate recursion. If the height or balance of
	 * the node changes, then the node's parent also needs to be updated.
	 * Calling this method is O(lg(n)).
	 * </p>
	 * 
	 * <p>
	 * It is OK to send a null node as a parameter, it is ignored.
	 * </p>
	 *
	 * @param current
	 *            a node who's height or balance may need to be updated
	 */
	private void updateNode(Node<E> current) {
		// Skip null nodes.

		if (current == null)
			return;

		// Just let a (possibly recursive) helper function do all the work.
		updateHeights(current);
		if (keepBalanced)
			updateBalances(current); // Students may change this call.
		else
			updateHeights(current); // Leave this one in place.

		// Skipped in solution

		while (current != null && fixHeight(current))
			current = current.parent;

		// Debugging/testing function
		// I've left it in and you may use it during testing. You
		// should comment it out prior to submitting, but this is not required.

		checkStructure();
	}

	/**
	 * <p>
	 * Updates the height in a node, and possibly in the parents of the node.
	 * (See updateNode.)
	 * </p>
	 * 
	 * <p>
	 * This method is recursive, O(lg(n)).
	 * </p>
	 * 
	 * @param current
	 *            a node that may need updating
	 */
	private void updateHeights(Node<E> current) {
		// Fix the height. If it changes, fix the height of the parent.

		if (fixHeight(current))
			updateHeights(current.parent);
	}

	/**
	 * <p>
	 * Ensures that the height field in the given Node is correct. (The heights
	 * of the children are retrieved but not checked.) If the height changed,
	 * true is returned. If the height was correct before the call, false is
	 * returned.
	 * </p>
	 * 
	 * <p>
	 * This method is -not- recursive, only the given node is fixed. It is O(1).
	 * </p>
	 * 
	 * <p>
	 * If the current node is null, no action is taken.
	 * </p>
	 * 
	 * @param current
	 *            a node reference, or null
	 * @return true iff the height of the node changed
	 */
	private boolean fixHeight(Node<E> current) {
		if (current == null)
			return false;

		int oldHeight = current.height;
		current.height = 0;

		if (current.left != null)
			current.height = current.left.height + 1;
		if (current.right != null)
			current.height = Math.max(current.height, current.right.height + 1);

		return current.height != oldHeight;
	}

	/**
	 * <p>
	 * Removes the first (in in-order traversal order) data entry object that is
	 * the target object (using ==). A reference to the removed data entry is
	 * returned. The target must not be null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param targetReference
	 *            any non-null data object reference
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the tree is nonempty and the target is null
	 */
	public E removeReference (E targetReference)
	{

		Node<E> currentNode= null;

		int currentHeight = 0;
		int oldHeight = 0;
		if(root == null || targetReference ==null) {
			throw new NullPointerException();
		}
		Node<E> temp = removeReference(root, targetReference,currentNode,currentHeight,oldHeight);

		if (temp ==null) 
			return null;
		else 
			return remove(temp);
	}

	/**
	 * Undocumented recursive removeReference helper function.
	 */
	private Node<E> removeReference(Node<E> node, E targetReference , Node<E>currentNode, int currentHeight,int oldHeight) {
		int compareResults = comparator.compare(node.data,targetReference);
		comparisonCount++;
		if(compareResults == 0) {
			if(targetReference == node.data) {
				if(currentNode ==null) {
					currentNode = node;
					oldHeight = currentHeight;
				}
				else if( currentHeight != oldHeight) {
					currentNode = node;
					oldHeight = currentHeight;
				}
			}
		}
		currentHeight++; 		
		if (compareResults >= 0 && node.left != null) {
			return removeReference( node.left,targetReference,currentNode,currentHeight,oldHeight);
		}
		else if( compareResults <= 0 && node.right != null) {
			return removeReference( node.right,targetReference,currentNode, currentHeight,oldHeight);
		}
		if(currentNode!= null) 
			return currentNode;
		else
			return null;
	}

	/*
	 * Removed - Students may write this method, or something else if they
	 * choose.
	 */

	/**
	 * <p>
	 * Corrects the height of this node n, then guarantees the AVL condition for
	 * this node. If the height changes, the balance of the parent is also
	 * adjusted. (This may propagate balancing actions all the way to the root
	 * of the tree.)
	 * </p>
	 * 
	 * <p>
	 * This is a private helper method, and students will complete this (or some
	 * similar) method.
	 * </p>
	 * 
	 * @param n
	 *            a node that may need to be balanced
	 */
	private void updateBalances(Node<E> n) {
		if(n==null){
			return;
		}
		updateHeights(n);
		if(Math.abs(returnSubHeight(n)) <=1 )
		{
			updateBalances(n.parent);
		}
		else{
			if(returnSubHeight(n) < -1){
				if(returnSubHeight(n.left) <= 0){
					rotationRight(n);
				}
				else{
					rotateLeftThenRight(n);
				}
			}
			else{
				if(returnSubHeight(n.right) >= 0){
					rotationLeft(n);
				}
				else{
					rotateRightThenLeft(n);
				}
			}
			updateBalances(n.parent);
		}
	}

	public void updateBalances() {
		updateBalances(root);
	}

	/**
	 * <p>
	 * Returns (height of the right subtree - height of the left subtree), or 0
	 * if n is null.
	 * </p>
	 * 
	 * @param node
	 *            a Node, or null
	 * @return the difference in height between right and left subtrees
	 */
	private int returnSubHeight(Node<E> node) {

		if(node == null)
			return 0;

		if (node.left == null && node.right == null) {
			return 0;
		} else if (node.left == null) {
			int height01 = node.right.height + 1;
			return height01;
		} else if (node.right == null) {
			int height02 = -node.left.height - 1;
			return height02;
		} else {
			int height03 = node.right.height - node.left.height;
			return height03;
		}
	}
	/*
	 * Removed - Students may write this method, or something else if they
	 * choose.
	 */

	/**
	 * <p>
	 * Given a node 'n' with a non-null right child, this method performs a left
	 * rotation around 'n'. The non-null right child will become the root of
	 * this subtree. After rotation, the root of this subtree is returned.
	 * </p>
	 * 
	 * <p>
	 * During the rotation, nodes are detached and reattached and subtree
	 * heights are updated where needed. Upon completion, the parent of 'n' (or
	 * root) will be updated to refer to the root of the left-rotated subtree
	 * (instead of 'n').
	 * </p>
	 * 
	 * <p>
	 * Note that while the references in the original parent of n will be
	 * correct, the height of the parent may become invalid as a result of
	 * rotation. The heights within the rotated subtree will be adjusted to be
	 * correct.
	 * </p>
	 * 
	 * <p>
	 * If the right child of 'n' is null, a null-pointer exception is thrown.
	 * </p>
	 * 
	 * @param node
	 *            the root of a subtree to rotate
	 * @throws NullPointerException
	 *             if the right child of n is null.
	 */
	private Node<E> rotationLeft(Node<E> node) {
		if (node == null) {
			throw new NullPointerException("tree is nonempty and the target is nul");
		}

		Node<E> currentNode = node;
		Node<E> rightChild = node.right;
		Node<E> partents = currentNode.parent;
		Node<E> leftChild = rightChild.left;
		rightChild.parent = partents;
		currentNode.right = rightChild.left;
		rightChild.left = currentNode;
		currentNode.parent = rightChild;
		if (partents == null) {
			root = rightChild;
		} else {
			if (partents.right == currentNode) {
				partents.right = rightChild;
			}
			else{
				partents.left = rightChild;
			}
		}
		if (leftChild != null) {
			leftChild.parent = currentNode;
		}
		updateHeights(currentNode);
		updateHeights(rightChild);
		return rightChild;
	}

	/*
	 * Removed - Students may write this method, or something else if they
	 * choose.
	 */

	/**
	 * <p>
	 * Given a node 'n' with a non-null left child, this method performs a right
	 * rotation around 'n'. The non-null left child will become the root of this
	 * subtree. After rotation, the root of this subtree is returned.
	 * </p>
	 * 
	 * <p>
	 * During the rotation, nodes are detached and reattached and subtree
	 * heights are updated where needed. Upon completion, the parent of 'n' (or
	 * root) will be updated to refer to the root of the right-rotated subtree
	 * (instead of 'n').
	 * </p>
	 * 
	 * <p>
	 * If the right child of 'n' is null, a null-pointer exception is thrown.
	 * </p>
	 * 
	 * @param node
	 *            the root of a subtree to rotate
	 * @throws NullPointerException
	 *             if the right child of n is null.
	 */

	/*
	 * Removed - Students may write this method, or something else if they
	 * choose.
	 */
	private Node<E> rotationRight(Node<E> node) {
		if (node == null) {
			throw new NullPointerException("tree is nonempty and the target is nul");
		}

		Node<E> currentNode = node;
		Node<E> leftChild = node.left;
		Node<E> partents = currentNode.parent;
		Node<E> rightChild = leftChild.right;
		leftChild.parent = partents;
		currentNode.left = leftChild.right;
		leftChild.right = currentNode;
		currentNode.parent = leftChild;
		if (partents == null) {
			root = leftChild;
		} else {
			if (partents.left == currentNode) {
				partents.left = leftChild;
			}
			else{
				partents.right = leftChild;
			}
		}

		if (rightChild != null) {
			rightChild.parent = currentNode;
		}
		updateHeights(currentNode);
		updateHeights(leftChild);
		return leftChild;
	}

	// double right
	private Node<E> rotateLeftThenRight(Node<E> node) {
		node.left = rotationLeft(node.left);
		return rotationRight(node);
	}

	// double left
	private Node<E> rotateRightThenLeft(Node<E> node) {
		node.right = rotationRight(node.right);
		return rotationLeft(node);
	}
	/**
	 * <p>
	 * Checks the tree to make sure it has valid structure. (Parent / child
	 * pointers match, heights are correct, etc.) (For debugging only.)
	 * </p>
	 * 
	 * @exception RuntimeException
	 *                if the structure is not valid
	 */
	private void checkStructure() {
		final int[] c = new int[1]; // Not ideal, but works.

		visitInOrder(n -> {
			comparator.toString();
			// Anonymous local inner class specified in a lambda - ouch!
			// Still, it has cool advantages and we'll discuss them later.
			c[0]++;
			int correctHeight = 0;
			if (n.left != null)
				correctHeight = 1 + n.left.height;
			if (n.right != null)
				correctHeight = Math.max(correctHeight, 1 + n.right.height);
			if (n.height != correctHeight)
				throw new RuntimeException("Height invariant not kept");
			if (n.parent == null && root != n)
				throw new RuntimeException("Root not set properly");
			if (n.parent != null && n.parent.left != n && n.parent.right != n)
				throw new RuntimeException("Node does not point to its parent");
			if (n.left != null && n.left == n.right)
				throw new RuntimeException("Left/right children are not distinct");

			// Important: Don't count these comparisons. They're for testing
			// only.

			if (n.left != null && comparator.compare(n.left.data, n.data) > 0)
				throw new RuntimeException("Left child greater than parent");
			if (n.right != null && comparator.compare(n.right.data, n.data) < 0)
				throw new RuntimeException("Right child less than parent");
		});
		if (c[0] != size)
			throw new RuntimeException("Node count " + c[0] + " does not match size " + size);
	}
}
