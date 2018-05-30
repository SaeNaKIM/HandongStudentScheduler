import java.awt.*;
import javax.swing.*;

public class Panel1 implements Panel{

	@Override
	public JPanel makePanel() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.YELLOW);
		return panel1;
	}

}
