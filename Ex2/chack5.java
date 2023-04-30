package Ex2;

public class chack5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double ans=0;
		double []p= {5};
		double x1=0;
		double x2=20;
		int n=4;
		double y=	Ex2.length(p, x1, x2, n);
System.out.println(y);
double g= (Math.abs(x1-x2)/4);

for (double i =x1;i<=x2;i=i+n) {
	System.out.println(Ex2.f(p,i));
	System.out.println(Ex2.f(p,i+n));
	double dis=Math.abs(Ex2.f(p,i)-Ex2.f(p,i+n));
	ans = ans +dis;
}
	}

}
