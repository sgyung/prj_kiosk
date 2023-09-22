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
import javax.swing.SwingConstants;
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
	private DefaultTableModel productInfoDtm ;
	
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
			
		 photoJlb=new JLabel();
			
		 codeJlb= new JLabel("상품코드"); 
		 typeJlb= new JLabel("상품종류"); 
		 nameJlb= new JLabel("상품명"); 
		 priceJlb= new JLabel("가격"); 
			 
		 //ComboBox생성
		productType=new DefaultComboBoxModel<String>();
		 jcbType=new JComboBox<String>( productType);
			 
		//String, ComboBox
		String[]  productDetail = {"상품코드","상품종류", "상품명", "가격", "등록일", "삭제여부"};
		productInfoDtm = new DefaultTableModel(null, productDetail);
		productInfo = new JTable(productInfoDtm);
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
		
		productInfoJlb.setFont(new Font("맑은 고딕",Font.BOLD,20));
		productInfoJlb.setBounds(45,35,200,35);
		productInfoJsp.setBounds(10,80,450,850);
		
		photoBtn.setBackground(new Color(255,195,14));
		photoBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		photoBtn.setBounds(490, 300, 200, 30);
		
		deleteBtn.setBackground(new Color(255,195,14));
		deleteBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
		deleteBtn.setBounds(580, 870, 100, 50);
		
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
		
		photoJlb.setBorder(new LineBorder(Color.red));
		photoJlb.setHorizontalAlignment(SwingConstants.CENTER);
		photoJlb.setBounds(490, 100, 200, 200);
		
		codeJtf.setBounds(550, 350, 140, 30);
		jcbType.setBounds(550, 400,140, 30);
		nameJtf.setBounds(550, 450, 140, 30);
		priceJtf.setBounds(550, 500, 140, 30);
		
		productJp.setBorder(new TitledBorder("상품관리"));
		productJp.setLayout(null);
		
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


	public DefaultTableModel getProductInfoDtm() {
		return productInfoDtm;
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
