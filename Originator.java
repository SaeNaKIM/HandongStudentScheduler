
public class Originator {
	private String friend_id = "";

	public void setInvite(String friend_id) {
		System.out.println("Setting list to " + friend_id);
		this.friend_id += friend_id;
	}

	public Memento saveToMemento() {
		System.out.println("Saving list to Memento");
		return new Memento(friend_id);
	}

	public void restoreFromMemento(Memento memento) {
		friend_id = memento.getSavedTime();
		System.out.println("List restored from Memento: " + friend_id);
	}
	
	public static class Memento {
		private final String fid;

		public Memento(String friendToSave) {
			fid = friendToSave;
		}

		public String getSavedTime() {
			return fid;
		}
	}
	
	public void clear() {
		this.friend_id = "";
	}
}


