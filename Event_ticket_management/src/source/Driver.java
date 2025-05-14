package source;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import dao.EventBookingSystem;
import dao.Role;
import exceptions.invaliidBookingException;
import entities.*;

public class Driver {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		EventBookingSystem system = new EventBookingSystem();
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("A1", "SuperAdmin");

        system.loadEvents();

        while (true) {
            System.out.println("\n===== Event Booking Menu =====");
            System.out.println("1. Register Attendee");
            System.out.println("2. Add Event");
            System.out.println("3. Book Ticket");
            System.out.println("4. Show Events");
            System.out.println("5. Remove Event (Admin)");
            System.out.println("6. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Attendee ID: ");
                        String aid = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String aname = scanner.nextLine();
                        system.registerUser(new Attendee(aid, aname));
                        break;

                    case 2:
                        System.out.print("Enter Event Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Available Tickets: ");
                        int tickets = scanner.nextInt();
                        scanner.nextLine();
                        system.addEvent(new Event(title, tickets));
                        break;

                    case 3:
                        System.out.print("Enter Attendee ID: ");
                        String attendeeId = scanner.nextLine();
                        System.out.print("Enter Event Title: ");
                        String eventTitle = scanner.nextLine();
                        system.bookTicket(attendeeId, eventTitle);
                        break;

                    case 4:
                        system.showEvents();
                        break;

                    case 5:
                    	System.out.print("Enter Event Title to Remove: ");
                        String removeTitle = scanner.nextLine();
                        
                        try {
                            Class<Admin> adminClass = Admin.class;
                            Method method = adminClass.getMethod("removeEvent", List.class, String.class);

                            if (method.isAnnotationPresent(Role.class)) {
                                Role role = method.getAnnotation(Role.class);
                                if ("Admin".equalsIgnoreCase(role.value())) {
                                    admin.removeEvent(system.getEvents(), removeTitle);
                                } else {
                                    System.out.println("Access denied: Insufficient role.");
                                }
                            } else {
                                System.out.println("Access denied: Method not role-protected.");
                            }
                        } catch (NoSuchMethodException e) {
                            System.out.println("Error accessing method: " + e.getMessage());
                        }
                        break;

                    case 6:
                        system.saveEvents();
                        System.out.println("You are Welcome!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (invaliidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
	}

}