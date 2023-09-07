package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import evt.MainEvt;

@SuppressWarnings("serial")
public class MainView extends JFrame {
   static public ImageIcon backImg = new ImageIcon("src/images/main_background.png");
   static public JLabel background = new JLabel(backImg);
   
   private JButton orderBtn;
   private JButton adminBtn;
   
   public MainView() {
      super("main");
      backImg.setDescription("dfdf");
      
      orderBtn = new JButton("주 문 하 기");
      adminBtn = new JButton("관리자");
      orderBtn.setBackground(new Color( 255,195,14 ));
      orderBtn.setForeground(Color.black);
      adminBtn.setBackground(new Color( 255,195,14 ));
      adminBtn.setForeground(Color.white);
      
      orderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 50));
      adminBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      
      ImageIcon mainLogoImg = new ImageIcon("src/images/logo.png");
      JLabel mainLogo = new JLabel(mainLogoImg);
      background.setBounds(0,0, 900, 1000);
      mainLogo.setBounds(150,10,600,600);
      
      //Event연결
      //orderBtn에 Evt를 연결
      orderBtn.addActionListener(new MainEvt(this));
      adminBtn.addActionListener(new MainEvt(this));
      
      //버튼 추가
      add(orderBtn);
      add(adminBtn);
      add(mainLogo);
      add(background);
      
      //사용자지정 Layout
      setLayout(null);
      orderBtn.setBounds(250, 650, 400 ,100 );
      adminBtn.setBounds(750, 900, 100, 30);
      
      setBounds(500, 0, 900, 1000);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }//MainView
   
   //getter
   public JButton getOrderBtn() {
      return orderBtn;
   }

   public JButton getAdminBtn() {
      return adminBtn;
   }
   
   public static void main(String[] args) {
      new MainView();
   }
   
}//class