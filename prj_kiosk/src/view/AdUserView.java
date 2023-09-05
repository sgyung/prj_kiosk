package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AdUserView extends JPanel{
	
	private JButton deleteBtn=new JButton("삭제"); 
	private JButton changeBtn=new JButton("수정"); 
	private JButton addBtn=new JButton("추가") ; 
	
	private JTextField numJtf=new JTextField();
	private JTextField telJtf=new JTextField();
	private JTextField pointJtf=new JTextField();
	private JTextField dateJtf= new JTextField(); 
	
	private JLabel numJlb= new JLabel("회원번호"); 
	private JLabel telJlb= new JLabel("전화번호"); 
	private JLabel pointJlb= new JLabel("적립금"); 
	private JLabel dateJlb= new JLabel("가입일"); 
	
	private JPanel userJp=new JPanel();
	
	public AdUserView() {
		
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
		
		setLayout(null);
		setVisible(true);
		
		userJp.setBounds(0,0,900,1000);
		
		deleteBtn.setBounds(480,800,60,50);
		changeBtn.setBounds(550, 800, 90, 50);
		addBtn.setBounds(650, 800, 90, 50);
		
		numJlb.setBounds(530, 350, 50, 30);
		telJlb.setBounds(530, 400, 50, 30);
		pointJlb.setBounds(530, 450, 50, 30);
		dateJlb.setBounds(530, 500, 50, 30);
		
		numJtf.setBounds(600, 350, 140, 30);
		telJtf.setBounds(600, 400,140, 30);
		pointJtf.setBounds(600, 450, 140, 30);
		dateJtf.setBounds(600, 500, 140, 30);
		
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public JButton getNewBtn() {
		return addBtn;
	}

	public void setNewBtn(JButton newBtn) {
		this.addBtn = newBtn;
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public JTextField getNumJtf() {
		return numJtf;
	}

	public void setNumJtf(JTextField numJtf) {
		this.numJtf = numJtf;
	}

	public JTextField getTelJtf() {
		return telJtf;
	}

	public void setTelJtf(JTextField telJtf) {
		this.telJtf = telJtf;
	}

	public JTextField getPointJtf() {
		return pointJtf;
	}

	public void setPointJtf(JTextField pointJtf) {
		this.pointJtf = pointJtf;
	}

	public JTextField getDateJtf() {
		return dateJtf;
	}

	public void setDateJtf(JTextField dateJtf) {
		this.dateJtf = dateJtf;
	}

	public JLabel getNumJlb() {
		return numJlb;
	}

	public void setNumJlb(JLabel numJlb) {
		this.numJlb = numJlb;
	}

	public JLabel getTelJlb() {
		return telJlb;
	}

	public void setTelJlb(JLabel telJlb) {
		this.telJlb = telJlb;
	}

	public JLabel getPointJlb() {
		return pointJlb;
	}

	public void setPointJlb(JLabel pointJlb) {
		this.pointJlb = pointJlb;
	}

	public JLabel getDateJlb() {
		return dateJlb;
	}

	public void setDateJlb(JLabel dateJlb) {
		this.dateJlb = dateJlb;
	}

	public JPanel getUserJp() {
		return userJp;
	}

	public void setUserJp(JPanel userJp) {
		this.userJp = userJp;
	}

	public static void main(String[] args) {
		new AdUserView();
	}
}
