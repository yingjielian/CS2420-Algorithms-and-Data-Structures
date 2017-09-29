package assignment03;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyTest {

	@Test
	public void SizeTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 1.0);
		myTest.insert(2, 1.0);
		myTest.insert(3, 1.0);
		myTest.insert(4, 1.0);
		myTest.insert(5, 1.0);
		myTest.insert(6, 1.0);

		assertEquals(7, myTest.size());
	}

	@Test
	public void SizeTest1() {

		// MyFactory<String> test = new MyFactory<String>();
		// UtahList<String> ArrayTest=test.makeArrayList();
		// ArrayTest.insert(0, "a");
		// ArrayTest.insert(1, "b");
		// ArrayTest.insert(2, "c");
		// ArrayTest.insert(3, "d");
		// ArrayTest.insert(4, "e");
		//
		// assertEquals(5, ArrayTest.size());
	}

	@Test
	public void getTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);

		assertEquals(myTest.getElement(1).equals(2.0), true);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getTest1() {
		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		assertEquals(myTest.getElement(1).equals(2.0), true);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getTest2() {
		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		assertEquals(myTest.getElement(-1).equals(2.0), true);
	}

	@Test
	public void insertTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.insert(2, 7.0);
		assertEquals(4, myTest.size());
		assertEquals(myTest.getElement(2).equals(7.0), true);
		assertEquals(myTest.getElement(3).equals(3.0), true);

	}

	@Test
	public void insertTest1() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(0, 2.0);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(0).equals(2.0), true);
		assertEquals(myTest.getElement(1).equals(1.0), true);

	}

	@Test
	public void insertTest2() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(1, 5.0);
		myTest.insert(2, 3.0);
		myTest.insert(2, 7.0);
		assertEquals(5, myTest.size());
		assertEquals(myTest.getElement(0).equals(1.0), true);
		// System.out.println(myTest.getElement(1));
		assertEquals(myTest.getElement(1).equals(5.0), true);

		assertEquals(myTest.getElement(2).equals(7.0), true);
		assertEquals(myTest.getElement(3).equals(3.0), true);
		assertEquals(myTest.getElement(4).equals(2.0), true);

	}

	@Test
	public void removeZeroTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.remove(0);
		assertEquals(0, myTest.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeLastTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.remove(2);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(2).equals(3.0), true);

	}

	@Test
	public void removeMidTest1() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.remove(1);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(1).equals(3.0), true);

	}

	@Test
	public void removeMidTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.remove(1);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(1).equals(3.0), true);

	}

	@Test
	public void removeMidTest2() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);// index1
		myTest.insert(2, 2.0);// index2
		myTest.insert(3, 2.0);// index3
		myTest.insert(4, 2.0);// index4
		myTest.insert(5, 3.0);
		myTest.remove(1);
		myTest.remove(1);
		myTest.remove(1);
		myTest.remove(1);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(1).equals(3.0), true);

	}
	
	@Test
	public void removeMidTest3() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeArrayList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);// index1
		myTest.insert(2, 3.0);// index2
		myTest.insert(3, 4.0);// index3
		myTest.insert(4, 5.0);// index4
		myTest.insert(5, 6.0);
		myTest.remove(1);
		myTest.remove(1);
		assertEquals(4, myTest.size());
		assertEquals(myTest.getElement(2).equals(5.0), true);

	}

	@Test
	public void setTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeLinkedList();
		myTest.insert(0, 1.0);
		myTest.setElement(0, 2.0);
		// System.out.println(myTest.getElement(0));
		assertEquals(myTest.getElement(0).equals(2.0), true);
	}
	@Test
	public void ArraysetTest() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeArrayList();
		myTest.insert(0, 1.0);
		myTest.setElement(0, 2.0);
		// System.out.println(myTest.getElement(0));
		assertEquals(myTest.getElement(0).equals(2.0), true);
	}
	@Test
	public void ArraysetTest1() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeArrayList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.setElement(2, 4.0);
		assertEquals(myTest.getElement(2).equals(4.0), true);
	}
	@Test
	public void ArrayRemoveTest1() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeArrayList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);
		myTest.insert(2, 3.0);
		myTest.remove(1);
		assertEquals(2,myTest.size());
		assertEquals(myTest.getElement(1).equals(3.0), true);
	}
	@Test
	public void removeArrayTest2() {

		MyFactory<Double> test = new MyFactory<Double>();
		UtahList<Double> myTest = test.makeArrayList();
		myTest.insert(0, 1.0);
		myTest.insert(1, 2.0);// index1
		myTest.insert(2, 2.0);// index2
		myTest.insert(3, 2.0);// index3
		myTest.insert(4, 2.0);// index4
		myTest.insert(5, 3.0);
		myTest.remove(1);
		myTest.remove(1);
		myTest.remove(1);
		myTest.remove(1);
		assertEquals(2, myTest.size());
		assertEquals(myTest.getElement(1).equals(3.0), true);

	}
}
