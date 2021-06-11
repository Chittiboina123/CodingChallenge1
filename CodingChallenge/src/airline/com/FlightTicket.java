package airline.com;

import java.util.ArrayList;

public class FlightTicket {
	private String flightName;
	private String source;
	private String destination;
	
	ArrayList<Passenger> passengerObj=new ArrayList<Passenger>();
	public FlightTicket(String flightName,String source,String destination)
	{
		super();
		this.flightName=flightName;
		this.source=source;
		this.destination=destination;
	}
	
	public String getFlightName()
	{
		return flightName;
	}
	public void setFlightName(String flightName)
	{
		this.flightName=flightName;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source=source;
	}
	public String getDestination()
	{
		return destination;
	}
	public void setDestination(String destination)
	{
		this.destination=destination;
	}
	
	public ArrayList<Passenger>getPassengerObj()
	{
		return passengerObj;
	}
	public void setPassengerObj(Passenger passenger)
	{
		this.passengerObj.add(passenger);
	}
	
}
