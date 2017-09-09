package assignment02;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment02.RollingList;

public class RollingListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/* -------------Test exceptions--------------- */
	@Test(expected=NoSuchElementException.class)
	public void testRemoveFirstException() {
		RollingList emptyList = new RollingList(1.2f,1);
		emptyList.removeFirst();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRemoveLastException(){
		RollingList emptyList = new RollingList(3f,1);
		emptyList.removeLast();
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetElementException(){
		RollingList emptyList = new RollingList(2f,0);
		emptyList.getElement(0);
		emptyList.getElement(1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetElementException(){
		RollingList emptyList = new RollingList(1f,2);
		emptyList.setElement(0, 1.0);
		emptyList.setElement(1, 2.0);
	}
	/*----------------------------------------------*/
	
	@Test
	public void testPrependWithSmallList(){
		RollingList smallList = new RollingList(1.5f,1);
		double testElement = 3.98;
		smallList.prepend(testElement);
		assertEquals(1,smallList.size());
		assertEquals(testElement,smallList.getElement(0),0);
	}

	@Test
	public void testAppendWithLargeList(){
		RollingList largeList = new RollingList(2.0f,1);
		for(int i = 0; i < 100; i++){
			largeList.append(i*1.0);
		}
		for(int i = 0; i < 100; i++){
			assertEquals(largeList.getElement(i),i*1.0,0);
		}
	}
	
	@Test
	public void testAppendAndPrepend(){
		double[] elementArray = new double[10_000];
		for(int i = 0; i < elementArray.length; i++){
			elementArray[i] = i*1.0;
		}
		
		RollingList myList = new RollingList(2.1f,0);
		for(int i = elementArray.length/2; i >=0; i--){
			myList.prepend(elementArray[i]);
		}
		for(int i = elementArray.length/2+1; i< elementArray.length; i++){
			myList.append(elementArray[i]);
		}
		
		assertEquals(myList.size(),elementArray.length);
		for(int i = 0; i < myList.size(); i++){
			assertTrue(myList.getElement(i)==elementArray[i]);
		}
	}
	
	@Test
	public void testPrependWithLargeList(){
		RollingList largeList = new RollingList(2.0f,0);
		for(int i = 0; i < 100; i++){
			largeList.prepend(i*1.5);
		}
		for(int i = 0; i < 100; i++){
			assertEquals(largeList.getElement(i),1.5*(100-1-i),0);
		}
	}
	
	@Test
	public void testResetArrayModificationCount(){
		RollingList largeList = new RollingList(2.0f,0);
		for(int i = 0; i < 10_000; i++){
			largeList.append(i*1.0);
		}
		assertTrue(largeList.getArrayModificationCount() > 0);
		largeList.resetArrayModificationCount();
		assertEquals(0,largeList.getArrayModificationCount());
	}
	
	@Test
	public void testWastedSpace(){
		RollingList myList = new RollingList(1.5f,1);
		assertEquals(myList.wastedSpace(),1,0);
		myList.append(3.98);
		assertEquals(myList.wastedSpace(),(5-1)/(1.0*5),0);
	}
	
	@Test
	public void testEnsureCapacity(){
		RollingList myList = new RollingList(2.0f,0);
		for(int i = 0; i < 5; i++){
			myList.append(i*1.0);
		}
		assertEquals(0.0,myList.wastedSpace(),0);
		assertEquals(5,myList.size());
		// Known that the initial arrayCapacity is 5.
		// With relativeGrowthRate of 2.0, the next arrayCapacity will be 10.
		double testElement = 3.98;
		myList.prepend(testElement);
		assertEquals(5+1,myList.size());
		assertEquals(4.0,myList.getElement(myList.size()-1),0);
		assertEquals(testElement,myList.getElement(0),0);
		assertEquals(myList.wastedSpace(),(10-6)/(1.0*10),0);
	}
	
	@Test
	public void testRemoveFirstFewTimes(){
		RollingList largeList = new RollingList(1.5f,1);
		int expectedListSize = 100_000;
		for(int i = 0; i < expectedListSize; i++){
			largeList.append(i);
		}
		assertEquals(expectedListSize,largeList.size());
		
		for(int i = 0; i < expectedListSize; i++){
			assertTrue(largeList.getElement(i)==i*1.0);
		}
		largeList.removeFirst();
		largeList.removeFirst();
		for(int i = 0; i < expectedListSize-2; i++){
			assertTrue(largeList.getElement(i)==1.0*(i+2));
		}
	}
	
	@Test
	public void testRemoveFirstMultipleTimes(){
		RollingList largeList = new RollingList(1.2f,10);
		int expectedLargeSize = 10000000;
		for(int i = 0; i < expectedLargeSize; i++){
			largeList.append(i*1.0);
		}
		assertEquals(expectedLargeSize,largeList.size());

		// remove the first 500 elements on list
		for(int i = 0; i < 500; i++){
			largeList.removeFirst();
		}
		assertEquals(expectedLargeSize-500,largeList.size());

		for(int i = 500; i < expectedLargeSize; i++){
			assertEquals(largeList.getElement(i-500),i*1.0,0);
		}
	}
	
	@Test
	public void testRemoveLastFewTimes(){
		RollingList largeList = new RollingList(1.5f,2);
		int expectedListSize = 10_000;
		for(int i = 0; i < expectedListSize; i++){
			largeList.prepend(1.0*i);
		}
		assertEquals(expectedListSize,largeList.size());
		
		for(int i = 0; i < expectedListSize; i++){
			assertTrue(largeList.getElement(i)==1.0*(expectedListSize-1-i));
		}
		largeList.removeLast();
		for(int i = 0; i < expectedListSize-1; i++){
			assertTrue(largeList.getElement(i)==1.0*(expectedListSize-1-i));
		}
	}
	
	@Test
	public void testRemoveLastMultipleTimes(){
		RollingList largeList = new RollingList(1.2f,10);
		int expectedLargeSize = 10000000;
		for(int i = 0; i < expectedLargeSize; i++){
			largeList.append(i*1.0);
		}
		assertEquals(expectedLargeSize,largeList.size());
		
		// remove the last 500 elements on list
		for(int i = 0; i < 500; i++){
			largeList.removeLast();
		}
		assertEquals(expectedLargeSize-500,largeList.size());
		
		for(int i = 0; i < expectedLargeSize-500; i++){
			assertTrue(largeList.getElement(i)==i*1.0);
		}
	}
	
	@Test
	public void testSetElement(){
		RollingList myList = new RollingList(1.0002f,10);
		for(int i = 0; i < 10_000; i++){
			myList.append(i*1.0);
		}
		myList.setElement(100,1.0*(-100));
		for(int i = 0; i< 10_000; i++){
			if(i==100){
				assertTrue(myList.getElement(i)==1.0*(-100));
			}
			else{
				assertTrue(myList.getElement(i)==1.0*i);
			}
		}
	}

}
