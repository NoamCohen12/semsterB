package Ex2;

import java.util.Arrays;

public class chack2 {

	public static void main(String[] args) {
	//	double[] P1 ={2,0,3, -1,0}, P2 = {0.1,0,1, 0.1,3};
		double[] po1 = {2,2};
		double[] po2 = {-3, 0.61, 0.2};
		System.out.println(Arrays.toString(po2));

	//	double[] po3 = {2,1,-0.7, -0.02,0.02};
	//	double[] po4 = {-3, 0.61, 0.2};
		double[] p12 = Ex2.add(po1, po2);
		System.out.println(Arrays.toString(po2));
		double[] minus1 = {-1};
		double EPS = 0.001; // the epsilon to be used for the root approximation.

		System.out.println(Arrays.toString(p12));//[-1.0, 2.61, 0.2]p12

System.out.println(Arrays.toString(po2));
		double[] pp2 = Ex2.mul(po2, minus1);//[-1.0, 2.61, 0.2]pp2
		System.out.println(Arrays.toString(pp2));
		
		double[] p1 = Ex2.add(p12, pp2);//
		
		System.out.println (Arrays.toString(p1));
		
		System.out.println(equals(p1, po1));

	}
	public static boolean equals(double[] p1, double[] p2) {
		double EPS = 0.001; // the epsilon to be used for the root approximation.

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
