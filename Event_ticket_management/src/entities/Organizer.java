package entities;

public class Organizer  extends User{
	private boolean active = true;
	
	public Organizer(String id, String name)
	{
		super(id, name);
	}
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	@Override
	public void showProfile() {
		System.out.println("Organizer ID: " + this.id + ", Name: " + this.name + ", Active: " + this.active);
		
	}

}
