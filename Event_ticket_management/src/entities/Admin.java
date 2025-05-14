package entities;

import dao.Role;


import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

@Role(value = "Admin")
public class Admin extends User{
	public Admin(String id, String name) {
        super(id, name);
    }
	
	@Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }
	
	@Role(value = "Admin")
	public void removeEvent(List<Event> events, String title) {
		boolean removed = false;
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (event.getTitle().equalsIgnoreCase(title.trim())) {
                iterator.remove();
                System.out.println("Event '" + title + "' removed successfully.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Event '" + title + "' not found.");
        }
    }

}
