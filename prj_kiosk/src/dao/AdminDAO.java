package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.AdminVO;

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
	
	public AdminVO selectAdmin(String id, String pass) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO aVO = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost.", "scott", "tiger");
			
			
			StringBuilder selectAdmin = new StringBuilder();
			selectAdmin
			.append("select admin_num, admin_pw from admin where admin_num = ? ")
			.append(" and admin_pw = ?");
			
			pstmt = con.prepareStatement(selectAdmin.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				aVO = new AdminVO();
				aVO.setAdminNum(rs.getString("admin_num"));
				aVO.setAdminPW(rs.getString("admin_pw"));
			}
		}finally {
			db.dbClose(rs, pstmt, con);
		}
		return aVO;
	}
}
