package assignment7;
import java.util.List;

public class ShapeFactory {

    
   public Shape createShape(ShapeType sType,Point origin ,List<Integer> dimentions) {
       
          if(origin==null)
              throw new NullPointerException("origin point can't be null");
       
          
         Shape s = null;
         
         if(sType==ShapeType.RECTANGLE && dimentions.size()>=2)
             s= new Rectangle(origin,dimentions.get(0),dimentions.get(1));
         else if(sType==ShapeType.SQUARE && dimentions.size()>=1)
             s= new Square(origin,dimentions.get(0));
         else if(sType==ShapeType.CIRCLE && dimentions.size()>=1)
             s= new Circle(origin,dimentions.get(0));
         else if(sType==ShapeType.TRIANGLE && dimentions.size()>=2)
             s= new Triangle(origin,dimentions.get(0),dimentions.get(1));
         else 
             throw new IllegalArgumentException("invalid dimensions or shape type");
         
         return s;
   }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
