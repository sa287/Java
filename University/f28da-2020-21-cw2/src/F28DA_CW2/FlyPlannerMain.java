package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FlyPlannerMain {

	public static void main(String[] args) {

		// Your implementation should be in FlyPlannerImpl.java, this class is only to
		// run the user interface of your programme.

		FlyPlannerImpl fi;
		fi = new FlyPlannerImpl();
		int legCount = 0;
		
		try {
			FlightsReader fr = new FlightsReader();
			if(fi.populate(fr)) {
				 Scanner scan = new Scanner(System.in);
			       System.out.println("Please enter the start airport");
			       String start = scan.nextLine();
			       
			       System.out.println("Please enter the end airport");
			       String end = scan.nextLine();
			      
  
			       TripImpl cheapTrip = fi.leastCost(start, end);
			      // Iterator<String> iteratorCheapTrip = cheapTrip.getFlights().iterator();
			       Iterator<FlightImpl> iteratorCheapTrip =  cheapTrip.getFlightsImpl().iterator();
			      
			       System.out.println("Trip for"+start+"to"+end);
			       System.out.println("Leg Leave       At     On   Arrive    At");
			       while(iteratorCheapTrip.hasNext()) {
			    	   
			    	   System.out.println("Leg:" + legCount+ "From" + iteratorCheapTrip.next().getFrom().toString()+"To "+iteratorCheapTrip.next().getTo().toString());
			    	   iteratorCheapTrip.next();
			       }
			       
			       System.out.println("The cost of the trip is:" + cheapTrip.totalCost());
			       System.out.println("The total travel time of the trip is:" + cheapTrip.airTime());
			       
			       
			       
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
			else {
				fi.populate(fr.getAirports(), fr.getFlights());
			}
			

			// Implement here your user interface using the methods of Part A. You could
			// optionally expand it to use the methods of Part B.

		} catch (FileNotFoundException | FlyPlannerException e) {
			e.printStackTrace();
		}

	}

}
