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

	public void insertInventory(InventoryVO inventoryVO) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			String invenType = inventoryVO.getiTypeCode();
			String invenTypeSeq = invenType + "_seq.nextval";
			
			StringBuilder insert = new StringBuilder();
			insert
			//sqlInjection위험
			.append("	insert into inventory(inventory_code, inventory_type_code, inventory_name, inventory_quantity, inventory_date)	")
			.append("	values( ?||"+ invenTypeSeq +", ?, ?, ?, sysdate)	")
			;
			
			pstmt = con.prepareStatement(insert.toString());
			
			pstmt.setString(1, invenType+"_");
			pstmt.setString(2, invenType);
			pstmt.setString(3, inventoryVO.getiName());
			pstmt.setInt(4, inventoryVO.getiMount());
			
			pstmt.executeQuery();
			
		} finally {
			
			db.dbClose(null, pstmt, con);
		}//end finally
	}
	
	
	public int updateInventory(InventoryVO inventoryVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt=null;
		int rowCnt = 0;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
		
			StringBuilder update = new StringBuilder();
			update
			.append("	update inventory	")
			.append("	set inventory_type_code = ?, inventory_name = ?, inventory_quantity=?	")
			.append(	"	where inventory_code = ?	")
			;
			
			pstmt = con.prepareStatement(update.toString());
			
			pstmt.setString(1, inventoryVO.getiTypeCode());
			pstmt.setString(2, inventoryVO.getiName());
			pstmt.setInt(3, inventoryVO.getiMount());
			pstmt.setString(4, inventoryVO.getiCode());

			rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
			
		} finally {
		
			db.dbClose(null, pstmt, con);
		}//end finally
		
	}//updateInventory
	
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
	
	public List<String> selectInventoryType() throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String selectType = "	select inventory_type_code from inventory_type	";
			pstmt =con.prepareStatement(selectType);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(rs.getString("inventory_type_code"));
				
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectInventoryType
	
	
}
