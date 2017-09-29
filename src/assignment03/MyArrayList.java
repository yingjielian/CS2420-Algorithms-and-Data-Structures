package assignment03;

/**
 * <p>
 * This class describes a simple list abstract data type. An ArrayList is a
 * list ADT that also tracks the number of modifications required to perform
 * various list operations.
 * </p>
 * 
 * @author Peter Jensen & Yingqi Song & Yingjie Lian
 * @version September 21, 2017
 */
public class MyArrayList<E> implements UtahList<E>
{
	private float relativeGrowthRate;
	private int arrayModificationCount;
	private int elementCounts;
	private int firstIndex;
	private E[] backingArray;

	/**
	 * <p>
	 * Creates a new rolling array with no elements, a backing array of size 5,
	 * and the specified growth rate. The growth rate parameters must ensure
	 * that the backing array always grows when needed.
	 * </p>
	 * 
	 * @param relativeGrowthRate
	 *            the growth rate multiplier
	 * @throws IllegalArgumentException
	 *             iff the growth rate does not ensure growth of the backing
	 *             array
	 */
	public MyArrayList ()
	{
		// Creats a backing array of size 5. Cannot be 0, because that will cause
		// some problem for calling other utility methods. This way will make it
		// easier.
		this.backingArray = (E[]) new Object[5];

		arrayModificationCount = 0;
		elementCounts = 0;
		firstIndex = 0;
	}

	/**
	 * <p>
	 * This method ensures that the backing array has enough space to store some
	 * number of elements (a desired capacity). Iff the backing array is too
	 * small, this method creates a larger backing array and copies the list
	 * elements into it. The larger backing array reference is then copied into the
	 * backing array reference, and the capacity and first element locations are updated
	 * to be correct.
	 * </p>
	 * 
	 * <p>
	 * (Note: Since list elements will be copied into a larger array, the array
	 * modification count will be significantly increased.)
	 * </p>
	 * 
	 * @param desiredCapacity
	 *            the needed capacity of the backing array
	 */
	private void ensureCapacity (int desiredCapacity)
	{
		// Initialize a temporary array in order to store the value from
		// the backingArray
		E[] largerArray;

		// Use the formula that constructor gave to us to enlarge the newSize
		int newSize = backingArray.length * 2;



		// Check if the size of the backingArray is smaller than desiredCapacity 
		// because if not, we do not need to enlarge the size of backingArray
		if(backingArray.length < desiredCapacity)
		{

			// Make the newSize that we calculated become the largerArray's size
			largerArray = (E[]) new Object[newSize];

			// Check if the size of largerArray is smaller or equal to the size
			// of backingArray, if it is throw IllegalArgumentException.
			// Because if when the size of largerArray is smaller or equal to the size
			// of backingArray, the largerArray is not the array that we want to be
			// (because we want to bigger size than backingArray)
			if(largerArray.length <= backingArray.length)
			{
				throw new IllegalArgumentException();
			}

			for(int i = 0; i < elementCounts; i++)
			{
				// Call getElement method to store the backing array
				// element is some order(formula used in getElement method)
				largerArray[i] = getElement(i);

				// arrayModificationCount plus one because every time the for loop
				// runs, the array changed
				arrayModificationCount++;
			}

			// Because the backingArray has stored into a new array, so we have
			// to re-track the firstIndex which is 0 now.
			firstIndex = 0;

			// Finally, store the element from the temporary largerArray back to backing
			// array.
			backingArray = largerArray;
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
	public E getElement (int index)
	{
		// Because if the index is negative or bigger than the size of
		// the array, there will not be any element exist. So at those situations,
		// throw IndexOutOfBoundsException.
		if(index < 0 || index >= elementCounts )
			throw new IndexOutOfBoundsException();

		// Use the formula that constructor gave to us on the assignment02 description
		return (E) backingArray[(index + firstIndex + backingArray.length) % backingArray.length];
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
	public void setElement (int index, E data)
	{
		// Because if the index is negative or bigger than the size of
		// the array, there will not be any element exist. So at those situations,
		// throw IndexOutOfBoundsException.
		if(index < 0 || index >= elementCounts )
			throw new IndexOutOfBoundsException();

		// Use the formula again to make sure put the value under the correct index
		// into backingArray
		backingArray[(index + firstIndex + backingArray.length) % backingArray.length] = data;
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
	public void insert(int index, E data) {

		if(index < 0 || index > elementCounts )
			throw new IndexOutOfBoundsException();
		
		ensureCapacity(elementCounts + 1);

		// Move the elements to the right after the specified index
		for(int i = elementCounts - 1; i >= index; i--)
		{
			backingArray[i + 1] = backingArray[i];
		}

		//Then, insert new element to backingArray[index]
		backingArray[index] = data;

		elementCounts++;
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
	public E remove(int index) {

		if(index < 0 || index >= elementCounts )
			throw new IndexOutOfBoundsException();

		E element = getElement(index);
		
		// Move the elements to the right exclude the value of the specified index
		for(int i = index; i < elementCounts; i++)
		{
			backingArray[i] = backingArray[i + 1];
		}
		elementCounts--;

		return element;
	}

	/**
     * Returns the size of the list; returns the number of elements in the list.
     * 
     * @return the size of the list
     */
	public int size() {
		return elementCounts;
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
	public int getModificationCount() {
		return arrayModificationCount;
	}

	/**
     * <p>
     * Resets (to 0) the modification count for this list.
     * </p>
     */
	public void resetModificationCount() {
		arrayModificationCount = 0;		
	}

}
