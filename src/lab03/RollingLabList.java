package lab03;

import java.util.ArrayList;

public class RollingLabList<T extends Comparable<T>> implements RollingList<T> {
	// Second "T" is just parameterized.
	// First "T" is making it generic
	public static void main() 
	{
//		RollingLabList<Double> list = new RollingLabList<>();
//		list.append(0.0);
//		Double d = list.removeFirst();
//		
//		RollingLabList<String> strings = new RollingLabList<>();
//		strings.append("String");
//		strings.append(0.0);
		
//		RollingLabList<Double> list = new RollingLabList<>();
//		RollingLabList<String> strings = new RollingLabList<>();
		
//		ArrayList<? extends Number> list = new ArrayList<>();
		
		ArrayList<? super Number> list;
		list = new ArrayList<Number>();
		list = new ArrayList<Object>();
		
//		list.add(new Object()); // It's null inside.
		list.add(1);
		
//		Comparable<?> comp = list.get(0); // Usable for return type
		
		Object returned = list.get(0);


	}

	@Override
	public T getElement(int i) {
		
//		T[] array;
//		T[] array1 = new T[0];
//		T[] array2 = (T[])new Object[0];
		
		return null;
	}

	@Override
	public void setElement(int i, T value) {
		value.compareTo(0.0);

	}

	@Override
	public void append(T value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepend(T value) {
		// TODO Auto-generated method stub

	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}