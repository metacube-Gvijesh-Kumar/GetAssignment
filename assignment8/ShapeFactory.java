package assignment8;
import java.util.List;

/**
 * implement the factory design pattern 
 * created the requested shape created using the origin as starting point for the screen
 */
public class ShapeFactory {

    /**
     * 
     * @param sType shape type to be created
     * @param origin starting for the shape creation
     * @param dimentions  holds the dimension of the shape for ex for circle the radius
     * @return
     */
   public static Shape createShape(ShapeType sType,Point origin ,List<Integer> dimentions) {
       
          if(origin==null)
              throw new NullPointerException("origin point can't be null");
       
          
         Shape s = null;
         
         // hold the length at zero index and breadth at 1st index
         if(sType==ShapeType.RECTANGLE && dimentions.size()>=2)
             s= new Rectangle(origin,dimentions.get(0),dimentions.get(1));
         
         // hold the length at zero index 
         else if(sType==ShapeType.SQUARE && dimentions.size()>=1)
             s= new Square(origin,dimentions.get(0));
         
         // holds the radius on the 0th index
         else if(sType==ShapeType.CIRCLE && dimentions.size()>=1)
             s= new Circle(origin,dimentions.get(0));
         
         // holds the height at zero index and base at the 1st index
         else if(sType==ShapeType.TRIANGLE && dimentions.size()>=2)
             s= new Triangle(origin,dimentions.get(0),dimentions.get(1));
         
         else 
             throw new IllegalArgumentException("invalid dimensions or shape type");
         
         return s;
   }

}
