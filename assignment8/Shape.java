package assignment8;

public interface Shape {
    
    public float getArea();
    public float getPerimeter();
    public Point getOrigin(); 
    public Boolean isPointEnclosed(Point p);
    public Boolean coordinatesWithIn(Point max);
    
}
