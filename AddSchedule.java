

import java.util.LinkedList;

public abstract class AddSchedule extends ManageSchedule {

	
	private int[][] time_slot = new int[32][7];
	private String category;
	protected ScheduleFactory sf; 
	
	
	
	AddSchedule(User user) {
		super(user);
		
	}
	
	final int[][] addScheduleStep1(String category, LinkedList<User> Users) {
		
		//template design pattern 
		this.category = category;
		this.time_slot = user.getTimeslot();
		if(isGroupSchedule()) { // hook method 
			this.time_slot = checkCompatibleSchedule(Users, this.time_slot);		
		}
		return this.time_slot;
	}
	
	final void adScheduleStep2(int day_of_week, int start_time, int end_time, String name) {
		
		//Factory design pattern 
		sf = new ConcreteScheduleFactory();
		Schedule one_schedule = sf.createSchedule(category);
		
		try {
			one_schedule.setDatatime(day_of_week, start_time, end_time);
			one_schedule.writeName(name);	
			user.getAllSchedule().add(one_schedule);
			user.updateTimeslot(day_of_week, start_time, end_time, name);
		}catch(NullPointerException e) {
			System.out.println("fail to make schedule(product) in factory");
		}
		
	}	
	
	boolean isGroupSchedule() { //hook method
		return false;
	}
	
	
	int[][] checkCompatibleSchedule(LinkedList<User> Users, int[][] time_slot){ return null; }// null implementation
}
