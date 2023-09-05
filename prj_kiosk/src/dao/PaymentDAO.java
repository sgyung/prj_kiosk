package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.UserVO;

public class PaymentDAO {

	private static PaymentDAO paymentDAO;
	
	private PaymentDAO() {
		
	}//PaymentDAO
	
	public static PaymentDAO getInstance() {
		if(paymentDAO == null) {
			paymentDAO = new PaymentDAO();
		}
		return paymentDAO;
	}//getInstance
	
	public UserVO selectUser(String telNum) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		UserVO uVO = null;
	
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			stmt = con.createStatement();
			
			StringBuilder selectUser = new StringBuilder();
			selectUser
			.append("select user_phone, user_point, user_withdrawal, user_date from user_info where user_phone = '")
			.append(telNum)
			.append("'");
			
			rs = stmt.executeQuery(selectUser.toString());
			
			if(rs.next()) {
				uVO = new UserVO(rs.getString("user_phone"),rs.getInt("user_point"),
							rs.getString("user_withdrawal"),rs.getDate("user_date"));
			}
			return uVO;	
		}finally {
			db.dbClose(rs, stmt, con);
		}
	}//selectUser
	
	public 
	
	
	
}//class
