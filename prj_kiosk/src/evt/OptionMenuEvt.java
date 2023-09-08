package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import view.MenuView;
import view.OptionMenuView;
import vo.MenuListVO;
import vo.OrderDetailVO;

public class OptionMenuEvt extends WindowAdapter implements ActionListener {
	private MenuView menuView;
	private OptionMenuView optionMenuView;
	
	public OptionMenuEvt(MenuView menuView, OptionMenuView optionMenuView ) {
		this.menuView = menuView;
		this.optionMenuView = optionMenuView;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == optionMenuView.getPlusBtn()) {
			addMenu();			
		}else if(ae.getSource() == optionMenuView.getMinusBtn()) {
			cancleMenu();
		}
	}
	
	public void addMenu() {
		OrderDetailVO odVO = new OrderDetailVO();
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
			quantity++;
			odVO.setoQuantity(quantity);
			optionMenuView.getQuantityTextField().setText(String.valueOf(odVO.getoQuantity()));
			System.out.println("추가");
	}
	
	public void cancleMenu() {
		OrderDetailVO odVO = new OrderDetailVO();
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
		if(quantity > 1){
			quantity--;
			odVO.setoQuantity(quantity);
			optionMenuView.getQuantityTextField().setText(String.valueOf(odVO.getoQuantity()));
			System.out.println("취소");
		}else if(quantity == 1) {
			JOptionPane.showMessageDialog(null, "최소 주문 개수는 1개 입니다.");
		}
	}
	
	public void setTotal() {
		MenuListVO mlVO = new MenuListVO();
		int menuPrice = mlVO.getPdPrice();
		int sizePrice = 0;
		int optionPrice = 0;
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
		int totalPrice = 0;
		
		if(optionMenuView.getExtraBtn().isSelected()) {
			sizePrice = 500;
			return;
		}
		if(optionMenuView.getAddShotBtn().isSelected()) {
			optionPrice = 500;
			return;
		}
		if(optionMenuView.getAddWhippingBtn().isSelected()) {
			optionPrice = 1000;
			return;
		}
		if(optionMenuView.getAddSyrupBtn().isSelected()) {
			optionPrice = 500;
			return;
		}
		
		totalPrice = (menuPrice*quantity) + sizePrice + optionPrice;
		
		optionMenuView.getTotalPriceLabel().setText(String.valueOf(totalPrice));
		
	}
	
	public void setMenu() {
		MenuListVO mlVO = new MenuListVO();
		
	}
	
}
