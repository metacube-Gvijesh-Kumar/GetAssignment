package assignment5;

// pls find the test's for this under test package file  test5

/**
 *
 *   It is a Immutable  that use's an array to represent a set of integers in the range 1-1000. 
 *   It  support public methods like - 
 *   isMember(int x) - check whether x is a member of the set and return a boolean value
 *  
 *   size() - return the size of the set
 *   
 *   isSubSet(intSet s) - check whether s is a subset of the set
 *   
 *   getComplement()  - return the complement set, you can assume that 1..1000 is the universal set
 *     
 *   You may use private helper methods. 
 *   
 *   Set operations -
 *   
 *   Union: Set of members that belong to the first set "or" the second set. 
 *   Intersection: Set of members that belong to both the first set "and" second set.
 *   Difference: Set of members that belong to the first set "and not" the second set. 
 *   Complement: Set of members that belong to the second (universal) set "and not" the first set. 
 *   
 *   Given the following sets:
 *   
 *      A={2,4,6,8,10}
 *      B={1,2,3,4,5}
 *  
 *      Union:        A∪B = {1,2,3,4,5,6,8,10}
 *      Intersection: A∩B = {2,4}
 *      Difference:   A-B = {6,8,10}
 *      Complement:   AB = {1,3,5}
 */

public class IntSet {
       
       /**
        * 'arr' holds a irreversible array and meant to initialized only in the constructor 
        *  The immutability of intset comes from this only  
        */
       private final int[] arr;
       final int upperRange = 100;//1000 in question // value in arr can't be greater than this
       final int lowerRange = 1;     // value in arr can't be lower than this
       
       /**
        * array based constructor meant to initialize the array arr 
        * @throws InstantiationError incase the array contains value out of allowed range
        * @param arr input array
        */
       public IntSet(int[] arr)throws InstantiationError {
           
           int[] count = new int[upperRange-lowerRange+1]; // holds count of each integer 
           
           for(int i=0;i<arr.length;i++){
               
               if(arr[i]>upperRange || arr[i]<lowerRange) // out of range value
                   throw new InstantiationError("Given arr contains value input value out of allowed range");

               int ind = arr[i]-lowerRange;              // maps the integer to the index in the count array
               
               count[ind]++;
               
               if(count[ind]>1)                         // intset is a set of integer can't have more than 1 for each integer
                   throw new InstantiationError("contains multiple integer of same value meant to be a set");
                   
           }
           
           this.arr=arr;
       }
       
       /**arr getter 
        * returns a copy of the arr
        * */
       public int[] getArr() {
           
           int [] copy = new int[arr.length];
         
           for(int i=0;i<arr.length;i++) {
               copy[i]=arr[i];
           }
           
           return copy;
       }
       
       /**
        * check if given x is part of the intset
        * @param x
        * @return Boolean
        */
       
       Boolean isMember(int x) {
           
           if(x>upperRange || x<lowerRange) // value will not exist if out of range
               return false;
           
           for(int i=0;i<arr.length;i++) { // traverse the arr to find x
               if(arr[i]==x)
                   return true;            // found x
           }
           return false;                   // arr exhausted didn't find x
       }

       /**
        * @return length of the arr 
        */
       int size() {
        return arr.length;   
       }
       
       /**
        * add's new member to intset by creating a new instance of it 
        * preserving the immutability
        * @param x
        * @return
        */
       IntSet add(int x) {
           
           if(isMember(x)) {    // x already exist in the set of integer
               return this;     // return the existing IntSet
           }
           
           int[] a  = new int[this.size()+1]; // array of size one large than the current
           
           for(int i=0;i<this.size();i++){
               a[i]=arr[i];
           }
           
           a[this.size()]=x;    // newer member added      
           IntSet i = new IntSet(a);
           
           return i;
       }
       
