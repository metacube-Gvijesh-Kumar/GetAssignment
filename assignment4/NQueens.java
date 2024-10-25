package assignment4;
import java.util.Arrays;

public class NQueens {
    
    Boolean [] columns;
    Boolean [] rows;
    Boolean [] rDiagonals;
    Boolean [] lDiagonals;
    Boolean [][] finalBoard;
   
    
    public Boolean safe(int r,int c, Boolean[][] res) {
        
        
        
        int rDInd = rDiagonalIndFinder(r,c);
        int lDInd = lDiagonalIndFinder(r,c);
        
        if(rows[r] || columns[c])
            return false;
        else if(rDInd!=-1 && rDiagonals[rDInd]) {
            return false;
        }
        else if(lDInd!=-1 && lDiagonals[lDInd])
            return false;
        else 
            return true;
    }
    
    public Boolean recursivelySolve(int r,int c,int q,int n,Boolean[][] board) {
        
        if(q==0) {
            System.out.println("reached solution");
           
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                   if(board[i][j])
                       this.finalBoard[i][j]=board[i][j];
                }
                
            }
            
            return true;
        }

        
        if(r<0 || c<0 || r>=n || c>=n) {
            return false;            
        }
        
        Boolean result=false;
            
        if(safe(r,c,board)) {
            
            int rDInd = rDiagonalIndFinder(r,c);
            int lDInd = lDiagonalIndFinder(r,c);
            
            board[r][c]=true;
            
            rows[r]=true;
            columns[c]=true;
            
            if(lDInd!=-1)                
                lDiagonals[lDInd]=true;
            
            if(rDInd!=-1)
                rDiagonals[rDInd]=true;
            
            q--;
            
            result=recursivelySolve(r+1,0,q,n,board);
            
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
        
        result = recursivelySolve(r,c+1,q,n,board);
        
        return result;
    }
    
    public int rDiagonalIndFinder(int r,int c){
        

        int length = this.rDiagonals.length;
        int middle = (length+1)/2-1;
        int n = this.rows.length;
        int ind =-1;
        
        if((r==0 && c==n-1) || (r==n-1 && c==0) )
            ind=-1;
        else if(r==c)
            ind=middle;
        else if(c>r)
            ind=middle+(c-r);
        else 
            ind=middle-(r-c);
        
        //System.out.println("row:" + r +' '+"column: "+ c+' '+"index: "+ind+" len:"+length);
        
        return ind;
        
    }
    public int lDiagonalIndFinder(int r,int c) {
        
        int n = this.rows.length;
        
        if((r==0 && c==0) || (r==n-1 && c==n-1) )
            return -1;
      
        return rDiagonalIndFinder(r,n-1-c);
    }
    
    public void solution(int n) {
        
        if(n==0 || n==2 )
            return;
        if(n==1) {
            this.finalBoard=new Boolean[][] {{true}};
            return;    
        }
        
        Boolean[][] board = new Boolean[n][n];
        this.finalBoard=new Boolean[n][n];
        
        this.rows = new Boolean[n];
        this.columns = new Boolean[n];
        System.out.println("n is given as: "+ n);
        int noOfDiagonals = 2*(n-1)-1;// 1+2*(n-2)
        this.rDiagonals = new Boolean[noOfDiagonals];
        this.lDiagonals = new Boolean[noOfDiagonals];
        
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
