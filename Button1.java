import java.awt.Color;
import javax.swing.JButton;

public class Button1 implements Button{

	@Override
	public JButton makeButton(String name) {
		JButton button1 = new JButton(name);
		button1.setBackground(Color.WHITE);
		return button1;
	}
	
}
