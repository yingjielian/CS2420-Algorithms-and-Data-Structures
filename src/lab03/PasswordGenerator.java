package lab03;


/** 
 * 
 * PasswordGenerator needs to be generic so it can generate passwords for anything that 
 * is Passwordable. 
 */
public interface PasswordGenerator <T extends Passwordable>{
	
	public String generatePassword(T key); // Change parameter type
}
