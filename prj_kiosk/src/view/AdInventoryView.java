package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AdInventoryView extends JPanel {
	
	private JButton changeBtn=new JButton("제품수정"); 
	private JButton newBtn=new JButton("새 제품") ; 
	
	private JTextField codeJtf=new JTextField();
	private JTextField nameJtf=new JTextField();
	private JTextField priceJtf= new JTextField(); 
	
	private JLabel codeJlb= new JLabel("제품코드"); 
	private JLabel typeJlb= new JLabel("분류"); 
	private JLabel nameJlb= new JLabel("제품명"); 
	private JLabel priceJlb= new JLabel("수량"); 
	
	private JPanel inventoryJp=new JPanel();
	
	private DefaultComboBoxModel<String> quantityType=new DefaultComboBoxModel<String>();
 
	private JComboBox<String> jcbType=new JComboBox<String>( quantityType);
  
	
	public AdInventoryView() {
		
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
		
		setLayout(null);
		setVisible(true);
		
		inventoryJp.setBounds(0,0,900,1000);
		
		changeBtn.setBounds(550, 800, 90, 50);
		newBtn.setBounds(650, 800, 90, 50);
		
		codeJlb.setBounds(530, 350, 50, 30);
		typeJlb.setBounds(530, 400, 50, 30);
		nameJlb.setBounds(530, 450, 50, 30);
		priceJlb.setBounds(530, 500, 50, 30);
		
		codeJtf.setBounds(600, 350, 140, 30);
		
		jcbType.setBounds(600, 400,140, 30);
		
		nameJtf.setBounds(600, 450, 140, 30);
		priceJtf.setBounds(600, 500, 140, 30);
		
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public JButton getNewBtn() {
		return newBtn;
	}

	public void setNewBtn(JButton newBtn) {
		this.newBtn = newBtn;
	}

	public JTextField getCodeJtf() {
		return codeJtf;
	}

	public void setCodeJtf(JTextField codeJtf) {
		this.codeJtf = codeJtf;
	}

	public JTextField getNameJtf() {
		return nameJtf;
	}

	public void setNameJtf(JTextField nameJtf) {
		this.nameJtf = nameJtf;
	}

	public JTextField getPriceJtf() {
		return priceJtf;
	}

	public void setPriceJtf(JTextField priceJtf) {
		this.priceJtf = priceJtf;
	}

	public JLabel getCodeJlb() {
		return codeJlb;
	}

	public void setCodeJlb(JLabel codeJlb) {
		this.codeJlb = codeJlb;
	}

	public JLabel getTypeJlb() {
		return typeJlb;
	}

	public void setTypeJlb(JLabel typeJlb) {
		this.typeJlb = typeJlb;
	}

	public JLabel getNameJlb() {
		return nameJlb;
	}

	public void setNameJlb(JLabel nameJlb) {
		this.nameJlb = nameJlb;
	}

	public JLabel getPriceJlb() {
		return priceJlb;
	}

	public void setPriceJlb(JLabel priceJlb) {
		this.priceJlb = priceJlb;
	}

	public JPanel getInventoryJp() {
		return inventoryJp;
	}

	public void setInventoryJp(JPanel inventoryJp) {
		this.inventoryJp = inventoryJp;
	}

	public DefaultComboBoxModel<String> getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(DefaultComboBoxModel<String> quantityType) {
		this.quantityType = quantityType;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public void setJcbType(JComboBox<String> jcbType) {
		this.jcbType = jcbType;
	}

	public static void main(String[] args) {
		new AdInventoryView();
	}
}
