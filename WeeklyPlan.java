import java.util.Iterator;
import java.util.LinkedList;

public class WeeklyPlan {

	// private Schedule[] schedules = new Schedule[50];
	// private int cur_idx = 0;
	private LinkedList<Schedule> schedules = new LinkedList<Schedule>();
	int[][] time_slot = new int[32][7]; // default initialize to zero.

	private Schedule one_schedule;

	public void makeSchedule(String category, boolean invite) {

		if (category.equals("class")) {
			one_schedule = new Class();
			one_schedule.setColor(1);

		} else if (category.equals("ClubAndSociety")) {
			one_schedule = new ClubAndSociety();
			one_schedule.setColor(2);

		} else if (category.equals("FixedMeal")) {
			one_schedule = new FixedMeal();
			one_schedule.setColor(3);

		} else if (category.equals("ProjectMeeting")) {
			one_schedule = new ProjectMeating();
			one_schedule.setColor(4);

		} else {
			System.out.println("category wrong");
		}

		// if(invite) {

		// weekly plan 객체에서 다른 사람의 정보를 조회하는 것이 아니라 HSS에서 해줘야 하는 부분인거 같음.
		// 이는 추후 변경하고 일단은 최대한 구현 할
		// checkCompatatibleSchedule();

		// }

	}

	/**
	 * @param
	 */
	public void addDateTimeAndName(int day_of_week, int start_time, int end_time, String name) {

		this.one_schedule.setDatatime(day_of_week, start_time, end_time);
		this.one_schedule.writeName(name);

		// add one schedule to linked list.
		schedules.add(this.one_schedule);
		updateTimeslot(day_of_week, start_time, end_time, name);

	}

	public void deleteSchedule(Schedule schedule) {

		String name = new String();
		name = schedule.getName();
		System.out.println("name:" + name);
		int find_idx = findSchedule(name);

		int[] datetime = schedules.get(find_idx).getDatetime();

		if (find_idx > -1) { // found
			this.schedules.remove(find_idx); // delete schedule
			updateTimeslot(datetime[0], datetime[1], datetime[2], name);
		} else {
			System.out.println("not find");
		}

	}

	public void deleteSchedule(String schedule_name) {

		int find_idx = findSchedule(schedule_name);
		int[] datetime = schedules.get(find_idx).getDatetime();

		if (find_idx > -1) { // found
			this.schedules.remove(find_idx); // delete schedule
			updateTimeslot(datetime[0], datetime[1], datetime[2], schedule_name);
		} else {
			System.out.println("not find");
		}

	}

	public int[][] checkCompatatibleSchedule(LinkedList<User> user_list, int num) {

		int[][] available_timeslot = new int[32][7]; // default initialize to zero.
		
		for(int k = 0; k < 32; k++) {
			for(int j = 0; j <7; j++) {
				
				available_timeslot[k][j] = time_slot[k][j];
			}				
		}

		Iterator<User> user_iterator = user_list.iterator();
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

	/**
	 * @return index of schedule to be requested to find
	 */
	public int findSchedule(String name) {

		Iterator<Schedule> iterator = schedules.iterator();
		int find_idx = -1;
		int idx = 0;
		while (iterator.hasNext()) {

			// Schedule find_s = iterator.next();
			if (name.equals(iterator.next().getName())) {
				find_idx = idx;
				break;

			}

			idx++;
		}

		return find_idx;

	}

	/**
	 * @return schedules
	 */
	public LinkedList<Schedule> getAllSchedule() { // to show other user

		return this.schedules;
	}

	public void updateTimeslot(int day_of_week, int start_time, int end_time, String name) {

		for (int i = start_time; i <= end_time; i++) {

			if (time_slot[i][day_of_week] == 0) {
				time_slot[i][day_of_week] = 1;
			} else {
				time_slot[i][day_of_week] = 0;
			}
		}

	}

	/**
	 * @return time_slot
	 */
	public int[][] getTimeslot() {
		return this.time_slot;
	}

	public void setTimeslot(int[][] initial_timeslot) {

		System.out.println("initial_timeslot");
		for (int i = 0; i < initial_timeslot.length; i++) {
			for (int j = 0; j < initial_timeslot[i].length; j++) {
				this.time_slot[i][j] = initial_timeslot[i][j];
			}

		}

	}

	public void initialSchedule(LinkedList<Schedule> schedules) {

		this.schedules = schedules;
	}

}