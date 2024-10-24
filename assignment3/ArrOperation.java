package assignment3;

/**
 * This class holds the methods related to array that help us to solve some array related problems
 */

public class ArrOperation {
	
	
	/**
	 * this methods finds largest mirror section in given array
	 * Mirror section in an array is a group of contiguous elements such that somewhere in the array, 
	 * the same group appears in reverse order. 
	 * For example maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
	 * @param arr input array containing integers
	 * @return integer length of largest mirror section in arr
	 * @throws AssertionError in case given array is empty
	 */
	public int largestMirrorSection(int[] arr) throws AssertionError{
		
		int m = arr.length;
	    
		if(m==0)
		  throw new AssertionError("empty array not allowed");
		
	    int res = 0; 
	    
	    /**
	     * This method uses the largest common sub string  as the common problem 
	     * to solve the given problem we consider that the reverse of the given array would have our '
	     * required mirror section in same order as the original and hence
	     * we take i index traversing from left to right and j from right to left 
	     * for every pair i and j we check if do they have a common sub string starting from
	     * i to curr index for left to right 
	     * curr to j for right to left considered array 
	     * 
	     */
	    for (int i = 0; i < m; i++) {
	        for (int j = m-1; j >=0 ; j--) {
	        	
	            int curr = 0;
	            
	            // if indices are in range and curr numbers are equal we can move further
	            while ((i + curr) < m && (j - curr) >=0 && arr[i + curr] == arr[j - curr]) 
	                curr++;
	            
	            //we are finding the largest such mirror section
	            res = Math.max(res, curr);
	        }
	    }
	    return res;
		
	}
	
	/**
	 * This method finds the number of clumps in the given array
	 * Clump in an array is a series of 2 or more adjacent elements of the same value. For example	
	 * countClumps([1, 2, 2, 3, 4, 4]) → 2
	 * @param arr 
	 * @return the no of clumps in the array
	 */
	
	public int noOFClumps(int[] arr) throws AssertionError {
		
		int m = arr.length;
		
		if(m==0)
			  throw new AssertionError("empty array not allowed");
		
		int res=0;
		int curr=arr[0]; //The currently considered clump member 
		int currCount=1; //total no of member of current clump
		
		for(int i=1;i<m;i++){
			
			if(arr[i]==curr) {     //part of the current clump
				currCount++;       //member count increase
				if(currCount==2) { //if two members then increase clump is confirmed
					res++;
				}
			}
			else {                 //current number not part of the clump
				currCount=1;       //reset member count 
				curr=arr[i];       //change the clump number
			}
				
		}
		
		return res;
	}
	
	/**
	 * This method fixs the postion of x while change the y location to next to x
	 * meaning that given a array arr
	 * Return an array that contains exactly the same numbers as the input array, 
	 * but rearranged so that every X is immediately followed by a Y.
	 * Do not move X within array, but every other number may move. For ex: 
	 * @param arr
	 * @param x
	 * @param y
	 * @return array with arranged according to requirement
	 */
	
	public int[]  fixXY (int[] arr,int x,int y) {
		
		if(x==y) {
			throw new AssertionError("x and y can't be equal");
		}
		
		int m =arr.length;
		int[] res = new int[m]; 
		
		if(m==0)
			throw new AssertionError("empty array not allowed");
		
		if(arr[m-1]==x)
			throw new AssertionError("last index can't hold a x ");
		
		
		int xCount =0; 
		int yCount =0;
		
		for(int i=0;i<m;i++){
			    
		    	if(arr[i]==x)
		    		xCount++;
		    	else if(arr[i]==y)
		    		yCount++;
		    	
		    	if(arr[i]==x && arr[i+1]==x)  // two adjacent x's are not allowed else y can't be placed for each x
		    		throw new AssertionError("found two consecutive x");
		    	
		    	res[i]=arr[i];                // copy array to the res array for result
		}
		
		if(xCount!=yCount)                    
			throw new AssertionError("unequal count of the x and y");
		
		int nextX = 0; // holds the index of the x that will be handled next
		int nextY = 0; // similarly for y
		
		while(nextY<m && nextX<m){          
				
			while(nextX<m && res[nextX]!=x) // finding the next x to be considered
				nextX++;
			while(nextY<m && res[nextY]!=y) // finding the next y to be considered
				nextY++;
		    
			if(nextX>=m || nextY>=m)        // didn't find any suitable pair of x and y
				break;
			
			if(res[nextX+1]==y) {           // means the x we founds already has y after it
				nextX++;                    // move a head so in next round we find next x 
				continue;
			}
			
			if(nextY>0 && res[nextY-1]==x) {
				nextY++;                    // means the y we founds already has x after it  
				continue;                   // move a head so in next round we find next y   
			}
			
			res[nextY]=res[nextX+1];       // compatible pair of x and y without partner  
			res[nextX+1]=y;                // swap the y with the x neighbor
			nextX++;                       // move ahead to find next x
			nextY++;					   // move ahead to find next y
		}
		
		
		return res;
		
	}
	
	/**
	 * this methods allow to find the index   
	 * Return the index if there is a place to split the input array 
	 * so that the sum of the numbers on one side is equal 
	 * to the sum of the numbers on the other side else return -1
	 * @param arr
	 * @return the index from which we should be break
	 */
	public int splitArray(int[] arr){
		
		int totalSum=0;     // will store the sum of all the numbers in the arr
		int m = arr.length;  
		
		if(m==0)
		    throw new AssertionError("empty array not allowed"); 
		
		for(int i=0;i<m;i++){    
		    totalSum+=arr[i];
		}
		
		int leftSum=0;        //if we are on i index this will store sum of all numbers on left of i
		
		for(int i=0;i<m;i++) {                 
			//current index i is part of the right side array
			int rightSum = totalSum-leftSum;  
			
			if(i!=0 && rightSum==leftSum)   // if i==0 then we are not splitting the array at all hence don't do that
				return i;
			
			leftSum+=arr[i];
		}
		
		return -1;          //  we are here means we didn't find any split index
	}
	
	public static void main(String[] args) {
		
		ArrOperation a =  new ArrOperation();
	    
		System.out.println(a.largestMirrorSection(new int[] {1, 2, 3, 8, 9, 3, 2, 1}));
		System.out.println(a.noOFClumps(new int[] {1, 2, 3, 8, 9, 3,3, 2, 1,1}));		
		System.out.println(a.fixXY(new int[] {1,2},1,2));
		System.out.println(a.splitArray(new int[] {1,2,1,4,2,3,2,4,1}));
		
	}

}
