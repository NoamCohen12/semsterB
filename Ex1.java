package smesterb;

import java.util.Scanner;

public class Ex1 {
//ID:322770629
//I used the website :https://www.tau.ac.il/~tsirel/dump/Static/knowino.org/wiki/Least_common_multiple.html
	public static void main(String[] args) {
		Scanner scaner =new Scanner (System.in);//here i get three numbers from user.
		long a=scaner.nextLong();
		long b=scaner.nextLong();
		long c=scaner.nextLong();
		scaner.close();
		double start = System.nanoTime();//check run time.
		lcmH(a,b,c);//call the function.

		double end = System.nanoTime();//stop the time.
		double dt = (end - start)/(1000.0);//calculate the run time.
		System.out.println("The runtime took: "+dt+" micro seconds."); 
		System.out.println("The mcd("+a+","+b+","+c+") = " + lcmH(a,b,c));

	}
	/*this are trhee function 
	 * 1.this function find The greatest common divisor (GCD) of two numbers.
	 * 2.this function find the least common multiple (MCD) of two numbers
	 * 3.this function The function checks the (MCD) of 
	 * two numbers input and saves them in a variable
	 * and checks the (MCD) of the variable with the third input
	 * 
	 *  
	 */
	
	
	//function number 1
	//According to Euclid's algorithm
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
	//function number 2 
	//according to the formula:lcm(a,b) = ab/gcd(a,b).
	public static long lcm(long a,long b) {
		long x=a*b;
		long y=gcd(a,b);
		return x/y;
	}
	//function number 3
	//according to the formula:lcm(a,b,c) = lcm(a,lcm(b,c))
	public static long lcmH(long a,long b,long c) {
		long x=lcm(a,b);
		return lcm(c,x);
	}


}








