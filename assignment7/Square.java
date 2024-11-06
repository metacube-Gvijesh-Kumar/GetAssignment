package assignment7;

public class Square implements Shape{
    
    Point bottomLeft;
    Point topRight;
    float width;
    Point origin;
    
    // considering one of the side parallel to the x axis
    
    Square(Point origin,float width){
        
        if(origin==null)
            throw new NullPointerException("given origin point is null");
        
        if(width<=0)
            throw new IllegalArgumentException("invalid width for the square");
        
        this.origin=origin;
        this.bottomLeft=new Point(origin.x,origin.y);
        this.topRight  =new Point(origin.x+width,origin.y+width);
        this.width=width;        
    }
   

    @Override
    public float getArea() {
        return width*width;
    }

    @Override
    public float getPerimeter() {
        return 4*width;
    }

    @Override
    public Point getOrigin() {
      return origin;    
    }
    
    @Override
    public Boolean isPointEnclosed(Point p) {
        
        if(p==null)
            throw new NullPointerException("given point p can't be null");
        
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
