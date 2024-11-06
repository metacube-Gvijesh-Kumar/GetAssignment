package assignment8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ShapeComparator implements Comparator<Shape> { 
    
    SortType st;
    Map<Shape,LocalDateTime> shapes;
    
    ShapeComparator(SortType st,Map<Shape,LocalDateTime> shapes){
        if(st==null || shapes==null)
            throw new NullPointerException("the given input can't be null");
        this.st=st;
    }
    
    @Override
    public int compare(Shape s1, Shape s2) {
        
        switch(st){
        
        case SortType.AREA:
            if(s1.getArea()==s2.getArea())
                return 0;
            else if(s1.getArea()>s2.getArea())
                return -1;
            else 
                return 1;
            
        case SortType.PERIMETER:
            if(s1.getPerimeter()==s2.getPerimeter())
                return 0;
            else if(s1.getPerimeter()>s2.getPerimeter())
                return -1;
            else 
                return 1;
            
        case SortType.ORIGIN:
            
            float dist1 = s1.getOrigin().distance(new Point(0,0));
            float dist2 = s2.getOrigin().distance(new Point(0,0));
            
            if(dist1==dist2)
                return 0;
            else if(dist1>dist2)
                return -1;
            else 
                return 1;
            
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

public class Screen {
    
     Map<Shape,LocalDateTime> shapes;
     Point max;
     List<Shape> shapeList;
   
     Screen(Point mx){
         
         if(mx.x<=0 || mx.y<=0)
             throw new IllegalArgumentException("invalid screen size");
         
         shapes= new HashMap<Shape,LocalDateTime>();
         shapeList = new ArrayList<Shape>();
         
     }
     
     //Add a shape object to the screen at a specified location with default orientation.
     public void AddShape(Shape s){

         if(s==null)
             throw new NullPointerException("origin point can't be null");
             
         if(!s.coordinatesWithIn(max))
             throw new IllegalArgumentException("shape out of screen");
         
         LocalDateTime current = LocalDateTime.now();
         shapes.put(s,current);
         shapeList.add(s);
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
     
     
     //Delete all shape objects of a specific type
     public void DeleteAllShapes(ShapeType s){
         
         List<Shape> toDelete = new ArrayList<Shape>();
         
         for (Map.Entry<Shape,LocalDateTime> mapElement : shapes.entrySet()) { 
             Shape sh = mapElement.getKey();
             toDelete.add(sh);
         }
         
         for(Shape x:toDelete) {
             shapes.remove(x);
             shapeList.remove(x);
         }
     }
      
     //Return the shape objects on the screen sorted in desired ascending order based on area, perimeter, timestamp or origin distance (distance of the origin of the shape from the origin of the screen). Consider various design options for this method.
     public List<Shape> sortedShapes(SortType st){
         List<Shape> res = new ArrayList<Shape>(shapeList);
         ShapeComparator scmp = new ShapeComparator(st,shapes);
         Collections.sort(res,scmp);
         return res;
     }
     
     //Return the list of shape objects enclosing a specified point.
     public List<Shape> enclosingShape(Point p){
         
         List<Shape>res = new ArrayList<Shape>();
         
         for(Shape s:shapeList) {
             if(s.isPointEnclosed(p))
                 res.add(s);
         }
         return res;
     }
     
}
