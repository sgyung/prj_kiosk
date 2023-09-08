package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.OrderDetailVO;
import vo.PaymentVO;
import vo.UserVO;

public class PaymentDAO {
	private static Connection con;
	private static ResultSet rs;
	
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
	
	public int insertPaymentTransaction(String telNum) throws SQLException{
		int cnt, cnt1, cnt2, cnt3, cnt4 = 0;
		
		DbConn db = DbConn.getInstance();
		con = db.getConnection("localhost", "scott", "tiger");
		con.setAutoCommit(false);
		
		PreparedStatement pstmt = null;
		
		
		String orderSeq = "select order_serial_seq.nextval oseq from dual";
		
		pstmt = con.prepareStatement(orderSeq);
		
		rs = pstmt.executeQuery();
		
		int orderNum = 0;
		
		if(rs.next()) {
		orderNum = rs.getInt("oseq");
		}
		pstmt.close();
		
		StringBuilder insertOrderMenu = new StringBuilder();
		insertOrderMenu
		.append("		insert into order_menu(order_serial_num,user_phone,order_num,order_status,order_time)			")
		.append("		values ('order_serial_'||?,?,order_num_seq.nextval,'준비중',sysdate)		"); // 오라클에서 준비중 defualt값으로 변경
		
		pstmt = con.prepareStatement(insertOrderMenu.toString());
		
		pstmt.setInt(1, orderNum);// 이벤트에서 값 설정 후 바인드 값 설정
		pstmt.setString(2, telNum);// 이벤트에서 값 설정 후 바인드 값 설정
		

		cnt1 = pstmt.executeUpdate();
		pstmt.close();
		
		String selectOrderDetailSeq = "select order_detail_seq.nextval odseq from dual";
		
		pstmt = con.prepareStatement(selectOrderDetailSeq);
		
		rs = pstmt.executeQuery();
		
		int orderDetailNum = 0;
		
		if(rs.next()) {
		orderDetailNum = rs.getInt("odseq");
		}
		pstmt.close();
		
		

		
		
		for(int i = 0; i < 2; i++) {
			StringBuilder insertOrderDetails = new StringBuilder();
			insertOrderDetails
			.append("			insert into order_detail(order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)		")
			.append("			values ('order_detail_'||?,?,?,?,?,?)																				");
			
			pstmt = con.prepareStatement(insertOrderDetails.toString());
			OrderDetailVO odVO = new OrderDetailVO();
			pstmt.setInt(1, orderDetailNum);
			pstmt.setInt(2, orderNum);
			pstmt.setString(3, "cof_1");
			pstmt.setInt(4, 2);
			pstmt.setString(5, "Y");
			pstmt.setString(5, "T");
			
			cnt2 = pstmt.executeUpdate();
			
			pstmt.close(); 
			
			String selectOptionSeq = "select opt_seq.nextval opseq from dual";
			
			pstmt = con.prepareStatement(selectOptionSeq);
			
			rs = pstmt.executeQuery();
			
			int optionNum = 0;
			
			if(rs.next()) {
			optionNum = rs.getInt("opseq");
			}
			
			pstmt.close();
			
			
			for(int j = 0; j < 2; j++){
				StringBuilder insertOrderOption = new StringBuilder();
				insertOrderOption
				.append("insert into order_option(option_num, order_detail_num, option_name, option_price)")
				.append("values ('opt_'||?,'order_detail_'||?,?,?)");
				
				pstmt = con.prepareStatement(insertOrderOption.toString());
				pstmt.setInt(1, optionNum);
				pstmt.setInt(2, 4);
				pstmt.setString(3, "샷추가" );
				pstmt.setInt(4, 500);	

				cnt3 = pstmt.executeUpdate();
				pstmt.close();
			}
			
		}
	
		
		StringBuilder insertPayment = new StringBuilder();
		insertPayment
		.append(	"insert into payment(payment_code,order_serial_num,payment_method_code,payment_status_code,order_price,discount_price,purchase_price,payment_date)	")
		.append(	"values ('pay_'||pay_seq.nextval,'order_serial_'||?,?,?,?,?,?,sysdate)																								");
		
		pstmt = con.prepareStatement(insertPayment.toString());
		
		PaymentVO pVO = new PaymentVO();
		
		pstmt.setInt(1, orderNum);
		pstmt.setString(2, "0");
		pstmt.setString(3, "Y");
		pstmt.setInt(4, 3000);
		pstmt.setInt(5, 0);
		pstmt.setInt(6, 3000);
		
		cnt4 = pstmt.executeUpdate();
		
		pstmt.close();
		
		rs.close();
		
		cnt = cnt1  + cnt4;
		return cnt;
	}
	
	public static void main(String[] args) {
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		
		try {
			if(paymentDAO.insertPaymentTransaction(null) == 2) {
				System.out.println("추가 성공");
				con.commit();
				
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}//main
	
	
}//class
