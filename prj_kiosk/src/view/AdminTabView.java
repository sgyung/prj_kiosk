package view;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class AdminTabView extends JFrame {
	
  public AdminTabView() {
	  
	  //TabbedPane
	  JTabbedPane adminTab =new JTabbedPane();
	  
	  //Button
	  JButton homeBtn=new JButton("HOME");
	  
	  //View 생성
	  AdProductView adPdView=new AdProductView();
	  AdInventoryView adIVView=new AdInventoryView();
	  AdUserView adUSView=new AdUserView();
	  AdOrderStatusView adOSView=new AdOrderStatusView();
	  AdSalesView adSLView=new AdSalesView();
	  
	  //Tab에 View 추가
	  adminTab.add(adPdView,"상품관리");
	  adminTab.add(adIVView,"재고관리");
	  adminTab.add(adUSView,"회원관리");
	  adminTab.add(adOSView,"매장관리");
	  adminTab.add(adSLView,"매출관리");
	  
	  //탭폰트, 위치설정
	  adminTab.setFont(new Font("맑은 고딕",Font.BOLD,40));
	  adminTab.setTabPlacement(JTabbedPane.LEFT);
	  
	  //컴포넌트 위치설정, 추가
	  adminTab.setBounds(0,0,900,1000);
	  homeBtn.setBounds(10,700,170,200);
	  
	  add(adminTab);
	  add(homeBtn);
	  setLayout(null);
	  setVisible(true);
	  setSize(900,1000);
	  
  }//AdminTabView
	public static void main(String[] args) {
       new AdminTabView();
	}//main

}//class
