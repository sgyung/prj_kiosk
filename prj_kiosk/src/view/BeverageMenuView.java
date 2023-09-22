package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BeverageMenuView extends JPanel{

	private JPanel Panel;

	//수정사항
	private JButton bevBtn;
	private JLabel bevNameLabel;
	private JLabel bevPriceLabel;
	private JPanel menuPanel;
	private List<JButton> menuButtonList;
	private List<JLabel> menuNameList;
	private List<JLabel> menuPriceList;
	private List<String> pdCodeList;
	
	public BeverageMenuView(MenuView mv) {
		Panel = new JPanel();
		
		//수정사항
		bevBtn = new JButton();
		bevNameLabel = new JLabel();
		bevPriceLabel = new JLabel();
		menuPanel = new JPanel();
		menuButtonList = new ArrayList<JButton>();
		menuNameList = new ArrayList<JLabel>();
		menuPriceList = new ArrayList<JLabel>();
		pdCodeList = new ArrayList<String>();
		

		Panel.setLayout(new GridLayout(0, 3, 100, 100));
		Panel.setBackground(Color.white);
		
//		MenuEvt me = new MenuEvt(mv);
		
		
		add(Panel);
	}//BeverageMenuView


		public JPanel getPanel() {
			return Panel;
		}


		public JButton getBevBtn() {
			return bevBtn;
		}


		public JLabel getBevNameLabel() {
			return bevNameLabel;
		}


		public JLabel getBevPriceLabel() {
			return bevPriceLabel;
		}


		public JPanel getMenuPanel() {
			return menuPanel;
		}


		public List<JButton> getMenuButtonList() {
			return menuButtonList;
		}


		public List<JLabel> getMenuNameList() {
			return menuNameList;
		}


		public List<JLabel> getMenuPriceList() {
			return menuPriceList;
		}


		public List<String> getPdCodeList() {
			return pdCodeList;
		}
		



	
}//class
