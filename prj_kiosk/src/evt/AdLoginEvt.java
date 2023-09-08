package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AdLoginView;
import view.MainView;

public class AdLoginEvt  implements ActionListener {
           
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
}//clas
