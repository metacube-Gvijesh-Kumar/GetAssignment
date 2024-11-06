package assignment8;

public class Triangle implements Shape{
    
    Point leftCorner;
    Point peak;
    Point rightCorner;
    
    Point origin;
    float height;
    float base;
    
    //requires the base to be parallel to the x axis
    
    Triangle(Point origin,float height,float base){
        
        if(origin==null)
            throw new NullPointerException("origin point can't be null");
        
        if(height<=0 || base<=0)
            throw new IllegalArgumentException("dimestions can't be negative");
        
        this.origin=origin;
        this.peak= new Point(origin.x+(float)0.5*base,origin.y+height);
        this.leftCorner=origin;
        this.rightCorner=new Point(origin.x+base,origin.y);
        this.base=base;
        this.height=height;
     
    }
    
    @Override
    public float getArea() {
        return (float) (0.5*base*height);
    }

    @Override
    public float getPerimeter() {
       return leftCorner.distance(rightCorner)+leftCorner.distance(peak)+rightCorner.distance(peak);
    }

    @Override
    public Point getOrigin() {
        return leftCorner;
    }

    static double area(Point p1,Point p2,Point p3)
    {
        if(p1==null || p2==null || p3==null)
            throw new NullPointerException("given point is null");
        
    return Math.abs((p1.x*(p2.y-p3.y) + p2.x*(p3.y-p1.y)+p3.x*(p1.y-p2.y))/2.0);
    }

   
    @Override
    public Boolean isPointEnclosed(Point p) {
        
        if(p==null)
            throw new NullPointerException("given point is null");
        
        double A = area (leftCorner,rightCorner,peak);
        
        double A1 = area (p,leftCorner,rightCorner);
        double A2 = area (p,leftCorner,peak);
        double A3 = area (p,rightCorner,peak);
        
        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }

    @Override
    public Boolean coordinatesWithIn(Point max) {
        
        if(max==null)
            throw new NullPointerException("given point is null");
        
        if(leftCorner.x<0 || leftCorner.y<0 || leftCorner.x>max.x || leftCorner.y>max.y)
            return false;
        
        if(rightCorner.x<0 || rightCorner.y<0 || rightCorner.x>max.x || rightCorner.y>max.y)
            return false;
        
        if(peak.x<0 || peak.y<0 || peak.x>max.x || peak.y>max.y)
            return false;
        
        return true;
    }

}
