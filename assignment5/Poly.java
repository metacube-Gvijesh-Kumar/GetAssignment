package assignment5;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is immutable class named Poly this uses an array to represent single variable polynomial.  
 * assuming that the coefficients are integers
 * this class holds common polynomial related methods
 */
public class Poly {
    
    public final Term [] terms;
    
    /**
     * Initializes the polynomial and also ensures that the duplicates terms don't exist
     * @param terms
     * @throws InstantiationError
     */
    public Poly(Term[] terms) throws InstantiationError {
        
        Map<Integer,Boolean> termMap = new HashMap<Integer,Boolean>();
        
        for(int i=0;i<terms.length;i++) {
            if(termMap.containsKey(terms[i].power))   //power already exist means duplicate terms 
                throw new  InstantiationError("duplicate terms exist");
            else 
                termMap.put(terms[i].power,true);
        }
        
        this.terms=terms;
    }
    
    
   /**
    * return the value of the polynomial for the given value of the variable
    * @param x
    * @return
    */
    public float evaluate(float x){
        
        float ans = (float)0;
        
        for(int i=0;i<terms.length;i++) { //traverse each term and calculate its value and add it to the ans
            Term t = terms[i];
            ans+=(float)t.coefficient*Math.pow(x,t.power); 
        }
        
        return ans;
    }

    /**
     * return the degree of the polynomial
     */
    public int degree(){
        int ans=0;
        
        for(int i=0;i<terms.length;i++) { 
            Term t = terms[i];
            ans=Math.max(ans,t.power);  // degree is equal to maximum power a polynomial has
        }
        
        return ans;
    }
    
    /**
     * adding two polynomial involves simply the coefficient of terms with same power
     * return the sum of the polynomials p1 and p2
     * @param p1
     * @param p2
     */
    public Poly addPoly(Poly p1, Poly p2){
     
     Map<Integer, Integer> powerToCoefficient = new HashMap<Integer, Integer>(); // maps the power to the coefficient  
     
     for(int i=0;i<p1.terms.length;i++) {
         powerToCoefficient.put(p1.terms[i].power,p1.terms[i].coefficient);      // fill the map with the p1 terms 
     }
     
     for(int i=0;i<p2.terms.length;i++) {                                        // now keep adding the coefficient to the previous power terms in map   
         if(powerToCoefficient.containsKey(p2.terms[i].power)) {             
             int coeff = powerToCoefficient.get(p2.terms[i].power) + p2.terms[i].coefficient;
             powerToCoefficient.put(p2.terms[i].power,coeff);    
         }    
         else    
             powerToCoefficient.put(p2.terms[i].power,p2.terms[i].coefficient);  // this power term doesn't exist in p1 but we must consider it in added poly
     }
     
     int totalTerms = powerToCoefficient.size(); 
     Term[] addedTerms = new Term[totalTerms];
     int ind=0;
     
     for (Map.Entry<Integer, Integer> entry :powerToCoefficient.entrySet()){ // retrieve the terms from the map to create a new polynomial
        Term term = new Term(entry.getKey(),entry.getValue());
        addedTerms[ind]=term;
        ind++;
     }
     
     Poly addedPoly = new Poly(addedTerms);
             
     return addedPoly;   
    }
    
    
    /**
     * In polynomial multiplication we simply multiply all the pair of terms with each other
     * return the product of polynomials p1 and p2
     * @param p1
     * @param p2
     */
    public Poly multiplyPoly(Poly p1, Poly p2){
        
        Map<Integer, Integer> powerToCoefficient = new HashMap<Integer, Integer>(); // maps the power to coefficient
        
        Term[] p1Terms = p1.terms;
        Term[] p2Terms = p2.terms;
        
        // pairs up all the terms of p1 with the term of p2
        for(int i=0;i<p1.terms.length;i++) {  
            
            for(int j=0;j<p2.terms.length;j++) {
                
                int power = p1Terms[i].power+p2Terms[j].power;              // in term multiplication power simply add's
                int coeff = p1Terms[i].coefficient*p2Terms[j].coefficient; // and coefficient multiplies
                
                if(powerToCoefficient.containsKey(power)){
                    coeff = powerToCoefficient.get(power) + coeff; // this ensures that all the same power terms are added to each other
                    powerToCoefficient.put(power,coeff);    
                }    
                else    
                    powerToCoefficient.put(power,coeff); 
                
            }
              
        }
        
        
        int totalTerms = powerToCoefficient.size();
        
        Term[] multipliedTerms = new Term[totalTerms];
        int ind=0;
        
        for (Map.Entry<Integer, Integer> entry :powerToCoefficient.entrySet()){ // retrieves all the terms from the maps to store in the array 
           Term term = new Term(entry.getKey(),entry.getValue());
           multipliedTerms[ind]=term;
           ind++;
        }
        
        Poly multipliedPoly = new Poly(multipliedTerms);
                
        return multipliedPoly;
        
    } 
    
    /**
     * @return String representing the polynomial
     */
    public String  print(){
        String exp ="";
        for(int i=0;i<terms.length;i++) {
                
            exp+= terms[i].coefficient+"*x^"+terms[i].power;
            System.out.print(terms[i].coefficient+"*x^"+terms[i].power);
                
            if(i!=terms.length-1) {
                exp+="+";
                System.out.print("+");
            }
        }
        System.out.println();
        return exp;
    }

    public static void main(String[] args) {
        
        Term[] t1 = {
          new Term(0,1),
          new Term(1,2),
          new Term(2,3)
        };
        Poly p1 = new Poly(t1);
        
        System.out.println(p1.degree());
        System.out.println(p1.evaluate(3));
       
        Term[] t2 = {
                new Term(3,2),
                new Term(2,5),
                new Term(1,1)
        };
        
        Poly p2 = new Poly(t2);   
        Poly p3 = p2.addPoly(p1, p2);
        p3.print();
           
        Poly p4 = p2.multiplyPoly(p1, p2);
        p4.print();

    }

}
