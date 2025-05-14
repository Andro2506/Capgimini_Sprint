package dao;

import java.util.*;

import entities.*;
import exceptions.invaliidBookingException;

import java.io.*;

public class EventBookingSystem {
	
	private List<Attendee> attendeeList = new ArrayList<>();
	private List<Organizer> orgList = new ArrayList<>();
	private List<Event> eventList = new ArrayList<>();
	private List<Ticket> ticketList = new ArrayList<>();
	
	
	public void registerUser(User user)
	{
		if(user instanceof Attendee) {
			attendeeList.add((Attendee) user);
			System.out.println("Attendee is registered!!");
		}
		else if(user instanceof Organizer) {
			orgList.add((Organizer) user);
			System.out.println("Organizer is registered!!");
		}
	}
	
	public void addEvent(Event event)
	{
		eventList.add(event);
		System.out.println("Event is added!!");
	}
	
	public void bookTicket(String attendeeId, String eventTitle) throws invaliidBookingException{
		Attendee attendee = attendeeList.stream().filter(a -> a.getId().equals(attendeeId)).findFirst().orElse(null);

        Event event = eventList.stream().filter(e -> e.getTitle().equalsIgnoreCase(eventTitle) && e.isAvailable()).findFirst().orElse(null);


        if (attendee == null || event == null) {
            throw new invaliidBookingException("Booking failed: Invalid attendee or event!!");
        }

        event.setAvailableTickets(event.getAvailableTickets() - 1);
        ticketList.add(new Ticket(attendee, event));
	}
	
	public void showEvents() {
		for(Event i : eventList) {
			System.out.println(i);
		}
	}
	
	public void saveEvents() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\soham\\OneDrive\\Desktop\\SaveEvents\\events.txt"))) {
	        for (Event event : eventList) {
	            writer.write(event.getTitle() + "," + event.getAvailableTickets());
	            writer.newLine();
	        }
	        System.out.println("Events saved to events.txt");
	    } catch (IOException e) {
	        System.out.println("Failed to save events: " + e.getMessage());
	    }
    }

	public void loadEvents() {
	    File file = new File("C:\\Users\\soham\\OneDrive\\Desktop\\SaveEvents\\events.txt");
	    if (!file.exists()) {
	        System.out.println("No events to load.");
	        return;
	    }

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 2) {
	                String title = parts[0];
	                int availableTickets = Integer.parseInt(parts[1]);
	                eventList.add(new Event(title, availableTickets));
	            }
	        }
	        System.out.println("Events loaded from events.txt");
	    } catch (IOException | NumberFormatException e) {
	        System.out.println("Failed to load events: " + e.getMessage());
	    }
	}

	public List<Event> getEvents() {
	    return eventList;
	}

	

}
