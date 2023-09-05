package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.InventoryVO;
import vo.OrderStatusVO;

public class OrderStatusDAO {

	private static OrderStatusDAO orderStatusDAO;
	
	public OrderStatusDAO() {
		
	}
	
	public static OrderStatusDAO getInstance() {
		if(orderStatusDAO == null) {
			orderStatusDAO = new OrderStatusDAO();
		}
		return orderStatusDAO;
	}
	
	public List<OrderStatusVO> selectAllOrderStatus() {
		List<OrderStatusVO> list = new ArrayList<OrderStatusVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		StringBuilder selectAllInventory= new StringBuilder();
		selectAllInventory
		.append("	select inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date	")
		.append("	from inventory								");
		
		pstmt=con.prepareStatement(selectAllInventory.toString());
		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			InventoryVO inventoryVO = new InventoryVO();
			inventoryVO.setiCode(rs.getString("inventory_code"));
			inventoryVO.setiTypeCode(rs.getString("inventory_type_code"));
			inventoryVO.setiName(rs.getString("inventory_name"));
			inventoryVO.setiMount(rs.getInt("inventory_quantity"));
			inventoryVO.setiInputDate(rs.getDate("inventory_date"));
			
			list.add(inventoryVO);
		}
		
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}
	
	
	
	
	
}
