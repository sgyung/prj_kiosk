package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.InventoryDAO;
import view.AdInventoryView;
import vo.InventoryVO;

public class AdInventoryEvt extends MouseAdapter implements ActionListener{

	private AdInventoryView adInvenView;
	
	public AdInventoryEvt(AdInventoryView adInvenView) {
		this.adInvenView = adInvenView;
		setInvenInfo();
		setInventoryType();
	}//AdInvetoryEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adInvenView.getChangeBtn()) {
			modifyInventory();
		}//end if
		if(e.getSource() == adInvenView.getNewBtn()) {
			 addNewInventory();
		}//end if
	}//actionPerformed
	
	@Override
	public void mouseClicked(MouseEvent e) {
		selectionInventory();
	}//mouseClicked

	public void setInvenInfo() {
		adInvenView.getInventoryInfoTm().setRowCount(0);
		
		InventoryDAO dao = InventoryDAO.getInstance();
		List<InventoryVO> list = new ArrayList<InventoryVO>();
		
		try {
			list = dao.selectAllInventory();
			String[] rows = new String[5];
			for( InventoryVO vo : list ) {
				rows[0] = vo.getiCode();
				rows[1] = vo.getiTypeCode();
				rows[2] = vo.getiName();
				rows[3] = String.valueOf(vo.getiMount());
				rows[4] = String.valueOf(vo.getiInputDate());
				
				adInvenView.getInventoryInfoTm().addRow(rows);
				
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adInvenView, "재고 리스트를 불러오는데 실패하였습니다.");
		}
	}//setInvenInfo
	
	
	public void selectionInventory() {
		int row = adInvenView.getInventoryInfo().getSelectedRow();
		
		InventoryDAO dao = InventoryDAO.getInstance();
		
		String invenCode = String.valueOf(adInvenView.getInventoryInfoTm().getValueAt(row, 0));
		
		try {
			InventoryVO vo = dao.selectInventory(invenCode);
			adInvenView.setCurrentInvenVO(vo);
			
			adInvenView.getCodeJtf().setText(vo.getiCode());
			adInvenView.getQuantityType().setSelectedItem(vo.getiTypeCode());
			adInvenView.getNameJtf().setText(vo.getiName());
			adInvenView.getAmountJtf().setText(String.valueOf(vo.getiMount()));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adInvenView, "재고를 불러오는데 실패했습니다.");
		}//end catch
		
	}//selectionInventory
	
	public void setInventoryType() {
		List<String> list = new ArrayList<String>();
		InventoryDAO dao = InventoryDAO.getInstance();
		String defaultType = "재고 종류를 선택해주세요.";
		
		list.add(defaultType);
		
		try {
			list = dao.selectInventoryType();
			
			adInvenView.getQuantityType().addAll(list);
			adInvenView.getQuantityType().setSelectedItem(defaultType);
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adInvenView, "재고 종류를 불러오는데 실패했습니다.");
		} 
		
	}//setInventoryType
	
	public void modifyInventory() {
		InventoryDAO dao = InventoryDAO.getInstance();
		InventoryVO currentVO = adInvenView.getCurrentInvenVO();
		
		
		if( adInvenView.getNameJtf().getText().isEmpty()) {
			JOptionPane.showMessageDialog(adInvenView, "재고 이름을 입력해주세요.");
			return;
		}//end if
		
		try {
			currentVO.setiCode(adInvenView.getCodeJtf().getText());
			currentVO.setiTypeCode(String.valueOf(adInvenView.getQuantityType().getSelectedItem()));
			currentVO.setiName(adInvenView.getNameJtf().getText());
			currentVO.setiMount(Integer.parseInt(adInvenView.getAmountJtf().getText()));
			
			int flag = dao.updateInventory(currentVO);
			
			if( flag == 1 ) {
				JOptionPane.showMessageDialog(adInvenView, "재고를 수정하였습니다.");
				setInvenInfo();
				return;
			}//end if
		} catch(NumberFormatException nbfe ) {
			JOptionPane.showMessageDialog(adInvenView, "가격은 숫자만 입력 가능합니다.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adInvenView, "재고를 수정하는데 실패하였습니다.");
		}//end catch
		
	}//modifyInventory
	
	public void addNewInventory() {
		
		if( adInvenView.getCurrentInvenVO() == null ) {
			
			String invenType = String.valueOf(adInvenView.getQuantityType().getSelectedItem());
			String invenName = adInvenView.getNameJtf().getText();
			String invenAmount = adInvenView.getAmountJtf().getText();
			
			if( invenType.equals("재고 종류를 선택해주세요.")) {
				JOptionPane.showMessageDialog(adInvenView, "재고 종류를 선택해주세요.");
				return;
			}//end if
			if( invenName.isEmpty()) {
				JOptionPane.showMessageDialog(adInvenView, "재고 이름을 입력해주세요.");
				return;
			}//end if
			if( invenAmount.isEmpty()) {
				JOptionPane.showMessageDialog(adInvenView, "재고 수량을 입력해주세요.");
				return;
			}//end if
			
			InventoryDAO dao = InventoryDAO.getInstance();
			InventoryVO vo = new InventoryVO();
			
			try {
				vo.setiTypeCode(invenType);
				vo.setiName(invenName);
				vo.setiMount(Integer.parseInt(invenAmount));
				
				dao.insertInventory(vo);
				JOptionPane.showMessageDialog(adInvenView, "재고를 추가하였습니다.");
				resetData();
				setInvenInfo();
				
			} catch(NumberFormatException nfe ) {
				JOptionPane.showMessageDialog(adInvenView, "수량은 숫자만 입력 가능합니다.");
			}	catch (SQLException e) {
				JOptionPane.showMessageDialog(adInvenView, "재고를 추가하는데 실패하였습니다.");
			}//end catch
			
			return;
		}//end if
		if( adInvenView.getCurrentInvenVO() != null ) {
			adInvenView.setCurrentInvenVO(null);
			resetData();
			return;
		}//end if
		
	}//addNewInventory
	
	public void resetData() {
		adInvenView.getCodeJtf().setText("자동생성");
		adInvenView.getQuantityType().setSelectedItem("재고 종류를 선택해주세요.");
		adInvenView.getNameJtf().setText("");
		adInvenView.getAmountJtf().setText("");
	}//resetData
	
	
}//class
