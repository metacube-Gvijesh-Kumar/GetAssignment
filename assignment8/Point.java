package assignment8;

/**
 * represent a point in the cartesian coordinate system
 */
public class Point {
    float x;
    float y;
    
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    Point(float x,float y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * find the distance of the this point from the point p
     * @param p
     * @return
     */
    public float distance(Point p){
        float distance = (float) Math.sqrt(Math.pow(Math.abs(p.x-this.x),2)+Math.pow(Math.abs(p.x-this.x),2));
        return distance;
    }
}
