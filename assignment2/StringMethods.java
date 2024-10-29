package assignment2;

/**
 * StringMethods class hold common string manipulation methods
 * it allows us to pass strings through the parameters but not using any member variable 
 */
public class StringMethods {
     
	public Boolean equals(String a,String b) {
		return a==b;
	}
	 
	/**
	 * this methods changes the case of the alphabets 
	 * ex small to capital , capital to small
	 * @param a string for which cases should be reverse
	 * @return reversed case of input string a ex "AbcD" then returns "aBCd"
	 */
	public String reverseCase(String a) {
		
		String reverseCased ="";
		
		for(Integer i=0;i<a.length();i++){
			
			char reverseChar=a.charAt(i);                     // will store the reverse case of the character
			 
		    if(a.charAt(i)>='a' && a.charAt(i)<='z')          // if small caps
		    	reverseChar  = (char)('A'+  a.charAt(i) -'a');// expression utilizes the ASCII code values to find corresponding capital alphabet 
		    else if(a.charAt(i)>='A' && a.charAt(i)<='Z')     // if capital caps
		    	reverseChar  = (char)('a'+  a.charAt(i) -'A');// expression utilizes the ASCII code values to find corresponding small alphabet
		    	    
		    reverseCased+=reverseChar;                        // keep storing the result in the buffer string
		    	
		}
		
		return reverseCased;
		
	}
	
	/**
	 * @param a string that has be reversed
	 * @return a reverse string of a ex "abcd" input then "dcba" output
	 */
	public String reverse(String a) {
		
		String reversed ="";
	    //parse string from last index to first index and keep storing the string in the reversed variable 
		for(Integer i=a.length()-1;i>=0;i--){
			reversed+=a.charAt(i);
		}
		
		return reversed;
		
	}
	
	
	/**
	 * @param a hold the string for which we are finding the longest word
	 * @return string containing the longest word
	 */
	public String longestWord(String a) {
		
		String result="";   //final result string
		String current =""; //holds sub string as we traverse through string a
		
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)!=' ')       // character must not be space
				current+=a.charAt(i);  // character is part of current word 
			else {                     // it is space a breaking character between the words 
				if(current.length()>=result.length()) 
					result=current;    // if length of current is greater than the previous words change result
				current="";            // empty the current word string so it can store next word
			}
		}
		
		if(current.length()>=result.length())  // there can be words at the end of string that won't compared in above for loop
			result=current;                    
		
		return result;
		
	}
	
	/**
	 * tests the class by calling all of its methods
	 */
	public static void main(String[] args) {
		
		StringMethods s = new StringMethods();
		String a = "RevErSE ThiS";
		String b = "finding the longest word in this string that occurs at last";
		
		System.out.println(s.equals(a,b));     
		System.out.println(s.reverseCase(a));
		System.out.println(s.reverse(a));
		System.out.println(s.longestWord(b));
		
	}
	
}
