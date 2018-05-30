

import java.util.Iterator;
import java.util.LinkedList;

public class AddGroupSchedule extends AddSchedule {
	
	
	AddGroupSchedule(User user) {
		super(user);
		
	}
		
	boolean isGroupSchedule() { //hook method
		return true;
	}
	
	int[][] checkCompatibleSchedule(LinkedList<User> Users, int[][] user_time_slot) {
		
		
		int[][] available_timeslot = new int[32][7]; // default initialize to zero.
		
		//clone user time_slot
		for(int k = 0; k < 32; k++) {
			for(int j = 0; j <7; j++) {
				
				available_timeslot[k][j] = user_time_slot[k][j];
			}				
		}

		Iterator<User> user_iterator = Users.iterator();
		while (user_iterator.hasNext()) {

			int[][] tmp_timeslot = user_iterator.next().getTimeslot();

			for (int k = 0; k < 32; k++) {
				for (int j = 0; j < 7; j++) {
					if ((tmp_timeslot[k][j] == 0 && available_timeslot[k][j] == 0)) {
						available_timeslot[k][j] = 0;
					} else {
						available_timeslot[k][j] = 1;
					}

				}
			}
		}
		return available_timeslot;

	}

}
