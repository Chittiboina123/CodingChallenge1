package airline.com;

public class NoSuchFlightException extends Exception {
	public String getMessage()
	{
		return "no such source or destination exists";
		
	}

}
