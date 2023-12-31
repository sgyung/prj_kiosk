package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import evt.AdInventoryEvt;
import vo.InventoryVO;

@SuppressWarnings("serial")
public class AdInventoryView extends JPanel {
	
	//InventoryVO
	private InventoryVO currentInvenVO;
	
	//Panel
	private JPanel inventoryJp;
	
	//Table
	private JTable inventoryInfo;
	private DefaultTableModel inventoryInfoTm ;
	
	//Button
	private JButton changeBtn;
	private JButton newBtn; 
	
	//Field
	private JTextField codeJtf;
	private JTextField nameJtf;
	private JTextField amountJtf; 
	
	//Label
	private JLabel inventoryInfoJlb;
	
	private JLabel codeJlb; 
	private JLabel typeJlb; 
	private JLabel nameJlb; 
	private JLabel priceJlb; 
	
	//ComboBox
	private DefaultComboBoxModel<String> quantityType;
	private JComboBox<String> jcbType;
  
	
	public AdInventoryView() {
		
		//Panel생성
		inventoryJp=new JPanel();
		
		//Button생성
		changeBtn=new JButton("제품수정"); 
		newBtn=new JButton("새 제품") ; 
		
		//TextField생성
		codeJtf=new JTextField("자동 생성");
		codeJtf.setEnabled(false);
		 nameJtf=new JTextField();
		 amountJtf= new JTextField(); 
		
		//Label생성
		 inventoryInfoJlb=new JLabel("재고관리");
		 codeJlb= new JLabel("제품코드"); 
		 typeJlb= new JLabel("분류"); 
		 nameJlb= new JLabel("제품명"); 
		 priceJlb= new JLabel("수량"); 
		 
		 //ComboBox생성
		 quantityType=new DefaultComboBoxModel<String>();
		 jcbType=new JComboBox<String>( quantityType);
		  
		//String, ScrollPane
		String[]  inventoryDetail = {"재고코드","분류", "제품명", "수량", "업데이트 날짜"};
		inventoryInfoTm = new DefaultTableModel(null, inventoryDetail);
		inventoryInfo = new JTable(inventoryInfoTm);
		JScrollPane inventoryInfoJsp = new JScrollPane(inventoryInfo);
		//Table 수정불가
		inventoryInfo.setDefaultEditor(Object.class, null);
		
		//Event
		AdInventoryEvt evt = new AdInventoryEvt(this);
		inventoryInfo.addMouseListener(evt);
		changeBtn.addActionListener(evt);
		newBtn.addActionListener(evt);
		
		//컴포넌트 위치설정, 추가
		setLayout(null);
		setVisible(true);
		
		inventoryJp.setBounds(0,0,900,1000);

		
		//라벨 폰트설정
		inventoryInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
		inventoryInfoJlb.setBounds(45,35,200,35);
		inventoryInfoJsp.setBounds(10,80,450,850);
		
		changeBtn.setBackground(new Color(255,195,14));
		changeBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		changeBtn.setBounds(470, 800, 100, 50);
		
		newBtn.setBackground(new Color(255,195,14));
		newBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		newBtn.setBounds(580, 800, 100, 50);
		
		codeJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		codeJlb.setBounds(470, 350, 70, 30);
		
		typeJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		typeJlb.setBounds(470, 400, 70, 30);
		
		nameJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		nameJlb.setBounds(470, 450, 70, 30);
		
		priceJlb.setFont(new Font("맑은 고딕",Font.BOLD,15));
		priceJlb.setBounds(470, 500, 70, 30);
		
		codeJtf.setBounds(550, 350, 140, 30);
		
		jcbType.setBounds(550, 400,140, 30);
		
		nameJtf.setBounds(550, 450, 140, 30);
		amountJtf.setBounds(550, 500, 140, 30);
		
		//테이블 폰트설정
		inventoryJp.add(inventoryInfoJlb);
		inventoryJp.add(inventoryInfoJsp);
		
		inventoryJp.setBorder(new TitledBorder("재고관리"));
		inventoryJp.setLayout(null);
		
		inventoryJp.add(codeJlb);
		inventoryJp.add(codeJtf);
		
		inventoryJp.add(typeJlb);
		inventoryJp.add(jcbType);
		
		inventoryJp.add(nameJlb);
		inventoryJp.add(nameJtf);
		
		inventoryJp.add(priceJlb);
		inventoryJp.add(amountJtf);
		
		inventoryJp.add(changeBtn);
		inventoryJp.add(newBtn);
		
		add(inventoryJp);
		
	}//AdInventoryView
	
	//Getter
	public JLabel getInventoryInfoJlb() {
		return inventoryInfoJlb;
	}

	public JTable getInventoryInfo() {
		return inventoryInfo;
	}

	public DefaultTableModel getInventoryInfoTm() {
		return inventoryInfoTm;
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public JButton getNewBtn() {
		return newBtn;
	}

	public JTextField getCodeJtf() {
		return codeJtf;
	}

	public JTextField getNameJtf() {
		return nameJtf;
	}

	public JTextField getAmountJtf() {
		return amountJtf;
	}

	public JLabel getCodeJlb() {
		return codeJlb;
	}

	public JLabel getTypeJlb() {
		return typeJlb;
	}

	public JLabel getNameJlb() {
		return nameJlb;
	}

	public JLabel getPriceJlb() {
		return priceJlb;
	}

	public JPanel getInventoryJp() {
		return inventoryJp;
	}

	public DefaultComboBoxModel<String> getQuantityType() {
		return quantityType;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public InventoryVO getCurrentInvenVO() {
		return currentInvenVO;
	}

	public void setCurrentInvenVO(InventoryVO currentInvenVO) {
		this.currentInvenVO = currentInvenVO;
	}

	public void setInventoryJp(JPanel inventoryJp) {
		this.inventoryJp = inventoryJp;
	}

	public void setInventoryInfo(JTable inventoryInfo) {
		this.inventoryInfo = inventoryInfo;
	}

	public void setInventoryInfoTm(DefaultTableModel inventoryInfoTm) {
		this.inventoryInfoTm = inventoryInfoTm;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public void setNewBtn(JButton newBtn) {
		this.newBtn = newBtn;
	}

	public void setCodeJtf(JTextField codeJtf) {
		this.codeJtf = codeJtf;
	}

	public void setNameJtf(JTextField nameJtf) {
		this.nameJtf = nameJtf;
	}

	public void setAmountJtf(JTextField amountJtf) {
		this.amountJtf = amountJtf;
	}

	public void setInventoryInfoJlb(JLabel inventoryInfoJlb) {
		this.inventoryInfoJlb = inventoryInfoJlb;
	}

	public void setCodeJlb(JLabel codeJlb) {
		this.codeJlb = codeJlb;
	}

	public void setTypeJlb(JLabel typeJlb) {
		this.typeJlb = typeJlb;
	}

	public void setNameJlb(JLabel nameJlb) {
		this.nameJlb = nameJlb;
	}

	public void setPriceJlb(JLabel priceJlb) {
		this.priceJlb = priceJlb;
	}

	public void setQuantityType(DefaultComboBoxModel<String> quantityType) {
		this.quantityType = quantityType;
	}

	public void setJcbType(JComboBox<String> jcbType) {
		this.jcbType = jcbType;
	}
	
	}//class
