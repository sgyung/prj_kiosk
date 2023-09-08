package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MenuView extends JFrame {
	
//	static List<menuVO> menuSelectedList;  
	
	private JButton purchaseBtn;
	private JButton cancelBtn;
	private JButton beforeBtn;
	private JLabel totalPriceLabel;
	private JTable orderDetail;
	private DefaultTableModel orderDetaildtm;
	
	private CoffeeMenuView cofMenu;
	private BeverageMenuView bevMenu;
	private DesertMenuView desMenu;

	public MenuView() {
		super("menu");
		//Label
		JLabel orderDetailLabel = new JLabel("주문내역");
		JLabel priceLabel = new JLabel("결제금액");
		totalPriceLabel = new JLabel("0");
		//Button
		purchaseBtn = new JButton("결제하기"); 
		cancelBtn = new JButton("선택취소"); 
		purchaseBtn.setBackground(new Color( 255,195,14 ));
		purchaseBtn.setForeground(Color.black);
		cancelBtn.setBackground(new Color( 47,47,47));
		cancelBtn.setForeground(Color.white);
		beforeBtn = new JButton("이전");
		//TabPane
		JTabbedPane menuJtp = new JTabbedPane();
		//menuPanel
		cofMenu = new CoffeeMenuView(this);
		bevMenu = new BeverageMenuView();
		desMenu = new DesertMenuView();
		//ScrollPane
		JScrollPane cofMenuJsp = new JScrollPane(cofMenu);
		JScrollPane bevMenuJsp = new JScrollPane(bevMenu);
		JScrollPane desMenuJsp = new JScrollPane(desMenu);
		//Table
		String[]  orderDetailCols = {"번호","상품명", "수량", "ice/hot", "option", "size","금액"};
		orderDetaildtm = new DefaultTableModel(null, orderDetailCols);
		orderDetail = new JTable(orderDetaildtm);
		JScrollPane orderDetailJsp = new JScrollPane(orderDetail); 

		//Font
		menuJtp.setFont(new Font("맑은 고딕", Font.BOLD ,35));
		orderDetailLabel.setFont(new Font("맑은 고딕", Font.BOLD ,20));
		priceLabel.setFont(new Font("맑은 고딕", Font.BOLD ,30));
		totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD ,30));
		purchaseBtn.setFont(new Font("맑은 고딕", Font.BOLD ,30));
		
		//Event
		
		//Tab 위치Left
		menuJtp.setTabPlacement(JTabbedPane.LEFT);
		
		
		//Tab에 JPanel 추가
		menuJtp.add(cofMenuJsp, "Coffee");
		menuJtp.add(bevMenuJsp, "Beverage");
		menuJtp.add(desMenuJsp, "Desert");
		
		
		//컴포넌트 위치설정, 추가
		MainView.background.setBounds(0,0, 900, 1000);
		
		menuJtp.setBounds(28,20, 830, 650);
		purchaseBtn.setBounds(640,810,200,80);
		
		priceLabel.setBounds(640,700,300,35);
		totalPriceLabel.setBounds(640,750,200,35);
		
		orderDetailLabel.setBounds(205,670,300,35);
		orderDetailJsp.setBounds(205,710, 380, 180);
		
		cancelBtn.setBounds(485,890,100,30);
		beforeBtn.setBounds(60, 810, 120, 80);
		
		setLayout(null);
		add(menuJtp);
		add(purchaseBtn);
		add(cancelBtn);
		add(beforeBtn);
		
		add(priceLabel);
		add(totalPriceLabel);
		
		add(orderDetailLabel);
		add(orderDetailJsp);
		
		add(MainView.background);
		setBounds(500, 0, 900, 1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}// MenuView
	
	public static void main(String[] args) {
		new MenuView();
	}
	
	//getter
	public JButton getPurchaseBtn() {
		return purchaseBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public JButton getBeforeBtn() {
		return beforeBtn;
	}

	public JLabel getTotalPriceLabel() {
		return totalPriceLabel;
	}

	public JTable getOrderDetail() {
		return orderDetail;
	}

	public DefaultTableModel getOrderDetaildtm() {
		return orderDetaildtm;
	}

	public CoffeeMenuView getCofMenu() {
		return cofMenu;
	}

	public BeverageMenuView getBevMenu() {
		return bevMenu;
	}

	public DesertMenuView getDesMenu() {
		return desMenu;
	}
	
}// class
