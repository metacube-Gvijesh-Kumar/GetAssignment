package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import assignment5.IntSet;
import assignment5.Poly;
import assignment5.Term;

public class test5 {

    @Test
    public void testIntSet() {
        
        int[] arr = {2,5,3,10};
        
        IntSet k = new IntSet(arr);
        int [] arr3 = k.getArr();
        assertArrayEquals(arr3,arr);
                  
        int[] arr2 = {2,3,83,5,33,4,10,24};
        IntSet j = new IntSet(arr2);
        
        assertEquals(j.isSubSet(k),true);
        
        System.out.print("complement: ");
        IntSet complementj = j.getComplement();
        complementj.print();
       
        j=j.union(complementj);
        System.out.print("unioun: ");
        j.print();
        
        j = new IntSet(arr2);
        IntSet diff = j.difference(k);
        
        assertArrayEquals(diff.getArr(),new int[]{4,24,33,83});
       
        
        int[] arr4 = {56,43,6,3,2,9,74};
        IntSet l = new IntSet(arr4);
        l = l.intersection(k);
        
        assertArrayEquals(l.getArr(),new int[]{2,3});
        
    }
    
    @Test
    public void testPoly(){
        
              Term[] t1 = {
                new Term(0,1),
                new Term(1,2),
                new Term(2,3)
              };
              Poly p1 = new Poly(t1);
              
              assertEquals(p1.degree(),2);
              assertEquals(p1.evaluate(3),34.0,0.001);
             
              Term[] t2 = {
                      new Term(3,2),
                      new Term(2,5),
                      new Term(1,1)
              };
              
              Poly p2 = new Poly(t2);   
              Poly p3 = p2.addPoly(p1, p2);
              assertEquals(p3.print(),"1*x^0+3*x^1+8*x^2+2*x^3");
                 
              Poly p4 = p2.multiplyPoly(p1, p2);
              assertEquals(p4.print(),"1*x^1+7*x^2+15*x^3+19*x^4+6*x^5");
        
    }
    
}
