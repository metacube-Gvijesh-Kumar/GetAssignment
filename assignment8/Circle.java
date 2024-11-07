package assignment8;

//please find the test class for this assignment under the test.java folder with name test8.java
/**
 *This class represent a circle and it implement important methods of the shape class
 */
class Circle implements Shape{
    
    Point center;
    float radius;
    Point origin; // it is the point on the screen with respect to which our circle is being created
    
    
    /**
     * @param origin
     * @param radius
     */
    Circle(Point origin,float radius){
            
        if(origin==null)
            throw new NullPointerException("given point is null");
        
         if(radius<0)
              throw new IllegalArgumentException("radius can't be negative");
         
         this.origin=origin;         
         this.radius=radius;
         
         // calculating the center coordinates using the origin point 
         // the below formula uses the properties such as the triangle similarity value and pythogorous formula to find the center with respect to the origin  
         
         float centerx = (float) (origin.x+radius*origin.x/Math.sqrt(origin.x*origin.x+origin.y*origin.y));
         float centery = (float) (origin.y+Math.sqrt(radius*radius-centerx*centerx));
         
         this.center=new Point(centerx,centery);  
    }
    
    @Override
    public float getArea() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    public float getPerimeter() {
        return (float) (Math.PI * radius * 2);
    }

    @Override
    public Point getOrigin() {
        return origin;
    }
   
    /**
     * finds if point p is inside the circle using the 
     * fact that if the point distance from center is more than radius then it is out of the circle
     */
    @Override
    public Boolean isPointEnclosed(Point p) throws NullPointerException {
        
        if(p==null)
            throw new NullPointerException("given point is null");
        
        //distance between the point p and center of the circle
        float distance = p.distance(center);
        return distance<=radius;
    }
    
    /**
     * it return true if the circle lies within the boundaries of the screen ex
     * with the rectangle of the  (0,0) and point max
     */
    @Override
    public Boolean coordinatesWithIn(Point max) {
        
        if(max==null)
            throw new NullPointerException("given point is null");
        
        // if the center is out of the the range then return false
        
        if(center.x<0 || center.y<0 || center.x>max.x || center.y>max.y)
            return false;
              
        if(origin.x<0 || origin.y<0 || origin.x>max.x || origin.y>max.y)
            return false;
        
        return true;
    }
    
    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
    }

}
