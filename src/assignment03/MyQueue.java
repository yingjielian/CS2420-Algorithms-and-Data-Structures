package assignment03;

/**
 * <p>
 * This class describes a simple queue ADT. A queue of elements in analogous
 * to a line at the store: You enter the line at the back (enqueue), and you
 * exit the line at the front (dequeue). This is a first-in, first-out data
 * structure.
 * </p>
 * 
 * @author Peter Jensen & Yingqi Song & Yingjie Lian
 * @version September 21, 2017
 */
public class MyQueue <E>implements UtahQueue<E>{
	//field
	UtahList<E> utList;

	/**
	 * We use the constructor to create a UtahList Object 
	 * @param List a UtahList Object
	 */
	public MyQueue(UtahList<E> List){
		utList = List;	
	}

	/**
	 * <p>
	 * Adds an element to the end of the queue.
	 * </p>
	 * 
	 * @param data
	 *            some data to put in the queue
	 */
	public void enqueue(E data) {
		utList.insert(utList.size(), data);

	}

	/**
	 * <p>
	 * Removes and returns the data from the front of the queue; removes and
	 * returns the element that has been in the queue the longest.
	 * </p>
	 * 
	 * @return the data that was removed from the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public E dequeue() {
		return utList.remove(0);
	}

	/**
	 * <p>
	 * Returns the number of queued elements.
	 * </p>
	 * 
	 * @return the size of the queue
	 */
	public int size() {
		return utList.size();
	}

}
