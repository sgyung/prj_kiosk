package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import view.AdminTabView;

public class AdminTabViewEvt extends WindowAdapter implements ActionListener {

	public AdminTabView admintabView;
	
	public AdminTabViewEvt(AdminTabView adminTabView) {
		this.admintabView=adminTabView;
	}//AdminTabViewEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
