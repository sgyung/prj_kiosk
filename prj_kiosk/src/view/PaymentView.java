package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import evt.PaymentEvt;
import vo.PaymentVO;
import vo.UserVO;

@SuppressWarnings("serial")
public class PaymentView extends JFrame {
	
	//UserVO 추가
	private UserVO currentUserVO;
	private PaymentVO currentPayVO;
	
	private JToggleButton cashBtn;
	private JToggleButton cardBtn;
	private JButton searchUserBtn;
	private JButton usePointBtn;
	//취소 버튼 추가
	private JButton cancelPointBtn; 
	private JTextField usePointTextField;
	private JLabel useAvailablePointLabel;
	
	private DefaultTableModel orderDetaildtm;
	private JTable orderDetail;
	
	private JLabel orderPrice;
	private JLabel discount;
	private JLabel purchasePrice;
	
	private JButton beforeBtn;
	private JButton paymentBtn;
	
	private MenuView menuView;
	
	
	public PaymentView(MenuView menuView) {
		
		this.menuView = menuView;
		// currentPayVO
		currentPayVO = new PaymentVO();
		
		//Button
		cashBtn = new JToggleButton("현금");
		cardBtn = new JToggleButton("카드");
		ButtonGroup payBtnGroup = new ButtonGroup();
		payBtnGroup.add(cashBtn);
		payBtnGroup.add(cardBtn);
		searchUserBtn = new JButton("적립/가입");
		usePointBtn = new JButton("사용");
		cancelPointBtn = new JButton("취소");
		
		beforeBtn = new JButton("이전");
		beforeBtn.setBackground(new Color( 255,195,14 ));
		beforeBtn.setForeground(Color.black);
		paymentBtn = new JButton("결제하기");
		paymentBtn.setBackground(new Color( 255,195,14 ));
		paymentBtn.setForeground(Color.black);
		
		//Label
		JLabel payMethodLabel = new JLabel("결제방법");
		JLabel searchUserLabel = new JLabel("회원조회");
		JLabel usePointLabel = new JLabel("적립금 사용");
		useAvailablePointLabel = new JLabel("적립금");
		
		JLabel orderPriceLabel = new JLabel("주문금액");
		orderPrice = new JLabel();
		JLabel discountLabel = new JLabel("할인금액");
		discount = new JLabel();
		JLabel purchasePriceLabel = new JLabel("결제금액");
		purchasePrice = new JLabel();		
		
		//TextField
		usePointTextField = new JTextField();
		
		//Separator
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		
		//Table
		String[]  orderDetailCols = {"번호","상품명", "수량", "ice/hot", "option", "size","금액"};
		orderDetaildtm = new DefaultTableModel(null, orderDetailCols);
		orderDetail = new JTable(orderDetaildtm);
		JScrollPane orderDetailJsp = new JScrollPane(orderDetail); 
		
		//Font
		Font smallFont = new Font("맑은 고딕", Font.BOLD, 20);
		Font largeFont = new Font("맑은 고딕", Font.BOLD, 30);
		payMethodLabel.setFont(smallFont);
		searchUserLabel.setFont(smallFont);
		usePointLabel.setFont(smallFont);
		useAvailablePointLabel.setFont(smallFont);
		orderPriceLabel.setFont(smallFont);
		orderPrice.setFont(largeFont);
		discountLabel.setFont(smallFont);
		discount.setFont(largeFont);
		purchasePriceLabel.setFont(smallFont);
		purchasePrice.setFont(largeFont);
		beforeBtn.setFont(largeFont);
		paymentBtn.setFont(largeFont);
		cashBtn.setFont(smallFont);
		cardBtn.setFont(smallFont);
		searchUserBtn.setFont(smallFont);
		
		//JPanel
		LineBorder line = new LineBorder(Color.black, 2);
		JPanel PricePanel = new JPanel();
		JPanel discountPanel = new JPanel();
		JPanel purchasePricelPanel = new JPanel();
		
		//Event
		PaymentEvt evt = new PaymentEvt(this, menuView);
		searchUserBtn.addActionListener(evt);
		usePointBtn.addActionListener(evt);
		cancelPointBtn.addActionListener(evt);
		beforeBtn.addActionListener(evt);
		paymentBtn.addActionListener(evt);
		
		PricePanel.setBorder(line);
		PricePanel.add(orderPrice);
		discountPanel.setBorder(line);
		discountPanel.add(discount);
		purchasePricelPanel.setBorder(line);
		purchasePricelPanel.add(purchasePrice);
		
		
		//컴포넌트 위치설정, 추가
		payMethodLabel.setBounds(50,50,100,100);
		cashBtn.setBounds(50,150,150,150);
		cardBtn.setBounds(250,150,150,150);
		searchUserLabel.setBounds(50,300,150,150);
		searchUserBtn.setBounds(50, 420,150,150);
		
		usePointLabel.setBounds(50, 600, 150, 100);
		usePointTextField.setBounds(50, 670, 250, 30 );
		usePointBtn.setBounds(310, 670, 90, 30);
		cancelPointBtn.setBounds(310, 705, 90, 30);
		useAvailablePointLabel.setBounds(50, 570, 200, 300);
		
		separator.setBounds(420, 50, 50, 750);
		
		orderDetailJsp.setBounds(440, 50, 425, 400);
		orderPriceLabel.setBounds(440, 450, 100,100 );
		PricePanel.setBounds(440, 520, 425, 50);
		discountLabel.setBounds(440, 550, 100, 100);
		discountPanel.setBounds(440, 620, 425, 50);
		purchasePriceLabel.setBounds(440, 650, 100, 100);
		purchasePricelPanel.setBounds(440, 730, 425, 70);
		
		beforeBtn.setBounds(50, 830, 200, 100);
		paymentBtn.setBounds(660, 830, 200, 100);
		
		setLayout(null);
		add(payMethodLabel);
		add(cashBtn);
		add(cardBtn);
		add(searchUserLabel);
		add(searchUserBtn);
		
		add(usePointLabel);
		add(usePointTextField);
		add(usePointBtn);
		add(cancelPointBtn);
		add(useAvailablePointLabel);
		
		add(separator);
		
		add(orderDetailJsp);
		add(orderPriceLabel);
		add(PricePanel);
		add(discountLabel);
		add(discountPanel);
		add(purchasePriceLabel);
		add(purchasePricelPanel);
		
		add(beforeBtn);
		add(paymentBtn);
		
		
		setBounds(500, 0, 900, 1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//PaymentView

	public JToggleButton getCashBtn() {
		return cashBtn;
	}

	public JToggleButton getCardBtn() {
		return cardBtn;
	}

	public JButton getSearchUserBtn() {
		return searchUserBtn;
	}

	public JButton getUsePointBtn() {
		return usePointBtn;
	}

	public JTextField getUsePointTextField() {
		return usePointTextField;
	}

	public JLabel getUseAvailablePointLabel() {
		return useAvailablePointLabel;
	}

	public DefaultTableModel getOrderDetaildtm() {
		return orderDetaildtm;
	}

	public JTable getOrderDetail() {
		return orderDetail;
	}

	public JLabel getOrderPrice() {
		return orderPrice;
	}

	public JLabel getDiscount() {
		return discount;
	}

	public JLabel getPurchasePrice() {
		return purchasePrice;
	}

	public JButton getBeforeBtn() {
		return beforeBtn;
	}

	public JButton getPaymentBtn() {
		return paymentBtn;
	}


	public UserVO getCurrentUserVO() {
		return currentUserVO;
	}

	public void setCurrentUserVO(UserVO currentUserVO) {
		this.currentUserVO = currentUserVO;
	}

	public void setPaymentBtn(JButton paymentBtn) {
		this.paymentBtn = paymentBtn;
	}

	public PaymentVO getCurrentPayVO() {
		return currentPayVO;
	}

	public JButton getCancelPointBtn() {
		return cancelPointBtn;
	}
	
}//class
