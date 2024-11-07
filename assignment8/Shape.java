package assignment8;

/**
 * interface for shape defines necessary method to be implemented for a shape
 */
public interface Shape {
    
    public float getArea();
    public float getPerimeter();
    public Point getOrigin(); 
    public Boolean isPointEnclosed(Point p);
    public Boolean coordinatesWithIn(Point max);
    public ShapeType getShapeType();
}
