package assignment01;
/**
 * This class contains 1 constructor, 2 accessors, 1 mutator, and 4 utility methods.
 * 
 * This class implements this idea: Creating a NamedObject. A NamedObject is an object 
 * that bundles together some String and some other object (a name and an object). The 
 * enclosed name (the String) is never null, but the enclosed object may be null.
 * 
 * @author Yingjie Lian
 * @class CS-2420
 * @version 08/31/2017
 */
public class NamedObject extends Object
{
	// Fields (object variables)
	private String name;
	private Object object;
	
	/**
	 * Constructor - initializes this object to contain the String
	 * name and object that are sent in by the caller (whoever 
	 * used 'new NamedObject').
	 * 
	 * @param name Any non-null String
	 * @param object Any object
	 * 
	 * Throws: java.lang.IllegalArgumentException - if the name is null
	 */
	public NamedObject(String name, Object object)
	{
		// Make sure the name is not null, otherwise, throw Exception
		if (name == null) 
			throw new IllegalArgumentException("The name is null.");

		this.name = name;
		this.object = object;
	}

	/**
	 * Accessor: Get the name of the NamedOject Object
	 * @return name of NamedOject Object
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Accessor: Get the object of the Object
	 * @return object of the Object
	 */
	public Object getObject()
	{
		return object;
	}

	/**
	 * Mutator: Set the name of the NamedOject Object with a new name
	 * @param newName String
	 * 
	 * Throws: java.lang.IllegalArgumentException - if the new name is null
	 */
	public void setName(String newName)
	{
		if (newName == null)
			throw new IllegalArgumentException("The new name is null.");

		name = newName;
	}

	/**
	 * Returns true if and only if this NamedObject and and the other NamedObject 
	 * have the same names and objects (using .equals). Null enclosed objects are 
	 * only considered equal to other null object references.
	 * 
	 * Because one Object has two parts: the name and its object content, so this 
	 * method will check null, type of the object, name and object contents step by 
	 * step. If two objects have all the parts match, they are equal!
	 * 
	 * Overrides: equals in class java.lang.Object
	 */
	public boolean equals(Object other)
	{
		if(other == null)
			return false;

		// Check if it is the type of NamedObject
		if(!(other instanceof NamedObject))
			return false;

		NamedObject castedOther = (NamedObject) other; // Make sure it is the type of NamedObject

		if(!this.name.equals(castedOther.getName())) // Check if two objects have the same name
			return false;

		// Check if two Object contain null inside its contents. There are 4 different situations. For example,
		// if we have Object A and Object B. If the contents of A is null, but the contents of B is not null, 
		// Obviously, they are not equal. Similarly, if the contents of A is not null, but the contents of B 
		// is null, they are not equal. However, if the contents of A and B are null, they are equal.
		if((this.getObject() == null && castedOther.getObject() != null) || (this.getObject() != null && castedOther.getObject() == null))
			return false;
		if(this.getObject() == null && castedOther.getObject() == null)
			return true;
		
		// If the contents of both A and B are not null, use .equals to check their contents.
		else
			return castedOther.getObject().equals(this.getObject());
	}

	/**
	 * Returns a hash value for this NamedObject. For this class because the object has two
	 * parts: name and object. So the hashCode value will contain two parts for each object.
	 * If they have both the same name and same object, they have the same hashcode value; 
	 * otherwise not.
	 * 
	 * If the object' object is null, it only has its name hashcode value.
	 * 
	 * Overrides: hashCode in class java.lang.Object
	 */
	public int hashCode()
	{
		int hash = 1;

		if(this.object != null)
		{	
			// The hash number can plus or multiply any integers, but 
			// different type must plus or multiply different integers.
			hash = hash * 2 + name.hashCode();
			hash = hash * 4 + this.object.hashCode();
		}
		else
		{
			hash = hash * 2 + name.hashCode();
		}
		return hash;
	}


	/**
	 * This method creates one NamedObject per String, using each String 
	 * in the array as the Name for each NamedObject. Each NamedObject will 
	 * store another NamedObject in its enclosed object field such that the 
	 * NamedObject with the name (data[n]) will have as it's enclosed object
	 * the NamedObject with the name (data[n+1]). The very last NamedObject 
	 * with the name (data[data.length-1]) will have a null enclosed object. 
	 * The returned NamedObject will have the name (data[0]) and will be chained 
	 * to the rest of the NamedObjects.
	 * 
	 * @param data array of non-null Strings
	 * @return a chain of NamedObjects containing the Strings as Names
	 * 
	 * Throws:
	 * java.lang.IllegalArgumentException - if the data array is empty or a String is null
	 * java.lang.NullPointerException - if the data array is null
	 * 
	 */
	public static NamedObject chain(String[] data)
	{
		if(data == null)
			throw new NullPointerException(); 
		
		// If the data array is empty, throw IllegalArgumentException
		if(data.length == 0)
			throw new IllegalArgumentException();
		// If the data array is null, throw IllegalArgumentException
		for(int dataLength = 0; dataLength < data.length; dataLength++)
		{
			if(data[dataLength] == null)
				throw new IllegalArgumentException();
		}
			
		//Initialize
		NamedObject current, result;

		// Because for the last chain, the object is null and its
		// name is the (n-1)th string inside the String array.
		// So I store the last chain first.
		result = new NamedObject(data[data.length - 1], null);
		
		// Use "for" loop set each item in the String array into 
		// the chain as NamedObject object
		for(int length = 1; length < data.length; length++)
		{
			current = new NamedObject(data[data.length - 1 - length], result);
			result = current;
		}
		return result;
	}



	/**
	 * This method extracts and returns the names stored in a chain of
	 * NamedObjects. Each NamedObject in the chain must have an object 
	 * that is another NamedObject, or null (to end the chain). The 
	 * returned array will have one element per NamedObject in the chain. 
	 * The elements will contain the names from this chain in the order 
	 * that they appear in the chain. (The name extracted from the parameter 
	 * 'chain' will be in the 0th array position, etc.) If 'chain' is null, 
	 * a zero-length array is returned.
	 * 
	 * @param chain A NamedObject chained to 0 or more other chained NamedObjects
	 * @return an array of Strings corresponding to the names in the chain
	 * 
	 * Throws: java.lang.ClassCastException - if the chain does not contain only NamedObjets
	 */
	public static String[] unchain(NamedObject chain)
	{

		if(!(chain instanceof NamedObject))
			throw new ClassCastException();
		
		NamedObject current = chain;
		NamedObject temporary = chain;
		int count = 1;

		// Use "while" loop to count how many objects inside the 'chain'
		// object. The idea of using "while" loop is because we do not 
		// know the amount of items, but we do know the last object must
		// be null. So when its get to the null, the "while" loop will 
		// stop, and we will know how many times it looped
		
		while(current.getObject() != null)
		{
			count++;
			// This will go through the 'chain' object until null (the last object)
			current = (NamedObject) current.getObject(); 
		}

		// Initialize the String array, make the array size equals
		// to the same amount of objects of the 'chain' object
		String[] result =  new String[count];

		// Set all the objects of the 'chain' object into the String 
		// array that we just initialized
		for(int length = 0; length < count; length++)
		{
			result[length] = temporary.getName().toString();
			temporary = (NamedObject) temporary.getObject(); 
		}

		return result;
	}
}

