package Ex2;

public class chack6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		double width = Math.abs(x2-x1);												// length of range to compute
		double n = width/numberOfTrapezoid;	
		for (double i=x1;i<x2;i=i+n) {
		double base1=Math.abs (Ex2.f(p1, i)-Ex2.f(p2, i));
		double base2=Math.abs (Ex2.f(p1, i+n)-Ex2.f(p2, i+n));
		double high=Math.abs(i-i+n);
		ans=ans+(base1+base2)/high;
		}
return ans;
}
}