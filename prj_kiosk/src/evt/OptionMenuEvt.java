package evt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.ProductDAO;
import oracle.sql.ConcreteProxyUtil;
import view.MenuView;
import view.OptionMenuView;
import vo.MenuListVO;
import vo.OptionVO;
import vo.OrderDetailVO;
import vo.UseInventoryVO;

public class OptionMenuEvt extends WindowAdapter implements ActionListener {
	public final static String imgPath = "C:/Users/USER/git/prj_kiosk/prj_kiosk/src/images/products/";
	
	private MenuView menuView;
	private OptionMenuView optionMenuView;
	private List<MenuListVO> menuList;
	private int totalPrice = 0;
//	private int tempCofQuqntity = 0;
//	private int tempSyruoQuqntity = 0;
//	private int tempExtraQuqntity = 0;
//	private int tempRegularQuqntity = 0;
//	private int tempWhippingQuqntity = 0;
//	private int tempMilkQuqntity = 0;
//	private int tempCakeQuqntity = 0;
	
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
//		List<UseInventoryVO> uiList = new ArrayList<UseInventoryVO>();
//		ProductDAO pDAO = ProductDAO.getInstance();
		int quantity = Integer.parseInt(optionMenuView.getQuantityTextField().getText());
//		int totalQuantity = 0;
		
//			uiList = pDAO.selectUseInventory(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
			quantity++;
//			for(int i = 0; i < uiList.size(); i++) {
//				if(optionMenuView.getProductName().getText().equals(uiList.get(i).getPdName()) && 
//						(uiList.get(i).getTotalQuantity() - (uiList.get(i).getUseQuantity()*quantity)) >=  0) {
//					
//				}else {
//					quantity--;
//					JOptionPane.showMessageDialog(optionMenuView, "재고 소진으로 관리자에게 문의하세요.");
//				}		
//			}
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
			optionMenuView.getProductImageLabel().setIcon(new ImageIcon(imgPath + menuView.getCofMenu().getCofNameLabel().getText().toString() + ".png"));
			optionMenuView.getAddKnifeBtn().setVisible(false);
			optionMenuView.getNoKnifeBtn().setVisible(false);
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")) {
			optionMenuView.getProductName().setText(new String(menuView.getBevMenu().getBevNameLabel().getText()));
			optionMenuView.getProductImageLabel().setIcon(new ImageIcon(imgPath + menuView.getBevMenu().getBevNameLabel().getText().toString() + ".png"));
			optionMenuView.getAddKnifeBtn().setVisible(false);
			optionMenuView.getNoKnifeBtn().setVisible(false);
		} else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")) {
			optionMenuView.getProductName().setText(new String(menuView.getDesMenu().getDesNameLabel().getText()));
			optionMenuView.getProductImageLabel().setIcon(new ImageIcon(imgPath + menuView.getDesMenu().getDesNameLabel().getText().toString() + ".png"));
			optionMenuView.getIceBtn().setVisible(false);
			optionMenuView.getHotBtn().setVisible(false);
			optionMenuView.getSeparator1().setVisible(false);
			optionMenuView.getSizeLabel().setVisible(false);
			optionMenuView.getRegularBtn().setVisible(false);
			optionMenuView.getExtraBtn().setVisible(false);
			optionMenuView.getAddShotBtn().setVisible(false);
			optionMenuView.getAddSyrupBtn().setVisible(false);
			optionMenuView.getAddWhippingBtn().setVisible(false);
			optionMenuView.getOptionLabel().setBounds(100,420,100,50);
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
		String addKnife = "";
		String noKnife = "";
		int shotPrice = 500;
		int syrupPrice = 500;
		int whippingPrice = 1000;
		
		int startIndex = 0;
		int endIndex = 0;
		
		ProductDAO pDAO = ProductDAO.getInstance();
		try {
			menuList = pDAO.selectMenuList(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
			for(int i = 0; i < menuList.size(); i++) {
				if(menuList.get(i).getPdName().equals(optionMenuView.getProductName().getText())){
					odVO.setPdCode(menuList.get(i).getPdCode());
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
					if(optionMenuView.getAddKnifeBtn().isSelected()) {
						startIndex = optionMenuView.getAddKnifeBtn().getText().indexOf("<HTML>") + "<HTML>".length();
						endIndex = optionMenuView.getAddKnifeBtn().getText().indexOf("</HTML>");
						if(startIndex != -1 && endIndex != -1) {
							addKnife = optionMenuView.getAddKnifeBtn().getText().substring(startIndex, endIndex);
						}
						odVO.setoKnifeOption(addKnife);
					}
					if(optionMenuView.getNoKnifeBtn().isSelected()) {
						startIndex = optionMenuView.getNoKnifeBtn().getText().indexOf("<HTML>") + "<HTML>".length();
						endIndex = optionMenuView.getNoKnifeBtn().getText().indexOf("</HTML>");
						if(startIndex != -1 && endIndex != -1) {
							noKnife = optionMenuView.getNoKnifeBtn().getText().substring(startIndex, endIndex);
						}
						odVO.setoKnifeOption(noKnife);
					}
					
					if(addShot != null || addSyrup != null || addWhipping != null) {
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
					}
					
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
					setMenuTable();	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
			if("I".equals(list.get(i).getoTempType())) {
				rowData[3] = "ice";
			}else if("H".equals(list.get(i).getoTempType())) {
				rowData[3] = "hot";
			}else {
				rowData[3] = "";
			}
			if(list.get(i).getoOptionName() != null && list.get(i).getoKnifeOption() == null) {
				rowData[4] = list.get(i).getoOptionName();
			}else if(list.get(i).getoOptionName().isEmpty() && list.get(i).getoKnifeOption() != null) {
				rowData[4] = list.get(i).getoKnifeOption();
			}else if(list.get(i).getoOptionName() == null && list.get(i).getoKnifeOption() == null){
				rowData[4] = "";
			}
			if("R".equals(list.get(i).getoSizeName())) {
				rowData[5] = "Regular";
			}else if("E".equals(list.get(i).getoSizeName())) {
				rowData[5] = "Extra";
			}else {
				rowData[5] = "";
			}
			rowData[6] = String.valueOf(list.get(i).getPdPrice());	
			
			orderTotalPrice += list.get(i).getPdPrice();
			
			menuView.getOrderDetaildtm().addRow(rowData);
			
			System.out.println(list.toString());
		}
		menuView.getTotalPriceLabel().setText(String.valueOf(orderTotalPrice));
		
	}
}
