package lab03.constructs;

import lab03.Passwordable;

public abstract class CoursePersonel implements Passwordable{
	private String name;
	public CoursePersonel(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
