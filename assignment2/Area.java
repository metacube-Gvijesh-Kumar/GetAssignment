package assignment2;

/**
 * This generic area class exposes multiple method to get the area of different shapes 
 * such as square,triangle,rectangle,circle
 * the methods here are defined as static so they can be called even without instance
 * This class holds constants used in the methods in form of final static variable 
 */
public class Area {
    
	final static double PI = 3.14;
	
	/**
	 * @param height of the right angled triangle
	 * @param width of the right angled triangle
	 * @return double area of the right angled triangle 
	 * @throws ArithmeticException
	 */
	public static double triangle(double height,double width) throws ArithmeticException {
		if(width<0 || height<0)
			throw new ArithmeticException("No negative length allowed");
		return width*height*0.5;
	}
	
	/** 
	 * @param height of rectangle
	 * @param width of rectangle
	 * @return area of the rectangle by simple height*width
	 * @throws ArithmeticException
	 */
	public static double rectangle(double height,double width) throws ArithmeticException {
		if(width<0 || height<0)
			throw new ArithmeticException("No negative length allowed");
		return width*height;
	}
	
	/**
	 * @param width
	 * @return area of the square using formula width*width
	 * @throws ArithmeticException
	 */
	public static double square(double width) throws ArithmeticException {
		if(width<0)
			throw new ArithmeticException("No negative length allowed");
		return width*width;
	}
	
	/** 
	 * @param radius
	 * @return return the area of the circle using pi*r^2 formula
	 * @throws ArithmeticException
	 */
	public static double circle(double radius) throws ArithmeticException {
		if(radius<0)
			throw new ArithmeticException("No negative length allowed");
		return PI*radius*radius;
	}
	
	public static void main(String[] args) {
		try {
	      System.out.println("Area of triangle: "+ Area.triangle(3,4));
	      System.out.println("Area of rectangle: "+ Area.rectangle(3,4));
	      System.out.println("Area of square: "+ Area.square(3.5));
	      System.out.println("Area of circle: "+ Area.circle(6));
	      }
		catch(ArithmeticException a) {
			System.out.println(a.getMessage());
		}
	}

}
