package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AdProductView extends JPanel {
	
	private JButton deleteBtn=new JButton("삭제"); 
	private JButton newBtn=new JButton("새상품"); 
	private JButton changeBtn=new JButton("상품수정") ; 
	private JButton photoBtn=new JButton("사진등록"); 
	
	private JLabel photoJlb=new JLabel("사진");
	
	private JTextField codeJtf=new JTextField();
	private JTextField nameJtf=new JTextField();
	private JTextField priceJtf= new JTextField(); 
	
	private JLabel codeJlb= new JLabel("상품코드"); 
	private JLabel typeJlb= new JLabel("상품종류"); 
	private JLabel nameJlb= new JLabel("상품명"); 
	private JLabel priceJlb= new JLabel("가격"); 
	
	private JPanel productJp=new JPanel();
	
	private DefaultComboBoxModel<String> productType=new DefaultComboBoxModel<String>();
 
	private JComboBox<String> jcbType=new JComboBox<String>( productType);
  
	
	public AdProductView() {
		
		productJp.setBorder(new TitledBorder("상품관리"));
		productJp.setLayout(null);
		productJp.add(photoJlb);
		productJp.add(photoBtn);
		
		productJp.add(codeJlb);
		productJp.add(codeJtf);
		
		productJp.add(typeJlb);
		productJp.add(jcbType);
		
		productJp.add(nameJlb);
		productJp.add(nameJtf);
		
		productJp.add(priceJlb);
		productJp.add(priceJtf);
		
		productJp.add(deleteBtn);
		productJp.add(changeBtn);
		productJp.add(newBtn);
		
		add(productJp);
		
		setLayout(null);
		setVisible(true);
		
		productJp.setBounds(0,0,720,1000);
		
		photoBtn.setBounds(530, 300, 200, 30);
		
		deleteBtn.setBounds(480,800,60,50);
		changeBtn.setBounds(550, 800, 90, 50);
		newBtn.setBounds(650, 800, 90, 50);
		
		codeJlb.setBounds(530, 350, 50, 30);
		typeJlb.setBounds(530, 400, 50, 30);
		nameJlb.setBounds(530, 450, 50, 30);
		priceJlb.setBounds(530, 500, 50, 30);
		
		photoJlb.setBounds(600, 100, 200, 200);
		codeJtf.setBounds(600, 350, 140, 30);
		
		jcbType.setBounds(600, 400,140, 30);
		
		nameJtf.setBounds(600, 450, 140, 30);
		priceJtf.setBounds(600, 500, 140, 30);
		
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public JButton getNewBtn() {
		return newBtn;
	}

	public void setNewBtn(JButton newBtn) {
		this.newBtn = newBtn;
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public JButton getPhotoBtn() {
		return photoBtn;
	}

	public void setPhotoBtn(JButton photoBtn) {
		this.photoBtn = photoBtn;
	}

	public JLabel getPhotoJlb() {
		return photoJlb;
	}

	public void setPhotoJlb(JLabel photoJlb) {
		this.photoJlb = photoJlb;
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

	public JPanel getProductJp() {
		return productJp;
	}

	public void setProductJp(JPanel productJp) {
		this.productJp = productJp;
	}

	public DefaultComboBoxModel<String> getProductType() {
		return productType;
	}

	public void setProductType(DefaultComboBoxModel<String> productType) {
		this.productType = productType;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public void setJcbType(JComboBox<String> jcbType) {
		this.jcbType = jcbType;
	}

	public static void main(String[] args) {
		new AdProductView();
	}
}
