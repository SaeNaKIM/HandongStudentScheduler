
public interface FileWrite {

	void Write(User user);
	
	void writeNotification(User user);

	void writeSentNotifications(User receiver, Notification notification);
	
}
