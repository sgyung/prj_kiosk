package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.AdLoginView;
import view.MainView;

public class AdLoginEvt extends WindowAdapter implements ActionListener {
           
	public AdLoginView adloginView;
	
	public AdLoginEvt(AdLoginView adLoginView) {
		this.adloginView=adLoginView;
	}//AdLoginEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()== adloginView.getHomeBtn()) {//홈버튼클릭시
			adloginView.setVisible(false);//관리자 로그인화면은 종료
			new MainView();		}//메인화면 띄우기
	}//actionPerformed


	@Override
	public void windowClosing(WindowEvent e) {
		adloginView.dispose();
	}
	
}//class
