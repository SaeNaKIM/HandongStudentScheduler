

public class RemoveSchedule extends ManageSchedule{

	//private User user;

	
	RemoveSchedule(User user){
		
		super(user);
		
	}
	
	
	public void removeSchedule(Schedule delete_schedule) {
		
		String name = new String();
		int[] datetime = new int[3];
		
		name = delete_schedule.getName();
		datetime = delete_schedule.getDatetime();
		//System.out.println("name:" + name);
		
		int find_idx = user.findSchedule(name, datetime);
		
		if (find_idx > -1) { // found
			user.getAllSchedule().remove(find_idx); // delete schedule
			user.updateTimeslot(datetime[0], datetime[1], datetime[2], name);
		} else {
			System.out.println("not find");
		}
		
		
	}
	
	
	public void removeSchedule(String name, int[] datetime ) {
		
		//String name = new String();
		//int[] datetime = new int[3];
		
		//name = delete_schedule.getName();
		//datetime = delete_schedule.getDatetime();
		//System.out.println("name:" + name);
		
		int find_idx = user.findSchedule(name, datetime);
		
		if (find_idx > -1) { // found
			user.getAllSchedule().remove(find_idx); // delete schedule
			user.updateTimeslot(datetime[0], datetime[1], datetime[2], name);
		} else {
			System.out.println("not find");
		}
		
		
	}
	
}
