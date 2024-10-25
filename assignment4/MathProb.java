package assignment4;

/**
 * This calass implement following mathematical problems.
 * L.C.M. of two numbers x and y. Receive x and y as method parameters and return computed value.
 * H.C.F of two numbers x and y using Euclid’s algorithm. Receive x and y as method parameters and return computed value.
 * Assuming x and y as positive integers.
 * 
 */

/**
 * holds methods for solving math problem such as lcm hcf
 */
public class MathProb {
	
    /**
     * finds the lcm for x and y numbers
     * @param x 
     * @param y
     * @param k the current running the multiple which gradually increase  
     * @return lcm of x and y
     */
	public int lcm(int x,int y,int k){
		
	    if(k<=0 || x<0 || y<0)
	        return -1;
	    
		if(x==1 || y==1 || k>=Math.min(x, y)) // when k exceeds x and y in such case there is no number that divide both  x and y
			return x*y;
		
		if(x==y)
			return x; // a number can divide itself fully
		
		if(x%k==0 && y%k==0)          // then k is a number that divide both x and y hence is part of the lcm
	        return k*lcm(x/k,y/k,2);
		else
			return lcm(x,y,k+1);     // k doesn't work so increase the k and retry
		
		//return x*y/hcf(x,y);
	}
	
	/**
	 * uses the euclidean algo to find the hcf of a and b 
	 * @param a
	 * @param b
	 * @return
	 */
	public int hcf(int a,int b) { 
		if (b == 0) 
		    return a;                
		  return hcf(b, a % b);
	}
	
	
	public static void main(String[] args) {
	    MathProb m = new MathProb();
	    
	    System.out.println("lcm: " +m.lcm(94, 63, 1));
	    System.out.println("lcm: " +m.hcf(94, 63));
	}

}
