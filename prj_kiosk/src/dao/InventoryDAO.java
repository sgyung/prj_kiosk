package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vo.InventoryVO;

public class InventoryDAO {

	private static InventoryDAO inventoryDAO;
	
	public InventoryDAO() {
	}
	
	public static InventoryDAO getInstance() {
		if(inventoryDAO == null) {
			inventoryDAO = new InventoryDAO();
		}
		return inventoryDAO;
	}
	
	public List<InventoryVO> selectAllInventory() throws SQLException {
		List<InventoryVO> list = new ArrayList<InventoryVO>();
		
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
	
	public InventoryVO selectInventory(String iCode) throws SQLException {
		InventoryVO inventoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectInventory=new StringBuilder();
			selectInventory
			.append("	select 	inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date	")
			.append("	from 	inventory								")
			.append("	where	inventory_code = ? 						");
			
			pstmt=con.prepareStatement(selectInventory.toString());
			
			pstmt.setString(1, iCode);
			
			rs=pstmt.executeQuery();
			
			if( rs.next() ) { 
				inventoryVO = new InventoryVO();
				inventoryVO.setiCode(rs.getString("inventory_code"));
				inventoryVO.setiTypeCode(rs.getString("inventory_type_code"));
				inventoryVO.setiName(rs.getString("inventory_name"));
				inventoryVO.setiMount(rs.getInt("inventory_quantity"));
				inventoryVO.setiInputDate(rs.getDate("inventory_date"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return inventoryVO;
	}
	
	
	/*
	 * public void selectInventoryType() {
	 * 
	 * }
	 */
	
	
	public void insertInventory(InventoryVO inventoryVO) throws SQLException {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			cstmt = con.prepareCall("{ call insert_inventory_proc(?,?,?,?,?,?,?) }");

			cstmt.setString(1, inventoryVO.getiCode());
			cstmt.setString(2, inventoryVO.getiTypeCode());
			cstmt.setString(3, inventoryVO.getiName());
			cstmt.setInt(4, inventoryVO.getiMount());
			cstmt.setDate(5, inventoryVO.getiInputDate());
			
			cstmt.registerOutParameter(6, Types.NUMERIC);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			
			cstmt.execute();
			
			int cnt = cstmt.getInt(6);
			String msg = cstmt.getString(7);
			System.out.println(cnt + " / " + msg);
		} finally {
			
			db.dbClose(null, cstmt, con);
		}//end finally
	}
	
	
	public void updateInventory(InventoryVO inventoryVO) throws SQLException {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
		
			cstmt = con.prepareCall("{ call update_inventory_proc(?,?,?,?,?) }");
			
			cstmt.setString(1, inventoryVO.getiCode());
			cstmt.setString(2, inventoryVO.getiTypeCode());
			cstmt.setString(3, inventoryVO.getiName());
			cstmt.setInt(4, inventoryVO.getiMount());
			cstmt.setDate(5, inventoryVO.getiInputDate());

			cstmt.execute();

		} finally {
		
			db.dbClose(null, cstmt, con);
		}//end finally
	}
	public void deleteInventory(String iCode) throws SQLException {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			cstmt = con.prepareCall("{ call delete_inventory_proc(?) }");

			cstmt.setString(1, iCode);

			cstmt.execute();

		} finally {
		
			db.dbClose(null, cstmt, con);
		}//end finally
	}
	
	
//	public static void main(String[] args) {
//		InventoryDAO i = new InventoryDAO();
//		try {
//			//System.out.println(i.selectInventory("md_1"));
//			i.deleteInventory("food_1");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
