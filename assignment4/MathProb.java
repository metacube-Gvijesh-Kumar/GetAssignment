package assignment4;

/**
 * This calass implement following mathematical problems.
 * L.C.M. of two numbers x and y. Receive x and y as method parameters and return computed value.
 * H.C.F of two numbers x and y using Euclid’s algorithm. Receive x and y as method parameters and return computed value.
 * Assuming x and y as positive integers.
 * 
 */

public class MathProb {
	
	public int lcm(int x,int y,int k){
		
		if(x==1 || y==1 || k>=Math.min(x, y))
			return x*y;
		
		if(x==y)
			return x;
		
		if(x%k==0 && y%k==0)
	        return k*lcm(x/k,y/k,k);
		else
			return lcm(x,y,k+1);
		
		//return x*y/hcf(x,y);
	}
	
	public int hcf(int a,int b) {
		if (b == 0)
		    return a;
		  return hcf(b, a % b);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
