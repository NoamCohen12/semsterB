package Ex2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;import java.util.Arrays;

/**
 *  * Introduction to Computer Science 2023, Ariel University,
 *  * Ex2: arrays, static functions and JUnit
 *
 * This JUnit class represents a JUnit (unit testing) for Ex2 - 
 * It contains few testing functions for the polynum functions as define in Ex2.
 * Note: you should add additional JUnit testing functions to this class.
 *
 * @author boaz.ben-moshe
 */

class Ex2Test {
	static final double[] P1 ={2,0,3, -1,0}, P2 = {0.1,0,1, 0.1,3};
	static double[] po1 = {2,2}, po2 = {-3, 0.61, 0.2};
	static double[] po3 = {2,1,-0.7, -0.02,0.02};
	static double[] po4 = {-3, 0.61, 0.2};

	@Test
	/**
	 * Tests that f(x) == poly(x).
	 */
	void testF() {
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0, 2, Ex2.EPS);
		assertEquals(fx1, 4, Ex2.EPS);
		assertEquals(fx2, 6, Ex2.EPS);
	}
	@Test
	/**
	 * Tests that p1(x) + p2(x) == (p1+p2)(x)
	 */
	void testF2() {
		double x = Math.PI;
		double[] po12 = Ex2.add(po1, po2);
		double f1x = Ex2.f(po1, x);
		double f2x = Ex2.f(po2, x);
		double f12x = Ex2.f(po12, x);
		assertEquals(f1x + f2x, f12x, Ex2.EPS);
	}
	@Test
	/**
	 * Tests that p1+p2+ (-1*p2) == p1
	 */
	void testAdd() {
		double[] p12 = Ex2.add(po1, po2);//[-1.0, 2.61, 0.2]p12
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);//
		double[] p1 = Ex2.add(p12, pp2);
		assertTrue(Ex2.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1+p2 == p2+p1
	 */
	void testAdd2() {
		double[] p12 = Ex2.add(po1, po2);
		double[] p21 = Ex2.add(po2, po1);
		assertTrue(Ex2.equals(p12, p21));
	}
	@Test
	/**
	 * Tests that p1+0 == p1
	 */
	void testAdd3() {
		double[] p1 = Ex2.add(po1, Ex2.ZERO);
		assertTrue(Ex2.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1*0 == 0
	 */
	void testMul1() {
		double[] p1 = Ex2.mul(po1, Ex2.ZERO);
		assertTrue(Ex2.equals(p1, Ex2.ZERO));
	}
	@Test
	/**
	 * Tests that p1*p2 == p2*p1
	 */
	void testMul2() {
		double[] p12 = Ex2.mul(po1, po2);
		double[] p21 = Ex2.mul(po2, po1);
		assertTrue(Ex2.equals(p12, p21));
	}
	@Test
	/**
	 * Tests that p1(x) * p2(x) = (p1*p2)(x),
	 */
	void testMulDoubleArrayDoubleArray() {
		double[] xx = {0,1,2,3,4.1,-15.2222};
		double[] p12 = Ex2.mul(po1, po2);
		for(int i = 0;i<xx.length;i=i+1) {
			double x = xx[i];
			double f1x = Ex2.f(po1, x);
			double f2x = Ex2.f(po2, x);
			double f12x = Ex2.f(p12, x);
			assertEquals(f12x, f1x*f2x, Ex2.EPS);
		}
	}
	@Test
	/**
	 * Tests a simple derivative examples - till ZERO.
	 */
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] pt = {2,6}; // 6x+2
		double[] dp1 = Ex2.derivative(p); // 2x + 6
		double[] dp2 = Ex2.derivative(dp1); // 2
		double[] dp3 = Ex2.derivative(dp2); // 0
		double[] dp4 = Ex2.derivative(dp3); // 0
		assertTrue(Ex2.equals(dp1, pt));
		assertTrue(Ex2.equals(Ex2.ZERO, dp3));
		assertTrue(Ex2.equals(dp4, dp3));
	}

	@Test
	/** 
	 * Tests the parsing of a polynom in a String like form.
	 */

	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1X^2+ 2.3x -1.1
		String sp2 = "3.1x^2 +2.3x -1.1";
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);

		double[] p2 = Ex2.getPolynomFromString(sp2);
		boolean isSame1 = Ex2.equals(p1, p);
		boolean isSame2 = Ex2.equals(p2, p);
		if(!isSame1) {fail();}
		if(!isSame2) {fail();}
		assertEquals(sp, Ex2.poly(p1));
	}

	@Test
	/**
	 * Tests the equality of pairs of arrays.
	 */
	public void testEquals() {
		double[][] d1 = {{0}, {1}, {1,2,0,0}};
		double[][] d2 = {Ex2.ZERO, {1+Ex2.EPS/2}, {1,2}};
		double[][] xx = {{-2*Ex2.EPS}, {1+Ex2.EPS*1.2}, {1,2,Ex2.EPS/2}};
		for(int i=0;i<d1.length;i=i+1) {
			assertTrue(Ex2.equals(d1[i], d2[i]));
		}
		for(int i=0;i<d1.length;i=i+1) {
			assertFalse(Ex2.equals(d1[i], xx[i]));
		}
	}

	@Test
	/**
	 * Tests is the sameValue function is symmetric.
	 */
	public void testSameValue2() {
		double x1=-4, x2=0;
		double rs1 = Ex2.sameValue(po1,po2, x1, x2, Ex2.EPS);
		double rs2 = Ex2.sameValue(po2,po1, x1, x2, Ex2.EPS);

		assertEquals(rs1,rs2,Ex2.EPS);
	}
	@Test
	/**
	 * Test the area function - it should be symmetric.
	 */
	public void testArea() {
		double x1=-4, x2=0;
		double a1 = Ex2.area(po1, po2, x1, x2, 100);
		double a2 = Ex2.area(po2, po1, x1, x2, 100);
		assertEquals(a1,a2,Ex2.EPS);
	}/////////////Tests I added
	@Test
	void testPolynomFromPoints () {//Tests by three points
		double[] xxx = {1,2,3};
		double[] yyy = {5,20,43};
		double[] tmp = {-2, 3, 4};
		double[] ans = Ex2.PolynomFromPoints(xxx, yyy); 

		assertArrayEquals(tmp, ans);                      //Tersting a parbola polinomal

		double[] xxx1 = {1,2,3,4};
		double[] yyy1 = {5,10,15,20};
		double[] tmp1 = {0,5};
		double[] ans1 = Ex2.PolynomFromPoints(xxx1, yyy1); 

		assertArrayEquals(null,ans1);                      //Tersting for probola polinomal too big


		double[] xxx2 = {1,2,3};
		double[] yyy2 = {3.81,5.02,6.63};
		double[] tmp2 = {03,0.61,0.2};
		double[] ans2 = Ex2.PolynomFromPoints(xxx2, yyy2); 

	 assertArrayEquals(tmp2,ans2,Ex2.EPS);                      //Tersting for probola polinomal like your example
	}
	 @Test
		void testPolynomFromPoints1 () {//Tests by two points
		 double[] xxx = {2,0};
			double[] yyy = {2,0};
			double[] tmp = {0,1};
			double[] ans = Ex2.PolynomFromPoints(xxx, yyy); 
			System.out.println(Arrays.toString(ans));
			 assertArrayEquals(tmp,ans,Ex2.EPS);                      //Tersting for line y=x

			 double[] xxx1 = {3,1};
				double[] yyy1 = {5,7};
				double[] tmp1 = {8,-1};
				double[] ans1 = Ex2.PolynomFromPoints(xxx1, yyy1); 
				System.out.println(Arrays.toString(ans1));
				 assertArrayEquals(tmp1,ans1,Ex2.EPS);                      //Tersting for line y=x


//				 double[] xxx2 = {0,0};
//					double[] yyy2 = {0,0};
//					double[] tmp2 = {0,0};
//					double[] ans2 = Ex2.PolynomFromPoints(xxx2, yyy2); 
//					System.out.println(Arrays.toString(ans2));
//					 assertArrayEquals(tmp2,ans2,Ex2.EPS);                      //Tersting for line y=x

			 
			 
			 
		 
	 
	 }

	
	@Test
	public void testFromString1() {
		double[] p = {-1.1, 2.3, 3.1};
		String sp = "3.1x^2+2.3x-1.1";
		double[] p1 = Ex2.getPolynomFromString(sp);
		assertEquals(p[0], p1[0]);
		assertEquals(p[1], p1[1]);
		assertEquals(p[2], p1[2]);

		double[] p3 = {3.0, 0.0, 7.0};
		String sp3 = "7.0x^2+3.0";
		double[] fp3 = Ex2.getPolynomFromString(sp3);
		assertEquals(p3[0], fp3[0]);
		assertEquals(p3[1], fp3[1]);
		assertEquals(p3[2], fp3[2]);
	}
	@Test
	void testEquals1() {
		double[] p1 = {2, 3, 4}, p2 = {2, 3, 4};
		double[] p3 = {2, 3, 4}, p4 = {2, 3, 4, 4};
		double[] p5 = {2, 3, 4}, p6 = {};
		double[] p7 = {}, p8 = {};
		double[] p9 = null, p10 = null;
		double[] p11 = null, p12 = {5};

		boolean ans1 = Ex2.equals(p1, p2);
		boolean ans2 = Ex2.equals(p3, p4);
		boolean ans3 = Ex2.equals(p5, p6);
		boolean ans4 = Ex2.equals(p7, p8);
		boolean ans5 = Ex2.equals(p9, p10);
		boolean ans6 = Ex2.equals(p11, p12);

		assertEquals (ans1, true);
		assertEquals (ans2, false);
		assertEquals (ans3, false);
		assertEquals (ans4, true);
		assertEquals (ans5, true);
		assertEquals (ans6, false);
	}
	@Test
	public void testPoly() {
		double [] poly = {1,2,3,4,5};
		double [] poly1 = {-1,2,3,4,-5};
		double [] poly2 = {0};
		double [] poly3 = {+1,2,3,4,+5};

		String str=Ex2.poly(poly);
		String str1=Ex2.poly(poly1);
		String str2=Ex2.poly(poly2);
		String str3=Ex2.poly(poly3);

		assertEquals("5.0x^4 +4.0x^3 +3.0x^2 +2.0x +1.0", str);  //Testing a normal polynomial
		assertEquals("-5.0x^4 +4.0x^3 +3.0x^2 +2.0x -1.0", str1);//Testing a polynomial with negative numbers at the limits
		assertEquals("0.0", str2);                                 //Testing the "zero polynomial"
		assertEquals("5.0x^4 +4.0x^3 +3.0x^2 +2.0x +1.0", str3); //Testing with numbers with a + character in the limits



	}
	@Test
	void testSameValue() {									// test if the function finds the interseption point of two polynoms 
		double eps = 0.001;

		double[] p1 = {10, 3};
		double[] p2 = {5, 1};
		double[] p3 = {2, 1, -0.7, -0.02, 0.02};
		double[] p4 = {-3, 0.61, 0.2};

		double ans1 = Ex2.sameValue(p1, p2, -10, 10, eps);
		double ans2 = Ex2.sameValue(p3, p4, 0, 5, eps);

		assertEquals(ans1, -2.5);
		assertEquals(ans2, 2.748, Ex2.EPS);					// tests the polynoms from Ex2.GUI
	}




	@Test
	void testLength() {/// test for the constant function
		double []poly= {5};
		double x1=0;
		double x2=20;
		int n=4;
		double y=	Ex2.length(poly, x1, x2, n);
		assertEquals(20, y);
	}

		@Test 
		void testLength1(){//Test to Poland from level 1
			double [] f2= {0,2};
			double ans2= Ex2.length(f2, 0, 2, 2);
			assertEquals(ans2,2* Math.sqrt(5),Ex2.EPS);
		}
		@Test 
		void testLength2(){//Test to Poland from level 2
			double [] f2= {0,0,1};
			double ans2= Ex2.length(f2, 0, 2, 2);
			assertEquals(ans2, Math.sqrt(2)+Math.sqrt(10),Ex2.EPS);
		}
		@Test 
		void testLength3(){//Test to zreo polinom or null polinom
			double [] f2= {0,0,0};
			double [] f3=null;
			double ans2= Ex2.length(f2, 0, 2, 2);
			double ans3= Ex2.length(f3, 0, 10, 2);
			assertEquals(ans2, 0,Ex2.EPS);
			assertEquals(ans3, 0,Ex2.EPS);

		}









		@Test
		void testArea1() {
			double[] p1 = {2, 1, -0.7, -0.02, 0.02};
			double[] p2 = {-3, 0.61, 0.2};

			double ans1 = Ex2.area(p1, p2, -2.343, 2.734, 10);

			assertEquals(ans1, 16.59002278872367, 0.2);




		}
		@Test
		public void testFromString2() {
			double[] p = {-1.1, 2.3, 3.1};
			String sp = "3.1x^2+2.3x-1.1";
			double[] p1 = Ex2.getPolynomFromString(sp);
			assertEquals(p[0], p1[0]);
			assertEquals(p[1], p1[1]);
			assertEquals(p[2], p1[2]);

			double[] p3 = {3.0, 0.0, 7.0};
			String sp3 = "7.0x^2+3.0";
			double[] fp3 = Ex2.getPolynomFromString(sp3);
			assertEquals(p3[0], fp3[0]);
			assertEquals(p3[1], fp3[1]);
			assertEquals(p3[2], fp3[2]);
		}
		@Test
		public void testAdd4() {
			double[] pm1 = {-1,-7,-8};
			double[] pm2 = {};
			double[] pm3 = Ex2.add(pm1, pm2);
			double[] pm4 = {5,-7,5};
			double[] pm5 = {1,2,3,5};

			double[] add = Ex2.add(pm4, pm5);
			double[] ans = {6,-5,8,5};
			assertArrayEquals(add, ans);// Testing arrays of different sizes
			assertArrayEquals(pm3, pm1);//Testing adding an empty array

		}
		@Test 
		void testmull () {
			double [] p1 = {5};
			double [] p2 = {1,2,3,4,5};
			double [] p3 = {5,10,15,20,25};
			double [] p12 = Ex2.mul(p1, p2);
			assertArrayEquals(p12, p3);                 //Tersting multiplication a normal polinomal
			double [] p4 = {0};
			double [] p5 = {1,2,3,4,5};
			double [] p6 = {0,0,0,0,0};          
			double [] p13 = Ex2.mul(p5, p4);
			assertArrayEquals(p13, p6);                  // //Testing multiplication an empty array
		}
		@Test 
		void testDerivativeArrayDoubleArray1() {
			double[] p = {1,2,3}; // 3X^2+2x+1
			double[] dp1 = {2,6}; // 6x+2
			double[] dp2 = Ex2.derivative(p);
			assertEquals(dp1[0], dp2[0],Ex2.EPS);
			assertEquals(dp1[1], dp2[1],Ex2.EPS);
			assertEquals(dp1.length, dp2.length);

			double[] f = {10}; // 10
			double[] f1 = {0}; // 0
			double[] f2 = Ex2.derivative(f);
			assertEquals(f1[0], f2[0],Ex2.EPS);           // //Testing An array with one member
			assertEquals(f1.length, f2.length);  


		}

	}
