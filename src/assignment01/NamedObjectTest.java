package assignment01;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * This class is the test class for the NamedObject class. This will
 * test every methods inside NamedObject class.
 * 
 * @author Yingjie Lian
 * @class CS-2420
 * @version 08/31/2017
 *
 */
public class NamedObjectTest {
	
	/**
	 * The following tests are going to test getName method. It
	 * has two cases: when name is null or general case.
	 * When the name is null, it will throw a IllegalArgumentException.
	 */
	@Test
	public void getName_general_case_test01()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		String name01 = object01.getName();
		assertEquals("nba", name01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getName__null_case_test02()
	{
		NamedObject object02 = new NamedObject(null, Color.BLACK);
	}
	
	/**
	 * The following tests are going to test getObjct method. It
	 * has two cases: when object is null or general case.
	 */
	@Test
	public void getObject_general_case_test01()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		Object getObject01 = object01.getObject();
		assertEquals(Color.BLACK, getObject01);
	}
	
	@Test
	public void getObject__null_case_test02()
	{
		NamedObject object03 = new NamedObject("nba", null);
		Object getObject02 = object03.getObject();
		assertEquals(getObject02, null);
	}
	
	/**
	 * The following tests are going to test setName method. It
	 * has two cases: when the parameter newName is null or 
	 * general case. When the parameter newName is null, it will 
	 * throw a IllegalArgumentException.
	 */
	@Test
	public void setName_general_case_test01()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		object01.setName("NFL");
		String newName01 = object01.getName();
		assertEquals(newName01, "NFL");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setName__null_case_test02()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		object01.setName(null);
	}
	
	/**
	 * 
	 * The following tests are going to test equals method. It
	 * has several different cases: when the object is null, when
	 * the object is not type of NamedObject, the names are different, 
	 * and so on. However, only one case the equals method will return
	 * true is their names and objects are exact the same (two null 
	 * object are also the same objects).
	 */
	@Test
	public void equals_null_case_test01()
	{
		NamedObject object01 = new NamedObject("nba", null);
		boolean equals01 = equals(object01);
		assertFalse(equals01);
	}
	
	@Test
	public void equals__differentObject_case_test02()
	{
		Object notNamedObject01 = Color.BLUE;
		boolean equals02 = equals(notNamedObject01);
		assertFalse(equals02);
	}
	
	@Test
	public void equals__differentName_case_test03()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		NamedObject object02 = new NamedObject("NFL", Color.BLACK);
		boolean equals03 = object01.equals(object02);
		assertFalse(equals03);
	}
	
	@Test
	public void equals__OneHasNull_case_test04()
	{
		NamedObject object01 = new NamedObject("nba", Color.BLACK);
		NamedObject object04 = new NamedObject("nba", new String[]{"a", "b", null, "c"});
		boolean equals04 = object01.equals(object04);
		assertFalse(equals04);
	}
	
	@Test
	public void equals__BothAreNull_case_test05() 
	{
		NamedObject object04 = new NamedObject("nba", null);
		NamedObject object01 = new NamedObject("nba", null);
		boolean equals05 = object01.equals(object04);
		assertTrue(equals05);
	}
	
	@Test
	public void equals__BothAreTheSame_case_test06() 
	{
		NamedObject object04 = new NamedObject("nba", Math.PI);
		NamedObject object01 = new NamedObject("nba", Math.PI);
		boolean equals06 = object01.equals(object04);
		assertTrue(equals06);
	}
	
	/**
	 * The following tests are going to test hashCode method. It has
	 * 3 cases: different name with same object, same name with different
	 * object, same object and same name. Only the last case, their hashcode
	 * will be the same.
	 */
	@Test
	public void hashCode__differentName_case_test01() 
	{
		NamedObject object01 = new NamedObject("nba", Math.PI);
		NamedObject object02 = new NamedObject("NFL", Math.PI);
		int hash01 = object01.hashCode();
		int hash02 = object02.hashCode();
		assertFalse(hash01 == hash02);
	}
	
	@Test
	public void hashCode__differentObject_case_test02() 
	{
		NamedObject object01 = new NamedObject("nba", Math.PI);
		NamedObject object02 = new NamedObject("nba", Math.abs(-88));
		int hash01 = object01.hashCode();
		int hash02 = object02.hashCode();
		assertFalse(hash01 == hash02);
	}
	
	@Test
	public void hashCode__SameNameAndObject_case_test03() 
	{
		NamedObject object01 = new NamedObject("nba", Math.PI);
		NamedObject object02 = new NamedObject("nba", Math.PI);
		int hash01 = object01.hashCode();
		int hash02 = object02.hashCode();
		assertTrue(hash01 == hash02);
	}
	
	/**
	 * The following tests are going to test chain method. 
	 * 
	 */
	@Test(expected = NullPointerException.class)
	//If the parameter String Array is null, it will throw a NullPointerException
	public void chain__null_case_test01() 
	{
		String[] array01 = null;
		NamedObject object01 = NamedObject.chain(array01);
	}
	
	//If the parameter String Array is empty, it will throw a IllegalArgumentException
	@Test(expected = IllegalArgumentException.class)
	public void chain__emptyString_case_test02() 
	{
		String[] array01 = new String[]{};
		NamedObject object01 = NamedObject.chain(array01);
	}
	
	//If the parameter String Array cotains null, it will throw a IllegalArgumentException
	@Test(expected = IllegalArgumentException.class)
	public void chain__StringContainsNull_case_test03() 
	{
		String[] array01 = new String[]{"a", "b", null, "c"};
		NamedObject object01 = NamedObject.chain(array01);
	}
	
	
	@Test
	// Test if the first object has the same header with its name
	public void chain__general_case_test04() 
	{
		String[] array01 = new String[]{"a", "b", "c", "d", "e"};
		NamedObject object01 = NamedObject.chain(array01);
		String a = object01.getName();
		assertEquals(a, "a");
	}
	
	@Test
	// Going through all the chain before null, check if the object has the same header
	// with its name
	public void chain__general_case_test05() 
	{
		String[] array01 = new String[]{"a", "b", "c", "d", "e"};
		NamedObject object01 = NamedObject.chain(array01);
		NamedObject object02 = (NamedObject) NamedObject.chain(array01).getObject();
		NamedObject object03 = (NamedObject) object02.getObject();
		NamedObject object04 = (NamedObject) object03.getObject();
		NamedObject object05 = (NamedObject) object04.getObject();
		String e = object05.getName();
		assertEquals(e, "e");
	}
	
	@Test
	// Going through all the chain until the last object, check if the last object
	// is null
	public void chain__lastObjectInChain_case_test06() 
	{
		String[] array01 = new String[]{"a", "b", "c", "d", "e"};
		NamedObject object01 = NamedObject.chain(array01);
		NamedObject object02 = (NamedObject) NamedObject.chain(array01).getObject();
		NamedObject object03 = (NamedObject) object02.getObject();
		NamedObject object04 = (NamedObject) object03.getObject();
		NamedObject object05 = (NamedObject) object04.getObject();
		NamedObject object06 = (NamedObject) object05.getObject();
		assertEquals(null, object06);
	}
	
	/**
	 * The following tests are going to test unchain method. 
	 */
	@Test(expected = ClassCastException.class)
	// If the parameter object is not the type of NamedObject, it will
	// throw a ClassCastException
	public void unchain__differentTypeObject_case_test01()
	{
		Object array01 = new String[]{"a", "b", "c", "d", "e"};
		String[] array02 = NamedObject.unchain((NamedObject) array01);
	}
	
	@Test 
	// Test if the method can unlock the input NamedObject into a String array
	public void unchain__general_case_test02()
	{
		String[] array01 = new String[]{"a", "b", "c", "d", "e"};
		NamedObject object01 = NamedObject.chain(array01);
		String[] array2 = NamedObject.unchain(object01);
		assertArrayEquals(array01, array2);
	}
	
	@Test 
	// Test if the method can unlock the input NamedObject into a String array
	public void unchain__general_case_test03()
	{
		String[] array01 = new String[]{"a", "b", "c", "d", "e"};
		NamedObject object01 = NamedObject.chain(array01);
		String[] array2 = NamedObject.unchain(object01);
		assertArrayEquals(array01, array2);
	}
	
	// Test if chain is null.
	public void unchain__general_case_test04()
	{
		String[] array01 = null;
		NamedObject object01 = NamedObject.chain(array01);
		String[] array2 = NamedObject.unchain(object01);
		assertArrayEquals(array01, array2);
	}
}
