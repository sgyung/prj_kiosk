package view;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CoffeeMenuView extends JPanel{
	
	private JButton btn9;
	
	public CoffeeMenuView() {
	
		//product Info
		btn9 = new JButton("아메리카노 그림");

		JLabel name1 = new JLabel("아아1");
		JLabel price1 = new JLabel("4000");
		

		
		//상품 예시
		JPanel productPanel = new JPanel();
		productPanel.setLayout(null);
		btn9.setBounds(0,0, 200, 200);
		name1.setBounds(0,210, 200,20);
		name1.setHorizontalAlignment(JLabel.CENTER);
		price1.setBounds(0, 250,200,20);
		price1.setHorizontalAlignment(JLabel.CENTER);
		productPanel.add(btn9);
		productPanel.add(name1);
		productPanel.add(price1);
		
		//font
		Font pdFont = new Font("맑은 고딕", Font.BOLD, 20);
		name1.setFont(pdFont);
		price1.setFont(pdFont);
		

		List<JPanel> list = new ArrayList<JPanel>();
		list.add(productPanel);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(3, 3 , 10, 10));
		for(int i=0; i<list.size(); i++) {
			list.get(i).setPreferredSize(new Dimension(200, 300));
			menuPanel.add(list.get(i));
		}
		
		add(menuPanel);
			
	}//CoffeeMenuView

	public JButton getBtn() {
		return btn9;
	}

	
}//class
