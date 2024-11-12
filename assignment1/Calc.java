package assignment1;

/**
* Design a class HexCalc that supports following methods for hexadecimal arithmetic. You can assume that this class will be working with only positive integers.
* Methods to add, subtract, multiply and divide two hexadecimal numbers. Each of the methods will receive hexadecimal numbers as strings, and will return the computed value as hexadecimal string. The strategy that can be followed by each of the methods is to convert the strings into numbers, perform the computation, and reconvert the result back into hexadecimal representation.
* Methods to compare two hexadecimal numbers for ==, > and <. Each of these methods will return a boolean value. Implement these methods without converting the arguments to decimal numbers for comparison.
* Return the decimal representation of hexadecimal number.
* Return the hexadecimal representation of decimal number.
* How would you generalize this to support arithmetic in any base?
*/

/* generic class Calc that holds the method for performing calculation on varying base and 
accepts parameter in form of string */ 

public class Calc {
	
	/**utility method used by others for reversing the string*/
	public static String reverse(String a) {
		
		String reversed ="";
	    //parse string from last index to first index and keep storing the string in the reversed variable 
		for(Integer i=a.length()-1;i>=0;i--){
			reversed+=a.charAt(i);
		}
		
		return reversed;
		
	}
	
	/**given the string 'a' that has a given 'base' this method can convert the any base string to decimal integer*/
 	
	public static Integer toDecimal(String a,Integer base) {
	    
		if(a=="" || base<=0)
		 return 0;	
		
		Integer dec = 0;
		Integer power = 0;
		Double ibase = (double)base; // this variable is created as the Math.pow require a double type input
		
		/**
		 * example hex to decimal 
		 * suppose a is 21B then 
		 * decimal = 16^1*1 + 16^0*11
		 * this gives us a idea that the if we traverse from last index to first then power increases from 0 to length-1
		 * the variable dec stores the answer
		 * for character>=0 and <=9 it is just the same number but for anything greater than that then we are calculating 
		 * corresponding number suppose for a and b it is 10 and 11
		 * the formula char - 'a' + 10 utilizes the ASCII code and find the corresponding number
		 * */
		for(Integer ind=a.length()-1;ind>=0;ind--){
			
			if(a.charAt(ind) >= '0' && a.charAt(ind) <= '9') 
			  dec+=(int)Math.pow(ibase,power)*(a.charAt(ind)-'0');
			else
			  dec+=(int)Math.pow(ibase,power)*(a.charAt(ind)-'A'+10);
			
			power++;// power increases from right to left
		}
		
 		return dec;
	}
	
	
	/**
	 * Decimal to given base calculation by repeated division of the decimal number by required base.
	 * */
	public static String decimalToGivenBase(Integer a,Integer base) {
		
		if(base==10 || a<=0)
		  return Integer.toString(a);
		
		String answer = "";
	    
		//repeatedly divide the number by the base and store the reminder in 'answer' string
		while(a>0){
			Integer rem =  a%base;
			a=a/base;
			if(rem<=9)
				answer+=((char)('0'+rem));
			else
				answer+=((char)('A'+ rem-10)); //this expression gives us the corresponding number for a given Hex letter  	
		}
		
		// the answer would be reverse of the string we got from start to end
		return reverse(answer);
	}
	
	
	/**
	 * The methods add,multiply,subtract,divide 
	 *  simply convert the given base string into decimal integer then perform the operation
	 *  as per their name at the end convert the result back into the original base string format
	 * */
	public static String add(String a,String b,Integer base){
		String answer="";
		Integer ia = toDecimal(a,base);
		Integer ib = toDecimal(b,base);
		answer = decimalToGivenBase(ia+ib,base);
		return answer;
	}
	
	public static String subtract(String a,String b,Integer base) {
		String answer="";
		Integer ia = toDecimal(a,base);
		Integer ib = toDecimal(b,base);
		answer = decimalToGivenBase(ia-ib,base);
		return answer;
	}
	
	public static String multiply(String a,String b,Integer base) {
		String answer="";
		Integer ia = toDecimal(a,base);
		Integer ib = toDecimal(b,base);
		answer = decimalToGivenBase(ia*ib,base);
		return answer;
	}
	
	public static String divide(String a,String b,Integer base) {
		String answer="";
		Integer ia = toDecimal(a,base);
		Integer ib = toDecimal(b,base);
		
		if(ib==0)
		return "Infinite";
		
		answer = decimalToGivenBase(ia/ib,base);
		return answer;
	}
	
	/**
	 * does a equals b
	 * */
	public static Boolean equals(String a,String b) {
		return a==b;
	}
	
	/**
	 * is a less than b
	 * this works using the fact that higher the number of digit in the string higher the value of the number
	 * and if the length is same then we compare the value of the char if it is higher then other the later is smaller
	 * */
	public static Boolean lessThan(String a,String b) {
		
		if(a.length()>b.length())
			return false;
		else if(a.length()<b.length())
			return true;
		
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)>b.charAt(i))
				return false;
			else if(a.charAt(i)<b.charAt(i))
				return true;		
		}
		
		return false;
		
	}
	
	/**
	 * is a greater than the b 
	 * only if a is not equals to b and also not less
	 */
	
	public static Boolean greaterThan(String a,String b) {
		return !equals(a,b) && !lessThan(a,b);
	}
	
	
	/**
	 * Removing trailing zeros so that it becomes easy to perform the operation in less than ,greater than, equals
	 * we traverse the string a and copy the string to answer only the necessary part 
	 * we use boolean flag to check if we have faced only faced zeros till now
	 * */
	
	public static String removeTrailingZeros(String a){
		String answer = "";
		Boolean flag  = true;
		
		for(Integer ind=0;ind<a.length();ind++) {
			if(a.charAt(ind)=='0' && flag)
				continue;
			else
				flag=false;
			
			answer+=a.charAt(ind);
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
			
		String a = "00053"; //83 in decimal
		String b = "001B";  //27 in decimal
		Integer base = 16;  //hexadecimal base
		
		a=Calc.removeTrailingZeros(a);
		b=Calc.removeTrailingZeros(b);
		
		System.out.println("string a after trailing zero removal :" + a);
		System.out.println("string b after trailing zero removal :" + b);
		
		System.out.println("our result of addition :" + Calc.add(a,b,base));
		System.out.println("our result of subtraction :" + Calc.subtract(a,b,base));
		System.out.println("our result of multiplication :" + Calc.multiply(a,b,base));
		System.out.println("our result of division :" + Calc.divide(a,b,base));
		
		System.out.println("to decimal :" + Calc.toDecimal(b,base));
		System.out.println("decimal to given base :" + Calc.decimalToGivenBase(555,base));
		
		System.out.println("a equals b :" + Calc.equals(a,b));
		System.out.println("a less than b :" + Calc.lessThan(a,b));
		System.out.println("a greater than b :" + Calc.greaterThan(a,b));
		
	}

}
