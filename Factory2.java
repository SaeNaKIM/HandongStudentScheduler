
public class Factory2 implements GuiFactory{

	@Override
	public Panel CreatePanel() {
		return new Panel2();
	}

	@Override
	public Button CreateButton() {
		// TODO Auto-generated method stub
		return new Button2();
	}
}
