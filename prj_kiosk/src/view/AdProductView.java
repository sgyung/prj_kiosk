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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AdProductView extends JPanel {
	
	//Panel
	private JPanel productJp=new JPanel();
	
	//Table
	private JTable productInfo;
	private DefaultTableModel productInfoTm ;
	
	//Button
	private JButton deleteBtn=new JButton("삭제"); 
	private JButton newBtn=new JButton("새상품"); 
	private JButton changeBtn=new JButton("상품수정") ; 
	private JButton photoBtn=new JButton("사진등록"); 
	
	//Field
	private JTextField codeJtf=new JTextField();
	private JTextField nameJtf=new JTextField();
	private JTextField priceJtf= new JTextField(); 
	
	//Label
	private JLabel productInfoJlb=new JLabel("상품관리");
	
	private JLabel photoJlb=new JLabel("사진");
	
	private JLabel codeJlb= new JLabel("상품코드"); 
	private JLabel typeJlb= new JLabel("상품종류"); 
	private JLabel nameJlb= new JLabel("상품명"); 
	private JLabel priceJlb= new JLabel("가격"); 
	
	//ComboBox
	private DefaultComboBoxModel<String> productType=new DefaultComboBoxModel<String>();
	private JComboBox<String> jcbType=new JComboBox<String>( productType);
	
	public AdProductView() {
		
		//String, ComboBox
		String[]  productDetail = {"상품코드","상품종류", "상품명", "가격", "등록일", "삭제여부"};
		productInfoTm = new DefaultTableModel(null, productDetail);
		productInfo = new JTable(productInfoTm);
		JScrollPane productInfoJsp = new JScrollPane(productInfo); 
		
        //컴포넌트 위치설정, 추가
		setLayout(null);
		setVisible(true);
		
		productJp.setBounds(0,0,695,950);
		
		productInfoJlb.setBounds(45,35,200,35);
		productInfoJsp.setBounds(10,80,450,700);
		
		photoBtn.setBounds(490, 300, 200, 30);
		
		deleteBtn.setBounds(430,800,60,50);
		changeBtn.setBounds(500, 800, 90, 50);
		newBtn.setBounds(600, 800, 90, 50);
		
		codeJlb.setBounds(490, 350, 50, 30);
		typeJlb.setBounds(490, 400, 50, 30);
		nameJlb.setBounds(490, 450, 50, 30);
		priceJlb.setBounds(490, 500, 50, 30);
		
		photoJlb.setBorder(new LineBorder(Color.red));
		photoJlb.setBounds(490, 100, 200, 200);
		
		codeJtf.setBounds(550, 350, 140, 30);
		jcbType.setBounds(550, 400,140, 30);
		nameJtf.setBounds(550, 450, 140, 30);
		priceJtf.setBounds(550, 500, 140, 30);
		
		productJp.setBorder(new TitledBorder("상품관리"));
		productJp.setLayout(null);
		
		productInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
		productJp.add(productInfoJlb);
		productJp.add(productInfoJsp);
		
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
		
	}//AdProductView

	//Getter
	public JLabel getProductInfoJlb() {
		return productInfoJlb;
	}


	public JTable getProductInfo() {
		return productInfo;
	}


	public DefaultTableModel getProductInfoTm() {
		return productInfoTm;
	}


	public JButton getDeleteBtn() {
		return deleteBtn;
	}


	public JButton getNewBtn() {
		return newBtn;
	}


	public JButton getChangeBtn() {
		return changeBtn;
	}


	public JButton getPhotoBtn() {
		return photoBtn;
	}


	public JLabel getPhotoJlb() {
		return photoJlb;
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


	public JPanel getProductJp() {
		return productJp;
	}


	public DefaultComboBoxModel<String> getProductType() {
		return productType;
	}


	public JComboBox<String> getJcbType() {
		return jcbType;
	}


	public static void main(String[] args) {
		new AdProductView();
	}//main
}
//class