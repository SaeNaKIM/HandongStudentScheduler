import javax.swing.JPanel;

public class Factory1 implements GuiFactory{

	@Override
	public Panel CreatePanel() {
		return new Panel1();
	}

	@Override
	public Button CreateButton() {
		// TODO Auto-generated method stub
		return new Button1();
	}
	
}
