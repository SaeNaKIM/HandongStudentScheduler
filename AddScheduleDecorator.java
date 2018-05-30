
public abstract class AddScheduleDecorator implements AddScheduleLastPage {
	
	protected AddScheduleLastPage decoratedO;
	
	public AddScheduleDecorator(AddScheduleLastPage decoratedO) {
		this.decoratedO = decoratedO;
	}

}
