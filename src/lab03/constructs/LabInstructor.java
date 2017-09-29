package lab03.constructs;

public class LabInstructor extends CoursePersonel {
	
	public LabInstructor(String name) {
		super(name);
	}

	@Override
	public String getPasswordableKey() {
		return "SUDO ACCESS";
	}

}
