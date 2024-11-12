package assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Internal wrapper class
 * used to combine info related to a process
 * */
class Process{
	 
	 Integer arrivalTime;
	 Integer burstTime;
     Integer completionTime;
     Integer waitingTime;
     Integer turnAroundTime;
     
     Process(Integer arrivalTime,Integer burstTime){
    	 this.arrivalTime=arrivalTime;
    	 this.burstTime=burstTime;
     }
}

/**
 * a comparator class to sort the arraylist of arraylist of integer based on the arrival time stored at 0th  index
 * and burst time stored at 1st index  
 * sort order  is as follows that increasing arrival time from left to right 
 * used the burst time as tie breaker 
 * */
class process2dComparator implements Comparator<ArrayList<Integer>> { 
	 
	
	/**return's 1 if a1 should come after a2 -1 otherwise*/
	
	public int compare(ArrayList<Integer> a1, ArrayList<Integer> a2) 
    { 
		
        if (a1.get(0) == a2.get(0)){ // equal arrival time
        	
        	if(a1.get(1) > a2.get(1)) // greater burst time of a1 
        		return -1;
        	else 
        		return 1;
        	
        } 
        else if (a1.get(0) < a2.get(0))  // lesser arrival time of a1
            return -1; 
        else
            return 1; // greater arrival time of a1
    } 
} 

/**
 * this class schedules the process and find their parameter such as waiting time  
 * uses a list to store the process 
 * */

public class JobScheduler {

	Integer noOfProcess;
	List<Process> processes;
	
	/**
	 * our constructor method that does the work of calculating the required timing for all the process
	 * given in form of a 2d array
	 * 
	 * arrivalTime burstTime
	 * 0            4
	 * 2            6
	 * 4            5
	 * 4            1
	 * 
	 * */
	JobScheduler(ArrayList<ArrayList<Integer>>processes2d){
		
		this.noOfProcess=processes2d.size();
		if(this.noOfProcess==0)
			return;
		
		Collections.sort(processes2d, new process2dComparator()); // sort based on the arrival time in order to process the first comer first
		
		processes =  new ArrayList<Process>(noOfProcess);
		
		for(int i=0;i<noOfProcess;i++) {
			
			Process p = new Process(processes2d.get(i).get(0),processes2d.get(i).get(1));
			
			if(i==0) { // first job with no one before them
				p.waitingTime=0; // zero waiting for first comer
				p.completionTime=p.burstTime + p.arrivalTime; // takes burst time from the arrival time to complete
				p.turnAroundTime=p.burstTime; // total time spent after the arrival is the same as burst only
			}
			else {
				
				Process lastProcess = processes.get(i-1);
				
				p.waitingTime    = lastProcess.completionTime - p.arrivalTime; // it waits from its arrival till the completion of its predecessor
				if(p.waitingTime<0) //means the process arrived after completion of previous process
					p.waitingTime=0;
				p.turnAroundTime = p.waitingTime + p.burstTime; // total time spent after the arrival
				p.completionTime = p.arrivalTime + p.turnAroundTime;
			}
			
			processes.add(p);
		}
		
	}
	
	public Integer averageWaitingTime() {
        
		Integer totalWaitingTime =0; // store the sum of the waiting time
        
		for(int i=0;i<noOfProcess;i++){
			Process p = processes.get(i);
			totalWaitingTime+=p.waitingTime;
		}
		
		return totalWaitingTime/noOfProcess;  //average of the total waiting time
	}
	
	public Integer maxWaitingTime() {
		
        Integer mxWt = 0;
         
		for(int i=0;i<noOfProcess;i++){      // traverse the whole array of process to the one with max waiting time 
			Process p = processes.get(i); 
			mxWt=Math.max(p.waitingTime,mxWt);
		}
		
		return mxWt;
	}
    
	public void printResult(){
		
		System.out.println("ArTime BrTime CmTime TATime WtTime");
		
		for(int i=0;i<noOfProcess;i++){     // traverse and print the all process and their details 
			Process p = processes.get(i);
			System.out.println(p.arrivalTime + "      " + p.burstTime + "      " + p.completionTime + "      "+ p.turnAroundTime + "      " + p.waitingTime);
		} 
		
	}
	
	/**
	 * test our job scheduler class by giving it a 2d array with details of the process 
	 * arrival time at 0th index and burst time at 2nd time 
	 * */
	public static void main(String[] args) {
		
		 ArrayList<ArrayList<Integer>> processes2d = new ArrayList<ArrayList<Integer>>(); 
		
		 ArrayList<Integer> a = new ArrayList<Integer>();
		 
		 a.add(0); 
	     a.add(10); 
	     processes2d.add(a);
	     
	     a = new ArrayList<Integer>();
	     
	     a.add(6); 
	     a.add(20); 
	     processes2d.add(a);
	     
	     a = new ArrayList<Integer>();
		 a.add(60); 
	     a.add(10); 
	     processes2d.add(a);
	     
	     a = new ArrayList<Integer>();
		 a.add(110); 
	     a.add(5); 
	     processes2d.add(a);
	     
		JobScheduler j =  new JobScheduler(processes2d);
		
		j.printResult();
		System.out.println("Average waiting Time: "+ j.averageWaitingTime());
		System.out.println("Max waiting time: " + j.maxWaitingTime());
	}

}
