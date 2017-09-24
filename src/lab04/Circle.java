package lab04;

import java.util.ArrayList;
import java.util.List;

public class Circle<T> extends Shape {

	List<Shape> shapes = new ArrayList<Shape>();
	List<Circle> circles = new ArrayList<Circle>();
	List<Square> squares = new ArrayList<Square>();{

		shapes.add(new Square()); 
	}
}