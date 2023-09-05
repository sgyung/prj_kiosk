package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
	private static AdminDAO adminDAO;
	
	private AdminDAO() {
		
	}
	
	public static AdminDAO getInstance() {
		if(adminDAO == null) {
			adminDAO = new AdminDAO();
		}
		return adminDAO;
	}
	
	public String selectAdmin(String id, String pass) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String adminId = "";
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost.", "scott", "tiger");
			
			stmt = con.createStatement();
			
			StringBuilder selectAdmin = new StringBuilder();
			selectAdmin
			.append("select id, pass from admin where id = '")
			.append(id)
			.append("' and pass = '")
			.append(pass)
			.append("'");
			
			rs = stmt.executeQuery(selectAdmin.toString());
			
			if(rs.next()) {
				adminId = rs.getString("id");			
			}
		}finally {
			db.dbClose(rs, stmt, con);
		}
		return adminId;
	}
}
