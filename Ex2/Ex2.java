package Ex2;

/**
 * Introduction to Computer Science 2023, Ariel University,
 * Ex2: arrays, static functions and JUnit
 *
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynom: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code here")
 *
 * @author boaz.benmoshe
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0;i<poly.length;i++) {
			double c = Math.pow(x, i);
			ans +=c*poly[i];
		}
		return ans;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x1) <= 0. 
	 * This function should be implemented recursively.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double f1 = f(p,x1);
		double f2 = f(p,x2);
		double x12 = (x1+x2)/2;
		double f12 = f(p,x12);
		if (f1*f2<=0 && Math.abs(f12)<eps) {return x12;}
		if(f12*f1<=0) {return root_rec(p, x1, x12, eps);}
		else {return root_rec(p, x12, x2, eps);}
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
	 * Note: this function only works for a set of points containing up to 3 points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {////
		double [] ans = null;
		int lx = xx.length;
		int ly = yy.length;
		if(xx!=null && yy!=null && lx==ly && lx>1 && lx<4) {
			// *** add your code here ***
			if (ly==2 && lx==2) {//In case we got 2 points by slope and point
				ans= new double [2];//Rank of X at most 1
				// y=mx+n
				double x1= xx[0];//(x1,y1)
				double fx1= yy[0];

				double x2= xx[1];//(x2,y2)
				double fx2= yy[1];
				double	m= (fx2-fx1)/(x2-x1);//The slope of the polynomial by the slope formula
				double	n=fx1-(m*x1);
				ans [0]= n;//Free variable
				ans [1]= m;//variable from degree 1
			}			/***
			 *I used this site to understand the calculation
			 *and the implementation of this function:
			 * http://chris35wills.github.io/parabola_python/
			 ***/
			else {

			double x1 = xx[2], x2 = xx[1], x3 = xx[0];
			double y1 = yy[2], y2 = yy[1], y3 = yy[0];

			double denom = (x1-x2) * (x1-x3) * (x2-x3);
			double a = (x3 * (y2-y1) + x2 * (y1-y3) + x1 * (y3-y2)) / denom;
			double b = (x3*x3 * (y1-y2) + x2*x2 * (y3-y1) + x1*x1 * (y2-y3)) / denom;
			double c = (x2 * x3 * (x2-x3) * y1+x3 * x1 * (x3-x1) * y2+x1 * x2 * (x1-x2) * y3) / denom;

			ans = new double [3];
			ans[0] = c;
			ans[1] = b;
			ans[2] = a;
			// **************************
		}
		}
		return ans;
	}

	/** Two polynoms are equal if and only if the have the same values f(x) for 1+n values of x, 
	 * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		if (p1==null && p2==null) {return true;}						// if the arrays are null
		if (p1==null || p2==null) {return false;}					// if one of them is null
		double[]ans1=fixArrays(p1);

		double [] ans2=fixArrays(p2);
		if(ans1.length!=ans2.length) {return false;}
		else {
			for (int i = 0; i<ans1.length; i++) {
				if (Math.abs(ans1[i]-ans2[i])>EPS) {						// compares the i location in both of polynoms
					return false;
				}
			}
		}


		return ans;
	}



	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		if(poly.length==0) {ans="0";}
		else {

			if (poly==null ) {return "";}					// if the array is empty

			if (poly.length==1) {											// if the array contains only one number
				return poly[0] + "";
			}

			for (int i = poly.length-1; i>=1; i--) {						// go over the array and full the string
				if (poly[i]>0) {
					ans = ans + " +" + poly[i] + "x^" + i;					// if the value is positive
				}
				if (poly[i]<0) {
					ans = ans + " " + poly[i] + "x^" + i;					// if the value is nagative
				}
			}

			if (poly[poly.length-1]>0) {
				ans = ans.substring(2, ans.length());							// removing the first "+" if exist
			}
			if (poly[1]!=0) {
				ans = ans.substring(0, ans.length()-2);							// removing chars "^1" if exist
			}

			if (poly[0]>0) {
				ans = ans + " +" + poly[0];
			}
			if (poly[0]<0) {
				ans = ans + " " + poly[0];
			}

			if(ans.charAt(0) == ' ') {											// if the string beggin with " -"
				ans = ans.substring(1, ans.length());
			}

			// **************************
		}
		return ans;
	}

	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		// *** add your code here ***

		if (f(p1, x1) > f(p2, x1)) {											// if p1 go's down and p2 go's up

			do {

				if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}	// calculate to know if this is the cut point

				if (f(p1, x12) < f(p2, x12)) {									// this means we passet over the cut point
					x2 = x12;													// change the maximal value of the range
					x12 = (x1+x2)/2;
				}

				else if (f(p1, x12) > f(p2, x12)) {								// this means we didn't pass over the cut point yet
					x1 = x12;													// change the minimal value of the range
					x12 = (x1+x2)/2;
				}

			} while (f(p1, x12) != f(p2, x12));
		}

		else if (f(p1, x1) < f(p2, x1)) {											// if p2 go's down and p1 go's up

			do {

				if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}		// calculate to know if this is the cut point

				if (f(p1, x12) < f(p2, x12)) {										// this means we didn't pass over the cut point yet
					x1 = x12;														// change the minimal value of the range
					x12 = (x1+x2)/2;
				}

				else if (f(p1, x12) > f(p2, x12)) {									// this means we passet over the cut point
					x2 = x12;														// change the maximal value of the range
					x12 = (x1+x2)/2;
				}

			} while (f(p1, x12) != f(p2, x12));
		}

		// *** add your code here ***

		// **************************
		return x12;
	}
	/**
	 * Given a polynom (p), a range [x1,x2] and an integer with the number (n) of sample points. 
	 * This function computes an approximation of the length of the function between f(x1) and f(x2) 
	 * using n inner sample points and computing the segment-path between them.
	 * assuming x1 < x2. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfSegments - (A positive integer value (1,2,...).
	 * @return the length approximation of the function between f(x1) and f(x2).
	 */
	public static double length(double[] p, double x1, double x2, int numberOfSegments) {
		double sum=0;
		if(p==null) {
			return 0;
		}
		for (int i = 0; i < p.length; i++) {
			sum= sum+p[i];
		}
		if(sum==0) {
			return sum;//In case I get an array of zeros
		}

		double ans = 0;
		double n= (Math.abs(x1-x2)/numberOfSegments);//Calculate how many segments there are in the sum

		for (double i =x1;i<x2;i=i+n) {
			double dis=Math.sqrt((f(p,i+n) - f(p,i)) * (f(p,i+n) - f(p,i)) + (i+n - i) * (i+n - i));//Distance between the points to calculate each segment separately
			ans = ans +dis;
		}
		////////////////////
		return ans;
	}

	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom). 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		// *** add your code here ***

		double width = Math.abs(x2-x1);												// length of range to compute
		double boxWidth = width/numberOfTrapezoid;								// the length of every box

		for (int i=0; i<numberOfTrapezoid; i++) {								// loop to run over all boxes
			double xi = x1 + boxWidth * i;
			double sum1 = f(p1, xi);
			double sum2 = f(p2, xi);
			double length = Math.abs(sum1-sum2);							// the length between two polynoms
			double boxArea = boxWidth * length;								// computes the area of specific box
			ans = ans + boxArea;											// sum this area to the general area
		}
		// **************************
		return ans;
	}

	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	public static double[] getPolynomFromString(String p) {
		/**
		 * assuming the input does'nt inclued spaces 
		 */
		if (p.equals("")) {
			return null;						// Dealing the case if the input is empty
		}
		p = p.replaceAll("-", "+-");			// To split according to '+'
		if (p.startsWith("+-")) {
			p = p.substring(1);					// removing the + if the String start with +-, so we can turn it to a negative number 
		}
		String[] parts = p.split("\\+");
		int deg = 0;							// Getting the degree to know the length of the array

		if (parts[0].contains("x")) {
			if (parts[0].contains("^")) {
				deg = Integer.parseInt(parts[0].split("\\^")[1].substring(0,1));
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
					deg = Integer.parseInt(part.split("\\^")[1].substring(0,1)); // Trying to get the decree of the other numbers to know where to put them in the array
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

	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {

		double[] sum = new double[Math.max(p1.length, p2.length)];

		if (p1.length==0 || p1==null) {return p2;}
		if (p2.length==0 || p2==null) {return p1;}

		if (p1.length==p2.length) {									// if both polynoms have the same length
			for(int i=0; i<p1.length; i++) {
				sum[i] = p1[i] + p2[i];
			}
		}

		else {
			int i = 0;
			while (i<p1.length && i<p2.length) {					// run over the arrays and sum their value to the new array
				sum [i] = p1[i] + p2[i];
				i++;
			}

			while (i<p1.length) {									// if p1 is the longest array
				sum[i] = p1[i];
				i++;
			}

			while (i<p2.length) {									// if p2 is the longest array
				sum[i] = p2[i];
				i++;
			}
		}


		return sum;
		// **************************
	}


	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		if (p1==ZERO||p2==ZERO) {
			return ZERO;
		}
		if (p1.length==1&&p2.length>p1.length) {
			double[] ans = new double [ p2.length];
			for(int i = 0; i<p2.length; i++) {
				ans[i]=p2[i]*p1[0];
			}
			return ans;


		}
		if (p2.length==1&&p1.length>p2.length) {
			double[] ans = new double [ p1.length];
			for(int i = 0; i<p1.length; i++) {
				ans[i]=p2[0]*p1[i];
			}
			return ans;
		}
		else  {

			double[] ans = new double [p1.length + p2.length - 1];

			for(int i = 0; i<p1.length; i++) {								// run over one index from first array
				for(int j = 0; j<p2.length; j++) {							// and mul it with all indexes of the second array
					ans[i+j] = ans[i+j] + p1[i]*p2[j];
				}
			} 
			return ans;
		}
	}
	/**
	 * This function computes the derivative polynom:.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		double[] deriv = {0};

		if (po.length==1) {return deriv;}

		deriv = new double[po.length-1];

		for (int i = 0; i<deriv.length; i++) {
			deriv[i] = po[i+1]*(i+1);
		}

		return deriv;
		// **************************
	}
	///////////////////////////////////
	//Helper functions I added 
	public static double[] fixArrays (double[]p1) {
		for(int i=p1.length-1;i>0;i--) {
			if (p1[i]!=0) {
				double ans[]=new double [i+1];
				for (int j=0;j<=i;j++) {
					ans[j]=p1[j];
				}
				return ans;

			}

		}
		return p1;

	}

}
