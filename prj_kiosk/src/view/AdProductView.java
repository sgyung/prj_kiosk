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

import evt.AdProductEvt;
import vo.ProductVO;

@SuppressWarnings("serial")
public class AdProductView extends JPanel {
	
	//ProductVO
	private ProductVO currentProduct;
	
	//Panel
	private JPanel productJp;
	
	//Table
	private JTable productInfo;
	private DefaultTableModel productInfoTm ;
	
	//Button
	private JButton deleteBtn; 
	private JButton newBtn; 
	private JButton changeBtn ; 
	private JButton photoBtn; 
	
	//Field
	private JTextField codeJtf;
	private JTextField nameJtf;
	private JTextField priceJtf; 
	
	//Label
	private JLabel productInfoJlb;
	
	private JLabel photoJlb;
	
	private JLabel codeJlb; 
	private JLabel typeJlb; 
	private JLabel nameJlb; 
	private JLabel priceJlb; 
	
	//ComboBox
	private DefaultComboBoxModel<String> productType;
	private JComboBox<String> jcbType;
	
	public AdProductView() {
		
		//Panel생성
		productJp=new JPanel();
		
		//Button생성
		deleteBtn=new JButton("삭제"); 
		 newBtn=new JButton("새상품"); 
		changeBtn=new JButton("상품수정") ; 
		 photoBtn=new JButton("사진등록"); 
		 
		 //TextField생성
		codeJtf=new JTextField("자동 생성");
		codeJtf.setEnabled(false);
		 nameJtf=new JTextField();
		 priceJtf= new JTextField(); 
			
		//Label
		 productInfoJlb=new JLabel("상품관리");
			
		 photoJlb=new JLabel("사진");
			
		 codeJlb= new JLabel("상품코드"); 
		 typeJlb= new JLabel("상품종류"); 
		 nameJlb= new JLabel("상품명"); 
		 priceJlb= new JLabel("가격"); 
			 
		 //ComboBox생성
		productType=new DefaultComboBoxModel<String>();
		 jcbType=new JComboBox<String>( productType);
			 
		//String, ComboBox
		String[]  productDetail = {"상품코드","상품종류", "상품명", "가격", "등록일", "삭제여부"};
		productInfoTm = new DefaultTableModel(null, productDetail);
		productInfo = new JTable(productInfoTm);
		JScrollPane productInfoJsp = new JScrollPane(productInfo);
		//Table 수정불가
		productInfo.setDefaultEditor(Object.class, null);
		
		//Event
		AdProductEvt evt = new AdProductEvt(this);
		productInfo.addMouseListener(evt);
		changeBtn.addActionListener(evt);
		deleteBtn.addActionListener(evt);
		photoBtn.addActionListener(evt);
		newBtn.addActionListener(evt);
		
        //컴포넌트 위치설정, 추가
		setLayout(null);
		setVisible(true);
		
		productJp.setBounds(0,0,900, 1000);
		
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

	public ProductVO getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(ProductVO currentProduct) {
		this.currentProduct = currentProduct;
	}
	
	

}//class
