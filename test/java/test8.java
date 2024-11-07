package test.java;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import assignment8.Point;
import assignment8.Screen;
import assignment8.Shape;
import assignment8.ShapeFactory;
import assignment8.ShapeType;
import assignment8.SortType;

public class test8 {
    
    @Test
    public void shapeTest(){
        
        List<Integer> dimensions = new ArrayList<Integer>();
        dimensions.add(1);
        
        Point origin = new Point(5,6);
        
        Screen smallScreen = new Screen(new Point(2,2));
       
        Shape sh = ShapeFactory.createShape(ShapeType.CIRCLE,origin,dimensions);
        
        try {
            smallScreen.AddShape(sh);
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
       
        Screen sc = new Screen(new Point(200,200));
        sc.AddShape(sh);
        
        System.out.println(sc.getTimeStamp(sh));
        
        sc.DeleteShape(sh);
        
        try {
            System.out.println(sc.getTimeStamp(sh));
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        Shape sh1 = ShapeFactory.createShape(ShapeType.CIRCLE,new Point(5,10),     new ArrayList<>(Arrays.asList(3)));
        Shape sh2 = ShapeFactory.createShape(ShapeType.RECTANGLE,new Point(10,10), new ArrayList<>(Arrays.asList(4,5)));
        Shape sh3 = ShapeFactory.createShape(ShapeType.CIRCLE,new Point(20,20),    new ArrayList<>(Arrays.asList(1)));
        Shape sh4 = ShapeFactory.createShape(ShapeType.TRIANGLE,new Point(2,2),    new ArrayList<>(Arrays.asList(1,4)));
        Shape sh5 = ShapeFactory.createShape(ShapeType.SQUARE,new Point(100,100),  new ArrayList<>(Arrays.asList(10)));
        Shape sh6 = ShapeFactory.createShape(ShapeType.TRIANGLE,new Point(50,50),  new ArrayList<>(Arrays.asList(10,15)));
        
        sc.AddShape(sh1);
        sc.AddShape(sh2);
        sc.AddShape(sh3);
        sc.AddShape(sh4);
        sc.AddShape(sh5);
        sc.AddShape(sh6);
        
        
        List<Shape> areaWise = sc.sortedShapes(SortType.AREA);
        
        for(int i=0;i<areaWise.size()-1;i++) {
            assertTrue(areaWise.get(i).getArea()<areaWise.get(i+1).getArea());
        }
        
        List<Shape> perimeterWise = sc.sortedShapes(SortType.PERIMETER);
        
        for(int i=0;i<perimeterWise.size()-1;i++) {
            assertTrue(perimeterWise.get(i).getPerimeter()<perimeterWise.get(i+1).getPerimeter());
        }
        
        List<Shape> originWise = sc.sortedShapes(SortType.ORIGIN);
        
        for(int i=0;i<originWise.size()-1;i++) {
            assertTrue(originWise.get(i).getOrigin().distance(new Point(0,0))<originWise.get(i+1).getOrigin().distance(new Point(0,0)));
        }
          
        sc.DeleteAllShapes(ShapeType.CIRCLE);
        
        System.out.println(sc.getTimeStamp(sh2));
        
        try {
            System.out.println(sc.getTimeStamp(sh1)); // checking for a circle type
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        Point p = new Point(2,3);
        List<Shape>enclosing=sc.enclosingShape(p);
        
        for(int i=0;i<enclosing.size();i++){
            assertTrue(enclosing.get(i).isPointEnclosed(p));
        }
        
        
    }

}
