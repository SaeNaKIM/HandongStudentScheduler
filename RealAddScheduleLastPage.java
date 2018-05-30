public class RealAddScheduleLastPage implements AddScheduleLastPage {
	
	public RealAddScheduleLastPage(){
		
		//setValues(addsch,user, category,day_num, first_index, last_index, name);
				
	}

	@Override
	public void setValues(AddSchedule addsch, User user, String category,int day_num, int first_index, int last_index, String name) {

		 addsch.adScheduleStep2(day_num, first_index, last_index, name);
	                
		//notification code 
	     if(!user.getInviteList().isEmpty()) {
	        	System.out.println("초대!!");
	        	int temp = 0;
	        	if (category.equals("class")) {
	            		temp = 1;
	 
	                } else if (category.equals("ClubAndSociety")) {
	                	temp = 2;
	 
	                } else if (category.equals("FixedMeal")) {
	                	temp = 3;
	 
	                } else if (category.equals("ProjectMeeting")) {
	            	temp = 4;
	            }
	        	while(!user.getInviteList().isEmpty()) {
	        		User receiver = user.getInviteList().iterator().next();
	        		MemoryManager.getInstance().sendNotifications(receiver, new Notification(user.getUserID(), new Schedule(day_num, first_index, last_index, temp, name)));
	        		user.getInviteList().removeFirst();
	        	}
	        	user.initializeInviteList();
	        }
	        else {
	        	System.out.println("초대X");
	        }
		
		 
		
	}
	
	
	

}
