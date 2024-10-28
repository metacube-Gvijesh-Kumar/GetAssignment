package assignment4;

public class knightTour {

    int [][] finalBoard;
  
    public Boolean recursivelySolve(int r,int c,int k,int n,int[][] board) {
        
        if(r<0 || c<0 || r>=n || c>=n) {
            return false;
        }
        
        if(k==0){
            
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    finalBoard[i][j]=board[i][j];
                }
            }
            
            return true;
        }
        
        Boolean result=false;
        
        
        if(board[r][c]==-1){

            board[r][c]=n*n-k;
            k--;
            
            for(int dr=-2;dr<=2;dr++){
                for(int dc=-2;dc<=2;dc++) {
                    if(Math.abs(dc)!=Math.abs(dr) && !(dc==0 || dr==0))
                    result=recursivelySolve(r+dr,c+dc,k,n,board);
                    if(result)
                        return result;
                }
            }
            
            k++;
            board[r][c]=-1;
            
        }
        
        return result; 
       
        
    }
    
    public void solution(int n) {
        
        if(n==0 || n==2 )
            return;
        if(n==1) {
            this.finalBoard=new int[][] {{0}};
            return;    
        }
        
        int[][] board   = new int[n][n];
        this.finalBoard = new int[n][n];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                board[i][j]=-1;
                this.finalBoard[i][j]=-1;
            }
        }
        
        Boolean ans = this.recursivelySolve(0,0,n*n,n,board);
        
         System.out.println("Does solution exist: "+ans);
    }
    
    public static void main(String[] args) {
        
        knightTour k = new knightTour();
        int n=8;
        k.solution(n);
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    System.out.printf("%d\t",k.finalBoard[i][j]);
            }
            System.out.println();
        }
        
    }

}
