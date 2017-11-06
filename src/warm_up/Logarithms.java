package warm_up;

import java.util.Scanner;

public class Logarithms 
{
	public static void main (String[] args)
	{
		double logValue = 0.0, number, base;
		int j = 0;
//		System.out.println("Hello World"); // #2
//		System.out.println("Input a number: "); 
//		Scanner in = new Scanner(System.in);
//		number = in.nextDouble();
//		System.out.println("Input a base: "); 
//		base = in.nextDouble();
		
//		logValue = naturalLog(in.nextDouble()); // #3
//		System.out.println(logValue);
//		
		for(double i = 100; i <= 1000; i += 100) // #4 & #6
		{
			logValue = logBaseTen(i);
			System.out.println(logValue);
		}
		System.out.println(logValue);
//		System.out.println(j);
		
//		double logNumWithBase = logWithBase(number, base); // #5
//		System.out.println(logNumWithBase);
		
//		double logNumWithBaseTwo = logBaseTwo(number);
//		System.out.println(logNumWithBaseTwo);
	}
	
	public static double naturalLog(double userInput)
	{
		return Math.log(userInput);
	}
	
	public static double logWithBase(double number, double base) // #5.1
	{
		return Math.log(number) / Math.log(base);
	}
	
	public static double logBaseTwo(double number) // #5.3
	{
		return Math.log(number) / Math.log(2);
	}
	
	public static double logBaseTen(double number) //#6
	{
		return Math.log(number) / Math.log(10);
	}
}
