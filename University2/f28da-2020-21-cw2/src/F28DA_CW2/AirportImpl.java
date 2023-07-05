package F28DA_CW2;

import java.util.Set;

public class AirportImpl implements AirportA {
	String airport;
	String code;
	
	public AirportImpl(String x, String y) {
		airport = y;
		code = x;
	}
	
	@Override
	public String getCode() {
	
		return this.code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.airport;
	}

}
