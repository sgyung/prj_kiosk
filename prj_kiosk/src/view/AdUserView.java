package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import evt.AdUserEvt;
import vo.UserVO;

@SuppressWarnings("serial")
public class AdUserView extends JPanel{
	
	//UserVO
	private UserVO currentUser;
	
	//Panel
	private JPanel userJp;
	
	//Table
	private JTable userInfo;
	private DefaultTableModel userInfoDtm ;
	
	//Button
	private JButton deleteBtn; 
	private JButton changeBtn; 
	private JButton addBtn; 
	
	//Field
	private JTextField numJtf;
	private JTextField telJtf;
	private JTextField pointJtf;
	private JTextField dateJtf; 
	
	//Label
	private JLabel userInfoJlb;
	
	private JLabel numJlb; 
	private JLabel telJlb; 
	private JLabel pointJlb; 
	private JLabel dateJlb; 
	
	public AdUserView() {
		
		//Panel생성
		userJp=new JPanel();
		
		//String, Scrollpane
		String[]  userDetail = {"전화번호", "적립금", "탈퇴여부", "가입일"};
		userInfoDtm = new DefaultTableModel(null, userDetail);
		userInfo = new JTable(userInfoDtm);
		userInfo.setDefaultEditor(Object.class, null);
		JScrollPane userInfoJsp = new JScrollPane(userInfo); 
		
		//Button생성
		deleteBtn=new JButton("삭제"); 
		changeBtn=new JButton("수정"); 
		addBtn=new JButton("회원 추가") ; 
		
		//Field생성
		numJtf=new JTextField();
		numJtf.setEnabled(false);
		telJtf=new JTextField();
		telJtf.setEnabled(false);
		pointJtf=new JTextField();
		dateJtf= new JTextField(); 
		dateJtf.setEnabled(false);
		
		//Label생성
		userInfoJlb=new JLabel("회원관리");
		
	    numJlb= new JLabel("삭제여부"); 
		telJlb= new JLabel("전화번호"); 
		pointJlb= new JLabel("적립금"); 
		dateJlb= new JLabel("가입일"); 
		
		//Event
		AdUserEvt userEvt = new AdUserEvt(this);
		userInfo.addMouseListener(userEvt);
		addBtn.addActionListener(userEvt);
		changeBtn.addActionListener(userEvt);
		deleteBtn.addActionListener(userEvt);
		
		
		//컴포넌트 위치설정 ,추가
		setLayout(null);
		setVisible(true);
		
		userJp.setBounds(0,0,900,1000);
		
		//라벨 폰트설정
		userInfoJlb.setBounds(45,35,200,35);
		userInfoJsp.setBounds(10,80,450,850);
		
		deleteBtn.setBackground(new Color(255,195,14));
		deleteBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		deleteBtn.setBounds(470, 800, 100, 50);
		
		changeBtn.setBackground(new Color(255,195,14));
		changeBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		changeBtn.setBounds(580, 800, 100, 50);
		
		addBtn.setBackground(new Color(255,195,14));
		addBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		addBtn.setBounds(580, 870, 100, 50);
		
		numJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		numJlb.setBounds(470, 350, 70, 30);
		
		telJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		telJlb.setBounds(470, 400, 70, 30);
		
		pointJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		pointJlb.setBounds(470, 450, 70, 30);
		
		dateJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		dateJlb.setBounds(470, 500, 70, 30);
		
		numJtf.setBounds(550, 350, 140, 30);
		telJtf.setBounds(550, 400,140, 30);
		pointJtf.setBounds(550, 450, 140, 30);
		dateJtf.setBounds(550, 500, 140, 30);
		
		//테이블 폰트설정
		userInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
//		userInfo.setFont(new Font("맑은고딕",Font.BOLD,20));
		userJp.add(userInfoJlb);
		userJp.add(userInfoJsp);
		
		userJp.setBorder(new TitledBorder("회원관리"));
		userJp.setLayout(null);
		
		userJp.add(numJlb);
		userJp.add(numJtf);
		
		userJp.add(telJlb);
		userJp.add(telJtf);
		
		userJp.add(pointJlb);
		userJp.add(pointJtf);
		
		userJp.add(dateJlb);
		userJp.add(dateJtf);
		
		userJp.add(deleteBtn);
		userJp.add(changeBtn);
		userJp.add(addBtn);
		
		add(userJp);
		
	}//AdUserView

	//Getter
	public JLabel getUserInfoJlb() {
		return userInfoJlb;
	}

	public JTable getUserInfo() {
		return userInfo;
	}

	public DefaultTableModel getUserInfoTm() {
		return userInfoDtm;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public JTextField getNumJtf() {
		return numJtf;
	}

	public JTextField getTelJtf() {
		return telJtf;
	}

	public JTextField getPointJtf() {
		return pointJtf;
	}

	public JTextField getDateJtf() {
		return dateJtf;
	}

	public JLabel getNumJlb() {
		return numJlb;
	}

	public JLabel getTelJlb() {
		return telJlb;
	}

	public JLabel getPointJlb() {
		return pointJlb;
	}

	public JLabel getDateJlb() {
		return dateJlb;
	}

	public JPanel getUserJp() {
		return userJp;
	}

	public UserVO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserVO currentUser) {
		this.currentUser = currentUser;
	}
	

}//class
