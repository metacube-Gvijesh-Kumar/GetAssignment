package assignment6;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * represent a cell of matrix that hold the value of cell 
 */
public class Cell{
    private final int row;
    private final int column;
    private final int value;
    
    /**Getters 
     * @return the property value
     */
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public int getValue() {
        return value;
    }

    /**
     * @param row 
     * @param column
     * @param value
     * @throws IllegalArgumentException in case row or column is invalid
     */
    Cell(int row,int column,int value)throws IllegalArgumentException{
        if(row<0 || column<0)
           throw new IllegalArgumentException("invalid row or column");
        if(value==0)
            throw new IllegalArgumentException("value can't be zero");
        this.row=row;
        this.column=column;
        this.value=value;
    }
    
    /**
     * this make sure that any map of the cell will only consider the row and column for the hash creation 
     * so for the cell with same row,column with different value will also use same key in the hash map
     */
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
            append(row).
            append(column).
            toHashCode();
    }
    
    /**
     * this make sure any object with same row,column equivalent any other cell with same row,column
     * without the value
     */
    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof Cell))
            return false;
        
        if (obj == this)
            return true;

        Cell c = (Cell) obj;
        
        if(row==c.getRow() && column==c.getColumn())
            return true;
        
         return false;
    }
      
    public static void main(String [] args) {
        System.out.println("called");
    }

}
