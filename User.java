
/**
 * User.java - User class includes user's information, who is the user of our program,
 *           and behaviors that the user can do about scheduler. 
 *  2018. 04. 27 ~
 * @author Saena, Ki-Ppeum, Haebin, Seonmouk 
 * @version 1.0
 */

import java.util.Iterator;
import java.util.LinkedList;

public class User {

	private String user_id;
	private String pw;
	private String name;
	private int student_id;
	// private WeeklyPlan my_weekly_plan = new WeeklyPlan();
	private LinkedList<Schedule> my_schedules = new LinkedList<Schedule>();
	private int[][] my_time_slot = new int[32][7];

	private LinkedList<User> invite_list = new LinkedList<User>();
	private LinkedList<Notification> notifications = new LinkedList<Notification>();

	/**
	 * User Constructor
	 * 
	 * @param user_id
	 * @param pw
	 */
	public User(String user_id, String pw) {

		setUserInformation(user_id, pw);

	}

	/**
	 * User Constructor - this constructor is called when a user is searching other
	 * user to make a group schedule.
	 * 
	 * @param user_id
	 */
	public User(String user_id) {

		this.user_id = user_id;

	}

	public User(String id, String pw2, String name, int stu_no) {

		this.user_id = id;
		this.pw = pw2;
		this.name = name;
		this.student_id = stu_no;

	}

	/**
	 * Set the id number and password of user
	 * 
	 * @param user_id
	 * @param pw
	 */
	public void setUserInformation(String user_id, String pw) {

		this.user_id = user_id;
		this.pw = pw;
	}

	public boolean requestSearchUser(String user_id) { // Now, user add other user to user's own list in this method.

		// HSS class 가 해주어야 하지 않을까...
		User searched_user = new User(user_id); // 이 아래로 맞는 syntax 인지 모르겠음… 확인 필요.c++에서는 안될 코드임.
		searched_user = MemoryManager.getInstance().findUser(user_id);

		if (searched_user != null) {
			invite_list.add(searched_user);
			return true;
		} else {
			System.out.println("searched user null!");
			return false;
		}

	}

	/**
	 * Get time_slot
	 * 
	 * @return
	 */
	public int[][] getTimeslot() {

		return my_time_slot;
	}

	public void setTimeslot(int[][] initial_timeslot) {

		System.out.println("initial_timeslot");
		for (int i = 0; i < initial_timeslot.length; i++) {
			for (int j = 0; j < initial_timeslot[i].length; j++) {
				this.my_time_slot[i][j] = initial_timeslot[i][j];
			}

		}

	}

	/**
	 * Give the user id number
	 * 
	 * @return return user id
	 */
	public String getUserID() {
		return this.user_id;
	}

	/**
	 * Give password for login
	 * 
	 * @return
	 */
	public String getPassword() {

		return this.pw;
	}

	public void setScheudules(LinkedList<Schedule> schedule) { // memory manager call

		this.my_schedules = schedule;

	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public int getStuNo() {
		// TODO Auto-generated method stub
		return this.student_id;
	}

	public void printAllSchedule() {

		int i = 0;
		Iterator<Schedule> iterator = my_schedules.iterator();
		while (iterator.hasNext()) {
			Schedule sc = iterator.next();
			System.out.println("i: " + i);
			System.out.println("schedule_name: " + sc.getName());
			System.out.println("schedule_color: " + sc.getColor());
			int[] datetime = sc.getDatetime();
			System.out.println("schedule_datetime: " + datetime[0] + datetime[1] + datetime[2]);
			i++;
		}
	}

	public LinkedList<Schedule> getAllSchedule() {

		return my_schedules;

	}

	public void updateTimeslot(int day_of_week, int start_time, int end_time, String name) {

		for (int i = start_time; i <= end_time; i++) {

			if (my_time_slot[i][day_of_week] == 0) {
				my_time_slot[i][day_of_week] = 1;
			} else {
				my_time_slot[i][day_of_week] = 0;
			}
		}

	}

	/**
	 * @return index of schedule to be requested to find
	 */
	public int findSchedule(String name, int[] datetime) {

		Iterator<Schedule> iterator = my_schedules.iterator();
		int find_idx = -1;
		int idx = 0;

		// System.out.println("name: "+name + "datetime: " + String.valueOf(datetime[0])
		// + String.valueOf(datetime[1]) + String.valueOf(datetime[2]));
		while (iterator.hasNext()) {

			Schedule find_s = iterator.next();
			if (name.equals(find_s.getName()) && datetime[0] == find_s.getDatetime()[0]
					&& datetime[1] == find_s.getDatetime()[1] && datetime[2] == find_s.getDatetime()[2]) {
				find_idx = idx;
				break;

			}

			idx++;
		}

		return find_idx;

	}

	public LinkedList<User> getInviteList() {

		return this.invite_list;

	}

	public void initializeInviteList() {

		this.invite_list.clear();

	}

	public LinkedList<Notification> getAllNotification() {

		return notifications;

	}

	public void removeLastFriend() {
		if(invite_list.isEmpty()) {}
		else
			invite_list.removeLast();
	}

}