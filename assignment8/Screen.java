package assignment8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This custom comparator class is required custom sorting using the  collection class
 */
class ShapeComparator implements Comparator<Shape> { 
    
    SortType st;
    Map<Shape,LocalDateTime> shapes; // required for the sorting based on the time the shape was placed on the screen
    
    ShapeComparator(SortType st,Map<Shape,LocalDateTime> shapes){
        if(st==null || shapes==null)
            throw new NullPointerException("the given input can't be null");
        this.shapes=shapes;
        this.st=st;
    }
    
    @Override
    public int compare(Shape s1, Shape s2) {
        
        switch(st){
        
        case SortType.AREA:
            if(s1.getArea()==s2.getArea())
                return 0;
            else if(s1.getArea()>s2.getArea())
                return 1;
            else 
                return -1;
            
        case SortType.PERIMETER:
            if(s1.getPerimeter()==s2.getPerimeter())
                return 0;
            else if(s1.getPerimeter()>s2.getPerimeter())
                return 1;
            else 
                return -1;
            
        case SortType.ORIGIN:
            
            float dist1 = s1.getOrigin().distance(new Point(0,0));
            float dist2 = s2.getOrigin().distance(new Point(0,0));
            
            if(dist1==dist2)
                return 0;
            else if(dist1>dist2)
                return 1;
            else 
                return -1;
            
        case SortType.TIMESTAMP:
            
            LocalDateTime dt1 = shapes.get(s1);
            
            LocalDateTime dt2 = shapes.get(s2);
            
            if(dt1.isEqual(dt2))
                return 0;
            else if(dt1.isAfter(dt2))
                return -1;
            else 
                return 1;
           
        }
        
        return 0;
        
    } 
} 

/**
 * the screen class stores the shapes and the timestamp at which the shapes was added to the screen 
 */
public class Screen {
    
     Map<Shape,LocalDateTime> shapes;
     Point max;
     List<Shape> shapeList; 
     
     /**
      * define the screen size from (0,0) to (mx) point and other initialization
      * @param mx
      */
     public Screen(Point mx){
         
         if(mx.x<=0 || mx.y<=0)
             throw new IllegalArgumentException("invalid screen size");
         
         shapes= new HashMap<Shape,LocalDateTime>();
         shapeList = new ArrayList<Shape>();
         max=mx;
         
     }
     
     /**
      * Add a shape object to the screen at a specified location with default orientation.
      * default orientation meaning one of the edge of the shape is parallel to the x axis
      * @param s
      */
     public void AddShape(Shape s){

         if(s==null)
             throw new NullPointerException("origin point can't be null");
             
         if(!s.coordinatesWithIn(max))
             throw new IllegalArgumentException("shape out of screen");
         
         LocalDateTime current = LocalDateTime.now();
         shapes.put(s,current);
         shapeList.add(s);
     }
     
     public LocalDateTime getTimeStamp(Shape s) {
         
         if(s==null)
             throw new NullPointerException("origin point can't be null");
         
         if(shapes.containsKey(s))
             return shapes.get(s);
         
         throw new IllegalArgumentException("this shape is not part of the screen");
     }
    
     //Delete a shape object from the screen
     public Boolean DeleteShape(Shape s){
         
         if(s==null)
             throw new NullPointerException("origin point can't be null");
         
         if(shapes.containsKey(s)) {
             shapes.remove(s);
             shapeList.remove(s);
             return true;
         }
        
         return false;
     }
     
     
     //Delete all shape objects of a specific type of shape
     public void DeleteAllShapes(ShapeType st){
         
         // collect the shapes to be deleted
         List<Shape> toDelete = new ArrayList<Shape>();
         
         for (Map.Entry<Shape,LocalDateTime> mapElement : shapes.entrySet()) { 
             Shape sh = mapElement.getKey();
             if(sh.getShapeType()==st)
             toDelete.add(sh);
         }
         
         for(Shape x:toDelete) {
             shapes.remove(x);
             shapeList.remove(x);
         }
     }
      
     //Return the shape objects on the screen sorted in desired descending order based on area, perimeter, timestamp or origin distance (distance of the origin of the shape from the origin of the screen). Consider various design options for this method.
     public List<Shape> sortedShapes(SortType st){
         List<Shape> res = new ArrayList<Shape>(shapeList);
         ShapeComparator scmp = new ShapeComparator(st,shapes);
         Collections.sort(res,scmp);
         return res;
     }
     
     //Return the list of shape objects enclosing a specified point.
     public List<Shape> enclosingShape(Point p){
         
         //collect shapes with the point enclosed
         List<Shape>res = new ArrayList<Shape>();
         
         for(Shape s:shapeList) {
             if(s.isPointEnclosed(p))
                 res.add(s);
         }
         return res;
     }
     
}
