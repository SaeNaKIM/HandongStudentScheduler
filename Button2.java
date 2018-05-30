import java.awt.Color;
import javax.swing.JButton;

public class Button2 implements Button{

	@Override
	public JButton makeButton(String name) {
		JButton button2 = new JButton(name);
		button2.setBackground(Color.GREEN);
		return button2;
	}

	
}
