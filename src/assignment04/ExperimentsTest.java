package assignment04;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ExperimentsTest {

	@Test
	public <T> void insertionSortTest() {

		Integer[] myIntArray = new Integer[] { 1, 2, 3, 4, 5, 9, 8, 7, 6 };
		Integer[] myIntArray1 = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Experiments.insertionSort(myIntArray, 0, myIntArray.length, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		});
		assertArrayEquals(myIntArray, myIntArray1);
	}

	@Test
	public <T> void test1() {
		

		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 8, 6, 4 };
		Integer[] myIntArray1 = new Integer[] { 6, 4, 3, 2, 7, 8, 9 };
		int a = Experiments.partition(myIntArray, 0, myIntArray.length, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		});
	//	System.out.println(Arrays.toString(myIntArray));
		assertArrayEquals(myIntArray, myIntArray1);
		assertEquals(a, 4);

	}
	
	@Test
	public <T> void test2() {
		

		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 8, 6, 4 };
		Integer[] myIntArray1 = new Integer[] { 2, 3, 4, 6, 7, 8, 9 };
		Experiments.quicksortWithCutoff(myIntArray, 0, myIntArray.length, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		}, 0);
		//System.out.println(Arrays.toString(myIntArray));
		assertArrayEquals(myIntArray, myIntArray1);

	}
	
	@Test
	public <T> void test3() {
		

		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 8, 6, 4 };
		Integer[] myIntArray1 = new Integer[] { 2, 3, 4, 6, 7, 8, 9 };
		Experiments.quicksort(myIntArray, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		});
		//System.out.println(Arrays.toString(myIntArray));
		assertArrayEquals(myIntArray, myIntArray1);

	}
//	@Test
//	public <T> void MegerSortTest() {
//		
//
//		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 8, 6, 4 };
//		Integer[] myIntArray1 = new Integer[] { 2, 3, 4, 6, 7, 8, 9 };
//		Experiments.threeWayMergesort(myIntArray, new Comparator<Integer>() {
//			public int compare(Integer leftHandSide, Integer rightHandSide) {
//				return leftHandSide.compareTo(rightHandSide);
//			}
//		});
//		System.out.println(Arrays.toString(myIntArray));
//		assertArrayEquals(myIntArray, myIntArray1);
//
//	}
//	
	
	@Test
	public <T> void MegerSortTest1() {
		

		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 6, 8, 4 };
		Integer[] myIntArray1 = new Integer[] { 2, 3, 4, 6, 7, 8, 9 };
		Experiments.twoWayMergesort(myIntArray, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		});
		System.out.println(Arrays.toString(myIntArray));
		assertArrayEquals(myIntArray, myIntArray1);
		
		String[] testArr = Experiments.generateMixedStrings(20, 1);
//		System.out.println(Arrays.toString(testArr));
//		
//		System.out.print(Experiments.comparisonCount);

	}
	@Test
	public <T> void MegerSortTest3() {
		

		Integer[] myIntArray = new Integer[] { 2, 9, 3, 7, 6, 8, 4 };
		Integer[] myIntArray1 = new Integer[] { 2, 3, 4, 6, 7, 8, 9 };
		Experiments.threeWayMergesort(myIntArray, new Comparator<Integer>() {
			public int compare(Integer leftHandSide, Integer rightHandSide) {
				return leftHandSide.compareTo(rightHandSide);
			}
		});
	//	System.out.println(Arrays.toString(myIntArray));
		assertArrayEquals(myIntArray, myIntArray1);
		
		String[] testArr = Experiments.generateMixedStrings(20, 1);
//		System.out.println(Arrays.toString(testArr));
//		
//		System.out.print(Experiments.comparisonCount);

	}
	
	

}
