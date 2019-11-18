package assignment08;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Yingjie Lian
 * @version 11.30.2017
 *
 */
public class DEPQueue {
	private int currentSize;

	private String[] array;
	
	private long comparisonCount;
	private long swapCount;
	

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	public DEPQueue() {
		currentSize = 0;
		array = new String[1000]; // safe to ignore warning
	}


	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public String removeMin() {
		//If empty, throw exception on delete event attempt
		if (currentSize == 0)
			throw new NoSuchElementException();
		//Store root as a temp to return after percolating
		
		scanDownward(0);
		String temp = array[0];
		//Move last item in tree to root
		array[0] = array[currentSize-1];
		swapCount++;
		array[currentSize-1] = null;
		//Reduce size
		currentSize--;
		//Move root value down to it's correct position
		
		//Return removed value.
		return temp;
	}
	
	public String removeMax(){
		//If empty, throw exception on delete event attempt
		if (currentSize == 0)
			throw new NoSuchElementException();
		//Store root as a temp to return after percolating
		scanUpward(0);
		String temp = array[0];
		
		//Move last item in tree to root
		array[0] = array[currentSize-1];
		//Move root value down to it's correct position
		
		swapCount++;
		array[currentSize-1] = null;
		//Reduce size
		currentSize--;
		
		
		//Return removed value.
		return temp;
	}
	
	/**
	 * Helper method to recursively swap our evaluated value down the tree until it
	 * 	reaches the correct position
	 * 
	 * @param i - the index value of the node being evaluated.
	 */
	private void scanDownward(int i)
	{
		//BASE CASE: if we've reached the end of the tree, exit
		if (i == currentSize-1)
			return;
		//Store values of the node we're evaluating and it's children
		String temp = array[i];
		String left;
		String right;
		//Check size in case we're on the lowest level of the tree
		//to avoid index out of bounds.
		if (2* i+1 > currentSize)
			left = null;
		else
			left = array[2* i+1];
		if (2* i+1 > currentSize)
			right = null;
		else
			right = array[2* i+2];

		//BASE CASE: if the children are both null, no swaps, exit
		if (right == null && left == null)
			return;
		//BASE CASE:  If the children are not null but are both larger than value at i, exit
		if ((right != null && left != null))
		{
			if (temp.compareTo(left) <= 0 && temp.compareTo(right) <= 0){
				comparisonCount++;
				return;
			}
		}
		else
		{
			//BASE CASE: If the left child is null and the right is larger than i, exit
			if (left == null && temp.compareTo(right) <= 0){
				comparisonCount++;
				return;
			}
			//BASE CASE: If the right child is null and the left is larger than i, exit
			else if (right == null && temp.compareTo(left) <= 0){
				comparisonCount++;
				return;
			}
		}
		//If left is null and right child is smaller than i, swap and recurse
		if (left == null && temp.compareTo(right)> 0)
		{
			comparisonCount++;
			array[i] = right;
			array[2*i+2] = temp;
			swapCount++;
			scanDownward(2 * i+2);
			return;
		}
		//If right is null and left child is smaller than i, swap and recurse
		else if (right == null && temp.compareTo(left) > 0)
		{
			comparisonCount++;
			array[i] = left;
			array[2*i+1] = temp;
			swapCount++;
			scanDownward(2 * i+1);
			return;
		}

		//If right child is larger than left child, swap with i and recurse
		if (array[2* i+2].compareTo(array[2* i+1]) < 0)
		{
			comparisonCount++;
			array[i] = array[2* i + 2];
			array[2*i + 2] = temp;
			swapCount++;
			scanDownward(2 * i+2);
		}
		//If left child is larger than right child, swap with i and recurse
		else {
			array[i] = array[2* i + 1];
			array[2*i + 1] = temp;
			swapCount++;
			scanDownward(2 * i + 1);
		}
	}
	
	/**
	 * Helper method to recursively swap our evaluated value up the tree until it
	 * 	reaches the correct position
	 * 
	 * @param i - the index value of the node being evaluated.
	 */
	private void scanUpward(int i)
	{
		//Store evaluated node as a temp variable
		String temp = array[i];
		//BASE CASE: if the evaluated node is larger than the parent, exit
		if (temp.compareTo(array[(i-1)/2])>= 0)
		{
			comparisonCount++;
			return;
		}
		//Swap evaluated node wth parent, recurse
		else 
		{
			array[i] = array[(i-1)/2];
			array[(i-1)/2] = temp;
			swapCount++;
			scanUpward((i-1)/2);
		}

	}


	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x -- the item to be inserted
	 */
	public void insert(String x) {
		//If no item is passed to add, add nothing
		if ( x == null)
			return;
		//Grow if needed.
		if (array[array.length-2] != null )
			grow();
		//Add x to the last spot in the tree maintaining completeness
		array[currentSize] = x;
		//Recursively swap up the tree to the correct position
		scanUpward(currentSize);
		//Increment size
		currentSize++;
	}

	/**
	 * Helper method to grow the array when needed
	 */
	private void grow()
	{
		//Generate new array that is double the size of current.
		String[] temp = new String[array.length * 2];
		for (int i = 0; i < currentSize; i++)
			temp[i] = array[i];
		//Swap out pointers, GC will destroy old array.
		array = temp;
	}
	
	/**
	 * 
	 * @return long the number of comparisonCount.
	 */
	public  long  getComparisonCount ()
	{
		return comparisonCount;
	}
	
	/**
	 * 
	 * @return long the number of swapCount.
	 */
	public  long  getSwapCount ()
	{
		return swapCount;
	}
}
