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
		.append("	select user_phone, user_point, user_date	")
		.append("	from user_info								");
		
		pstmt=con.prepareStatement(selectAllUser.toString());
		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			UserVO userVO = new UserVO();
			userVO.setuTelNum(rs.getString("user_phone"));
			userVO.setuRemainReward(rs.getInt("user_point"));
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
			.append("	select	user_phone, user_point, user_date	")
			.append("	from	user_info							")
			.append("	where	user_phone = ? 						");
			
			pstmt=con.prepareStatement(selectUser.toString());
			
			pstmt.setString(1, uTelNum);
			
			rs=pstmt.executeQuery();
			
			if( rs.next() ) { 
				userVO = new UserVO();
				userVO.setuTelNum(rs.getString("user_phone"));
				userVO.setuRemainReward(rs.getInt("user_point"));
				userVO.setuSignupDate(rs.getDate("user_date"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return userVO;
	}
	
	public void insertUser( UserVO userVO ) throws SQLException {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
		//3.
			cstmt = con.prepareCall("{ call insert_user_info_proc(?,?,?,?,?) }");
		//4. bind변수 값 설정
		//in parameter
			cstmt.setString(1, userVO.getuTelNum());
			cstmt.setInt(2, userVO.getuRemainReward());
			cstmt.setDate(3, userVO.getuSignupDate());
		//out parameter
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.VARCHAR);
		//5.
			cstmt.execute();
		//6. registerOutParameter에 할당된 값 얻기
			int cnt = cstmt.getInt(4);
			String msg = cstmt.getString(5);
			System.out.println(cnt + " / " + msg);
		} finally {
		//7.
			db.dbClose(null, cstmt, con);
		}//end finally
	}
	
	public void updateUser( UserVO userVO ) throws SQLException {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");

			cstmt = con.prepareCall("{ call update_user_info_proc(?,?,?) }");

			cstmt.setString(1, userVO.getuTelNum());
			cstmt.setInt(2, userVO.getuRemainReward());
			cstmt.setDate(3, userVO.getuSignupDate());

			cstmt.execute();

		} finally {
		//7.
			db.dbClose(null, cstmt, con);
		}//end finally
	}
	
	public void deleteUser( String uTelNum ) throws SQLException  {
		Connection con = null;
		CallableStatement cstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");

			cstmt = con.prepareCall("{ call delete_user_info_proc(?) }");

			cstmt.setString(1, uTelNum);

			cstmt.execute();

		} finally {

			db.dbClose(null, cstmt, con);
		}//end finally
	}
	
//	public static void main(String[] args) {
//		UserDAO u = new UserDAO();
//		try {
//			//System.out.println(i.selectInventory("md_1"));
//			System.out.println(u.selectAllUser());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
}
