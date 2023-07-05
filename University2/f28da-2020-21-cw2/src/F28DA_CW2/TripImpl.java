package F28DA_CW2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jgrapht.GraphPath;

public class TripImpl implements TripA<AirportImpl, FlightImpl> {
	 GraphPath<AirportImpl, FlightImpl> theTrip;
	 public TripImpl( GraphPath<AirportImpl, FlightImpl> x) {
		 theTrip = x;
	 }
	@Override
	public List<String> getStops() {
		// TODO Auto-generated method stub
		List<String> stops = new ArrayList<String>();
		Iterator<AirportImpl> x = this.theTrip.getVertexList().iterator();
		while(x.hasNext()) {
			stops.add(x.next().toString());
			x.next();
		}
	
		return stops;
	}

	@Override
	public List<String> getFlights() {
		// TODO Auto-generated method stub
		List<String> flightList  = new ArrayList<String>();
		Iterator<FlightImpl> x = this.theTrip.getEdgeList().iterator();
		while(x.hasNext()) {
			flightList.add(x.next().toString());
			x.next();
		}
		return flightList;
	}
	public List<FlightImpl> getFlightsImpl(){
		List<FlightImpl> flightList = this.theTrip.getEdgeList();
		return flightList;
		
		
	}
	public List<AirportImpl> getAirportImpl(){
		List<AirportImpl> airportList = this.theTrip.getVertexList();
		return airportList;
		
		
	}

	@Override
	public int totalHop() {
		// TODO Auto-generated method stub
		return this.theTrip.getLength();
	}

	@Override
	public int totalCost() {
		// TODO Auto-generated method stub
		return (int) this.theTrip.getWeight() ;
	}

	@Override
	public int airTime() {
		// TODO Auto-generated method stub
		List<String> flightList  = getFlights();
		Iterator<FlightImpl> iteratorTime = this.theTrip.getEdgeList().iterator();
		String timeX = iteratorTime.next().getFromGMTime();
		while(iteratorTime.hasNext()!=false) {
			
			iteratorTime.next();
		}
		String timeY = iteratorTime.next().getToGMTime();
		int startTime = Integer.parseInt(timeX);
		int endTime = Integer.parseInt(timeY);
		int total = ((endTime - startTime)*1440);
		return total;
	}

}
