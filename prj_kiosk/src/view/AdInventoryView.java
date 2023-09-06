package view;

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

@SuppressWarnings("serial")
public class AdInventoryView extends JPanel {
	
	//Panel
	private JPanel inventoryJp=new JPanel();
	
	//Table
	private JTable inventoryInfo;
	private DefaultTableModel inventoryInfoTm ;
	
	//Button
	private JButton changeBtn=new JButton("제품수정"); 
	private JButton newBtn=new JButton("새 제품") ; 
	
	//Field
	private JTextField codeJtf=new JTextField();
	private JTextField nameJtf=new JTextField();
	private JTextField priceJtf= new JTextField(); 
	
	//Label
	private JLabel inventoryInfoJlb=new JLabel("재고관리");
	
	private JLabel codeJlb= new JLabel("제품코드"); 
	private JLabel typeJlb= new JLabel("분류"); 
	private JLabel nameJlb= new JLabel("제품명"); 
	private JLabel priceJlb= new JLabel("수량"); 
	
	//ComboBox
	private DefaultComboBoxModel<String> quantityType=new DefaultComboBoxModel<String>();
	private JComboBox<String> jcbType=new JComboBox<String>( quantityType);
  
	
	public AdInventoryView() {
		
		//String, ScrollPane
		String[]  inventoryDetail = {"제품코드","분류", "제품명", "수량", "업데이트 날짜", "삭제여부"};
		inventoryInfoTm = new DefaultTableModel(null, inventoryDetail);
		inventoryInfo = new JTable(inventoryInfoTm);
		JScrollPane inventoryInfoJsp = new JScrollPane(inventoryInfo); 
		setLayout(null);
		setVisible(true);
		
		//컴포넌트 위치설정, 추가
		inventoryJp.setBounds(0,0,900,1000);
		
		//라벨 폰트설정
		inventoryInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
		inventoryInfoJlb.setBounds(45,35,200,35);
		inventoryInfoJsp.setBounds(10,80,450,700);
		
		changeBtn.setBounds(510, 800, 90, 50);
		newBtn.setBounds(610, 800, 80, 50);
		
		codeJlb.setBounds(490, 350, 50, 30);
		typeJlb.setBounds(490, 400, 50, 30);
		nameJlb.setBounds(490, 450, 50, 30);
		priceJlb.setBounds(490, 500, 50, 30);
		
		codeJtf.setBounds(550, 350, 140, 30);
		
		jcbType.setBounds(550, 400,140, 30);
		
		nameJtf.setBounds(550, 450, 140, 30);
		priceJtf.setBounds(550, 500, 140, 30);
		
		//테이블 폰트설정
		inventoryInfo.setFont(new Font("맑은고딕",Font.BOLD,20));
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
		inventoryJp.add(priceJtf);
		
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

	public JTextField getPriceJtf() {
		return priceJtf;
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

	public static void main(String[] args) {
		new AdInventoryView();
	}//main
}//class
