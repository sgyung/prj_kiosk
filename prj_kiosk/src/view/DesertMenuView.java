package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import evt.MenuEvt;

@SuppressWarnings("serial")
public class DesertMenuView extends JPanel {
	private JPanel Panel;

	//수정사항
	private JButton desBtn;
	private JLabel desNameLabel;
	private JLabel desPriceLabel;
	private JPanel menuPanel;
	private List<JButton> menuButtonList;
	private List<JLabel> menuNameList;
	private List<JLabel> menuPriceList;
	
	public DesertMenuView(MenuView mv) {
		Panel = new JPanel();
		
		//수정사항
		desBtn = new JButton();
		desNameLabel = new JLabel();
		desPriceLabel = new JLabel();
		menuPanel = new JPanel();
		menuButtonList = new ArrayList<JButton>();
		menuNameList = new ArrayList<JLabel>();
		menuPriceList = new ArrayList<JLabel>();
		

		Panel.setLayout(new GridLayout(1, 3, 100, 100));
		
//		MenuEvt me = new MenuEvt(mv);
		
		
		add(Panel);
	}
	
	public JPanel getPanel() {
		return Panel;
	}

	public JButton getDesBtn() {
		return desBtn;
	}

	public JLabel getDesNameLabel() {
		return desNameLabel;
	}

	public JLabel getDesPriceLabel() {
		return desPriceLabel;
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
