package lab05;

import java.util.Comparator;
import java.util.List;

import lab05.database.Database;
import lab05.database.Student;
import lab05.database.Tester;

/**
 * Comparator Practice for Lab 05, CS2420 - Fall 2017 
 * @author ryans
 */
public class ComparatorPractice {

	//	public static class StringAsNumberComparator implements Comparator<String>
	//	{
	//		public int compare(String lhs, String rhs)
	//		{
	//			return Integer.parseInt(lhs) - Integer.parseInt(rhs);
	////			if(Integer.parseInt(lhs) == Integer.parseInt(rhs))
	////			{
	////				return 0;
	////			}
	////			if(Integer.parseInt(lhs) > Integer.parseInt(rhs))
	////			{
	////				return 1;
	////			}
	////			return -1;
	//		}
	//	}

	public static void main(String[] args) {
		Database database = new Database();
		Tester tester = new Tester();

		/*
		 * This is an example of using a named Comparator Class. 
		 * Check it out at the bottom of this file!
		 */
		tester.sortByName(new NameComparator());
		/** OR */
		/*
		 * This is an example of using a comparator as an "Anonymous Class"
		 */
		tester.sortByName(new Comparator<Student>() {
			@Override
			public int compare(Student leftHandSide, Student rightHandSide) {
				return leftHandSide.name.compareTo(rightHandSide.name);
			}
		});
		/** OR */
		/*
		 * This is an example of using Java 8's Lambda notation.
		 */
		tester.sortByName((leftHandSide, rightHandSide) -> leftHandSide.name.compareTo(rightHandSide.name));

		/*
		 * The rest are up to you! How many can you get?
		 * You can test your comparator against the "Database" like so...
		 */

		List<Student> studentsOrderedByName = database.getStudentsOrderedBy(new NameComparator()); // or any other Comparator synatx.
		//		printStudentList(studentsOrderedByName); // comment me out to avoid printint out the entire list.

		/* 
		 * Sort the students by their Major alphabetically, 
		 * 	then by their gpas in reverse order (high to low), 
		 * 		and list all the girls before the boys.
		 */
		tester.sortByMajor_ReverseGPA_GirlsFirst(new Comparator<Student>(){
			public int compare(Student lhs, Student rhs){
				int result = lhs.major.compareTo(rhs.major);
				if(result == 0){
					result = Double.compare(rhs.gpa , lhs.gpa); // Change order because we want high to low
					if (result == 0)
					{
						result = lhs.gender.compareTo(rhs.gender);
					}

				}
				return result;
			}
		});

		/*
		 * Sort the students by the number of cats they have, 
		 *   then number of dogs,
		 *      finally by reverse order of their LAST name.
		 *      (All names have the format: First Last)
		 */
		tester.sortBy_numCats_numDogs_ReverseLastName(new Comparator<Student>(){
			public int compare(Student lhs, Student rhs){
				int result = Integer.compare(lhs.numCats, rhs.numCats);
				if(result == 0)
				{
					result = Integer.compare(lhs.numDogs, rhs.numDogs);
					if(result == 0)
					{
						String[] lhsName = lhs.name.split(" ");
						String[] rhsName = rhs.name.split(" ");

						result = rhsName[1].compareTo(lhsName[1]);
					}
				}
				return result;
			}
		});

		/*
		 * Sort the students by the length of their name (shorter first)
		 *    then by their uid in lexicographical order.
		 */
		tester.sortByNameLength_UID(new Comparator<Student>(){

			@Override
			public int compare(Student lhs, Student rhs) {
				int result = Double.compare(lhs.name.length(), rhs.name.length());
				if(result == 0)
				{
					String[] lhsUid = lhs.uid.split("u");
					String[] rhsUid = rhs.uid.split("u");

					int lhsUID = Integer.parseInt(lhsUid[1]);
					int rhsUID = Integer.parseInt(rhsUid[1]);

					for(int i = 6; i > 0; i--)
					{
						result = Integer.compare((int)((lhsUID + Math.pow(10, i)) / Math.pow(10, i)), (int)((rhsUID + Math.pow(10, i)) / Math.pow(10, i)));

					}

				}
				return result;
			}

		});

		/*
		 * Sort by UID in lexicographical order
		 *    sort by GPA (high to low)
		 *       sort by age (Younger to older)
		 */
		tester.sortByUid_ReverseGPA__age(new Comparator<Student>(){
			public int compare(Student lhs, Student rhs) {
				int result = 0;
				String[] lhsUid = lhs.uid.split("u");
				String[] rhsUid = rhs.uid.split("u");

				int lhsUID = Integer.parseInt(lhsUid[1]);
				int rhsUID = Integer.parseInt(rhsUid[1]);

				for(int i = 6; i > 0; i--)
				{
					result = Integer.compare((int)((lhsUID + Math.pow(10, i)) / Math.pow(10, i)), (int)((rhsUID + Math.pow(10, i)) / Math.pow(10, i)));

				}

				return result;
			}

		});

		/*
		 * Sort by names that have the fewest number of vowels (aeiou) first
		 *    then by who has more dogs
		 *       then by GPA (high to low)
		 *       
		 *       These are the best students.
		 */

		tester.sortBySmallestNumberOfVowels_Dogs_GPA(new Comparator<Student>(){
			public int compare(Student lhs, Student rhs) {

				int lhsNameVowels = countVowels(lhs.name);
				int rhsNameVowels = countVowels(rhs.name);
				
				// Don't think there is something wrong!
				int result = Integer.compare(lhsNameVowels, rhsNameVowels);
				if(result == 0 )
				{
					result = Integer.compare(rhs.numDogs, lhs.numDogs);
					if(result == 0 )
					{
						result = Double.compare(rhs.gpa , lhs.gpa);
					}
				}

				return result;

			}

		});

		tester.runTest();
	}

	public static int countVowels(String s)
	{
		int result = 0;
		String lowerCase = s.toLowerCase();
		for(int i = 0; i < s.length(); i++)
		{
			
			Character cha = lowerCase.charAt(i);
			if(cha.equals('a') || cha.equals('e') || cha.equals('i') || cha.equals('o') || cha.equals('u'))
				result++;
		}
		return result;
	}

	/**
	 * You can use this method to print out the list returned from the database.
	 * Only use it for debugging purposes.
	 * @param students
	 */
	private static void printStudentList(List<Student> students) {
		students.forEach(System.out::println); // <-- this is some funky Java 8 syntax! 
	}

	/**
	 * This is an example of how you can define a class to be a comparator.
	 * Usually this is done if the comparator has dependencies on other data, and needs 
	 * work done inside its constructor.
	 * @author ryans
	 */
	private static class NameComparator implements Comparator<Student> {
		@Override
		public int compare(Student leftHandSide, Student rightHandSide) {
			return leftHandSide.name.compareTo(rightHandSide.name);
		}

	}

}
