package assignment4;

public class knightTour {

    int [][] finalBoard;
   
    
    
    
    public Boolean recursivelySolve(int r,int c,int k,int n,int[][] board) {
            
        if(r<0 || c<0 || r>=n || c>=n) {
            return false;            
        }
        
        Boolean result=false;
        int nxtr=r;
        int nxtc=c;
        
        if(board[nxtr][nxtc]==-1){
            board[nxtr][nxtc]=n-k;
            k--;
            result=recursivelySolve(nxtr,nxtc,k,n,board);
            k++;
        }
        
        if(result)
            return result;
        
        result = recursivelySolve(r,c+1,k,n,board);
        
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
        
        Boolean ans = this.recursivelySolve(0,0,n,n,board);
         System.out.println(ans);
    }
    
    public static void main(String[] args) {
        
        knightTour k = new knightTour();
        int n=9;
        k.solution(n);
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    System.out.printf("%d\t",k.finalBoard[i][j]);
            }
            System.out.println();
        }
        
    }

}
