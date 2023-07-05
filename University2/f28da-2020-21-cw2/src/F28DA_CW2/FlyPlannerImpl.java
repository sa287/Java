package F28DA_CW2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class FlyPlannerImpl implements FlyPlannerA<AirportImpl,FlightImpl> {
	Graph<AirportImpl, FlightImpl> graph1 = new SimpleDirectedWeightedGraph<AirportImpl,FlightImpl>(FlightImpl.class);
	HashSet<AirportImpl> airportSet = new HashSet<AirportImpl>();
	HashSet<FlightImpl> flightSet = new HashSet<FlightImpl>();
	@Override
	public boolean populate(FlightsReader fr) {
		// TODO Auto-generated method stub
		
		Iterator<String[]> iterator1 = fr.getAirports().iterator();
		while(iterator1.hasNext()) {
			AirportImpl temp = new AirportImpl(iterator1.next()[0].toString(),iterator1.next()[2].toString());
			graph1.addVertex(temp);
			
			airportSet.add(temp);
			
		}
		
		Iterator<String[]> iterator2 = fr.getFlights().iterator();
		
		
		while(iterator2.hasNext()) {
			
			//System.out.println("hello");

			//System.out.println(iterator2.next()[3].toString());
			try {
			
			AirportImpl start = airport(iterator2.next()[1].toString());
			AirportImpl end = airport(iterator2.next()[3].toString());
			
			String cost = iterator2.next()[5];
			int costFinished = Integer.parseInt(cost);
			String code = iterator2.next()[0].toString();
			String fromTime = iterator2.next()[2].toString();
			String toTime = iterator2.next()[3].toString();		
		//	try {
			if(start!=null && end!=null) {
			//	System.out.println(start.getCode());
			//	System.out.println(end.getCode());
			if(start!=end) {
				FlightImpl x = graph1.addEdge(start, end);
			
			graph1.setEdgeWeight(x, costFinished);
			x.cost = costFinished;
			x.flightcode = code;
		
			x.fromTime = fromTime;
			x.toTime = toTime;
			flightSet.add(x);
			
			}
			}
			}
			
		//	}
			catch(Exception e) {
				//System.out.println("Trip doesn't exist");
				
			}
			
			
			
		
		}
		return true;
	
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) {

		
		Iterator<String[]> iterator1 = airports.iterator();
		while(iterator1.hasNext()) {
			AirportImpl temp = new AirportImpl(iterator1.next()[0].toString(),iterator1.next()[2].toString());
			graph1.addVertex(temp);
			airportSet.add(temp);
			
		}
		
		Iterator<String[]> iterator2 = flights.iterator();
		
		while(iterator2.hasNext()) {
			try {
			AirportImpl start = airport(iterator2.next()[1].toString());
			AirportImpl end = airport(iterator2.next()[3].toString());
			
			String cost = iterator2.next()[5];
			int costFinished = Integer.parseInt(cost);
			String code = iterator2.next()[0].toString();
			String fromTime = iterator2.next()[2].toString();
			String toTime = iterator2.next()[4].toString();	
			if(start!=null && end!=null) {
				if(start!=end) {	
			FlightImpl x = graph1.addEdge(start, end);
			
			graph1.setEdgeWeight(x, costFinished);
			x.cost = costFinished;
			x.flightcode = code;
		
			x.fromTime = fromTime;
			x.toTime = toTime;
			flightSet.add(x);
			
				}
			}}
		catch(Exception e) {
				//System.out.println("Trip doesn't exist");
				
			}
	
		}
		return true;
	
	}

	@Override
	public AirportImpl airport(String code) {
		Iterator<AirportImpl> x = airportSet.iterator();
		List<AirportImpl> y = new ArrayList<AirportImpl>();
		while(x.hasNext()) {
			y.add(x.next());
		}
		for(int i = 0; i< y.size(); i++) {
			if(y.get(i).getCode().equals(code)) {
				return y.get(i);
			}
		}
		//while(!x.next().getCode().equals(code)) {
	//		//System.out.println(x.next().getCode());
		//	if(x.hasNext()==false) {
		//		return null;
		//	}
		//	x.next();
		//	if(x.next().getCode().equals(code)) {
		//		
		//		return x.next();
		//	}
	//	}
		return null;
	}

	@Override
	public FlightImpl flight(String code) {
		// TODO Auto-generated method stub
		Iterator<FlightImpl> x = flightSet.iterator();
		while(!x.next().getFlightCode().equals(code)) {
			x.next();
			if(x.next().getFlightCode().equals(code)) {
				return x.next();
			}
		}
		return null;
	}

	@Override
	public TripImpl leastCost(String from, String to) throws FlyPlannerException {
		// TODO Auto-generated method stub
		DijkstraShortestPath<AirportImpl, FlightImpl> dijk = new DijkstraShortestPath<>(graph1);
		AirportImpl x = airport(from);
		AirportImpl y = airport(to);
		//System.out.println(airport("SBN"));
		
		try {
		TripImpl cheapest = new TripImpl(dijk.findPathBetween(graph1,x,y));
		return cheapest;
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public TripImpl leastHop(String from, String to) throws FlyPlannerException {
		// TODO Auto-generated method stub
		BFSShortestPath<AirportImpl, FlightImpl> bfs = new BFSShortestPath<>(graph1);
		AirportImpl x = airport(from);
		AirportImpl y = airport(to);
		
		TripImpl shortest = new TripImpl(bfs.findPathBetween(graph1,x,y));
		return shortest;
	}

	@Override
	public TripImpl leastCost(String from, String to, List<String> excluding) throws FlyPlannerException {
		// TODO Auto-generated method stub
		airportSet.removeAll(excluding);
		DijkstraShortestPath<AirportImpl, FlightImpl> dijk = new DijkstraShortestPath<>(graph1);
		AirportImpl x = airport(from);
		AirportImpl y = airport(to);
		
		TripImpl cheapest = new TripImpl(dijk.findPathBetween(graph1, x, y));
		
		return cheapest;
	}

	@Override
	public TripImpl leastHop(String from, String to, List<String> excluding) throws FlyPlannerException {
		
		// TODO Auto-generated method stub
		airportSet.removeAll(excluding);
		BFSShortestPath<AirportImpl, FlightImpl> bfs = new BFSShortestPath<>(graph1);
		AirportImpl x = airport(from);
		AirportImpl y = airport(to);
		
		TripImpl shortest = new TripImpl(bfs.findPathBetween(graph1,x,y));
		return shortest;
		
	}

	

}
