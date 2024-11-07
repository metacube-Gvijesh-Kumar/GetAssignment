package assignment8;

/**
 * This class represent a rectangle and it implement important methods of the shape class
 * this uses the bottom left and top right corner of the rectangle to represent the rectangle 
 */
class Rectangle implements Shape{
    
    Point bottomLeft;
    Point topRight;
    
    float length; // horizontal
    float breadth;// vertical
    Point origin;
    
    /**
     * the rectangle is being created on the screen with origin as the bottom left corner 
     * considering one of the side parallel to the x axis
     * @param origin
     * @param length
     * @param breadth
     */
    Rectangle(Point origin,float length,float breadth){
        
        if(length<=0 || breadth<=0)
            throw new IllegalArgumentException("invalid dimentions");
        
        if(origin==null)
            throw new NullPointerException("given origin is null");
        
        this.origin=origin;
        this.bottomLeft=new Point(origin.x,origin.y);
        //calculating the top right corner using the origin and length and breadth 
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
    
    /**
     * check if the point p is being enclosed inside the rectangle
     */
    @Override
    public Boolean isPointEnclosed(Point p) {
        
        if(p==null)
            throw new NullPointerException("given point is null");
        
        if(p.x>=bottomLeft.x && p.x<=topRight.x && p.y>=bottomLeft.y && p.y<=topRight.y)
            return true;
        return false;
    }
    
    /**
     * it return true if the rectangle lies within the boundaries of the screen 
     * with the rectangle of the  (0,0) and point max
     */
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
    
    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }
        
}
