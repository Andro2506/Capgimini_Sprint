package entities;

public class Ticket {
	private Attendee attendee;
	private Event event;
	private String status;
	
	public Ticket(Attendee attendee, Event event)
	{
		this.status = "Booked";
		this.attendee = attendee;
		this.event = event;
		
	}
	
	public void cancel()
	{
		this.status = "Cancelled";
		event.setAvailableTickets(event.getAvailableTickets()+1);
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public String ticketDetails()
	{
		return "Ticket for: " + attendee.name + ", to event: " + event.getTitle() + ", Status: " + status;
	}


}
