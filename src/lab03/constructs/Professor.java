package lab03.constructs;

@SuppressWarnings("unused")
public class Professor extends LabInstructor {

	private boolean tenure;

	public Professor(String name, boolean tenure) {
		super(name);
		this.tenure = tenure;
	}

	@Override
	public String getPasswordableKey() {
		return "UNLIMITED ACCESS";
	}

}
