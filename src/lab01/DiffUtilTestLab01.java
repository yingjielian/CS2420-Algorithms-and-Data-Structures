package lab01;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class DiffUtilTestLab01 {
	
//	@BeforeClass
//	public static void init()
//	{
//		
//	}
//	
//	@AfterClass
//	public static void deconstruct()
//	{
//		
//	}
//	
//	@After 
//	public void teardown()
//	{
//		
//	}
//	
//	
//	@Before // Run the method before every time
//	public void setup()
//	{
//		
//	}

	@Test
	public void arrayFullofZeroes() {
		int [] zeroes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int result = DiffUtil.findSmallestDiff(zeroes);
//		if(result == 0) {
//			System.out.println("Test Passed.");
//		}
//		else
//		{
//			System.out.println("Test Failed :( ");
//		}
		Assert.assertEquals(0, result); //Change the import to "import org.junit.Assert.*"
	}
	
	@Test
	public void twoItemsPositiveAscending()
	{
		int[] twoItems = new int[]{11, 16};
		int result = DiffUtil.findSmallestDiff(twoItems);
		Assert.assertEquals(5, result);
	}
	
	@Test
	public void negativeAlternating()
	{
		int[] array = new int[]{-10, -5, -15, -3, -17};
		int result = DiffUtil.findSmallestDiff(array);
		Assert.assertEquals(2, result);
	}
	@Test
	public void you()
	{
		
	}
	
	@Test
	public void you()
	{
		int[] deadly = null;
		int result = DiffUtil.findSmallestDiff(deadly);
		Assert.assertEquals(-1, result);
	}
}
