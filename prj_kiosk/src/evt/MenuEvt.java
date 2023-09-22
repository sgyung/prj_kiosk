package evt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import dao.ProductDAO;
import view.MainView;
import view.MenuView;
import view.OptionMenuView;
import view.PaymentView;
import vo.MenuListVO;
import vo.OrderDetailVO;
import vo.UseInventoryVO;

public class MenuEvt extends WindowAdapter implements ActionListener, ChangeListener, MouseListener {
   public final static String imgPath = "C:\\kiosk\\images\\products\\";
   
   private MenuView menuView;
   private List<JPanel> menuAllList;
   private List<MenuListVO> menuList; 
   
   public MenuEvt(MenuView menuView) {
      this.menuView = menuView;

   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      selectMenu(ae);
      if(ae.getSource() == menuView.getCancelBtn()) {
         cancelSelectMenu();
      }
      if(ae.getSource() == menuView.getPurchaseBtn()) {
         if( MenuView.menuSelectedList.size() == 0 ) {
            JOptionPane.showMessageDialog(menuView, "상품을 선택해주세요.");
            return;
         }//end if
         new PaymentView(menuView);
      }//end if
      if(ae.getSource() == menuView.getBeforeBtn()) {
         menuView.dispose();
         new MainView();
      }//end if
   }
   
   @Override
   public void mouseClicked(MouseEvent me) {
      

   }
   
   @Override
   public void stateChanged(ChangeEvent ce) {
      if( ce.getSource() == menuView.getMenuJtp() ) {
         setMenuList();
         spreadPanel();
      }
      
   }
   public void selectMenu(ActionEvent ae) {
//      if(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("cof")) {
         for(int i = 0; i < menuView.getCofMenu().getMenuButtonList().size(); i++) {
            if(ae.getSource() == menuView.getCofMenu().getMenuButtonList().get(i)) {
               menuView.getCofMenu().getCofNameLabel().setText(new String(menuView.getCofMenu().getMenuNameList().get(i).getText()));
               menuView.getCofMenu().getCofPriceLabel().setText(new String(menuView.getCofMenu().getMenuPriceList().get(i).getText()));
               new OptionMenuView(menuView);
            }
         }
//      } else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")) {
         for(int i = 0; i < menuView.getBevMenu().getMenuButtonList().size(); i++) {
            if(ae.getSource() == menuView.getBevMenu().getMenuButtonList().get(i)) {
               menuView.getBevMenu().getBevNameLabel().setText(new String(menuView.getBevMenu().getMenuNameList().get(i).getText()));
               menuView.getBevMenu().getBevPriceLabel().setText(new String(menuView.getBevMenu().getMenuPriceList().get(i).getText()));
               new OptionMenuView(menuView);
            }
         }
//      } else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")) {
         for(int i = 0; i < menuView.getDesMenu().getMenuButtonList().size(); i++) {
            if(ae.getSource() == menuView.getDesMenu().getMenuButtonList().get(i)) {
               menuView.getDesMenu().getDesNameLabel().setText(new String(menuView.getDesMenu().getMenuNameList().get(i).getText()));
               menuView.getDesMenu().getDesPriceLabel().setText(new String(menuView.getDesMenu().getMenuPriceList().get(i).getText()));
               new OptionMenuView(menuView);
            }
         }
//      }
      
   }
   
   // MenuListVO를 가져오는 메소드
   public void setMenuList() {
      ProductDAO pDAO = ProductDAO.getInstance();
      
      try {
         menuList = pDAO.selectMenuList(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         
          menuView.getCofMenu().getPanel().removeAll();
            menuView.getBevMenu().getPanel().removeAll();
            menuView.getDesMenu().getPanel().removeAll();
         
         
         if(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("cof")) {
            setCofPanel();
         } else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")) {
            setBevPanel();
         } else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")) {
            setDesPanel();
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   }
   
   // List<JPanel>에 있는 panel을 뽑아서 배치하는 메소드
   public void spreadPanel() {
      
      JPanel panel = null;
      
      if(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("cof")){
         panel = menuView.getCofMenu().getPanel();
      }else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("bev")){
         panel = menuView.getBevMenu().getPanel();
      }else if (menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase().equals("des")){
         panel = menuView.getDesMenu().getPanel();
      }

      for( JPanel menu : menuAllList ) {
         panel.add(menu);
      }
   }

