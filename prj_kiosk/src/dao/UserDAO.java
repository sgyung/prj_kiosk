package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vo.UserVO;

public class UserDAO {

	private static UserDAO userDAO;
	
	public UserDAO() {
	}
	
	public static UserDAO getInstance() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		
		return userDAO;
	}
	
	
	public List<UserVO> selectAllUser() throws SQLException {
		List<UserVO> list = new ArrayList<UserVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		StringBuilder selectAllUser= new StringBuilder();
		selectAllUser
		.append("	select user_phone, user_point, user_withdrawal, user_date	")
		.append("	from user_info								");
		
		pstmt=con.prepareStatement(selectAllUser.toString());
		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			UserVO userVO = new UserVO();
			userVO.setuTelNum(rs.getString("user_phone"));
			userVO.setuRemainReward(rs.getInt("user_point"));
			userVO.setuWithdrawal(rs.getString("user_withdrawal"));
			userVO.setuSignupDate(rs.getDate("user_date"));
			
			list.add(userVO);
		}
		
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
	}
	
	public UserVO selectUser(String uTelNum) throws SQLException {
		UserVO userVO = null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectUser=new StringBuilder();
			selectUser
			.append("	select	user_phone, user_point, user_withdrawal, user_date	")
			.append("	from	user_info							")
			.append("	where	user_phone = ? 						");
			
			pstmt=con.prepareStatement(selectUser.toString());
			
			pstmt.setString(1, uTelNum);
			
			rs=pstmt.executeQuery();
			
			if( rs.next() ) { 
				userVO = new UserVO();
				userVO.setuTelNum(rs.getString("user_phone"));
				userVO.setuRemainReward(rs.getInt("user_point"));
				userVO.setuWithdrawal(rs.getString("user_withdrawal"));
				userVO.setuSignupDate(rs.getDate("user_date"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return userVO;
	}
	
	public void insertUser( UserVO userVO ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder user = new StringBuilder();
			user
			.append("	insert into user_info( user_phone, user_point, user_withdrawal, user_date )	")
			.append("	values( ? , ?, 'n', sysdate	)	");
			
			pstmt = con.prepareStatement(user.toString());
			
			pstmt.setString(1, userVO.getuTelNum());
			pstmt.setInt(2, userVO.getuRemainReward());
			
			pstmt.executeQuery();

		} finally {
			db.dbClose(null, pstmt, con);
		}//end finally
	}
	
	public int updateUser( UserVO userVO ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt=null;
		int cnt = 0;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder updateUser = new StringBuilder();
			updateUser
			.append("	update user_info	")
			.append("	set user_point= ?	")
			.append("	where user_phone= ? 	")
			;

			pstmt = con.prepareStatement(updateUser.toString());

			pstmt.setInt(1, userVO.getuRemainReward());
			pstmt.setString(2, userVO.getuTelNum());

			cnt = pstmt.executeUpdate();
			
			return cnt;
		} finally {
			
			db.dbClose(null, pstmt, con);
			
		}//end finally
	}
	
	public int deleteUser( String uTelNum ) throws SQLException  {
		Connection con = null;
		PreparedStatement pstmt=null;
		int cnt = 0;
		
		DbConn db = DbConn.getInstance();
		
		try {
			StringBuilder update = new StringBuilder();
			update
			.append("	update user_info	")
			.append("	set user_withdrawal='y'	")
			.append("	where user_phone= ? 	")
			;
			
			con=db.getConnection("localhost", "scott", "tiger");

			pstmt = con.prepareStatement(update.toString());

			pstmt.setString(1, uTelNum);

			cnt = pstmt.executeUpdate();
			
			return cnt;

		} finally {

			db.dbClose(null, pstmt, con);
			
		}//end finally
		
	}//deleteUser

}//class
