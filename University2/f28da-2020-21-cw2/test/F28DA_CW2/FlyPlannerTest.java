package F28DA_CW2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class FlyPlannerTest {

	FlyPlannerImpl fi;

	@Before
	public void initialize() {
		fi = new FlyPlannerImpl();
		try {
			fi.populate(new FlightsReader());
		} catch (FileNotFoundException | FlyPlannerException e) {
			e.printStackTrace();
		}
	}

	// Add your own tests here!
	//
	// You can get inspiration from the tests in FlyPlannerProvidedTest
	// that uses the provided data set but also from the
	// leastCostCustomTest test that uses a custom-made graph

	@Test
	public void anotherTest() {
		// Here we take inspiration from the leastCostCustom test, we add in records with varying airports and flights information. You can see that we are trying to trick the system
		// into perhpas making an error due to the formatting and validation occurring.
			FlyPlannerImpl fp = new FlyPlannerImpl();
			HashSet<String[]> airports = new HashSet<String[]>();
			String[] a1= {"A1","City1","A1"}; airports.add(a1);
			String[] a2= {"A2","City2","1232194291"}; airports.add(a2);
			
			HashSet<String[]>  flights = new HashSet<String[]>();
			String[] f1= {"F1","A1","1000","A2","1100","500"}; flights.add(f1);
			String[] f2= {"F2","A1","1000","A3","1100","50"}; flights.add(f2);
		
			fp.populate(airports, flights);
			try {
				System.out.println(fp);
				TripImpl lc = fp.leastCost("A2", "A3");
				assertEquals(100,lc.totalCost());
			} catch (FlyPlannerException e) {
				e.printStackTrace();
				fail();
			}
		}
	@Test
	public void anotherTest() {
		// Here we take inspiration from the leastCostCustom test, we add in records with varying airports and flights information. You can see that we are trying to trick the system
		// into perhpas making an error due to the formatting and validation occurring.
			FlyPlannerImpl fp = new FlyPlannerImpl();
			HashSet<String[]> airports = new HashSet<String[]>();
			String[] a1= {"A1","City1","A1"}; airports.add(a1);
			String[] a2= {"A2","City2","1232194291"}; airports.add(a2);
			
			HashSet<String[]>  flights = new HashSet<String[]>();
			String[] f1= {"F1","A1","1000","A2","1100","500"}; flights.add(f1);
			String[] f2= {"F2","A1","1000","A3","1100","50"}; flights.add(f2);
		
			fp.populate(airports, flights);
			try {
				System.out.println(fp);
				TripImpl lc = fp.leastCost("A2", "A3");
				assertEquals(100,lc.totalCost());
			} catch (FlyPlannerException e) {
				e.printStackTrace();
				fail();
			}
		}
	@Test
	public void anotherTest2() {
		// Like the previous one, we are trying to trick the system. This time changing flight codes to match airplanes codes, again testing validation.
		
			FlyPlannerImpl fp = new FlyPlannerImpl();
			HashSet<String[]> airports = new HashSet<String[]>();
			String[] a1= {"A12222","City1","A1"}; airports.add(a1);
			String[] a2= {"A2","City2","1232194291"}; airports.add(a2);
			
			HashSet<String[]>  flights = new HashSet<String[]>();
			String[] f1= {"A12222","A1","1000","A2","2000","50"}; flights.add(f1);
			String[] f2= {"A2","A1","1000","A3","1100","46"}; flights.add(f2);
		
			fp.populate(airports, flights);
			try {
				System.out.println(fp);
				TripImpl lc = fp.leastCost("A2", "A3");
				assertEquals(100,lc.totalCost());
			} catch (FlyPlannerException e) {
				e.printStackTrace();
				fail();
			}
		}
	
	
	

}
