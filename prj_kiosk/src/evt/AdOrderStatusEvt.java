package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
			System.out.println(1);
			showOrder(selectdetailOrder()); 
		}
		if(e.getSource() == adOrderStatusView.getCompleteBtn()) {
			completeOrder(selectOrder());
		}
		if(e.getSource() == adOrderStatusView.getRePrintBtn()) {
			reloadOrder();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == adOrderStatusView.getOrderInfo()) {
					selectOrder();
			}
	}
	
	//전체 
	public void setOrderList() {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		
		try {
			List<OrderStatusVO> list = dao.selectAllOrderStatus();
			
			adOrderStatusView.getOrderInfoTm().setRowCount(0);
			
			String[] rowData = null;
			
			 
			for(OrderStatusVO orderStatusVO : list) {
				rowData = new String[5];
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
	
	
	public void reloadOrder() {
		setOrderList();
	}
	
	//테이블에서 선택한 열의 oNum의 ..
	public OrderStatusVO selectOrder() {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		OrderStatusVO vo = null;
		int row = adOrderStatusView.getOrderInfo().getSelectedRow();
		String oNum = (String) adOrderStatusView.getOrderInfo().getValueAt(row, 0);
		
		try {
			vo = dao.selectOrderStatus(oNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	public OrderStatusVO selectdetailOrder() {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		OrderStatusVO vo = null; 
		int row = adOrderStatusView.getOrderInfo().getSelectedRow();
		String oNum = (String) adOrderStatusView.getOrderInfo().getValueAt(row, 0);
		
		try {
			vo = dao.selectDetailStatus(oNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;//detail한 내용들이 들어가있음.
		
	}
	
	
	public void completeOrder( OrderStatusVO orderStatusVO ) {
		OrderStatusDAO dao = OrderStatusDAO.getInstance();
		try {
			dao.updateOrderStatus(orderStatusVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showOrder( OrderStatusVO orderStatusVO) {
		StringBuilder detailOrder = new StringBuilder();
		detailOrder.append("상품명\t"+orderStatusVO.getPdName()+"\n")
		.append("HOT/ICE\t"+orderStatusVO.getoTempType()+"\n")
		.append("옵션\t"+orderStatusVO.getOpName()+"\n")
		.append("크기\t"+orderStatusVO.getoSize()+"\n")
		.append("가격\t"+orderStatusVO.getTotalPrice()+"\n")
		;
		
		JTextArea jta = new JTextArea(detailOrder.toString(), 10, 30);
		JScrollPane jsp = new JScrollPane( jta );
		
		JOptionPane.showMessageDialog(null, jsp);
		
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
