package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AdOrderStatusView extends JPanel {
	
	private JButton checkBtn=new JButton("주문확인"); 
	private JButton rePrintBtn=new JButton("재출력") ; 
	
	private JPanel orderJp=new JPanel();
	
	public AdOrderStatusView() {
		
		orderJp.setBorder(new TitledBorder("주문관리"));
		orderJp.setLayout(null);
		
		orderJp.add(checkBtn);
		orderJp.add(rePrintBtn);
		
		add(orderJp);
		
		setLayout(null);
		setVisible(true);
		
		orderJp.setBounds(0,0,900,1000);
		
		checkBtn.setBounds(550, 800, 90, 50);
		rePrintBtn.setBounds(650, 800, 90, 50);
		
	}
	
	public JButton getCheckBtn() {
		return checkBtn;
	}


	public void setCheckBtn(JButton checkBtn) {
		this.checkBtn = checkBtn;
	}


	public JButton getRePrintBtn() {
		return rePrintBtn;
	}


	public void setRePrintBtn(JButton rePrintBtn) {
		this.rePrintBtn = rePrintBtn;
	}


	public JPanel getOrderJp() {
		return orderJp;
	}


	public void setOrderJp(JPanel orderJp) {
		this.orderJp = orderJp;
	}


	public static void main(String[] args) {
		new AdOrderStatusView();
	}
}
