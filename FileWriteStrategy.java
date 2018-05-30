
public class FileWriteStrategy{
	private FileWrite strategy;
	
	public FileWriteStrategy(FileWrite strategy) {
	    this.strategy = strategy;
	  }
	  
	  //use the strategy
	  public void writeFile(User user) {
		  strategy.Write(user);
	  }
	  
	  public void writeNotification(User user) {
		  strategy.writeNotification(user);
	  }
	  
	  public void writeSentNotifications(User receiver, Notification notification) {
		  strategy.writeSentNotifications(receiver, notification);
	  }
}
