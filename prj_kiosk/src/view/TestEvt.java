package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestEvt implements ActionListener{

	private MenuView menuView;
	
	public TestEvt(MenuView menuView) {
		this.menuView = menuView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuView.getCofMenu().getBtn()) {
			new OptionMenuView(menuView);
		}
	}

}
