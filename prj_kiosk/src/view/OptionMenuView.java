package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import evt.OptionMenuEvt;

@SuppressWarnings("serial")
public class OptionMenuView extends JDialog{
	
//	private MenuView menuView;
	
	private JButton selectionBtn;
	private JButton cancel;
	private JToggleButton iceBtn;
	private JToggleButton hotBtn;
	private JToggleButton regularBtn;
	private  JToggleButton extraBtn;
	private JToggleButton addShotBtn;
	private JToggleButton addWhippingBtn;
	private JToggleButton addSyrupBtn;
	private JButton minusBtn;
	private JButton plusBtn;
	private JLabel productImageLabel;
	private JLabel productName;
	private JLabel totalPriceLabel;
	private JTextField quantityTextField;
	
	 public OptionMenuView(MenuView menuView) {
		 super(menuView, "option", true);
//		 this.menuView = menuView;
		 
		 //배경
		 ImageIcon backImg = new ImageIcon("src/images/option_background.png");
		 JLabel background = new JLabel(backImg);

		 //Button
		 selectionBtn = new JButton("선택완료");
		  cancel = new JButton("취소");
		 
		 ButtonGroup iceHotBtnGroup = new ButtonGroup();
		 iceBtn = new JToggleButton("ICE");
		 hotBtn = new JToggleButton("HOT");
		 iceHotBtnGroup.add(iceBtn);
		 iceHotBtnGroup.add(hotBtn);
		 
		 ButtonGroup sizeBtnGroup = new ButtonGroup();
		 regularBtn = new JToggleButton("Regular");
		 regularBtn.setSelected(true);
		 extraBtn = new JToggleButton("Extra");
		 sizeBtnGroup.add(regularBtn);
		 sizeBtnGroup.add(extraBtn);
		 
		 addShotBtn = new JToggleButton("<HTML>샷추가<br> +500원</HTML>");
		 addWhippingBtn = new JToggleButton("<HTML>휘핑추가<br>+1000원</HTML>");
		 addSyrupBtn = new JToggleButton("<HTML>시럽추가<br>+500원</HTML>");
		 
		 minusBtn = new JButton("-");
		 plusBtn = new JButton("+");
		 //Label
		 JPanel productImg = new JPanel();
		 productImageLabel = new JLabel("img");
		 productImg.setBorder(new LineBorder(Color.BLACK));
		 productImg.add(productImageLabel);
		 JLabel productNameLabel = new JLabel("상품이름");
		 productName = new JLabel(); 
		 JLabel priceLabel = new JLabel("금액");
		 totalPriceLabel = new JLabel("10000");
		 JLabel sizeLabel = new JLabel("사이즈");
		 JLabel optionLabel = new JLabel("옵션");
		 //TextField
		 quantityTextField = new JTextField("1");
		 quantityTextField.setHorizontalAlignment(SwingConstants.CENTER);
		 //Separator
		 JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		 JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
		 JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
		 
		 //Font
		 Font largeFont = new Font("맑은 고딕", Font.BOLD, 30);
		 Font smallFont = new Font("맑은 고딕", Font.BOLD, 20);
		 productNameLabel.setFont(largeFont);
		 productName.setFont(largeFont);
		 minusBtn.setFont(largeFont);
		 plusBtn.setFont(largeFont);
		 priceLabel.setFont(largeFont);
		 totalPriceLabel.setFont(largeFont);
		 iceBtn.setFont(smallFont);
		 hotBtn.setFont(smallFont);
		 sizeLabel.setFont(largeFont);
		 extraBtn.setFont(smallFont);
		 regularBtn.setFont(smallFont);
		 optionLabel.setFont(largeFont);
		 addShotBtn.setFont(smallFont);
		 addWhippingBtn.setFont(smallFont);
		 addSyrupBtn.setFont(smallFont);
		 selectionBtn.setFont(smallFont);
		 cancel.setFont(smallFont);
		 
		 //컴포넌트 위치설정, 추가
		 background.setBounds(0,0,800,800);
		 
		 productImg.setBounds(100,50, 200,200);
		 productNameLabel.setBounds(350, 50, 200, 50);
		 productName.setBounds(350,100,200,50);
		 
		 minusBtn.setBounds(350, 210, 60, 40);
		 quantityTextField.setBounds(420, 210, 60, 40);
		 plusBtn.setBounds(490, 210, 60, 40);
		 priceLabel.setBounds(600, 150, 100, 50);
		 totalPriceLabel.setBounds(600, 200, 100, 50);
		 
		 separator1.setBounds(70,260,650,100);
		 iceBtn.setBounds(70,270,300,40);
		 hotBtn.setBounds(420,270,300,40);
		 separator2.setBounds(70,320,650,100);
		 
		 sizeLabel.setBounds(100, 370, 100, 50);
		 regularBtn.setBounds(250, 370, 150, 50);
		 extraBtn.setBounds(450,370,150, 50);
		 
		 optionLabel.setBounds(100,470,100,50);
		 addShotBtn.setBounds(250, 470, 130, 100);
		 addSyrupBtn.setBounds(420, 470, 130, 100);
		 addWhippingBtn.setBounds(590, 470, 130, 100);
		 
		 separator3.setBounds(70, 600, 650, 100);
		 
		 cancel.setBounds(170, 630, 200, 80);
		 selectionBtn.setBounds(420, 630, 200, 80);
		 
		 //이벤트 추가
		 OptionMenuEvt omEvt = new OptionMenuEvt(menuView, this);
		 plusBtn.addActionListener(omEvt);
		 minusBtn.addActionListener(omEvt);
		 regularBtn.addActionListener(omEvt);
		 extraBtn.addActionListener(omEvt);
		 addShotBtn.addActionListener(omEvt);
		 addSyrupBtn.addActionListener(omEvt);
		 addWhippingBtn.addActionListener(omEvt);
		 cancel.addActionListener(omEvt);
		 selectionBtn.addActionListener(omEvt);
		 
		 setLayout(null);
		 add(productImg);
		 add(productNameLabel);
		 add(productName);
		 add(minusBtn);
		 add(quantityTextField);
		 add(plusBtn);
		 add(priceLabel);
		 add(totalPriceLabel);
		 
		 add(separator1);
		 add(iceBtn);
		 add(hotBtn);
		 add(separator2);
		 
		 add(sizeLabel);
		 add(regularBtn);
		 add(extraBtn);
		 
		 add(optionLabel);
		 add(addShotBtn);
		 add(addWhippingBtn);
		 add(addSyrupBtn);
		 
		 add(separator3);
		 
		 add(selectionBtn);
		 add(cancel);
		 
		 add(background);
		 
		 setBounds(550,100,800,800);
		 setVisible(true);
	 }//OptionMenuView

	 
	 //getter
	public JButton getSelectionBtn() {
		return selectionBtn;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JToggleButton getIceBtn() {
		return iceBtn;
	}

	public JToggleButton getHotBtn() {
		return hotBtn;
	}

	public JToggleButton getRegularBtn() {
		return regularBtn;
	}

	public JToggleButton getExtraBtn() {
		return extraBtn;
	}

	public JToggleButton getAddShotBtn() {
		return addShotBtn;
	}

	public JToggleButton getAddWhippingBtn() {
		return addWhippingBtn;
	}

	public JToggleButton getAddSyrupBtn() {
		return addSyrupBtn;
	}

	public JButton getMinusBtn() {
		return minusBtn;
	}

	public JButton getPlusBtn() {
		return plusBtn;
	}

	public JLabel getProductImageLabel() {
		return productImageLabel;
	}

	public JLabel getProductName() {
		return productName;
	}

	public JLabel getTotalPriceLabel() {
		return totalPriceLabel;
	}

	public JTextField getQuantityTextField() {
		return quantityTextField;
	}
	 
}//class
