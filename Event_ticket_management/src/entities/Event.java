package entities;

import java.io.Serializable;

public class Event implements Serializable{
	private String title;
	private int availableTickets;
	
	public Event(String title, int availableTickets) {
		this.title=title;
		this.availableTickets = availableTickets;
	}
	
	// getter & setters
	public String getTitle() {
		return title;
	}
	
	public int getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}
	
	// methods
	public boolean isAvailable() {
		return this.availableTickets>0;
	}
	
	@Override
	public String toString() {
		return "Event [title=" + title + ", availableTickets=" + availableTickets + "]";
	}
	

}
