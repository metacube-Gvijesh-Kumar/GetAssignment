package assignment6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * represent's two-dimensional sparse matrices 
 * for space efficiency it only stores stores only non zero elements of a matrix.
 * it provides common matrix operation such as transposition,add,multiply,check symmetry etc.
 */
public class SparseMatrix {
    
    private final int rows;
    private final int columns;
    private final Cell[] cells;
    //maps each cell to its value for easy access 
    private final Map<Cell,Integer> cellMap;
    
    public Cell[] getCells() {
        return cells;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    
    public Map<Cell,Integer> getCellMap() {
        return cellMap;
    }
    
    /**
     * constructor that used the cells array as input and create the cell map that is easy to traverse and use
     * @param cells
     * @param rows
     * @param columns
     * @throws IllegalArgumentException in case invalid rows,column,cell input
     */
    SparseMatrix(Cell[] cells,int rows,int columns) throws IllegalArgumentException{
        
        if(rows<=0 || columns<=0)
            throw new IllegalArgumentException("invalid no of rows and columns");
        
        Map<Cell,Integer> tmpCellMap = new HashMap<Cell,Integer>();
        
        for(int i=0;i<cells.length;i++){
           
            // in case the cells are out of bound or the cell is repeating we throw exception
           if(tmpCellMap.containsKey(cells[i]) || cells[i].getRow()>=rows || cells[i].getColumn()>=columns)
               throw new IllegalArgumentException("duplicate cell in the input array of cell");
            // we are not supposed to store the zero value cells
           if(cells[i].getValue()==0)
               throw new IllegalArgumentException("we are not saving the zero values in memory");
           tmpCellMap.put(cells[i],cells[i].getValue());    
        }
        
        this.cellMap = tmpCellMap;
        this.rows = rows;
        this.columns = columns;
        this.cells = cells;
    }
   
   
    /*
     * transpose is defined as if the matrix is rotated taking the diagonal as matrix or say rows become column and vice versa
     * The method simply create a new sparse matrix by swapping the row,column       
     * @return
     */
    public SparseMatrix transpose(){
        
        Cell[] transCells = new Cell[cells.length];
        
        for(int i=0;i<cells.length;i++){
           Cell c = new Cell(cells[i].getColumn(),cells[i].getRow(),cells[i].getValue()); // swap row and column
           transCells[i]=c;         
        }
        SparseMatrix trans = new SparseMatrix(transCells,rows,columns);
        return trans;
    }
    
    /**
     * A matrix is symmetric if transpose is same as original or say for the row,columns swapped the value don't change
     * @return
     */
    public Boolean isSymmetric(){
        
        for(int i=0;i<cells.length;i++){
            Cell c = new Cell(cells[i].getColumn(),cells[i].getRow(),cells[i].getValue()); // swap and check
            if(!cellMap.containsKey(c))      // the swapped cell must exist
                return false;
            if(cellMap.get(c)!=c.getValue()) // the value must be same as well
                return false;
         }
        
         return true;
    }
    
    /**
     * return instanceMatrix+s
     * The cell to cell addition of matrix
     * @param s
     * @return sparseMatrix result of addition
     */
    public SparseMatrix add(SparseMatrix s){
        
        if(s.getRows()!=rows || s.getColumns()!=columns)
            throw new IllegalArgumentException("the dimension of the input matrix is not the same as the instance");
      
        Map<Cell,Integer> sCellMap = s.getCellMap();
       
        // for every cell of the current interface find the corresponding cell in s
        for(int i=0;i<cells.length;i++){
            Cell c =cells[i];
            if(sCellMap.containsKey(c))
               sCellMap.put(c,sCellMap.get(c)+c.getValue()); // store result in the s itself for now
            else
               sCellMap.put(c,c.getValue()); // if cell don't exist create
        }
        
        List<Cell> addedCellsList = new ArrayList<Cell>();
        
        for (Map.Entry<Cell,Integer> mapElement : sCellMap.entrySet()) { // sCellMap contains result
            
            Cell c = mapElement.getKey();
            int value = mapElement.getValue();
            Cell addedCell = new Cell(c.getRow(),c.getColumn(),value); // create new cell for the result can't use s cell's
            addedCellsList.add(addedCell);
        }
        
        Cell[] addedCells = new Cell[addedCellsList.size()]; // used for sparseMatrix creation
         
        for(int i=0;i<addedCellsList.size();i++) {
            addedCells[i]=addedCellsList.get(i);
        }
        
        SparseMatrix result = new SparseMatrix(addedCells,rows,columns);
        return result;
        
    }
    
    /**
     * 
     * @param s
     * @return
     */
    public SparseMatrix multiply(SparseMatrix s){
       
        int sRows = s.getRows();
        int sColumns= s.getColumns();
        
        // for multiplication of r*c to s*t rules are c must be equal to s 
        if(columns!=sRows)
            throw new IllegalArgumentException("the dimension of the input matrix is not compatible for multiplication with the instance");
        
        Map<Cell,Integer> sCellMap = s.getCellMap();
        
        //will hold the result of multiplication 
        Map<Cell,Integer> mulCellMap = new HashMap<Cell,Integer>();
        
        //every cell in the this matrix contributes to the final mul matrix by multipliying itself to some cell in the s matrix
        // this way we only traverse non-zero cells
        
        for (Map.Entry<Cell,Integer> mapElement : cellMap.entrySet()) {
            
            Cell cl = mapElement.getKey();
          
            for(int sc=0;sc<sColumns;sc++) { // each cell of this interact with the corresponding cell of  each s column
              
               Cell scl = new Cell(cl.getColumn(),sc,-1); // for r,c cell of this corresponds to the c,sc 
               
               if(!sCellMap.containsKey(scl))
                   continue;
               
               int mlVal =cl.getValue()*sCellMap.get(scl); // value of this cell * value of s cell
               
               Cell mlcl = new Cell(cl.getRow(),sc,-1);
               
               if(mulCellMap.containsKey(mlcl)) // if the previous cells also contributed consider it 
                  mlVal+=mulCellMap.get(mlcl);
               
               mulCellMap.put(mlcl,mlVal);      // create the result Map
               
            }  
            
        }
        
        // created the array of cell required to create the sparse matrix
        
        Cell[] mulCells = new Cell[mulCellMap.size()];
        int ind=0;
        
        for (Map.Entry<Cell,Integer> mapElement : mulCellMap.entrySet()) {
          
            Cell cl = mapElement.getKey();
            
            // the cl.getValue might be always contains the correct value hence use the value from the maps
            Cell mulCell = new Cell(cl.getRow(),cl.getColumn(),mapElement.getValue());
            
            mulCells[ind++]=mulCell;
           
        }
        
        SparseMatrix result = new SparseMatrix(mulCells,rows,sColumns);
        
        return result;
       
    }
    
    public void print() {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                Cell c = new Cell(i,j,-1);
                if(cellMap.containsKey(c))
                   System.out.print(cellMap.get(c));
                else
                   System.out.print(0); 
                System.out.print('\t');
            }
            System.out.println();
        }
        System.out.println();
        
    }
    
    public static void main(String[] args) {
        
        Cell[] cells = new Cell[] {
          new Cell(0,0,1),
          new Cell(1,1,2),
          new Cell(2,2,3),
          new Cell(0,2,3)
        };
        
        SparseMatrix s = new SparseMatrix(cells,3,3);
        s.print();        
        System.out.println(s.isSymmetric());
                
        SparseMatrix trans = s.transpose();
        trans.print();
        
        SparseMatrix added = s.add(trans);
        added.print();
        
        SparseMatrix multiplied = trans.multiply(added);
        multiplied.print();
    }

}
