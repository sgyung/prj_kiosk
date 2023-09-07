package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class AdminTabView extends JFrame {
	
  public AdminTabView() {
	  
	  //image
	   ImageIcon adminSmallLogoImg = new ImageIcon("src/images/ad_logo_small.png");
	   JLabel adminSmallLogo = new JLabel(adminSmallLogoImg);
	   adminSmallLogoImg.setDescription("dfdf");
	  
	   //배경색설정
	  JPanel backroundJp=new JPanel(); 
	  backroundJp.setBackground(new Color(255,195,14));
	  
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
	  setLayout(null);
	  setVisible(true);
	  setSize(900,1000);
	  
	  backroundJp.setBounds(0,0,900,1000);
	  adminSmallLogo.setBounds(15,550,150,150);
	  adminTab.setBounds(0,0,900,1000);
	  homeBtn.setBounds(7,750,170,200);
	  
	  add(adminTab);
	  add(homeBtn);
	  add(adminSmallLogo);
	  add(backroundJp);
	  
  }//AdminTabView
	public static void main(String[] args) {
       new AdminTabView();
	}//main

}//class
