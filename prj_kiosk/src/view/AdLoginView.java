package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdLoginView extends JFrame {

	private JButton homeBtn;
	private JButton loginBtn;
	
	private JTextField idField;
	private JPasswordField pwField;
	
	public AdLoginView(){
		super("관리자 로그인");
		
		JButton homeBtn=new JButton("홈");
		JButton loginBtn=new JButton("로그인");
		
		JTextField idField=new JTextField();
		
		JPasswordField pwField=new JPasswordField();
		
		add(homeBtn);
		add(loginBtn);
		add(idField);
		add(pwField);
		
		setLayout(null);
		setSize(900,1000);
		setVisible(true);
		
		homeBtn.setBounds(50,380 , 100, 100);
		loginBtn.setBounds(550,350 , 100, 100);
		idField.setBounds(350,350 , 180, 30);
		pwField.setBounds(350,400 , 180, 30);
		
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(JButton loginBtn) {
		this.loginBtn = loginBtn;
	}

	public JButton getHomeBtn() {
		return homeBtn;
	}

	public void setHomeBtn(JButton homeBtn) {
		this.homeBtn = homeBtn;
	}

	public JTextField getIdField() {
		return idField;
	}

	public void setIdField(JTextField idField) {
		this.idField = idField;
	}

	public JPasswordField getPwField() {
		return pwField;
	}

	public void setPwField(JPasswordField pwField) {
		this.pwField = pwField;
	}
	public static void main(String[] args) {
		new AdLoginView();

	}//main
}//class
