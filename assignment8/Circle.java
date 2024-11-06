package assignment8;

public class Circle implements Shape{
    
    Point center;
    float radius;
    Point origin;
    
    Circle(Point origin,float radius){
            
        if(origin==null)
            throw new NullPointerException("given point is null");
        
         if(radius<0)
              throw new IllegalArgumentException("radius can't be negative");
         
         this.origin=origin;         
         this.radius=radius;
         
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

    @Override
    public Boolean isPointEnclosed(Point p) throws NullPointerException {
        //distance between the point p and center of the circle
        if(p==null)
            throw new NullPointerException("given point is null");
        
        float distance = p.distance(center);
        return distance<=radius;
    }

    @Override
    public Boolean coordinatesWithIn(Point max) {
        
        if(max==null)
            throw new NullPointerException("given point is null");
        
        if(center.x<0 || center.y<0 || center.x>max.x || center.y>max.y)
            return false;
              
        if(origin.x<0 || origin.y<0 || origin.x>max.x || origin.y>max.y)
            return false;
        
        return true;
    }

}
