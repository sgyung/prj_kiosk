package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import view.MenuView;

public class MenuEvt extends WindowAdapter implements ActionListener{
	private MenuView menuView;
	
	public MenuEvt(MenuView menuView) {
		this.menuView = menuView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
	
}
