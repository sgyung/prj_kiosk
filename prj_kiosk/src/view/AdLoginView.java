package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class AdLoginView extends JFrame {

	//Label
	private JLabel adminJlb=new JLabel("관리자"); 
	private JLabel imageJlb=new JLabel("이미지"); 
	
	//Button
	private JButton homeBtn;
	private JButton loginBtn;
	
	//Field
	private JTextField idField;
	private JPasswordField pwField;
	
	public AdLoginView(){
		super("관리자 로그인");
		
		//Button
		JButton homeBtn=new JButton("홈");
		JButton loginBtn=new JButton("로그인");
		
		//Field
		JTextField idField=new JTextField();
		JPasswordField pwField=new JPasswordField();
		
		//컴포넌트 위치설정, 추가
		
		setLayout(null);
		setSize(900,1000);
		setVisible(true);
		
		//라벨 폰트설정
		adminJlb.setFont(new Font("맑은고딕",Font.BOLD,40));
		adminJlb.setBounds(370,200,140,70);
		
		imageJlb.setBorder(new LineBorder(Color.red));
        imageJlb.setBounds(330,300,230,230);		
		
		homeBtn.setBounds(80,720 , 100, 110);
		loginBtn.setBounds(600,720 , 100, 110);
		
		idField.setBounds(350,720 , 200, 50);
		pwField.setBounds(350,780 , 200, 50);
		
		add(adminJlb);
		add(imageJlb);
		
		add(homeBtn);
		add(loginBtn);
		add(idField);
		add(pwField);
		
	}//AdLoginView

	//getter
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
