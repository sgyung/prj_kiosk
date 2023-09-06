package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import view.AdLoginView;
import view.MainView;
import view.MenuView;

//WindowAdaptor와 <<interface>> ActionListener
public class MainEvt extends WindowAdapter implements ActionListener{

	//mainView : MainView
	private MainView mainView;
	
	//MainEvt( mainView )
	public MainEvt( MainView mainView) {
		this.mainView=mainView;
	}//MainEvt
	
	//actionPerformed( ActionEvent ) : void
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mainView.getOrderBtn()) {//버튼 클릭 시
			//orderBtn하는 일 추가
			JOptionPane.showMessageDialog(mainView, "주문 창으로 이동합니다.");
			new MenuView();
		}//end if
		if(ae.getSource() == mainView.getAdminBtn()) {//버튼 클릭 시
			//adminBtn 하는 일 추가
			JOptionPane.showMessageDialog(mainView, "관리자 로그인 창으로 이동합니다.");
			new AdLoginView();
		}//end if
	}//actionPerformed
	
	//windowClosing( WindowEvent ) : void
	@Override
	public void windowClosing(WindowEvent we) {
		mainView.dispose();
	}//windowClosing	
	
}//class
