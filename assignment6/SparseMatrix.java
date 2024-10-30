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
    
    SparseMatrix(Cell[] cells,int rows,int columns){
        
        if(rows<=0 || columns<=0)
            throw new IllegalArgumentException("invalid no of rows and columns");
        
        Map<Cell,Integer> tmpCellMap = new HashMap<Cell,Integer>();
        
        for(int i=0;i<cells.length;i++){
           if(tmpCellMap.containsKey(cells[i]) || cells[i].getRow()>=rows || cells[i].getColumn()>=columns)
               throw new IllegalArgumentException("duplicate cell in the input array of cell");
           if(cells[i].getValue()==0)
               throw new IllegalArgumentException("we are not saving the zero values in memory");
           tmpCellMap.put(cells[i],cells[i].getValue());    
        }
        
        this.cellMap = tmpCellMap;
        this.rows = rows;
        this.columns = columns;
        this.cells = cells;
    }
   
   

    public SparseMatrix transpose(){
        
        Cell[] transCells = new Cell[cells.length];
        
        for(int i=0;i<cells.length;i++){
           Cell c = new Cell(cells[i].getColumn(),cells[i].getRow(),cells[i].getValue());
           transCells[i]=c;         
        }
        SparseMatrix trans = new SparseMatrix(transCells,rows,columns);
        return trans;
    }
            
    public Boolean isSymmetric(){
        
        for(int i=0;i<cells.length;i++){
            Cell c = new Cell(cells[i].getColumn(),cells[i].getRow(),cells[i].getValue());
            if(!cellMap.containsKey(c))
                return false;
            if(cellMap.get(c)!=c.getValue())
                return false;
         }
        
         return true;
    }
    
    public SparseMatrix add(SparseMatrix s){
        
        if(s.getRows()!=rows || s.getColumns()!=columns)
            throw new IllegalArgumentException("the dimension of the input matrix is not the same as the instance");
      
        Map<Cell,Integer> sCellMap = s.getCellMap();
       
        for(int i=0;i<cells.length;i++){
            Cell c =cells[i];
            if(sCellMap.containsKey(c))
               sCellMap.put(c,sCellMap.get(c)+c.getValue());
            else
               sCellMap.put(c,c.getValue()); 
        }
        
        List<Cell> addedCellsList = new ArrayList<Cell>();
        
        for (Map.Entry<Cell,Integer> mapElement : sCellMap.entrySet()) {
            
            Cell c = mapElement.getKey();
            int value = mapElement.getValue();
            Cell addedCell = new Cell(c.getRow(),c.getColumn(),value);
            addedCellsList.add(addedCell);
        }
        
        Cell[] addedCells = new Cell[addedCellsList.size()];
         
        for(int i=0;i<addedCellsList.size();i++) {
            addedCells[i]=addedCellsList.get(i);
        }
        
        SparseMatrix result = new SparseMatrix(addedCells,rows,columns);
        return result;
        
    }
    
    public SparseMatrix multiply(SparseMatrix s){
       
        int sRows = s.getRows();
        int sColumns= s.getColumns();
        
        if(columns!=sRows)
            throw new IllegalArgumentException("the dimension of the input matrix is not compatible for multiplication with the instance");
        
        Map<Cell,Integer> sCellMap = s.getCellMap();
        
        Map<Cell,Integer> mulCellMap = new HashMap<Cell,Integer>();
        
        for (Map.Entry<Cell,Integer> mapElement : cellMap.entrySet()) {
            
            Cell cl = mapElement.getKey();
          
            for(int sc=0;sc<sColumns;sc++) {
               
               int mlVal=0;
               
               Cell scl = new Cell(cl.getColumn(),sc,-1);
               
               if(!sCellMap.containsKey(scl))
                   continue;
               
               mlVal =cl.getValue()*sCellMap.get(scl);
               
               Cell mlcl = new Cell(cl.getRow(),sc,-1);
               
               if(mulCellMap.containsKey(mlcl))
                  mlVal+=mulCellMap.get(mlcl);
               
               mulCellMap.put(mlcl,mlVal);
               
            }  
            
        }
        
        Cell[] mulCells = new Cell[mulCellMap.size()];
        int ind=0;
        
        for (Map.Entry<Cell,Integer> mapElement : mulCellMap.entrySet()) {
          
            Cell cl = mapElement.getKey();
            
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
