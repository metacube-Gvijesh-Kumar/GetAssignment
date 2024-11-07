package assignment8;

/**
 * This class represent a square and it implement important methods of the shape class
 * this uses the bottom left and top right corner of the rectangle to represent the rectangle 
 */
class Square implements Shape{
    
    Point bottomLeft;
    Point topRight;
    float width;
    Point origin;
    
    /**
     * the square is being created on the screen with origin as the bottom left corner 
     * considering one of the side parallel to the x axis
     * @param origin
     * @param width
     */
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
    
    /**
     * check if the point p is being enclosed inside the square
     */
    @Override
    public Boolean isPointEnclosed(Point p) {
        
        if(p==null)
            throw new NullPointerException("given point p can't be null");
        
        if(p.x>=bottomLeft.x && p.x<=topRight.x && p.y>=bottomLeft.y && p.y<=topRight.y)
            return true;
        return false;
    }
    
    /**
     * it return true if the square lies within the boundaries of the screen ex
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
        return ShapeType.SQUARE;
    }
        
}
