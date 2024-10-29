package assignment4;

/**
 *  This class contains method that help us find the solution for the knight tour problem and find suitable moves
 *  A knight's tour is a sequence of moves of a knight on a chess board such that the knight visits every square only once. 
 *  If the knight ends on a square that is one knight's move from the beginning square (so that it could tour the board again immediately,
 *  following the same path), the tour is closed, otherwise it is open.
 */
public class knightTour {

    int [][] finalBoard; // will hold end result
    
    /**
     * at the end of the finding a tour check if the tour is closed or nor
     * @param board
     * @param n
     * @return true if the tour is closed
     */
    boolean canReachBackStart(int[][] board,int n) {
        
        //final move 
        int endRow=0; 
        int endCol=0;
        
        //starting cell
        int startRow=0;        
        int startCol=0;
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j]==0) {          // start position move no
                    startRow=i;
                    startCol=j;
                }
                else if(board[i][j]==n*n-1) { // final move no is this
                    endRow=i;
                    endCol=j;
                }
            }
        }
        
        // for the startPostiton to be reachable from the endPostion 
        
        if((Math.abs(startCol-endCol)==2 && Math.abs(startRow-endRow)==1) ||  (Math.abs(startCol-endCol)==1 && Math.abs(startRow-endRow)==2))
            return true;
          
        return false;
           
    }
    
    /**
     * this method finds the solution for knight tour problem pass the cell's row and column from where knight should start tour  
     * @param r current row
     * @param c current column
     * @param k knights remaining to be placed
     * @param n dimension of the board
     * @param board holds the the moves played by the knight for ex. if number in the cell is 3 then knight at the end of the 3rd move it was there
     * @param closedTour if closed tour then knight need to reach the same starting cell
     * @return true if the solution exist for the given dimensions
     */
    private Boolean recursivelySolve(int r,int c,int k,int n,int[][] board,boolean closedTour) {
        
        System.out.println("Running");
        System.out.println(".");
        
        if(r<0 || c<0 || r>=n || c>=n) { // out of range 
            return false;
        }
        
        if(k==0){ // base condition all the knights placed successfully
            
            if(closedTour && !canReachBackStart(board,n)) // if solution requires closedTour and closed tour is not possible with this setup 
                return false;
            
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    finalBoard[i][j]=board[i][j]; // store the solution in the finalBoard so it preserves
                }
            }
            
            return true;
        }
        
        Boolean result=false;
        
        
        if(board[r][c]==-1){         // -1 means unvisited 

            board[r][c]=n*n-k;     // the move number 
            k--;                  // knight placed
            
            // the below loops tries all the possible moves for knight from given cell
            
            for(int dr=-2;dr<=2;dr++){ // dr means difference in the row if have to move
                for(int dc=-2;dc<=2;dc++) { // dc means difference in the column if have to move
                    
                    if(Math.abs(dc)!=Math.abs(dr) && !(dc==0 || dr==0)) // horse moves such that dr!=dc means on the dc,dr should be 1 and other has to be 2
                    result=recursivelySolve(r+dr,c+dc,k,n,board,closedTour);       //
                    if(result)
                        return result;
                }
            }
            // back tracking
            k++;
            board[r][c]=-1;
            
        }
        
        return result; 
       
        
    }
    
    /**
     * setup the parameter for calling the recursive method
     * @param n board dimension
     * @param closedTour if closed tour then knight need to reach the same starting cell
     */
    public void solution(int n,boolean closedTour) {
        
        if(n==0 || n==2 ) // no solution exist
            return;
        if(n==1) {
            this.finalBoard=new int[][] {{0}};
            return;    
        }
        
        // initialize the arrays
        int[][] board   = new int[n][n];
        this.finalBoard = new int[n][n];
        
        // marks the board cell as unvisited
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                board[i][j]=-1;
                this.finalBoard[i][j]=-1;
            }
        }
        
        Boolean ans = this.recursivelySolve(0,0,n*n,n,board,closedTour);
        
         System.out.println("Does solution exist: "+ans);
    }
    
    public static void main(String[] args) {
        
        
        knightTour k = new knightTour();
        int n=8;
        boolean closedTour = true; 
        k.solution(n,closedTour);
        
        // evenly prints the board to console
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    System.out.printf("%d\t",k.finalBoard[i][j]);
            }
            System.out.println();
        }
        
    }

}
