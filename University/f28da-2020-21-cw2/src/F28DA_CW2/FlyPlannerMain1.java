package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FlyPlannerMain {

	public static void main(String[] args) {

		// Your implementation should be in FlyPlannerImpl.java, this class is only to
		// run the user interface of your programme.
		try {
		FlyPlannerImpl fi = new FlyPlannerImpl();
		FlightsReader fr = new FlightsReader();
		Scanner scanner = new Scanner(System.in);
		boolean success = false;
		success = fi.populate(fr);
		
		
		if(success) {
				  
			       System.out.println("start");
			       String from = scanner.nextLine();
			       
			       System.out.println("end");
			       String to = scanner.nextLine();
			      
  
			       TripImpl voyage = fi.leastCost(from, to);
			      
			       System.out.println("The trip is from" + from + "to the destination of" + to);
			       
			       
			  
			       Iterator<FlightImpl> tripIter =  voyage.voyage.getEdgeList().listIterator();
			      
			       while(tripIter.hasNext() == true ) {
			    	   FlightImpl temp = tripIter.next();
			    	   System.out.println(temp.toString());
			    	   
			       }
			       
			 
			      
			
			
			
			}
			else {
				success = fi.populate(fr.getAirports(), fr.getFlights());
				if(success) {
					System.out.println("start");
				       String from = scanner.nextLine();
				       
				       System.out.println("end");
				       String to = scanner.nextLine();
				      
	  
				       TripImpl voyage = fi.leastCost(from, to);
				      
				       System.out.println("The trip is from" + from + "to the destination of" + to);
				       
				       
				       
				  
				       Iterator<FlightImpl> tripIter =  voyage.voyage.getEdgeList().listIterator();
				      
				       while(tripIter.hasNext() == true ) {
				    	   FlightImpl temp = tripIter.next();
				    	   System.out.println(temp.toString());
				    	   
				       }
				}
			
}
			
		}
		catch(Exception e) {
			
		}
		} 

	}


