package lab03;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Lab03PasswordGenerator implements a PasswordGenerator, and is itself Generic. 
 * 
 */
public class Lab03PasswordGenerator <T extends Passwordable> implements PasswordGenerator<T> {

	/**
	 * Call createMd5Hash, and pass in the "passwordableFiled" from the supplied key.
	 * Print out the full hash, then return the first eight characters.
	 * @param key
	 * @return
	 */
	// missing method from interface
	
	@SuppressWarnings("unused")
	private String createMd5Hash(String input) {
		MessageDigest md5 = getmd5();
		md5.update(input.getBytes());
		byte byteData[] = md5.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteData.length; i++) {
			//convert byte to pretty ascii string, ensuring a leading zero.
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	private MessageDigest getmd5() {
		try {
			 return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// MD5 should always work.
		}
		return null;
	}
	
	public static void main(String[] args) {
		// make a Lab03PasswordGenerator that can take any passwordable. What can be passed into the generatePassword method? 
		// make a Lab03PasswordGenerator that can take any CoursePersonel. What can be passed into the generatePassword method? 
		// make a Lab03PasswordGenerator that can take any TeacherAssistant. What can (and cannot) be passed into the generatePassword method? 
		
		// make a Lab03PasswordGenerator that is parameterized with Students
		// Run the password function, and supply it to cs2420-lab.eng.utah.edu
	}

	@Override
	public String generatePassword(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
