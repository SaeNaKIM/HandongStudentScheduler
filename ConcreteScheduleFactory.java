

public class ConcreteScheduleFactory extends ScheduleFactory {

	@Override
	Schedule createSchedule(String category) {
		
		System.out.println("category:" + category);
		switch(category){
			
			case "class": return new Class();
			case "FixedMeal":return new FixedMeal();
			case "ClubAndSociety" : return new ClubAndSociety();
			case "ProjectMeeting" : return new ProjectMeating();
			
		}
		System.out.println("return null");
		return null;
	}

	
	
}
