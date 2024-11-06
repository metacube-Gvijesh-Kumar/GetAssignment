package assignment7;

public class Point {
    float x;
    float y;
    
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    Point(float x,float y){
        this.x=x;
        this.y=y;
    }

    public float distance(Point p){
        float distance = (float) Math.sqrt(Math.pow(Math.abs(p.x-this.x),2)+Math.pow(Math.abs(p.x-this.x),2));
        return distance;
    }
}
