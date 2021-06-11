/*display the menu
 * the user input is number of flights to enter
 * create the flight
 * create a passenger
 * book a ticket for that passenger to the particular flight
 * input source to destination
 * to fetch the flight details
 * corresponding passenger details by the source and destination*/

package airline.com;

import java.util.ArrayList;
import java.util.Scanner;

public class TicketReservation {
	
	static Scanner scn=new Scanner(System.in);
	static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Passenger [] passengerObj=null;
		FlightTicket [] flightObj=null;
		
		do {
			displayMenu();
			System.out.println("Enter Choice");
			byte choice=scn.nextByte();
			switch(choice)
			{
			case 1:
				flightObj=createFlight();
				break;
			case 2:
				passengerObj=createPassenger(flightObj);
				break;
			case 3:
				displayFlightDetails(flightObj);
				break;
			case 4:
				System.exit(0);
			}
		}while(true);
	}
	private static void displayFlightDetails(FlightTicket[] flightObj) {
		// TODO Auto-generated method stub
		String [] arr=new String [2];
		arr=inputSourceToDestination(flightObj);
		String sr=arr[0];
		String dest=arr[1];
		for(int i=0;i<flightObj.length;i++)
		{
			if(flightObj[i].getSource().equals(sr)&&flightObj[i].getDestination().equals(dest))
			{
				String flightName=flightObj[i].getFlightName();
				System.out.println("*****************************************");
				System.out.println("flight name"+flightName);
				
				System.out.println("source"+flightObj[i].getSource());
				System.out.println("Destination"+flightObj[i].getDestination());
				System.out.println("passengers are :");
				
				ArrayList<Passenger> p1=flightObj[i].getPassengerObj();
				
				for(int j=0;j<p1.size();j++)
				{
					System.out.println("*******************************************");
					System.out.println("details of passenger "+(j+1));
					char ch=p1.get(j).getLastName().charAt(0);
					if(ch>='a'&&ch<='z')
					{
						ch=(char)(p1.get(j).getLastName().charAt(0)-32);
					}
					System.out.println("name"+ch+"."+p1.get(j).getAge());
					System.out.println("age"+p1.get(j).getAge());
					System.out.println("gender"+p1.get(j).getGender());
					System.out.println("**********************************************************");
				}
				
				
			}
		}
	}
	private static Passenger [] createPassenger(FlightTicket [] flightObj) {
		// TODO Auto-generated method stub
		System.out.println("Enter the no of passengers");
		int ticketNo=scn.nextInt();
		Passenger [] passenger =new Passenger[ticketNo];
		
		
		for(int i=0;i<passenger.length;i++)
		{
			System.out.println("Enter the details of passenger"+(i+1));
			System.out.println("Enter the passenger firstname");
			String s1=scn.next();
			
			System.out.println("Enter the passenger lastname");
			String s2=scn.next();
			
			System.out.println("enter the age");
			byte a=scn.nextByte();
			
			System.out.println("Enter the gener");
			String g=validateGender();
			
			
			String array[]=new String[2];
			array=inputSourceToDestination(flightObj);
			String source=array[0];
			String destination=array[1];
			
			int count=countNoOfFlightAvailable(flightObj,source,destination);
			
			FlightTicket []passFlight=new FlightTicket[count];
			int b=0;
			
			for(int j=0;j<flightObj.length;j++)
			{
				if(flightObj[j].getSource().equals(source)&&flightObj[j].getDestination().equals(destination))
				{
					passFlight[b]=flightObj[j];
					b++;
				}
			}
			
			System.out.println("flights for source and destination");
			
			for(int j=0;j<passFlight.length;j++)
			{
				System.out.println((j+1)+". "+passFlight[j].getFlightName());
			}
			System.out.println("choice your flight index number");
			int flightno=scn.nextInt();
			passenger[i]=new Passenger(s1,s2,a,g);
			for(int j=0;j<flightObj.length;j++)
			{
				if(flightObj[j].getFlightName().equals(passFlight[flightno-1].getFlightName()))
				{
					flightObj[j].setPassengerObj(passenger[i]);
					
					ArrayList<Passenger>p=flightObj[j].getPassengerObj();
							
				}
			}
			
			System.out.println("ticket is booked succesfully");	
		}
		return passenger;
	}

	private static String[] inputSourceToDestination(FlightTicket[] flightObj) {
		// TODO Auto-generated method stub
		String [] arr=new String[2];
		System.out.println("Enter the source");
		arr[0]=scn.next();
		System.out.println("Enter the destination");
		arr[1]=scn.next();
		int count=countNoOfFlightAvailable(flightObj,arr[0],arr[1]);
		
		try
		{
			if(count==0)
			{
				throw new NoSuchFlightException();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.getMessage();
			
			arr=inputSourceToDestination(flightObj);
		}
		return arr;
	}

	private static int countNoOfFlightAvailable(FlightTicket[] flightObj, String Source, String destination) 
	{
		// TODO Auto-generated method stub
		count=0;
		for(int j=0;j<flightObj.length;j++)
		{
			if(flightObj[j].getSource().equals(Source)&&flightObj[j].getDestination().equals(destination))
			{
				count++;
			}
		}
		return count;
	}

	private static String validateGender() {
		// TODO Auto-generated method stub
		String s=scn.next();
		
		String g="";
		String male="male";
		String female="female";
		if(s.equals(male)||s.equals(female))
		{
			g=s;
		}
		else
		{
			System.out.println("please enter either male or female");
			g=validateGender();
		}
		return g;
		
	}

	private static FlightTicket[] createFlight() {
		// TODO Auto-generated method stub
		System.out.println("create no of flights how many you need");
		int n=scn.nextInt();
		FlightTicket [] flight=new FlightTicket[n];
		for(int i=0;i<flight.length;i++)
		{
			System.out.println("Enter the details of flight"+(i+1));
			String flightName=uniqueFlightName();
			System.out.println("Enter the source");
			String source=scn.next();
			System.out.println("Enter the destination");
			String destination=scn.next();
			flight[i]=new FlightTicket(flightName,source,destination);
			
		}
		return flight;
	}

	private static String uniqueFlightName() {
		// TODO Auto-generated method stub
		System.out.println("Enter the flight name");
		String str=scn.next();
		
		return str;
	}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("***************************************************");
		System.out.println("1.create a flight");
		System.out.println("2.book ticket");
		System.out.println("3.fetch the flight and corresponding passenger details");
		System.out.println("4.exit");
		System.out.println("***************************************************************");
		
	}

}
