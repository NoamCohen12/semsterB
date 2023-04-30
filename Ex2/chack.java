package Ex2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;

public class chack {

	public static void main(String[] args) {
		double[] xx = {-2*Ex2.EPS};
		double[] d1 = {0};
		System.out.println("--------------");
		double[] d2 = {1};
		double[] d3 = {1+Ex2.EPS/2};
		System.out.println("--------------");
		double[] d4 = {1,2};
		double[] d5 = {1,2,Ex2.EPS/2};
		double[][] p = {{0}, {1}, {1,2,0,0}};
		double[][] p1 = {{-2*Ex2.EPS}, {1+Ex2.EPS*1.2}, {1,2,Ex2.EPS/2}};
		for(int i=0;i<p.length;i=i+1) {
		System.out.println(equals(p[i], p1[i]));	
		}
System.out.println(EPS);
//		System.out.println(Arrays.toString(fixArrays(d1)));
//		System.out.println(Arrays.toString(fixArrays(xx)));
//		System.out.println(equals(xx, d1));
//		System.out.println("--------------");
//		
//		System.out.println(Arrays.toString(fixArrays(d2)));
//		System.out.println(Arrays.toString(fixArrays(d3)));
//		System.out.println(equals(d2,d3));
//		System.out.println(Math.abs(d2[0]-d3[0]));
//		System.out.println(Math.abs(d2[0]-d3[0])>EPS);
//		System.out.println(1-1.0005);
//		System.out.println(0.0005);
//		
//		System.out.println("--------------");
//		System.out.println(Arrays.toString(fixArrays(d4)));
//		System.out.println(Arrays.toString(fixArrays(d5)));
//		System.out.println(equals(d4, d5));
		
		
	}
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.

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
	
}
