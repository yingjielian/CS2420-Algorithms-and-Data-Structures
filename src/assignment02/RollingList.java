package assignment02;


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>
 * A rolling list is just a list ADT backed by an array with performance roughly
 * equivalent or superior to a linked list for most operations.  This list
 * implementation can only hold double values.
 * </p>
 * 
 * <p>
 * This list ADT has a restricted set of operations: You can view and modify any
 * element of the list, but you can only add or remove elements from the front
 * or back end of the list.
 * </p>
 * 
 * <p>
 * The size and growth rate of the backing array is determined when each rolling
 * list is constructed. The initial size of the backing array is always 5. When
 * an element is added to the list, but the backing array is full, a new, larger
 * backing array is created and the elements are copied from the old array to
 * the new array. The size of the new backing array is determined as follows:
 * </p>
 * 
 * <p>
 * <i>newSize = ceiling(oldSize * relativeGrowthRate + absoluteGrowthRate)</i>
 * </p>
 *
 * <p>
 * This class also keeps track of how many times modifications are made to the
 * backing array, and it provides static methods for getting and resetting this
 * count. (By keeping track of the number of times something was written into an
 * array, students can estimate the complexity of their solution.) As an absolute
 * rule, no unnecessary modifications should be made to the backing array.
 * </p>
 * 
 * <p>
 * For assignment 02, this class has additional specific implementation
 * requirements. Please review assignment 02 for details.
 * </p>
 * 
 * @author --- Yingjie Lian ---
 * @version --- 09/09/2017 ---
 * @Class CS-2420
 */

// Self remind: ceiling is to use Math.ceil
public class RollingList
{
	// Fields
	private float relativeGrowthRate;
	private int absoluteGrowthRate;
	private int arrayModificationCount;
	private int elementCounts;
	private int firstIndex;
	private double[] backingArray;

	// Constructor

	/**
	 * <p>
	 * Creates a new rolling array with no elements, a backing array of size 5,
	 * and the specified growth rate. The growth rate parameters must ensure
	 * that the backing array always grows when needed.
	 * </p>
	 * 
	 * @param relativeGrowthRate
	 *            the growth rate multiplier
	 * @param absoluteGrowthRate
	 *            the growth rate constant
	 * @throws IllegalArgumentException
	 *             iff the growth rate does not ensure growth of the backing
	 *             array
	 */
	public RollingList (float relativeGrowthRate, int absoluteGrowthRate)
	{
		// Creats a backing array of size 5. Cannot be 0, because that will cause
		// some problem for calling other utility methods. This way will make it
		// easier.
		this.backingArray = new double[5];

		// newSize = ceiling(oldSize * relativeGrowthRate + absoluteGrowthRate)

		// Based on the newSize formula, if relativeGrowthRate is less than one, 
		// the newSize won't grow. However, relativeGrowthRate and absoluteGrowthRate
		// are the parameters that have to make the newSize grow. If not, throw 
		// IllegalArgumentException.
		if(relativeGrowthRate < 1)
			throw new IllegalArgumentException();

		//If the relativeGrowthRate equals to 1 and absoluteGrowthRate is not positive, 
		// then the newSize won't grow too.
		if(relativeGrowthRate == 1 && absoluteGrowthRate <= 0)
			throw new IllegalArgumentException();

		// Finally, make sure use the formula will let the newSize grow, otherwise 
		// throw IllegalArgumentException.
		if((int) Math.ceil((backingArray.length * relativeGrowthRate + absoluteGrowthRate)) < backingArray.length)
			throw new IllegalArgumentException();

		this.relativeGrowthRate = relativeGrowthRate;
		this.absoluteGrowthRate = absoluteGrowthRate;
		arrayModificationCount = 0;
		elementCounts = 0;
		firstIndex = 0;
	}

	// Private utility methods

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
		double[] largerArray;

		// Use the formula that constructor gave to us to enlarge the newSize
		int newSize = (int) Math.ceil((backingArray.length * relativeGrowthRate + absoluteGrowthRate));

		// Make the newSize that we calculated become the largerArray's size
		largerArray = new double[newSize];

