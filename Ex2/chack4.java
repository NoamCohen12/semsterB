package Ex2;

public class chack4 {

	public static void main(String[] args) {

	}
	public static double area1(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		double width = Math.abs(x2-x1);												// length of range to compute
		double boxWidth = width/numberOfTrapezoid;								// the length of every box
double j=1;
		
		for (double i=0; i<=numberOfTrapezoid; i++) {	
			double xi = x1 + boxWidth * i;
			double x2i = x1 + boxWidth * j;
			j++;
			double sum1 = f(p1, xi);
			double sum2 = f(p2, xi);
			double sum3 = f(p1, x2i);
			double sum4 = f(p2, x2i);
			double high=Math.abs(sum1-sum2);
			double width1=sum1+sum3;
			double width2=sum2+sum4;
			double areaT=((width1+width2)*high)/2;
			ans =ans +areaT;
		
		}
		
		
		return ans;
	}
	
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		double width = Math.abs(x2-x1);												// length of range to compute
		double boxWidth = width/numberOfTrapezoid;								// the length of every box

		for (double i=0; i<numberOfTrapezoid; i++) {								// loop to run over all boxes
			double xi = x1 + boxWidth * i;
			double sum1 = f(p1, xi);
			double sum2 = f(p2, xi);
			double length = Math.abs(sum1-sum2);							// the length between two polynoms
			double boxArea = boxWidth * length;								// computes the area of specific box
			ans = ans + boxArea;											// sum this area to the general area
		}
		////////////////////
		return ans;
	}
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0;i<poly.length;i++) {
			double c = Math.pow(x, i);
			ans +=c*poly[i];
		}
		return ans;
	}

}