   // List<Panel>에 panel추가
   public void setCofPanel() {
      menuAllList = new ArrayList<JPanel>();
      List<String> producutList = new ArrayList<String>();
      List<UseInventoryVO> uiList = new ArrayList<UseInventoryVO>();
      ProductDAO pDAO = ProductDAO.getInstance();
      
      JButton btn = menuView.getCofMenu().getCofBtn();
      JLabel name = menuView.getCofMenu().getCofNameLabel();
      JLabel price = menuView.getCofMenu().getCofPriceLabel();
      JPanel menuPanel = menuView.getCofMenu().getMenuPanel();
      String pdCode = "";
      
      int cofTotalQuantity = 0;
      int milkTotalQuantity = 0;
      boolean flag = false;
      
      try {
         producutList = pDAO.selectDeleteCheck(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         uiList = pDAO.selectUseInventory(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         for(int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i).toString());
            System.out.println(i);
            System.out.println(producutList.get(i));
            System.out.println(uiList.get(i).getPdCode());
            if("N".equals(producutList.get(i)) /*&& ("cof_" + (i+1)).equals(uiList.get(i).getPdCode())*/ ) {
                  for(int j = 0; j < uiList.size(); j++) {
                	  System.out.println(uiList.get(j).toString());
                     if(menuList.get(i).getPdName().toString().equals(uiList.get(j).getPdName()) && uiList.get(j).getiName().contains("원두")) {
                        cofTotalQuantity = uiList.get(j).getTotalQuantity();
                        System.out.println(cofTotalQuantity);
                     }else if(menuList.get(i).getPdName().toString().equals(uiList.get(j).getPdName()) && uiList.get(j).getiName().contains("우유")) {
                        milkTotalQuantity = uiList.get(j).getTotalQuantity();
                        System.out.println(milkTotalQuantity);
                        flag = true;
                     }
                  }
                  if((cofTotalQuantity > 20 && flag == false) || (cofTotalQuantity > 20 && flag == true && milkTotalQuantity > 20)) {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.addActionListener(this);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                       btn.setContentAreaFilled(false);
                     menuView.getCofMenu().getMenuButtonList().add(btn);
                     menuView.getCofMenu().getMenuNameList().add(name);
                     menuView.getCofMenu().getMenuPriceList().add(price);
                     menuView.getCofMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }else {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.addActionListener(this);
                     btn.setEnabled(false);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                     btn.setContentAreaFilled(false);
                     menuView.getCofMenu().getMenuButtonList().add(btn);
                     menuView.getCofMenu().getMenuNameList().add(name);
                     menuView.getCofMenu().getMenuPriceList().add(price);
                     menuView.getCofMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }
               }
            }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   
   public void setBevPanel() {
      menuAllList = new ArrayList<JPanel>();
      List<String> producutList = new ArrayList<String>();
      List<UseInventoryVO> uiList = new ArrayList<UseInventoryVO>();
      ProductDAO pDAO = ProductDAO.getInstance();
      
      JButton btn = menuView.getBevMenu().getBevBtn();
      JLabel name = menuView.getBevMenu().getBevNameLabel();
      JLabel price = menuView.getBevMenu().getBevPriceLabel();
      JPanel menuPanel = menuView.getBevMenu().getMenuPanel();
      String pdCode = "";
      int syrupTotalQuantity = 0;
      int milkTotalQuantity = 0;
      boolean flag = false;
      
      
      try {
         producutList = pDAO.selectDeleteCheck(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         uiList = pDAO.selectUseInventory(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         for(int i = 0; i < menuList.size(); i++) {
            if("N".equals(producutList.get(i))/* && ("bev_" + (i+1)).equals(uiList.get(i).getPdCode())*/) {
                  for(int j = 0; j < uiList.size(); j++) {
                     if(menuList.get(i).getPdName().toString().equals(uiList.get(j).getPdName()) && (uiList.get(j).getiName().contains("시럽"))||
                    		 uiList.get(j).getiName().contains("펄") || uiList.get(j).getiName().contains("파우더") || uiList.get(j).getiName().contains("사이다")
                    		 || uiList.get(j).getiName().contains("주스")) {
                        syrupTotalQuantity = uiList.get(j).getTotalQuantity();
                     }else if(menuList.get(i).getPdName().toString().equals(uiList.get(j).getPdName()) && uiList.get(j).getiName().contains("우유")) {
                        milkTotalQuantity = uiList.get(j).getTotalQuantity();
                        flag = true;
                     }
                  }
                  if((syrupTotalQuantity > 20 && flag == false) || (syrupTotalQuantity > 20 && flag == true && milkTotalQuantity > 20)) {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.addActionListener(this);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                       btn.setContentAreaFilled(false);
                     menuView.getBevMenu().getMenuButtonList().add(btn);
                     menuView.getBevMenu().getMenuNameList().add(name);
                     menuView.getBevMenu().getMenuPriceList().add(price);
                     menuView.getBevMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }else {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.addActionListener(this);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                       btn.setContentAreaFilled(false);
                       btn.setEnabled(false);
                     menuView.getBevMenu().getMenuButtonList().add(btn);
                     menuView.getBevMenu().getMenuNameList().add(name);
                     menuView.getBevMenu().getMenuPriceList().add(price);
                     menuView.getBevMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }
               }
            }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   }

   public void setDesPanel() {
      menuAllList = new ArrayList<JPanel>();
      List<String> producutList = new ArrayList<String>();
      List<UseInventoryVO> uiList = new ArrayList<UseInventoryVO>();
      ProductDAO pDAO = ProductDAO.getInstance();
      
      JButton btn = menuView.getDesMenu().getDesBtn();
      JLabel name = menuView.getDesMenu().getDesNameLabel();
      JLabel price = menuView.getDesMenu().getDesPriceLabel();
      JPanel menuPanel = menuView.getDesMenu().getMenuPanel();
      String pdCode = "";
      int cakeTotalQuantity = 0;
      
      try {
         producutList = pDAO.selectDeleteCheck(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         uiList = pDAO.selectUseInventory(menuView.getMenuJtp().getTitleAt(menuView.getMenuJtp().getSelectedIndex()).substring(0, 3).toLowerCase());
         for(int i = 0; i < menuList.size(); i++) {
       
            if("N".equals(producutList.get(i)) /*&& ("des_" + (i+1)).equals(uiList.get(i).getPdCode()) &&
                  menuList.get(i).getPdName().toString().equals(uiList.get(i).getiName())*/) {
                  cakeTotalQuantity = uiList.get(i).getTotalQuantity();
                  if(cakeTotalQuantity > 0) {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.addActionListener(this);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                       btn.setContentAreaFilled(false);
                     menuView.getDesMenu().getMenuButtonList().add(btn);
                     menuView.getDesMenu().getMenuNameList().add(name);
                     menuView.getDesMenu().getMenuPriceList().add(price);
                     menuView.getDesMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }else {
                     btn = new JButton(new ImageIcon(imgPath + menuList.get(i).getImgName().toString()));
                     name = new JLabel(menuList.get(i).getPdName().toString());
                     name.setHorizontalAlignment(JLabel.CENTER);
                     price = new JLabel(String.valueOf(menuList.get(i).getPdPrice()));
                     price.setHorizontalAlignment(JLabel.CENTER);
                     pdCode = menuList.get(i).getPdCode();
                     menuPanel = new JPanel();
                     menuPanel.setLayout(new BorderLayout());
                     menuPanel.setBackground(Color.white);
                     btn.setEnabled(false);
                     btn.addActionListener(this);
                     btn.setBorderPainted(false);
                     btn.setFocusPainted(false);
                       btn.setContentAreaFilled(false);
                     menuView.getDesMenu().getMenuButtonList().add(btn);
                     menuView.getDesMenu().getMenuNameList().add(name);
                     menuView.getDesMenu().getMenuPriceList().add(price);
                     menuView.getDesMenu().getPdCodeList().add(pdCode);
                     menuPanel.add(btn, BorderLayout.NORTH);
                     menuPanel.add(name, BorderLayout.CENTER);
                     menuPanel.add(price, BorderLayout.SOUTH);
                     menuAllList.add(menuPanel);
                  }
               }
            }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void cancelSelectMenu() {
      @SuppressWarnings("static-access")
	List<OrderDetailVO> list = menuView.menuSelectedList;
      int orderTotalPrice = 0;
      int selectedRow = menuView.getOrderDetail().getSelectedRow();
      
      if(selectedRow != -1) {
         menuView.getOrderDetaildtm().removeRow(selectedRow);
         list.remove(selectedRow);

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
            if(list.get(i).getoOptionName() != null && list.get(i).getoKnifeOption() == null ) {
               rowData[4] = list.get(i).getoOptionName();
            }else if(list.get(i).getoOptionName().isEmpty() && list.get(i).getoKnifeOption() != null) {
               rowData[4] = list.get(i).getoKnifeOption();
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
         }
         
         menuView.getTotalPriceLabel().setText(String.valueOf(orderTotalPrice));
      }
   }
   
   
   

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   
   

}