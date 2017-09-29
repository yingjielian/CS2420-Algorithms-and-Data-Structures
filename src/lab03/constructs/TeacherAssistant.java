package lab03.constructs;

import lab03.Passwordable;

@SuppressWarnings("unused")
public class TeacherAssistant extends CoursePersonel {
	private boolean parttime;
	private String rank;
	private String name;
	public TeacherAssistant(String name, String rank, boolean parttime) {
		super(name);
		this.name = name;
		this.rank = rank;
		this.parttime = parttime;
	}
	
	@Override
	public String getPasswordableKey() {
		// An example of the ternary operator! Looks confusing, 
		// I just want to introduce it now. We'll talk about it later. 
		return parttime ? name + rank : name;
	}

}
