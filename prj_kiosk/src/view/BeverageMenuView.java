package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import evt.MenuEvt;

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
		

		Panel.setLayout(new GridLayout(1, 3, 100, 100));
		
		MenuEvt me = new MenuEvt(mv);
		
		
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
		



	
}//class
