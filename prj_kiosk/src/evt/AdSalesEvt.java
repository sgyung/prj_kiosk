package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.SalesDAO;
import view.AdSalesView;
import vo.SalesConditionVO;
import vo.SalesDetailVO;

public class AdSalesEvt implements ActionListener, ItemListener{
	
	private AdSalesView adSaleView;
	
	public AdSalesEvt( AdSalesView adSaleView ) {
		this.adSaleView = adSaleView;
		setProducType();
	}//AdSalesView
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED ) {
			adSaleView.getProductType().removeAllElements();
			String type = String.valueOf(adSaleView.getNameType().getSelectedItem());
			setProductName(type);
		}//end if
	}//itemStateChanged
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adSaleView.getCheckBtn()) {
			getSalesDetail();
		}//end if
		if(e.getSource() ==  adSaleView.getDayCheckBtn()) {
			List<SalesDetailVO> list = getDailySalesDetail();
			addSalesDtm(list);
		}//end if
		if(e.getSource() == adSaleView.getMonthCheckBtn()) {
			String month = JOptionPane.showInputDialog("원하는 월을 입력해 주세요.");
			List<SalesDetailVO> list = getMonthSalesDetail(Integer.parseInt(month));
			
			if( list.isEmpty() ) {
				JOptionPane.showMessageDialog(adSaleView, "해당하는 월의 데이터가 없습니다.");
				return;
			}//end if
			
			addSalesDtm(list);
		}//end if
		
	}//actionPerformed

	public void getSalesDetail(){
		SalesDAO dao = SalesDAO.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SalesConditionVO saleConVO = new SalesConditionVO();
		
		String startDate = adSaleView.getStartJtf().getText();
		String endDate = adSaleView.getEndJtf().getText();
		String pdName = String.valueOf(adSaleView.getJcbType().getSelectedItem());
		String pdType = String.valueOf(adSaleView.getJcbName().getSelectedItem());
		Date startUtilDate = null;
		Date endUtilDate = null;
		
		if(  pdType.equals("전체") ) {
			pdType = "null";
		}//end if
		if(pdName.equals("상품종류를 선택해주세요") || pdName.equals("전체") ) {
			pdName = "null";
		}//end if
		
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
	
		//검색 조건
		try {
			saleConVO.setPdType(pdType);
			saleConVO.setPdName(pdName);
			
			//날짜 필드가 비어있지 않다면 Date타입으로 변환
			//변환중 날짜 형식의 문자열이 아니라면 Exception
			if( !startDate.isEmpty() || !endDate.isEmpty()) {
				startUtilDate = sdf.parse(startDate);
				endUtilDate = sdf.parse(endDate);
				
				if(startUtilDate.compareTo(endUtilDate) > 0  ) {
					JOptionPane.showMessageDialog(adSaleView, "종료일은 시작일보다 더 작을 수 없습니다.");
					return;
				}//end if	
			}//end if
			
			//모든 입력값이 입력되지 않았을 경우 천체 데이터 출력	
			if( (saleConVO.getStartDate() == null || saleConVO.getEndDate() == null) && saleConVO.getPdName().equals("null") && saleConVO.getPdType().equals("null") ) {
					list = dao.selectAllSalesDetail();
					addSalesDtm(list);	
			} else {
				saleConVO.setStartDate(startUtilDate);
				saleConVO.setEndDate(endUtilDate);
				System.out.println(saleConVO);
				
				list = dao.selectSalesDetail(saleConVO);
				addSalesDtm(list);	
			}//end else
			
		}catch(ParseException parseError) {
			JOptionPane.showMessageDialog(adSaleView, "시작일과 종료일의 날짜 형식이 올바르지 않습니다.\n다시 확인해주세요.");
		}catch (SQLException sqlError) {
			sqlError.printStackTrace();
			JOptionPane.showMessageDialog(adSaleView, "데이터를 불러오는중 error");
		}//end catch
		
	}//getSalesDetailList
	
	public void addSalesDtm(List<SalesDetailVO> list) {
		adSaleView.getSalesInfoTm().setRowCount(0);
		String[] rows = new String[8];
		int totalSalesAmount =0;
		
		for( int i = 0; i<list.size(); i++) {
			rows[0] = list.get(i).getPdTypeCode();
			rows[1] = list.get(i).getPdName();
			rows[2] = list.get(i).getoTempType();
			rows[3] = list.get(i).getoSizeName();
			rows[4] = list.get(i).getoOptionName();
			rows[5] = String.valueOf(list.get(i).getPdQuantity());
			rows[6] = String.valueOf(list.get(i).getOrderDetailPrice());
			rows[7] = String.valueOf(list.get(i).getPmDate());
			
			totalSalesAmount += list.get(i).getOrderDetailPrice();
			
			adSaleView.getSalesInfoTm().addRow(rows);
			
			//Table row 높이 설정
			adSaleView.getSalesInfo().setRowHeight(i, 50);
			
			
		}//end for
		adSaleView.getTotalLabel().setText(String.valueOf( totalSalesAmount ));
	}//addSalesDtm
	
	public void setProducType() {
		SalesDAO dao = SalesDAO.getInstance();
		
		List<String> comboBoxList = new ArrayList<String>();
		comboBoxList.add("전체");
		
		try {
			List<String> selectList = dao.selectProductType();
			comboBoxList.addAll(selectList);
			adSaleView.getNameType().addAll(comboBoxList);
			adSaleView.getJcbName().setSelectedItem("전체");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}//setProductType
	
	public void setProductName( String type ) {
		SalesDAO dao = SalesDAO.getInstance();
		
		List<String> comboBoxList = new ArrayList<String>();
		comboBoxList.add("전체");
			
		try {
			List<String> selectList = dao.selectProductName(type);
			comboBoxList.addAll(selectList);
			adSaleView.getProductType().addAll(comboBoxList);
			adSaleView.getJcbType().setSelectedItem("전체");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//setProductName
	
	public List<SalesDetailVO> getDailySalesDetail() {
		SalesDAO dao = SalesDAO.getInstance();
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		try {
			list = dao.selectDaySalesDetail();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchDailySales
	
	public List<SalesDetailVO> getMonthSalesDetail(int month){
		SalesDAO dao = SalesDAO.getInstance();
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		try {
			list = dao.selectMonthSalesDetail(month);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//getMonthlySalesDetail
	

}//class
