package entities;

public class Attendee extends User {
	
	public Attendee(String id, String name)
	{
		super(id, name);
	}
	
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}

	@Override
	public void showProfile() {
		System.out.println("Attendee ID: " + this.id + ", Name: " + this.name);
	}
	

}