		// Check if the size of the backingArray is smaller than desiredCapacity 
		// because if not, we do not need to enlarge the size of backingArray
		if(backingArray.length < desiredCapacity)
		{
			// Check if the size of largerArray is smaller or equal to the size
			// of backingArray, if it is throw IllegalArgumentException.
			// Because if when the size of largerArray is smaller or equal to the size
			// of backingArray, the largerArray is not the array that we want to be
			// (because we want to bigger size than backingArray)
			if(largerArray.length <= backingArray.length)
			{
				throw new IllegalArgumentException();
			}

			for(int i = 0; i < backingArray.length; i++)
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

	// Supported list ADT operations.

	/**
	 * <p>
	 * Retrieves a value from the list.
	 * </p>
	 * 
	 * @param i
	 *            the element to retrieve
	 * @return 
	 *            the value of the specified element
	 */
	public double getElement (int i)
	{
		// Because if the index is negative or bigger than the size of
		// the array, there will not be any element exist. So at those situations,
		// throw IndexOutOfBoundsException.
		if(i < 0 || i >= elementCounts )
			throw new IndexOutOfBoundsException();

		// Use the formula that constructor gave to us on the assignment02 description
		return backingArray[(i + firstIndex + backingArray.length) % backingArray.length];
	}

	/**
	 * <p>
	 * Changes an element's value in this list. Note: This will increase the
	 * array modification count.
	 * </p>
	 * 
	 * @param i
	 *            the element to change
	 * @param value
	 *            the new value (any double) for this element
	 */
	public void setElement (int i, double value)
	{
		// Because if the index is negative or bigger than the size of
		// the array, there will not be any element exist. So at those situations,
		// throw IndexOutOfBoundsException.
		if(i < 0 || i >= elementCounts )
			throw new IndexOutOfBoundsException();

		// Use the formula again to make sure put the value under the correct index
		// into backingArray
		backingArray[(i + firstIndex + backingArray.length) % backingArray.length] = value;

		// arrayModificationCount plus one because every time the setElement method get
		// called, the array changed
		arrayModificationCount++;
	}

	/**
	 * 
	 * <p>
	 * Adds (appends) a value to the end of the list (increasing the element
	 * count). The last entry is the entry with an element index of (length of
	 * list) - 1.
	 * </p>
	 * 
	 * @param value
	 *            any double
	 */
	public void append (double value)
	{
		// Call ensureCapacity method to make sure there is enough
		// for user to append a value.
		ensureCapacity(elementCounts + 1);

		// Because after appending one value to the backingArray, 
		// the elements should plus one more.
		elementCounts++;

		// Call setElement method to set the value become the last
		// element inside the backingArray
		setElement(elementCounts - 1, value);
	}

	/**
	 * <p>
	 * Adds (inserts) a value before the first element in the list (increasing
	 * the element count). The value becomes the first entry. The first entry is
	 * the entry with an element index of 0.
	 * </p>
	 * 
	 * @param value
	 *            any double
	 */
	public void prepend (double value) 
	{
		ensureCapacity(elementCounts + 1); // How to make sure there is not something in the last entry

		// Because what prepend do is to add the value to be the first element 
		// inside the backingArray, so the first index is no longer the one before,
		// it should minus one now.
		firstIndex--;

		// Because after prepending one value to the backingArray, 
		// the elements should plus one more.
		elementCounts++;

		// Call setElement method to set the value become the first
		// element inside the backingArray
		setElement(0, value);
	}

	/**
	 * <p>
	 * Removes the last entry in the list (decreasing the element count). The
	 * last entry is the entry with an element index of (length of list) - 1.
	 * </p>
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty prior to this call
	 */
	public void removeLast ()
	{
		// Check if there is element inside the backingArray, if not
		// throw NoSuchElementException. Because if there is no element,
		// then user cannot do anything to remove the element.
		if(elementCounts == 0)
			throw new NoSuchElementException();

		// Because we did not change the first index, we just count the 
		// element minus one, so now the last element is the one before 
		// old last element.
		elementCounts--;
	}

	/**
	 * <p>
	 * Removes the first entry in the list (decreasing the element count). The
	 * first entry is the entry with an element index of 0.
	 * </p>
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty prior to this call
	 */
	public void removeFirst ()
	{
		// Check if there is element inside the backingArray, if not
		// throw NoSuchElementException. Because if there is no element,
		// then user cannot do anything to remove the element.
		if(elementCounts == 0)
			throw new NoSuchElementException();

		// Use for loop to move every index to the place before them,
		// because the first element need to be removed
		for(int i = 0; i < elementCounts - 1; i++)
		{
			backingArray[i] = getElement(i + 1);
		}

		// Since the first element has been removed, then the amount of
		// elements need to reduce one each time.
		elementCounts--;
	}

	/**
	 * <p>
	 * Returns the number of elements in this rolling list.
	 * </p>
	 * 
	 * @return 
	 *         the number of elements in this list
	 */
	public int size ()
	{
		return elementCounts;
	}

	// Public utility methods (instrumentation)

	/**
	 * <p>
	 * Clears (sets to 0) the array modification count.
	 * </p>
	 */
	public void resetArrayModificationCount ()
	{
		arrayModificationCount = 0;
	}

	/**
	 * <p>
	 * Returns the array modification count.
	 * </p>
	 * 
	 * @return 
	 *         a count of the number of times the backing array has changed
	 */
	public int getArrayModificationCount ()
	{
		return arrayModificationCount;
	}

	/**
	 * <p>
	 * Returns the percentage of the backing array that is unused. Return values
	 * will be between [0.0 and 1.0].
	 * </p>
	 * 
	 * @return 
	 *         the percentage of the backing array that is wasted space
	 */
	public double wastedSpace () 
	{
		// To count the wastedSpace, we have to find out the total elements in the array
		// and the size of the array. Use the size of the array minus the total elements 
		// is the wastedSpace, then divide the size of the array to got the percentage.
		double wastedSpace = 1 - ((double) elementCounts / (double) backingArray.length);
		return wastedSpace;
	}
}
