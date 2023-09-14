package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.ProductDAO;
import oracle.sql.ConcreteProxyUtil;
import view.MenuView;
import view.OptionMenuView;
import vo.MenuListVO;
import vo.OptionVO;
import vo.OrderDetailVO;

public class OptionMenuEvt extends WindowAdapter implements ActionListener {
	private MenuView menuView;
	private OptionMenuView optionMenuView;
	private List<MenuListVO> menuList;
	private int totalPrice;
	
	public OptionMenuEvt(MenuView menuView, OptionMenuView optionMenuView ) {
		this.menuView = menuView;
		this.optionMenuView = optionMenuView;
		setMenu();
		setTotal();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == optionMenuView.getPlusBtn()) {
			addQuantity();	
			setTotal();
		}else if(ae.getSource() == optionMenuView.getMinusBtn()) {
			minusQuqntity();
			setTotal();
		}else if (ae.getSource() == optionMenuView.getRegularBtn()) {
			setTotal();
		}else if(ae.getSource() == optionMenuView.getExtraBtn()) {
			setTotal();
		}else if(ae.getSource() == optionMenuView.getAddShotBtn()) {
			setTotal();
			System.out.println(optionMenuView.getAddShotBtn().getText());
		}else if(ae.getSource() == optionMenuView.getAddSyrupBtn()) {
			setTotal();
		}else if(ae.getSource() == optionMenuView.getAddWhippingBtn()) {
			setTotal();
		}else if(ae.getSource() == optionMenuView.getCancel()) {
			optionMenuView.dispose();
		}else if(ae.getSource() == optionMenuView.getSelectionBtn()) {
			completeSelect();
			optionMenuView.dispose();
		}
			
	}
	
	// 개수 증가 버튼 메소드
	public void addQuantity() {
		OrderDetailVO odVO = new OrderDetailVO();
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
			quantity++;
			odVO.setoQuantity(quantity);
			optionMenuView.getQuantityTextField().setText(String.valueOf(odVO.getoQuantity()));
	}
	
	// 개수 차감 버튼 메소드
	public void minusQuqntity() {
		OrderDetailVO odVO = new OrderDetailVO();
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
		if(quantity > 1){
			quantity--;
			odVO.setoQuantity(quantity);
			optionMenuView.getQuantityTextField().setText(String.valueOf(odVO.getoQuantity()));
		}else if(quantity == 1) {
			JOptionPane.showMessageDialog(null, "최소 주문 개수는 1개 입니다.");
		}
	}
	
	// 금액 출력 메소드
	public void setTotal() {
		int menuPrice = 0;
	
		if(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("cof")) {
			menuPrice = Integer.parseInt(menuView.getCofMenu().getCofPriceLabel().getText()); 
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")) {
			menuPrice = Integer.parseInt(menuView.getBevMenu().getBevPriceLabel().getText());
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")) {
			menuPrice = Integer.parseInt(menuView.getDesMenu().getDesPriceLabel().getText());
		}
		int sizePrice = 0;
		int optionPrice = 0;
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
		int tempSizePrice = 0;
		
		if(optionMenuView.getExtraBtn().isSelected()) {
			if(tempSizePrice == 0) {
				tempSizePrice = 500;
				sizePrice = tempSizePrice;
			}
		}else if(optionMenuView.getRegularBtn().isSelected()) {
			if(tempSizePrice == 500 ) {
				totalPrice -= 500; 
			}
		}
		if(optionMenuView.getAddShotBtn().isSelected()) {
			optionPrice += 500;
		}
		if(optionMenuView.getAddWhippingBtn().isSelected()) {
			optionPrice += 1000;
		}
		if(optionMenuView.getAddSyrupBtn().isSelected()) {
			optionPrice += 500;
		}
		
		totalPrice = (menuPrice*quantity) + sizePrice + optionPrice;
		
		optionMenuView.getTotalPriceLabel().setText(String.valueOf(totalPrice));
	
		
	}
	
	// 옵션창에 선택한 메뉴 정보 보여주는 메소드
	public void setMenu() {
		if(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("cof")) {
			optionMenuView.getProductName().setText(new String(menuView.getCofMenu().getCofNameLabel().getText())); 
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")) {
			optionMenuView.getProductName().setText(new String(menuView.getBevMenu().getBevNameLabel().getText()));
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")) {
			optionMenuView.getProductName().setText(new String(menuView.getDesMenu().getDesNameLabel().getText()));
		}
	
	}
	
	// 선택완료 메소드
	public void completeSelect() {
		OrderDetailVO odVO = new OrderDetailVO();
		OptionVO oVO = new OptionVO();
		String addShot = "";
		String addSyrup = "";
		String addWhipping = "";
		String tempOptionName = "";
		int shotPrice = 500;
		int syrupPrice = 500;
		int whippingPrice = 1000;
		
		int startIndex = 0;
		int endIndex = 0;
		
		odVO.setPdName(optionMenuView.getProductName().getText());
		odVO.setoQuantity(Integer.parseInt(optionMenuView.getQuantityTextField().getText()));
		if(optionMenuView.getIceBtn().isSelected()) {
			odVO.setoTempType("I");
		}else if(optionMenuView.getHotBtn().isSelected()) {
			odVO.setoTempType("H");
		}
		
		if(optionMenuView.getAddShotBtn().isSelected()) {
			startIndex = optionMenuView.getAddShotBtn().getText().indexOf("<HTML>") + "<HTML>".length();
			endIndex = optionMenuView.getAddShotBtn().getText().indexOf("<br>");
			if(startIndex != -1 && endIndex != -1) {
				addShot = optionMenuView.getAddShotBtn().getText().substring(startIndex, endIndex);
			}
			oVO.setOptionName(addShot);
			oVO.setOptionPrice(shotPrice);
		}
		if(optionMenuView.getAddSyrupBtn().isSelected()) {
			startIndex = optionMenuView.getAddSyrupBtn().getText().indexOf("<HTML>") + "<HTML>".length();
			endIndex = optionMenuView.getAddSyrupBtn().getText().indexOf("<br>");
			if(startIndex != -1 && endIndex != -1) {
				addSyrup = optionMenuView.getAddSyrupBtn().getText().substring(startIndex, endIndex);
			}
			oVO.setOptionName(addSyrup);
			oVO.setOptionPrice(syrupPrice);
		}
		if(optionMenuView.getAddWhippingBtn().isSelected()) {
			startIndex = optionMenuView.getAddWhippingBtn().getText().indexOf("<HTML>") + "<HTML>".length();
			endIndex = optionMenuView.getAddWhippingBtn().getText().indexOf("<br>");
			if(startIndex != -1 && endIndex != -1) {
				addWhipping = optionMenuView.getAddWhippingBtn().getText().substring(startIndex, endIndex);
			}
			oVO.setOptionName(addWhipping);
			oVO.setOptionPrice(whippingPrice);
		}
		
		tempOptionName = addShot + " " + addSyrup + " " + addWhipping;
		if(tempOptionName.startsWith(" ") || tempOptionName.endsWith(" ")) {
			tempOptionName = tempOptionName.trim();
			tempOptionName = tempOptionName.replaceAll(" ", ",");
		}
		if(!(tempOptionName.startsWith(" ") && tempOptionName.endsWith(" ")) && !tempOptionName.contains("  ")) {
			tempOptionName = tempOptionName.replaceAll(" ", ",");
		}
		if(tempOptionName.contains("  ")) {
			tempOptionName = tempOptionName.replaceAll("\\s+", ",");
		}
		
		odVO.setoOptionName(tempOptionName);
		
		if(optionMenuView.getRegularBtn().isSelected()) {
			odVO.setoSizeName("R");
			odVO.setoSizePrice(0);
		}else if(optionMenuView.getExtraBtn().isSelected()) {
			odVO.setoSizeName("E");
			odVO.setoSizePrice(500);
		}
		
		odVO.setPdPrice(totalPrice);
		
		List<OrderDetailVO> list = menuView.menuSelectedList;

		list.add(odVO);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		setMenuTable();	
	}
	
	public void setMenuTable() {
		List<OrderDetailVO> list = menuView.menuSelectedList;
		int orderTotalPrice = 0;

		menuView.getOrderDetaildtm().setRowCount(0);
		String[] rowData = null;
		
		for(int i = 0; i < list.size(); i++) {
			rowData = new String[7];
			
			rowData[0] = String.valueOf(i+1);
			rowData[1] = list.get(i).getPdName();
			rowData[2] = String.valueOf(list.get(i).getoQuantity());
			if(list.get(i).getPdName().equals("I")) {
				rowData[3] = "ice";
			}else if(list.get(i).getPdName().equals("H")) {
				rowData[3] = "hot";
			}
			rowData[4] = list.get(i).getoOptionName();
			if(list.get(i).getoSizeName().equals("R")) {
				rowData[5] = "Regular";
			}else if(list.get(i).getoSizeName().equals("E")) {
				rowData[5] = "Extra";
			}
			rowData[6] = String.valueOf(list.get(i).getPdPrice());	
			
			orderTotalPrice += list.get(i).getPdPrice();
			
			menuView.getOrderDetaildtm().addRow(rowData);
		}
		
		menuView.getTotalPriceLabel().setText(String.valueOf(orderTotalPrice));
		
	}
}
