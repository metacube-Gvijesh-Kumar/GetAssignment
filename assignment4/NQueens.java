package assignment4;
import java.util.Arrays;

/**
 * contains recursive method that solves famous n queen problem 
 * at the same time contains helper method to achieve the solution
 * member variable's help with the 
 * 
 * The important thing about this approach is that I use dedicate every cell to two diagonals ex
 * right aligned \ and left aligned / 
 * for a board of size n 
 * there can be 2*(n-1)-1 diagonals containing more than one cell
 * Right Aligned Diagonal of the cell can be found using the fact that 
 * for cell (r,c) if r==c then it will lie in the middle diagonal
 * any other cell's diagonal can be calculated based on its positional difference with any (x,y) cell on row where x==y
 * Left Aligned Diagonal is nothing different , but it is just the result from the right aligned diagonal but with the row wise rotation towards right
 */
public class NQueens {
   
    Boolean [] columns;      // store if there is any queen in given column 
    Boolean [] rows;         // store if there is any queen in given row
    Boolean [] rDiagonals;   // store if for given right tilted '\' diagonal there is any queen in the diagonal
    Boolean [] lDiagonals;   // store if for given left tilted '/' diagonal there is any queen in the diagonal
    Boolean [][] finalBoard; // store the final solution that is found at the end of the solution
   
    /**
     * for given row column check if it is safe to place a diagonal 
     * check if the place is not under attack from any side
     * @param r row
     * @param c column
     * @param res 
     * @return true if only the requested cell is safe and not under attack by any queen
     */
     
    private Boolean safe(int r,int c, Boolean[][] res) {
           
        int rDInd = rDiagonalIndFinder(r,c); 
        int lDInd = lDiagonalIndFinder(r,c);
        
        if(rows[r] || columns[c]) // checking rows and columns
            return false;
        else if(rDInd!=-1 && rDiagonals[rDInd]) { // checking right aligned diagonals
            return false;
        }
        else if(lDInd!=-1 && lDiagonals[lDInd])   // checking left aligned diagonals
            return false;
        else 
            return true;
    }
    
    /**
     * 
     * @param r row
     * @param c column
     * @param q remaining queens count to be placed
     * @param n dimension of board n*n
     * @param board holds the positions where queen has been placed
     * @return true if any form of result was found
     */
    private Boolean recursivelySolve(int r,int c,int q,int n,Boolean[][] board) {
        
        if(q==0) { // all queens placed
            System.out.println("reached solution");
           
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                   if(board[i][j])                        
                       this.finalBoard[i][j]=board[i][j]; // store the ans in the finalBoard
                }
                
            }
            
            return true;
        }

        
        if(r<0 || c<0 || r>=n || c>=n) { // out of bound return false
            return false;            
        }
        
        Boolean result=false;
            
        if(safe(r,c,board)) {
            
            int rDInd = rDiagonalIndFinder(r,c); //find the right diagonal
            int lDInd = lDiagonalIndFinder(r,c); // find the left diagonal
            
            board[r][c]=true; // mark the queen on the board
            
            rows[r]=true;     // mark the row as occupied
            columns[c]=true;  // mark the column as occupied
            
            if(lDInd!=-1)     // make sure it is not a single cell diagonal           
                lDiagonals[lDInd]=true;
            
            if(rDInd!=-1)     // make sure it is not a single cell diagonal
                rDiagonals[rDInd]=true;
            
            q--;     // count of queen remaining decreased
            
            result=recursivelySolve(r+1,0,q,n,board); // place next queen in next row
            
            // back tracking 
            q++;  
            
            board[r][c]=false;
            rows[r]=false;
            columns[c]=false;
            
            if(lDInd!=-1)                
                lDiagonals[lDInd]=false;
            
            if(rDInd!=-1)
                rDiagonals[rDInd]=false;
        }
        
        if(result)
            return result;
        
        result = recursivelySolve(r,c+1,q,n,board); // for case where we skip placing a queen on the current cell
        
        return result;
    }
    
    /**
     * 
     * @param r row
     * @param c columns
     * @return the index of the right diagonal for the cell (r,c)
     */
    private int rDiagonalIndFinder(int r,int c){
        

        int length = this.rDiagonals.length; // finds the no of diagonals
        int middle = (length+1)/2-1;         // the index of the middle diagonal passing through (r,c) where r==c 
        int n = this.rows.length;             
        int ind =-1;                        // default value used if the diagonal is just that is passes through only one cell 
        
        if((r==0 && c==n-1) || (r==n-1 && c==0) )  // single diagonals cell
            ind=-1;
        else if(r==c)                              // middle index for r==c
            ind=middle;
        else if(c>r)                               // if the cell is right to index     
            ind=middle+(c-r);                      // then c-r as the c is larger
        else 
            ind=middle-(r-c);                      // in this case cell is left to the index
                                                    
        // for simplicity we could have used  middle + Math.abs(r-c);              
       
        return ind;
        
    }
    
    /**
     * @param r row
     * @param c columns
     * @return the index of the left diagonal for the cell (r,c)
     */
    private int lDiagonalIndFinder(int r,int c) {
        
        int n = this.rows.length;
        
        if((r==0 && c==0) || (r==n-1 && c==n-1) ) // single diagonals cell
            return -1;
      
        return rDiagonalIndFinder(r,n-1-c);       // the left diagonal is index is same as the right but rotated towards right row wise
    }
    
    /**
     * this act as a setup for the recursive methods this 
     * gives the method the required parameter in right way 
     * @param n the dimension of board also the no of queens to placed
     */
    public void solution(int n) {
        
        if(n==0 || n==2 )   // no solution exist for these dimensions
            return;
        if(n==1) {
            this.finalBoard=new Boolean[][] {{true}}; // for dimension 1 simply put the queen at the only cell
            return;    
        }
        
        // initialize the arrays to used in the recursive function
        
        Boolean[][] board = new Boolean[n][n];
        this.finalBoard=new Boolean[n][n];     
        
        this.rows = new Boolean[n];            
        this.columns = new Boolean[n];
        
        System.out.println("n is given as: "+ n);
        
        int noOfDiagonals = 2*(n-1)-1;         //calculated formula 
        this.rDiagonals = new Boolean[noOfDiagonals];
        this.lDiagonals = new Boolean[noOfDiagonals];
        
        //default value filling
        Arrays.fill(rows, false);
        Arrays.fill(columns, false);
        Arrays.fill(rDiagonals, false);
        Arrays.fill(lDiagonals, false);
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                board[i][j]=false;
                this.finalBoard[i][j]=false;
            }
        }
        
        System.out.println("is possible to place n queen: "+this.recursivelySolve(0,0,n,n,board));

    }
    
    public static void main(String[] args) {
        
        NQueens r = new NQueens();
        int n=9;
        r.solution(n);
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(r.finalBoard[i][j])                     
                    System.out.print("1"+" ");
                else
                    System.out.print("0"+" ");
            }
            System.out.println();
        }
        
    }

}
