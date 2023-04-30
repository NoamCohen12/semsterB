package Ex2;

import java.util.Arrays;

public class chack3 {

	public static void main(String[] args) {//
		String s="3.1x^2 +2.3x -1.1";
		double []arr=getPolynomFromString(s);
		System.out.println(Arrays.toString(arr));
		String s1 ="-1.2x^3 5";
		int deg = 0;							// Getting the degree to know the length of the array
		String[] s2=new String [2];
			s2=	s1.split(" ");
		System.out.println(Arrays.toString(s2));
System.out.println(s2[0]);
		

	}
	public static double[] getPolynomFromString(String p) {

	if (p.equals("")) {
		return null;						// Dealing the case if the input is empty
	}
	p = p.replaceAll("-", "+-");			// To split according to '+'
	if (p.startsWith("+-")) {//"-1.2x^3 +3.1x^2 +2.0"
		p = p.substring(1);					// removing the + if the String start with +-, so we can turn it to a negative number 
	}
	String[] parts = p.split("\\+");
	int deg = 0;							// Getting the degree to know the length of the array

	if (parts[0].contains("x")) {
		if (parts[0].contains("^")) {
			String s3="";
s3=(parts[0].split("\\^")[1]);
deg=Integer.parseInt(s3.substring(0, 1));
			System.out.println(deg);
		} 
		else {
			deg = 1; 						// if there isn't any degree on the x, it means that the degree is One (2x+4)
		}
	} 
	else {
		return new double[]{Double.parseDouble(parts[0])}; // There isn't any degree, we can return the array with the number itself  
	}

	double[] pol = new double[deg + 1];

	for (String part : parts) {
		if (part.isBlank()) {
			continue;
		}
		if (part.contains("x")) {
			if (part.contains("^")) {
				
				deg = Integer.parseInt(part.split("\\^")[1].substring(0, 1)); // Trying to get the decree of the other numbers to know where to put them in the array
			}
			else {
				deg = 1;
			}
		} 
		else {
			deg = 0;
		}

		if (part.startsWith("x")) { 					// Dealing with the case the x jasn't any coefficient
			pol[deg] = 1;
		} else if (part.startsWith("-x")) {
			pol[deg] = -1;
		} else {
			pol[deg] = Double.parseDouble(part.split("x")[0]); // Putting the number into the array according to the degree 
		}
	}

	return pol;
}
}