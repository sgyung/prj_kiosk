package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AdOrderStatusView extends JPanel {
	
	//Panel
	private JPanel orderJp=new JPanel();
	
	//Label
	private JLabel orderInfoJlb=new JLabel("주문관리");
	
	//Table
	private JTable orderInfo;
	private DefaultTableModel orderInfoTm ;
	
	//Button
	private JButton checkBtn=new JButton("주문확인"); 
	private JButton rePrintBtn=new JButton("재출력") ; 
	
	public AdOrderStatusView() {
		
		//String, Scrollpane 설정
		String[]  salesDetail = {"주문번호","주문내역", "가격", "주문상태", "주문일시"};
		orderInfoTm = new DefaultTableModel(null, salesDetail);
		orderInfo = new JTable(orderInfoTm);
		JScrollPane orderInfoJsp = new JScrollPane(orderInfo); 
		
		//테이블 폰트설정
		orderInfo.setFont(new Font("맑은고딕",Font.BOLD,20));
		orderJp.add(orderInfoJlb);
		orderJp.add(orderInfoJsp);
		
		orderJp.setBorder(new TitledBorder("주문관리"));
		orderJp.setLayout(null);
		
		orderJp.add(checkBtn);
		orderJp.add(rePrintBtn);
		
		add(orderJp);
		
		setLayout(null);
		setVisible(true);
		
		orderJp.setBounds(0,0,900,1000);
		
		//라벨폰트설정
		orderInfoJlb.setFont(new Font("맑은고딕",Font.BOLD,20));
		orderInfoJlb.setBounds(45,35,200,35);
		orderInfoJsp.setBounds(10,80,450,700);
		
		checkBtn.setBounds(510, 800, 90, 50);
		rePrintBtn.setBounds(610, 800, 80, 50);
		
	}//AdOrderStatusView

	//Getter
	public JPanel getOrderJp() {
		return orderJp;
	}

	public JLabel getOrderInfoJlb() {
		return orderInfoJlb;
	}

	public JTable getOrderInfo() {
		return orderInfo;
	}

	public DefaultTableModel getOrderInfoTm() {
		return orderInfoTm;
	}

	public JButton getCheckBtn() {
		return checkBtn;
	}

	public JButton getRePrintBtn() {
		return rePrintBtn;
	}

	public static void main(String[] args) {
		new AdOrderStatusView();
	}
}
