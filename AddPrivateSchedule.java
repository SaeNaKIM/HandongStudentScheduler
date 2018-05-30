

public class AddPrivateSchedule extends AddSchedule {
	
				
	AddPrivateSchedule(User user) {
		super(user);
	}

	boolean isGroupSchedule() { //hook method
		return false;
	}
	

}