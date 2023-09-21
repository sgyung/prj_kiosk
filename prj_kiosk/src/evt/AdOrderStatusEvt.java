package evt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.OrderStatusDAO;
import view.AdOrderStatusView;
import vo.OrderStatusVO;

public class AdOrderStatusEvt implements ActionListener,MouseListener {

	private AdOrderStatusView adOrderStatusView;
	
	public AdOrderStatusEvt( AdOrderStatusView adOrderStatusView ) {
		this.adOrderStatusView = adOrderStatusView;
		setOrderList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adOrderStatusView.getDetailBtn()) {
			showOrder(getOrderNum()); 
		}
		if(e.getSource() == adOrderStatusView.getCompleteBtn()) {
			completeOrder(getOrderNum());
			setOrderList();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == adOrderStatusView.getOrderInfo()) {
				getOrderNum();
			}
	}
	
	//전체 OrderList 출력
	public void setOrderList() {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		
		try {
			List<OrderStatusVO> list = dao.selectAllOrderStatus();
			
			adOrderStatusView.getOrderInfoTm().setRowCount(0);
			
			String[] rowData = null;
			
			 
			for(OrderStatusVO orderStatusVO : list) {
				rowData = new String[3];
				rowData[0] = orderStatusVO.getoNum();
				rowData[1] = orderStatusVO.getoStatus();
				rowData[2] = ( String.valueOf(orderStatusVO.getoDate()) );
				
				adOrderStatusView.getOrderInfoTm().addRow(rowData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	//테이블에서 선택한 열의 oNum의 해당하는 주문번호얻기 
	public String getOrderNum() {
		int row = adOrderStatusView.getOrderInfo().getSelectedRow();
		String oNum = (String) adOrderStatusView.getOrderInfo().getValueAt(row, 0);
		
		return oNum;
	}
	
	public void completeOrder( String oNum ) {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		try {
			dao.updateOrderStatus(oNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	
	//셀을 클릭하면 해당하는 주문번호의 디테일 주문을 Frame 에 Table 에 출력
	public void showOrder( String oNum ) {
		OrderStatusDAO dao = new OrderStatusDAO();
		List<OrderStatusVO> list = new ArrayList<OrderStatusVO>();
		try {
			list = dao.selectDetailStatus(oNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // JFrame 생성
        JFrame frame = new JFrame("상세내역");
        frame.setSize(600, 200);

        // JTable , DefaultTableModel 생성
        String[] columnName = {"제품명", "ICE/HOT", "사이즈", "옵션", "수량", "가격"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnName);
        JTable table = new JTable(tableModel);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);

        String[] rowData = null;
        // vo로 list 만들기
        for (OrderStatusVO vo : list) {
        	rowData = new String[6];
        	rowData[0]=vo.getPdName();
        	rowData[1]=vo.getoTempType();
        	rowData[2]=vo.getoSize();
        	rowData[3]=vo.getOpName();
        	rowData[4]=( String.valueOf( vo.getoMount() ) );
        	rowData[5]=( String.valueOf( vo.getoDetailPrice() ) );
        	
            tableModel.addRow(rowData);
        }
        
        // 주문량이 많을 수 있으니 JScrollPane 생성 후 테이블 삽입
        JScrollPane scrollPane = new JScrollPane(table);
        
        // JScrollPane을 frame에 삽입
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setLocation(150,300);
        
        frame.setVisible(true);
        
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
