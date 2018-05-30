
public class Notification {
	String sender;
	Schedule schedule;

	public Notification() {
		
	}
	
	public Notification(String sender, Schedule schedule) {
		this.sender = sender;
		this.schedule = schedule;
	}
	
	public String getSender() {
		return sender;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public boolean equals(Object o) {
		if(((Notification)o).schedule.equals(schedule))
			return ((Notification)o).sender.equals(sender);
	    return false;
	}
}