       /**
        * check if given s is sub set of our arr array
        * to be a subset every member of s should exist in our arr
        * @param s
        * @return
        */
       public Boolean isSubSet(IntSet s) {
           int [] sArr = s.getArr();
           for(int i=0;i<sArr.length;i++) { 
               if(!isMember(sArr[i])) // element does not exist meaning not a subset
                   return false; 
           }
                   return true;      //  all member exist in arr
       }
       
       
       /**
        * @return new instance of IntSet that is complement of the current 
        */
       public IntSet getComplement() {
           
           
           boolean[] existing = new boolean[upperRange-lowerRange+1]; // holds if integer is member of current 
           
           for(int i=0;i<arr.length;i++){
               int ind = arr[i]-lowerRange;              // maps the integer to the index in the 'existing' array
               existing[ind]=true;
           }
           
           int  complementSize = 0;          
           
           for(int i=0;i<existing.length;i++){
              if(!existing[i]) // if member doesn't exist then part of the complement                         
                  complementSize++;
           }
           
           int[] complementArr = new int[complementSize];
          
           int ind=0;  // current index / filling point of complementArr
          
           for(int i=0;i<existing.length;i++) {
               if(!existing[i]) {
                   complementArr[ind]=i+lowerRange; // turning the index back into the integer
                   ind++;
               }
            }
           
           return new IntSet(complementArr);
           
       }
       
       
       /**
        * Union: Set of members that belong to the first set "or" the second set. 
        * first set here being current arr of this instance
        * @param s 
        * @return new Intset that is the union of the both 
        */
       public IntSet union( IntSet s) {
           
           if(s.size()==0 || this.size()==0)
               return null;

           
           Boolean[] unionMember = new Boolean[upperRange-lowerRange+1]; // holds if integer should be member of union
           
           for(int i=0;i<arr.length;i++){
               int ind = arr[i]-lowerRange; // maps the integer to the index in the unionMember array
               unionMember[ind]=true;
           }
           
           int[] sArr = s.getArr();
           
           for(int i=0;i<sArr.length;i++){
               int ind = sArr[i]-lowerRange; // maps the integer to the index in the unionMember array
               unionMember[ind]=true;
           }
           
           int  unionSize = 0;   // final union size              
           
           for(int i=0;i<unionMember.length;i++){
              if(unionMember[i]) // if member  then part of the union                         
                  unionSize++;
           }
           
           int[] unionArr = new int[unionSize];
          
           int ind=0;
          
           for(int i=0;i<unionMember.length;i++) {
               if(unionMember[i]) {
                   unionArr[ind]=i+lowerRange;  // turning the index back into the integer
                   ind++;
               }
            }
           
           return new IntSet(unionArr);
           
       }
       
       /**
        * Difference: Set of members that belong to the first set "and not" the second set.  
        * @param s  
        * @return new instance of IntSet contains difference elements
        */
       public IntSet difference( IntSet s) {
           
           if(s.size()==0 || this.size()==0)
               return null;

           
           boolean[] differenceMember = new boolean[upperRange-lowerRange+1]; // holds if integer should be member of difference
           
           for(int i=0;i<arr.length;i++){
               int ind = arr[i]-lowerRange; // maps the integer to the index in the differenceMember array
               differenceMember[ind]=true;  // this way the all member of arr get into difference member. some would be removed later
           }
           
           int[] sArr = s.getArr();
           
           for(int i=0;i<sArr.length;i++){
               int ind = sArr[i]-lowerRange; // maps the integer to the index in the differenceMember array
               if(differenceMember[ind])     // if it existed already means the 'arr' contains it and hence must be removed
               differenceMember[ind]=false;
           }
           
           int  diffSize = 0;   // final difference size              
           
           for(int i=0;i<differenceMember.length;i++){
              if(differenceMember[i]) // if member  then part of the difference                       
                  diffSize++;
           }
           
           int[] differenceArr = new int[diffSize];
          
           int ind=0;
          
           for(int i=0;i<differenceMember.length;i++) {
               if(differenceMember[i]) {
                   differenceArr[ind]=i+lowerRange;  // turning the index back into the integer
                   ind++;
               }
            }
           
           return new IntSet(differenceArr);
           
       }
       
       /**
        * Intersection: Set of members that belong to both the first set "and" second set.
        * @param s 
        * @return new instance of intSet that is that contains the elements of the intersection
        */
       
       public IntSet intersection( IntSet s) {
           
           if(s.size()==0 || this.size()==0)
               return null;
           
           boolean[] Member = new boolean[upperRange-lowerRange+1]; // holds if integer should be member of current IntSet
           
           for(int i=0;i<arr.length;i++){
               int ind = arr[i]-lowerRange; // maps the integer to the index in the Member array
               Member[ind]=true;           //  this way the all member of arr get into  member.
           }
           
           int[] sArr = s.getArr();
           
           int  interSize = 0;   // final intersection size
           
           for(int i=0;i<sArr.length;i++){
               int ind = sArr[i]-lowerRange; // maps the integer to the index in the Member array
               if(Member[ind])     // if it existed already means the 'arr' contains it and hence must be considered
                interSize++;
           }
           
           
           int[] intersectionArr = new int[interSize];
          
           int fillingAt=0;
          
           for(int i=0;i<sArr.length;i++){
               int ind = sArr[i]-lowerRange; // maps the integer to the index in the Member array
               if(Member[ind])              // if it existed already means the 'arr' contains it and hence must be considered
               intersectionArr[fillingAt++]=sArr[i]; // added the sArr[i] as it is common btn both
            }
           
           return new IntSet(intersectionArr);
          
       }
       
       /**
        * print whole array
        */
       public void print(){
           for(int i=0;i<arr.length;i++) {
              System.out.print(arr[i]+ " ");    
           }
           
           System.out.println();
       }

 
   
}
