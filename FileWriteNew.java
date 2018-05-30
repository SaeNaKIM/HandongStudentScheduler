import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileWriteNew implements FileWrite {
	
	@Override
	public void Write(User user) {
		// TODO Auto-generated method stub
		writeUser(user);
		writeFile(user);
	}
	
	private void writeUser(User user) {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/" + "user" + ".txt";
		File curfile = new File(path);
		if (curfile.exists()) {
			try {
				//if (findUser(name) == null) {
					FileWriter fw = new FileWriter(curfile, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw);
					out.println(user.getUserID());
					out.println(user.getPassword());
					out.close();
				//}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("user.txt does not exist.");
		}
	}
	
	private void writeFile(User user) {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path + "/data/users/" + user.getUserID() + ".txt";
		File curfile = new File(path);
		try {
			if (curfile.createNewFile() == true) {
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curfile), "UTF-8")));
				// PrintWriter out = new PrintWriter(curfile);
				out.println(user.getUserID());
				out.println(user.getPassword());
				out.println(user.getUserName());
				out.println(user.getStuNo());
				for (int i = 0; i < 32; i++) {
					out.println("0 0 0 0 0 0 0");
				}
				out.close();
				System.out.println(user.getUserID() + ".txt created.");
			} else {
				System.out.println(user.getUserID() + ".txt already exists.");
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
				System.out.println(user.getUserID() + ".txt created.");
			} else {
				System.out.println(user.getUserID() + ".txt already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeSentNotifications(User receiver, Notification notification) {}
}
