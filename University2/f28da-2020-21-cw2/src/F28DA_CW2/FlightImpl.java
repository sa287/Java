package F28DA_CW2;

import org.jgrapht.graph.DefaultWeightedEdge;

public class FlightImpl extends DefaultWeightedEdge implements Flight {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int cost;
	public String flightcode;
	public String fromTime;
	public String toTime;
	
	public FlightImpl() {
		cost = 0;
		flightcode = null;
		fromTime = "";
		toTime = "";
	}
	public String getFlightCode() {
		// TODO Auto-generated method stub
		return this.flightcode;
	}

	@Override
	public AirportImpl getTo() {
		// TODO Auto-generated method stub
		return (AirportImpl) this.getSource();
	}

	@Override
	public AirportImpl getFrom() {
		// TODO Auto-generated method stub
		return (AirportImpl) this.getTarget();
	}

	@Override
	public String getFromGMTime() {
		// TODO Auto-generated method stub
		return this.fromTime;
	}

	@Override
	public String getToGMTime() {
		// TODO Auto-generated method stub
		return this.toTime;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return this.cost;
	}

}
