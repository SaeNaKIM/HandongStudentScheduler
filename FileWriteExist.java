import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileWriteExist implements FileWrite {
	
	@Override
	public void Write(User user) {
		// TODO Auto-generated method stub
		writeFile(user);
	}

	private void writeFile(User user) {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/" + user.getUserID() + ".txt";
		File curfile = new File(path);
		try {
			if (curfile.createNewFile() == true) {
				System.out.println(user.getUserID() + ".txt does not exist.");
			} else {
				curfile.delete();
				curfile.createNewFile();
				int[][] temp = new int[32][7];
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curfile), "UTF-8")));
				// FileWriter fw = new FileWriter(curfile,false);
				// BufferedWriter bw = new BufferedWriter(fw);
				// PrintWriter out = new PrintWriter(bw);
				out.println(user.getUserID());
				out.println(user.getPassword());
				out.println(user.getUserName());
				out.println(user.getStuNo());
				temp = user.getTimeslot();
				for (int i = 0; i < 32; i++)
					for (int j = 0; j < 7; j++) {
						out.print(temp[i][j]);
						if (j < 6)
							out.print(" ");
						else
							out.println("");
					}
				for (int i = 0; i < user.getAllSchedule().size(); i++) {
					out.println((user.getAllSchedule().get(i).getDatetime())[0]);
					out.println((user.getAllSchedule().get(i).getDatetime())[1]);
					out.println((user.getAllSchedule().get(i).getDatetime())[2]);
					out.println(user.getAllSchedule().get(i).getColor());
					out.println(user.getAllSchedule().get(i).getName());
				}
				out.close();
				System.out.println(user.getUserID() + ".txt saved.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeNotification(User user) {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/notification/" + user.getUserID() + ".txt";
		File curfile = new File(path);
		try {
			if (curfile.createNewFile() == true) {
				System.out.println(user.getUserID() + ".txt does not exist.");
			} else {
				curfile.delete();
				curfile.createNewFile();
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curfile), "UTF-8")));
				
				for (int i = 0; i < user.getAllNotification().size(); i++) {
					out.println(user.getAllNotification().get(i).getSender());
					out.println(user.getAllNotification().get(i).getSchedule().getColor());
					out.println((user.getAllNotification().get(i).getSchedule().getDatetime())[0]);
					out.println((user.getAllNotification().get(i).getSchedule().getDatetime())[1]);
					out.println((user.getAllNotification().get(i).getSchedule().getDatetime())[2]);
					out.println(user.getAllNotification().get(i).getSchedule().getName());
				}
				out.close();
				System.out.println(user.getUserID() + ".txt notification saved.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeSentNotifications(User receiver, Notification notification) {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/notification/" + receiver.getUserID() + ".txt";
		File curfile = new File(path);
		try {
			if (curfile.createNewFile() == true) {
				System.out.println(receiver.getUserID() + ".txt does not exist.");
			} else {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(curfile, true)));
				out.println(notification.getSender());
				out.println(notification.getSchedule().getColor());
				out.println((notification.getSchedule().getDatetime())[0]);
				out.println((notification.getSchedule().getDatetime())[1]);
				out.println((notification.getSchedule().getDatetime())[2]);
				out.println(notification.getSchedule().getName());
				out.close();
				System.out.println(receiver.getUserID() + ".txt notification saved.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
