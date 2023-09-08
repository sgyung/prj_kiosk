package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AdUserView extends JPanel{
	
	//Panel
	private JPanel userJp;
	
	//Table
	private JTable userInfo;
	private DefaultTableModel userInfoTm ;
	
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
		String[]  userDetail = {"회원번호","전화번호", "적립금", "가입일"};
		userInfoTm = new DefaultTableModel(null, userDetail);
		userInfo = new JTable(userInfoTm);
		JScrollPane userInfoJsp = new JScrollPane(userInfo); 
		
		//Button생성
		deleteBtn=new JButton("삭제"); 
		changeBtn=new JButton("수정"); 
		addBtn=new JButton("추가") ; 
		
		//Field생성
		numJtf=new JTextField();
		telJtf=new JTextField();
		pointJtf=new JTextField();
		dateJtf= new JTextField(); 
		
		//Label생성
		userInfoJlb=new JLabel("회원관리");
		
	    numJlb= new JLabel("회원번호"); 
		telJlb= new JLabel("전화번호"); 
		pointJlb= new JLabel("적립금"); 
		dateJlb= new JLabel("가입일"); 
		//컴포넌트 위치설정 ,추가
		setLayout(null);
		setVisible(true);
		
		userJp.setBounds(0,0,900,1000);
		
		//라벨 폰트설정
		userInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
		userInfoJlb.setBounds(45,35,200,35);
		userInfoJsp.setBounds(10,80,450,700);
		
		deleteBtn.setBounds(430,800,60,50);
		changeBtn.setBounds(500, 800, 90, 50);
		addBtn.setBounds(600, 800, 90, 50);
		
		numJlb.setBounds(490, 350, 50, 30);
		telJlb.setBounds(490, 400, 50, 30);
		pointJlb.setBounds(490, 450, 50, 30);
		dateJlb.setBounds(490, 500, 50, 30);
		
		numJtf.setBounds(550, 350, 140, 30);
		telJtf.setBounds(550, 400,140, 30);
		pointJtf.setBounds(550, 450, 140, 30);
		dateJtf.setBounds(550, 500, 140, 30);
		
		//테이블 폰트설정
		userInfo.setFont(new Font("맑은고딕",Font.BOLD,20));
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
		return userInfoTm;
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

}//class
