package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import evt.AdLoginEvt;

@SuppressWarnings("serial")
public class AdLoginView extends JFrame  {

	//image
	private ImageIcon adminLogoImg = new ImageIcon("src/images/ad_logo.png");
	private  JLabel adminLogo = new JLabel(adminLogoImg);
	
	//Label
	private JLabel adminJlb=new JLabel("관리자"); 
	
	//Button
	private JButton homeBtn;
	private JButton loginBtn;
	
	//Field
	private JTextField idField;
	private JPasswordField pwField;
	
	public AdLoginView(){
		super("관리자 로그인");
		
		MainView.backImg.setDescription("dfdf");
		
		//Button
		homeBtn=new JButton("홈");
		 loginBtn=new JButton("로그인");
		
		//Field
		idField=new JTextField();
		 pwField=new JPasswordField();
		
		//홈버튼누르면 메인으로 가는 이벤트
		homeBtn.addActionListener(new AdLoginEvt(this));
		
		//컴포넌트 위치설정, 추가
		
		setLayout(null);
		setSize(900,1000);
		setVisible(true);
		
		
		MainView.background.setBounds(0,0,900,1000);
		adminLogo.setBounds(310,300,270,230);
		
		//라벨 폰트설정
		adminJlb.setFont(new Font("맑은고딕",Font.BOLD,40));
		adminJlb.setBounds(370,200,140,70);
		
		homeBtn.setBounds(80,720 , 100, 110);
		loginBtn.setBounds(600,720 , 100, 110);
		
		idField.setBounds(350,720 , 200, 50);
		pwField.setBounds(350,780 , 200, 50);
		
		add(adminJlb);
		
		add(homeBtn);
		add(loginBtn);
		add(idField);
		add(pwField);
		
		add(adminLogo);
		add(MainView.background);
		
	}//AdLoginView

	//getter
	public ImageIcon getAdminLogoImg() {
		return adminLogoImg;
	}

	public JLabel getAdminLogo() {
		return adminLogo;
	}

	public JLabel getAdminJlb() {
		return adminJlb;
	}

	public JButton getHomeBtn() {
		return homeBtn;
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}

	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPwField() {
		return pwField;
	}
	public static void main(String[] args) {
	new AdLoginView();
		
	}//main
}//class
