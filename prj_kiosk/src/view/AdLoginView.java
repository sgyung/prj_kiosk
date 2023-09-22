package view;

import java.awt.Color;
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
	private ImageIcon adminLogoImg ;
	private  JLabel adminLogo ;
	
	//Label
	private JLabel idJlb; 
	private JLabel pwJlb; 
	
	//Button
	private JButton homeBtn;
	private JButton loginBtn;
	
	//Field
	private JTextField idField;
	private JPasswordField pwField;
	
	public AdLoginView(){
		super("관리자 로그인");
		
		//Logo생성
		 adminLogoImg = new ImageIcon("C:\\kiosk\\images\\ad_logo.png");
		 adminLogo = new JLabel(adminLogoImg);
		 
		//Label생성
		idJlb=new JLabel("I D");
		pwJlb=new JLabel("PW");
		
		//Button생성
		homeBtn=new JButton("HOME");
		 loginBtn=new JButton("LogIn");
		
		//Field생성
		idField=new JTextField();
		pwField=new JPasswordField();
		
		//홈버튼누르면 메인으로 가는 이벤트
		homeBtn.addActionListener(new AdLoginEvt(this));
		
		//로그인 이벤트 : 성공 - 관리자 탭실행 , 실패 - 로그인실패 알림
		loginBtn.addActionListener(new AdLoginEvt(this));
		idField.addActionListener(new AdLoginEvt(this));
		pwField.addActionListener(new AdLoginEvt(this));
				
		//컴포넌트 위치설정, 추가
		
		setLayout(null);
	
		MainView.backImg.setDescription("dfdf");
		MainView.background.setBounds(0,0,900,1000);
		adminLogo.setBounds(310,300,270,230);
		
		//라벨 폰트설정		
		idJlb.setFont(new Font("맑은고딕",Font.BOLD,30));
		idJlb.setBounds(320,720,50,50);
		
		pwJlb.setFont(new Font("맑은고딕",Font.BOLD,30));
		pwJlb.setBounds(310,770,50,50);
		
		homeBtn.setBackground(new Color(255,195,14));//버튼색 설정 (노란색)
		homeBtn.setFont(new Font("맑은고딕",Font.BOLD,20));
		homeBtn.setBounds(50,800 , 100, 110);
		
		loginBtn.setBackground(new Color(255,195,14));
		loginBtn.setFont(new Font("맑은고딕",Font.BOLD,20));
		loginBtn.setBounds(600,720 , 100, 110);
		
		idField.setBounds(370,720 , 200, 50);
		pwField.setBounds(370,780 , 200, 50);
		
		add(idJlb);
		add(pwJlb);
		
		add(homeBtn);
		add(loginBtn);
		add(idField);
		add(pwField);
		
		add(adminLogo);
		add(MainView.background);
		
		setBounds(500, 0, 900, 1000);
		setVisible(true);
	}//AdLoginView

	//getter
	public ImageIcon getAdminLogoImg() {
		return adminLogoImg;
	}

	public JLabel getAdminLogo() {
		return adminLogo;
	} 

	public JLabel getIdJlb() {
		return idJlb;
	}

	public JLabel getPwJlb() {
		return pwJlb;
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

}//class
