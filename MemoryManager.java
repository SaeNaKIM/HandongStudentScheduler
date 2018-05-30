import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MemoryManager{
	
	private LinkedList<User> users = new LinkedList<User>();
	

	private static MemoryManager theInstance = new MemoryManager();

	private MemoryManager() {
		this.makeUserList();
	}

	public static MemoryManager getInstance() {
		return theInstance;
	}

	private void wipeMemory() {
		users.clear();
	}

	private void readFile(String file) {

		String id, pw, name;
		int stu_no;
		int timeslot[][] = new int[32][7];
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/" + file + ".txt";
		File curfile = new File(path);
		if (curfile.exists()) {
			try {
				int day, start, end, color;
				String timeName, temp;
				// FileReader fileReader = new FileReader(curfile);
				// BufferedReader bufferedReader = new BufferedReader(fileReader);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(curfile), "UTF-8"));
				id = bufferedReader.readLine();
				pw = bufferedReader.readLine();
				name = bufferedReader.readLine();
				stu_no = Integer.parseInt(bufferedReader.readLine());
				System.out.println("id = " + id);
				System.out.println("pw = " + pw);
				System.out.println("name = " + name);
				System.out.println("stu_no = " + stu_no);
				User user = new User(id, pw, name, stu_no);
				for (int i = 0; i < 32; i++) {
					String strtemp = bufferedReader.readLine();
					String[] strs = strtemp.trim().split("\\s+");
					for (int j = 0; j < 7; j++) {
						timeslot[i][j] = Integer.parseInt(strs[j]);
					}
				}

				System.out.println(Arrays.deepToString(timeslot));
				user.setTimeslot(timeslot);

				while ((temp = bufferedReader.readLine()) != null) {
					day = Integer.parseInt(temp);
					start = Integer.parseInt(bufferedReader.readLine());
					end = Integer.parseInt(bufferedReader.readLine());
					color = Integer.parseInt(bufferedReader.readLine());
					timeName = bufferedReader.readLine();
					user.getAllSchedule().add(new Schedule(day, start, end, color, timeName));
				}
				if (findUser(id) == null)
					users.add(user);
				else {
					users.remove(users.indexOf(findUser(id)));
					users.add(user);
				}

				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("file does not exist.");
		}
	}
	
	private void saveFile(User user) {
		FileWriteStrategy write = new FileWriteStrategy(new FileWriteExist());
		write.writeFile(user);
		//write.writeNotification(user);
	}
	
	private void saveNotification(User user) {
		FileWriteStrategy write = new FileWriteStrategy(new FileWriteExist());
		write.writeNotification(user);
	}
	
	public void sendNotifications(User receiver, Notification notification) {
		//System.out.println("Sending Notifications.");
		FileWriteStrategy write = new FileWriteStrategy(new FileWriteExist());
		write.writeSentNotifications(receiver, notification);
	}

	private void makeUserList() {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/" + "user" + ".txt";
		File curfile = new File(path);
		if (curfile.exists()) {
			try {
				Scanner scanner = new Scanner(curfile);
				while (scanner.hasNextLine()) {
					String id = scanner.nextLine();
					String pw = scanner.nextLine();
					readFile(id);
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("user.txt does not exist.");
		}
		System.out.println("Size of users: " + users.size());
	}
	
	private void readNotification(User user) {
		String sender, name;
		int type, day, start, end;
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/notification/" + user.getUserID() + ".txt";
		File curfile = new File(path);
		if (curfile.exists()) {
			try {
				// FileReader fileReader = new FileReader(curfile);
				// BufferedReader bufferedReader = new BufferedReader(fileReader);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(curfile), "UTF-8"));
				while ((sender = bufferedReader.readLine()) != null) {
				type = Integer.parseInt(bufferedReader.readLine());
				day = Integer.parseInt(bufferedReader.readLine());
				start = Integer.parseInt(bufferedReader.readLine());
				end = Integer.parseInt(bufferedReader.readLine());
				name = bufferedReader.readLine();
				System.out.println("sender = " + sender);
				System.out.println("type = " + type);
				System.out.println("day = " + day);
				System.out.println("start = " + start);
				System.out.println("end = " + end);
				System.out.println("name = " + name);
				if(user.getAllNotification().contains(new Notification(sender, new Schedule(day, start, end, type, name))) == false)
					user.getAllNotification().add(new Notification(sender, new Schedule(day, start, end, type, name)));
				//notifications.add(new Notification(sender, new Schedule(day, start, end, type, name)));
				}
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("file does not exist.");
		}
	}
	
	public User findUser(String user_id) {

		int find_user_idx = -1;
		int i = 0;
		while (i < users.size()) {
			if (user_id.equals(users.get(i).getUserID())) {
				find_user_idx = i;
				break;
			}
			i++;
		}
		if (find_user_idx == -1)
			return null;
		return users.get(find_user_idx);
	}

	private void updateUser(User user) {
		if (findUser(user.getUserID()) == null)
			System.out.println(user.getUserID() + " does not exist.");
		else {
			users.remove(users.indexOf(findUser(user.getUserID())));
			users.add(user);
		}
	}

	public User loginAuthentication(String id, char[] cs) {
		if (findUser(id) != null) {
			if (String.valueOf(cs).equals(findUser(id).getPassword())) {
				MemoryManager.getInstance().readNotification(MemoryManager.getInstance().findUser(id));
				return findUser(id);
			}
		}
		return null;
	}

	public void exitProcedure(User user) {
		updateUser(user);
		saveFile(user);
		wipeMemory();
	}

	public void logoutProcedure(User user) {
		updateUser(user);
		saveFile(user);
	}
	
	public void notificationProcedure(User user) {
		saveNotification(user);
		readNotification(user);
	}

	public void registerUser(String id, String pw, String stuName, int stuNo) {
		User user = new User(id, pw, stuName, stuNo);
		//writeUser(id, pw);
		//writeFile(id, pw, stuName, stu_no);
		FileWriteStrategy write = new FileWriteStrategy(new FileWriteNew());
		write.writeFile(user);
		write.writeNotification(user);
		readFile(id);
	}
	/*
	public static void main(String[] args) {
		MemoryManager.getInstance().readNotification(MemoryManager.getInstance().findUser("alpha"));
		System.out.println("Sender: " + MemoryManager.getInstance().findUser("alpha").getAllNotification().get(0).getSender());
	}*/
	 
}