package assignment7;

public class Rectangle implements Shape{
    
    Point bottomLeft;
    Point topRight;
    
    float length; // horizontal
    float breadth;// vertical
    Point origin;
    
    // considering one of the side parallel to the x axis
    
    Rectangle(Point origin,float length,float breadth){
        
        if(length<=0 || breadth<=0)
            throw new IllegalArgumentException("invalid dimentions");
        
        if(origin==null)
            throw new NullPointerException("given origin is null");
        
        this.origin=origin;
        this.bottomLeft=new Point(origin.x,origin.y);
        this.topRight  =new Point(origin.x+length,origin.y+breadth);
        
        this.breadth=breadth;
        this.length=length;
    }
   

    @Override
    public float getArea() {
        return length*breadth;
    }

    @Override
    public float getPerimeter() {
        return 2*(length+breadth);
    }

    @Override
    public Point getOrigin() {
      return origin;    
    }
    
    @Override
    public Boolean isPointEnclosed(Point p) {
        
        if(p==null)
            throw new NullPointerException("given point is null");
        
        if(p.x>=bottomLeft.x && p.x<=topRight.x && p.y>=bottomLeft.y && p.y<=topRight.y)
            return true;
        return false;
    }

    @Override
    public Boolean coordinatesWithIn(Point max) {
        
        if(max==null)
            throw new NullPointerException("given point is null");
        
        if(bottomLeft.x<0 || bottomLeft.y<0 || bottomLeft.x>max.x || bottomLeft.y>max.y)
            return false;
        
        if(topRight.x<0 || topRight.y<0 || topRight.x>max.x || topRight.y>max.y)
            return false;
        
        return true;
    }
        
}
