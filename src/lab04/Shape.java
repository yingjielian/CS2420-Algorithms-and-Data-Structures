package lab04;

import java.util.ArrayList;
import java.util.List;

public class Shape<T> {

	List<Shape> shapes = new ArrayList<Shape>();
	List<Circle> circles = new ArrayList<Circle>();
	List<Square> squares = new ArrayList<Square>();{


		circles = shapes; // Illegal 

	}
}
