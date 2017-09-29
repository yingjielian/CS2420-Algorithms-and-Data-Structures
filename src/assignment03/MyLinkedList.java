package assignment03;
/**
 * This class is the MyLinked list , this class implements UtahList 
 * 
 * @author Peter Jensen & Yingqi Song & Yingjie Lian
 * @version September 21, 2017
 * 
 */

public class MyLinkedList<E> implements UtahList<E> {
	Node<E> head;
	Node<E> tail;
	int ModificationCount = 0;
	int size = 0;

	private class Node<E> {
		private E data;
		private Node<E> previous;
		private Node<E> next;

		public Node(E _data, Node<E> _previous, Node<E> _next) {
			this.data = _data;
			this.previous = _previous;
			this.next = _next;
		}
	}
	/**
	 * <p>
	 * Returns an element from the list. The first element is at position 0.
	 * Valid positions are [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @return the element from that position in the list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	@Override
	public E getElement(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("There are no index in the list.");
		}
		Node<E> currentNode = head;
		// this for loop can get we need the element

		for (int i = 1; i <= index; i++) {
			currentNode = currentNode.next;

		}
		return (E) currentNode.data;

	}
	/**
	 * <p>
	 * Changes an existing element in the list to the given data value. The
	 * first element is at position 0. Valid positions are [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @param data
	 *            some data value
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	@Override
	public void setElement(int index, E data) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("There are no index in the list.");
		}
		Node<E> currentNode = head;
		// this for loop can get we need the element
		for (int i = 1; i <= index; i++) {
			currentNode = currentNode.next;
		}
		currentNode.data = data;
	}
	/**
	 * <p>
	 * Inserts an element (some data) in the specified position in the list.
	 * Valid positions are [0..size]. Any elements at or after this position are
	 * moved to one position later in the list to make space for this element,
	 * and the element count grows by 1. If an element is inserted at the end of
	 * the list (at position <i>size</i>), it is appended to the list.
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @param data
	 *            some data value
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	@Override
	public void insert(int index, E data) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("There are no index in the list.");
		}

		Node<E> currentNode = new Node<E>(data, null, null);

		if (head == null) {
			head = currentNode;
			tail = head;
			size++;

		} else if (index == 0) // add element to first
		{
			currentNode.next = head;
			head.previous = currentNode;
			head = currentNode;
			size++;

		} else if (index == size()) // add element to  last
		{
			tail.next = currentNode;
			currentNode.previous = tail;
			tail = currentNode;
			size++;

		} else // add element to  middle
		{
			Node<E> nextElement = head;
			for (int i = 1; i <= index; i++) {
				nextElement = nextElement.next;
			}
			currentNode.previous = nextElement.previous;
			nextElement.previous.next = currentNode;
			currentNode.next = nextElement;
			nextElement.previous = currentNode;
			size++;

		}
		ModificationCount++;
	}
	/**
	 * <p>
	 * Removes and returns an element from the list. Subsequent elements are
	 * moved to one position earlier in the list (so that there is not a hole in
	 * the list), and the element count is decreased by 1. Valid positions are
	 * [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @return the removed element
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("There are no index in the list.");
		}
		//remove the first element
		if (index == 0) {
			if (size() == 0) {
				throw new IndexOutOfBoundsException("Empty Linked List");
			}
			E removedData = head.data;
			head = head.next;
			size--;
			return removedData;

		}
		//remove the last element
		if (index == (size() - 1)) {
			if (size() == 0) {
				throw new IndexOutOfBoundsException("Empty Linked List");
			}
			Node<E> currentNode = head;
			Node<E> previousNode = head;

			while (currentNode.next != null) {
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
			E removedData = currentNode.data;
			previousNode.next = null;
			size--;

			return removedData;

		}
		//remove the mid element
		if (index > 0 && index < size() - 1) {
			Node<E> currentNode = head;
			for (int i = 1; i <= index; i++) {
				currentNode = currentNode.next;
			}
			E removedData = currentNode.data;
			currentNode.previous.next = currentNode.next;
			currentNode.next.previous = currentNode.previous;
			size--;
			return removedData;
		}

		return null;
	}
	/**
	 * Returns the size of the list; returns the number of elements in the list.
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * <p>
	 * Returns the list modification count. This allows us to track and evaluate
	 * the performance of this list. (It is not normally part of a list ADT.)
	 * </p>
	 * 
	 * <p>
	 * For array-backed lists, this corresponds to the total number of array
	 * modifications (any writes to array locations). For linked lists, this
	 * corresponds to the total number of times a node is merged or removed from
	 * the list.
	 * </p>
	 * 
	 * @return a count of the number of times the backing array has changed
	 */
	@Override
	public int getModificationCount() {
		return ModificationCount;
	}
	/**
	 * <p>
	 * Resets (to 0) the modification count for this list.
	 * </p>
	 */
	@Override
	public void resetModificationCount() {
		ModificationCount = 0;

	}

}
