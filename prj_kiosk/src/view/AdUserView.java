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
	private JPanel userJp=new JPanel();
	
	//Table
	private JTable userInfo;
	private DefaultTableModel userInfoTm ;
	
	//Button
	private JButton deleteBtn=new JButton("삭제"); 
	private JButton changeBtn=new JButton("수정"); 
	private JButton addBtn=new JButton("추가") ; 
	
	//Field
	private JTextField numJtf=new JTextField();
	private JTextField telJtf=new JTextField();
	private JTextField pointJtf=new JTextField();
	private JTextField dateJtf= new JTextField(); 
	
	//Label
	private JLabel userInfoJlb=new JLabel("회원관리");
	
	private JLabel numJlb= new JLabel("회원번호"); 
	private JLabel telJlb= new JLabel("전화번호"); 
	private JLabel pointJlb= new JLabel("적립금"); 
	private JLabel dateJlb= new JLabel("가입일"); 
	
	public AdUserView() {
		
		//String, Scrollpane
		String[]  userDetail = {"회원번호","전화번호", "적립금", "가입일"};
		userInfoTm = new DefaultTableModel(null, userDetail);
		userInfo = new JTable(userInfoTm);
		JScrollPane userInfoJsp = new JScrollPane(userInfo); 
		
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
