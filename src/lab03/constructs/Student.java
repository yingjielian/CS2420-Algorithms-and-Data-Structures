package lab03.constructs;

import lab03.Passwordable;

@SuppressWarnings("unused")
public class Student implements Passwordable {

	private String uid;
	private String name;
	
	public Student(String name, String uid) {
		this.name = name;
		this.uid = uid.toLowerCase();
		if(!uid.startsWith("u")) {
			throw new IllegalArgumentException("Your UID needs to start with a u");
		}
		if(uid.length() != 8) {
			throw new IllegalArgumentException("Your UID has the incorrect number of characters. Should be 8");
		}
	}
	@Override
	public String getPasswordableKey() {
		return this.uid;
	}
}
