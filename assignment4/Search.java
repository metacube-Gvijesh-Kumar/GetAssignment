package assignment4;

public class Search {
    
    public int binarySearch(int[] arr,int key,int startInd,int endInd) {
        
        if(startInd>endInd)
            return -1;
        
        int middle = (startInd+endInd)/2;
            
        if(arr[middle]==key)
            return middle;
        else if(arr[middle]>key)
            return binarySearch(arr,key,startInd,middle-1);
        else
            return binarySearch(arr,key,middle+1,endInd);
                
    }
    
    public int linearSearch(int[] arr,int key,int ind) {
        
        if(ind>=arr.length)
            return -1;
        
        if(arr[ind]==key)
            return ind;
        else
            return linearSearch(arr,key,ind+1);
         
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
