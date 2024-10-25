package assignment4;

import java.util.Arrays;

/**
 * This class holds methods that use recursion
 * The methods are meant to search for a given key element in the array
 */

public class Search {
    
    /**
     * This implementation assumes the arr to be sorted as per the assignment
     * @param arr will be searched
     * @param key to be found in the arr 
     * @param startInd possible range of the search
     * @param endInd
     * @return
     */
    public int binarySearch(int[] arr,int key,int startInd,int endInd) {
        
        
        if(startInd>endInd || startInd<0 || endInd<0 || startInd>=arr.length || endInd>=arr.length)               // element doesn't exist at all
            return -1;
        
        int middle = (startInd+endInd)/2; // the mid index for comparison
            
        if(arr[middle]==key)              // the key is found the mid index
            return middle;                
        else if(arr[middle]>key)          // key is in the left part of the array   
            return binarySearch(arr,key,startInd,middle-1);
        else                              // key is in the right part of the array  
            return binarySearch(arr,key,middle+1,endInd);
                
    }
    
    public int linearSearch(int[] arr,int key,int ind) {
            
        if( ind<0 || ind>=arr.length)  // we didn't find the key
            return -1;          
        
        if(arr[ind]==key)    // the key is at the current index ind
            return ind;
        else
            return linearSearch(arr,key,ind+1); // find the key in next index 
                              
    }
    
    public static void main(String[] args) {
        
        Search s = new Search();
        int[] arr = {1,232,242,32,42,2,4,4,2,24,5,2,4};
        
        System.out.println(s.linearSearch(arr,24,0));
        
        Arrays.sort(arr);
        System.out.println(s.binarySearch(arr,24,0,arr.length-1));
        

    }

}
