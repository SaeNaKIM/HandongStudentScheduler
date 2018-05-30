import javax.swing.JOptionPane;

public class KindAddPrivateScheduleDecorator extends AddScheduleDecorator{

	public KindAddPrivateScheduleDecorator(AddScheduleLastPage decoratedO) {
		super(decoratedO);
		
	}

	@Override
	public void setValues(AddSchedule addsch, User user, String category,int day_num, int first_index, int last_index, String name) {
		decoratedO.setValues(addsch, user, category, day_num, first_index, last_index, name);
		JOptionPane.showMessageDialog(null,"Suceed to add private shedule! Go for it!", "Success", JOptionPane.PLAIN_MESSAGE);      	
		
	}


}
