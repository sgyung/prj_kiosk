package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.AdminDAO;
import view.AdLoginView;
import view.AdminTabView;
import view.MainView;
import vo.AdminVO;

public class AdLoginEvt extends WindowAdapter implements ActionListener {
           
	public AdLoginView adv;
	
	public AdLoginEvt(AdLoginView adLoginView) {
		this.adv=adLoginView;
	}//AdLoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	    if (ae.getSource() == adv.getHomeBtn()) {
	        adv.setVisible(false);
	        new MainView();
	    } else if (ae.getSource() == adv.getLoginBtn() || ae.getSource() == adv.getIdField() || ae.getSource() == adv.getPwField()) {
	        adminLogin();
	       adv.getIdField().setText(""); 
	       adv.getPwField().setText(""); 
	    }//end if
	}//actionPerformed

	public void adminLogin() {
	    String id = adv.getIdField().getText();
	    String pass = String.valueOf(adv.getPwField().getPassword());
	    AdminDAO aDAO = AdminDAO.getInstance();
	    
	    try {
	        AdminVO aVO = aDAO.selectAdmin(id, pass); 

	        if (id.isEmpty() || pass.isEmpty()) {
	            JOptionPane.showMessageDialog(null,"아이디와 패스워드를 모두 입력해주세요");//아이디나 패스워드가 비었을때
	        } else if (aVO != null && id.equals(aVO.getAdminNum()) && pass.equals(aVO.getAdminPW())) {
	        	JOptionPane.showMessageDialog(null, "안녕하세요 관리자님");
	        	adv.dispose();
	            //adv.setVisible(false); // 관리자 로그인 화면 종료
	            new AdminTabView(); // 메인 화면 띄우기
	        } else if (aVO == null) {
	            JOptionPane.showMessageDialog(null, "아이디 또는 패스워드를 확인해주세요");//아이디와 패스워드가 다를때
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}//adminLogin

	@Override
	public void windowClosing(WindowEvent e) {
		adv.dispose();
	}//windowClosing
	
}//class
