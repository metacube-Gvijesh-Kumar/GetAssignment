package test.java;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import assignment3.ArrOperation;

/**
 * holds test methods for the assignment 3 
 */
public class Test3 {
   
	public static Stream<Arguments> mirrorArguments() { 
		// source of test case for the 
        Stream<Arguments> args = Stream.of(	
            Arguments.of((Object)new int [] {1, 2, 3, 8, 9, 3, 2, 1},3),
            Arguments.of((Object)new int [] {7, 1, 4, 9, 7, 4, 1},2),
            Arguments.of((Object)new int [] {1, 2, 1, 4},3),
            Arguments.of((Object)new int [] {1, 4, 5, 3, 5, 4, 1},7)
        );
        return args;
    }
    
    @ParameterizedTest
    @MethodSource(value = "mirrorArguments")
    
	public void testMirror(int [] x,int ans) {
    	ArrOperation a =  new ArrOperation();
    	int result = a.largestMirrorSection(x); 
    	assertEquals(ans,result);
	}
	
    
    public static Stream<Arguments> clumpArguments() {
        Stream<Arguments> args = Stream.of(	
            Arguments.of((Object)new int [] {1,2,1,1,2,3,4,4,1},2),
            Arguments.of((Object)new int [] {7,1,1,4,9,17,17,4,4,1},3),
            Arguments.of((Object)new int [] {1,2},0),
            Arguments.of((Object)new int [] {1},0)
        );
        return args;
    }
    
    @ParameterizedTest
    @MethodSource(value = "clumpArguments")
    
	public void testClump(int [] x,int ans) {
    	ArrOperation a =  new ArrOperation();
    	int result = a.noOFClumps(x); 
    	assertEquals(ans,result);
	}
    
    public static Stream<Arguments> fixXYArguments() {
        Stream<Arguments> args = Stream.of(	
            Arguments.of((Object)new int [] {1,2,1,4,2,3,2,4,1},2,1),
            Arguments.of((Object)new int [] {7,1,1,4,9,17,17,4,4,1},4,1),
            Arguments.of((Object)new int [] {1,2},1,2),
            Arguments.of((Object)new int [] {1},0,1),
            Arguments.of((Object)new int [] {1},1,0)
        );
        return args;
    }
    
    @ParameterizedTest
    @MethodSource(value = "fixXYArguments")
    
	public void testfixXY(int [] arr,int x,int y) {
    	ArrOperation a =  new ArrOperation();
    	int[] result = a.fixXY(arr,x,y); 
    	
    	for(int i=0;i<result.length;i++){
    		if(result[i]==x) {
    			if(i==result.length || result[i+1]!=y)
    				assertEquals(1,2);
    		}
    	}
    	
	}
	
    public static Stream<Arguments> splitArrayArguments() {
        Stream<Arguments> args = Stream.of(	
            Arguments.of((Object)new int [] {1,2,1,4,2,3,2,4,1}),
            Arguments.of((Object)new int [] {7,1,1,4,9,17,17,4,4,1}),
            Arguments.of((Object)new int [] {1,2}),
            Arguments.of((Object)new int [] {1}),
            Arguments.of((Object)new int [] {1})
        );
        return args;
    }
    
    @ParameterizedTest
    @MethodSource(value = "splitArrayArguments")
    
	public void testSplitArray(int [] arr) {
    	
    	ArrOperation a =  new ArrOperation();
    	int result = a.splitArray(arr); 
    	
    	
    	if(result==-1) {
    		System.out.println(result);
    		return;
    	}
    		
    	
    	int lsum =0;
    	int rsum =0;
    	
    	for(int i=0;i<arr.length;i++){    	
    		if(i<result)
    			lsum+=arr[i];
    		else
    			rsum+=arr[i];
    	}
    	
    	assertEquals(lsum,rsum);
    	
	}
    
}
