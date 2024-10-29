package assignment2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  class Marksheet reads in grades of n no of students.
 *  Assume n should be a positive integer and grades will be between 0 and 100 both inclusive. 
 *  It supports common methods applicable to grades such as max,min,average,pass percentage
 *  This class assumes that for each student a single grade is associated 
 */




public class Marksheet {
	
	Integer noOfStudents;      // Total no of students
	ArrayList<Double> grades; // grades of each student
	final static double thresholdMarks = 40; // minimum marks to be considered pass
	
	/**
	 * constructor initializes the members
	 */
	Marksheet()
	{	
		noOfStudents=0;
	    grades=new ArrayList<Double>();
	}
	
	
	/**
	 * we are expecting a integer n at first and later n number of double
	 * if something else is input we prompt a msg to the console and make them re enter a new input
	 * it updates the grades member variable holding n grades of the student
	 */
	public void takeGradeInput() {
		
		Scanner scanner = new Scanner(System.in); // scanner class instance to take input from the console
		
		System.out.println("Please enter the number of students: "); 
		
		this.noOfStudents=0; // Initially set it 0 indicating that it needs to be inputed
		
		while (scanner.hasNext()) {
        	
			if(this.noOfStudents==0){
				if(scanner.hasNextInt() && scanner.nextInt()>0){
					this.noOfStudents = scanner.nextInt();
					System.out.println("Now please enter the grades for each of them  seprerated by space not greater than 100.00 upto 2 decimal digits:");
				}
				else
					System.out.println("please enter valid integer greater than 0 : this is invalid :"+ scanner.next());
				
        	}
			else if (scanner.hasNextDouble()) { 
				
                Double grade = scanner.nextDouble();
                
                if(grade>=0.0 && grade<=100.0) // if entered grade is in correct range 
                    grades.add(grade);         // store it in the list
                else 
                	System.out.println("the grade must be in valid range btn 0 to 100 :"+ scanner.next());	
            } 
  
            else { 
                System.out.println("pls enter valid input we found this :"+ scanner.next()); // invalid character given in place of a grade
            }
			
			if(this.noOfStudents!=0 && grades.size()>=this.noOfStudents) // this means required no of grades for each student is collected
				break;                                                   // can stop taking input   
            
        }
        
        scanner.close();                       
        System.out.println("grades");          // print the grades collected from input
        for(Double i : grades) {
        	System.out.println(i);
        }
	}
    
	/**
	 * @return average of all the grades scored by students
	 */
	public Double average() {
		
		Double gradesSum = 0.00;
		
		for(Integer i=0;i<noOfStudents;i++) { //traverse grades  
			gradesSum+=grades.get(i);        //keep adding to aggregate all the grades
		}
		
		Double avg= Math.round( gradesSum/noOfStudents * 100.0 ) / 100.0 ; // rounds off the answer to upto 2 digit precision
		
		return avg;
	}
	
    /**
     * @return double maximum grade scored by any student
     */
	public Double maximum() {
		
		Double max=0.00;
		
		for(Integer i=0;i<noOfStudents;i++) { //traverse
			max=Math.max(max,grades.get(i)); //compare 
		}
		
		return max;
	}
	
	/**
	 * @return double minimum grade scored by any student
	 */
	public Double minimum() {
		
		Double min=100.00;
		
		for(Integer i=0;i<noOfStudents;i++) { //traverse
			min=Math.min(min,grades.get(i)); //compare
		}
		
		return min;
	}
	
	/**
	 * @return pass percentage  who must have scored above threshold marks 
	 * @throws ArithmeticException in case the noOfStudents is zero then percentage calculation result in divsion by zero
	 */
	
	public Double passPercentage() throws ArithmeticException {
		
		Integer noOfPassed = 0;
		
		for(Integer i=0;i<noOfStudents;i++) {   //traverse
			if(grades.get(i)>=thresholdMarks)  //check if passed
			noOfPassed++;                     //count of total passed
		}
		
		Double result = (double) (noOfPassed*100/noOfStudents); // percentage of total passed from noOfStudents
		
		result = Math.round( result * 100.0 ) / 100.0 ;         // round off upto the 2 digit precision 
		
		return result;
	}
	
	
	/**
	 * this main method helps us to use and test the MarkSheet class
	 * it creates a instance of the class and call all the methods
	 */
	public static void main(String[] args) {
		
		Marksheet sg =  new Marksheet();
		sg.takeGradeInput();
		
		System.out.println("Average : " + sg.average()); 
		System.out.println("Maximum : " + sg.maximum());
		System.out.println("Minimum : " + sg.minimum());
		
		try { // percentage can throws exceptions use try catch
		System.out.println("Pass Percentage : " + sg.passPercentage());  
		}
		catch(ArithmeticException a) {
			System.out.println("the number of students were zero pls give valid input"); // our error occurs due to division by zero
		}
		
	}

}
