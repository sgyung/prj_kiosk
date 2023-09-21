package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vo.OrderDetailVO;
import vo.PaymentVO;
import vo.UserVO;

public class PaymentDAO {
	private static Connection con;
	private static ResultSet rs;
	
	private static PaymentDAO paymentDAO;
	
	private int orderNumber;
	
	private PaymentDAO() {
		
	}//PaymentDAO
	
	public static PaymentDAO getInstance() {
		if(paymentDAO == null) {
			paymentDAO = new PaymentDAO();
		}
		return paymentDAO;
	}//getInstance
	
	
	public int insertPaymentTransaction(List<OrderDetailVO> orderList, PaymentVO payVO, UserVO userVO ) throws SQLException{
		int result = 0;
		int cnt1=0; //order_menu
		int cnt2=0; //user_info
		int cnt3=0; //order_detail
		int cnt4=0; //order_option
		int cnt5=0; //inventory
		int cnt6=0; //payment
		
		DbConn db = DbConn.getInstance();
		con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
		con.setAutoCommit(false);
		
		PreparedStatement pstmt = null;
		
		//order_menu 테이블 insert
		
		//order_seq 시퀀스
		String orderSeq = "select order_serial_seq.nextval oseq from dual";
		
		int orderSerial = 0;
		pstmt = con.prepareStatement(orderSeq);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			orderSerial = rs.getInt("oseq");
		}
		pstmt.close();
		
		//order_num_seq 시퀀스
		String orderNumSeq = "select order_num_seq.nextval onseq from dual";
		
		int orderNum = 0;
		pstmt = con.prepareStatement(orderNumSeq);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			orderNum = rs.getInt("onseq");
		}//end if
		pstmt.close();
		
		//주문번호 설정
		orderNumber = orderNum;
		
		StringBuilder insertOrderMenu = new StringBuilder();
		if( userVO == null ) {
			insertOrderMenu
			.append("		insert into order_menu(order_serial_num,order_num,order_status,order_time)			")
			.append("		values ('order_serial_'||?,?,'준비중',sysdate)		");
			
			pstmt = con.prepareStatement(insertOrderMenu.toString());
			
			pstmt.setInt(1, orderSerial);
			pstmt.setInt(2, orderNum);
			
			
			cnt1 = pstmt.executeUpdate();
			
			pstmt.close();
		
		} else {
			//선택된 회원이 있을경우 (UserVO != null) 
			insertOrderMenu
			.append("		insert into order_menu(order_serial_num,user_phone,order_num,order_status,order_time)			")
			.append("		values ('order_serial_'||?,?,?,'준비중',sysdate)		");
			
			pstmt = con.prepareStatement(insertOrderMenu.toString());
			
			String userTel = userVO.getuTelNum();
			
			pstmt.setInt(1, orderSerial);
			pstmt.setString(2,  userTel);
			pstmt.setInt(3, orderNum);
			
			cnt1 = pstmt.executeUpdate();
			
			pstmt.close();
			
			//User_info 테이블 point update
			StringBuilder insertUser = new StringBuilder();
			insertUser
			.append("	update user_info	")
			.append("	set user_point=?	")
			.append("	where user_phone=?	")
			;
			
			int currentPoint = userVO.getuRemainReward();
			int savePoint = (int)Math.round(payVO.getTotalPrice() * 0.03);
			int resultPoint = currentPoint + savePoint;
			
			pstmt = con.prepareStatement(insertUser.toString());
			
			pstmt.setInt(1, resultPoint);
			pstmt.setString(2, userTel);
			
			cnt2 = pstmt.executeUpdate();
			
			pstmt.close();			
		}//end else
		
		//주문상세리스트		
		for(int i = 0; i < orderList.size(); i++) {
			
			String pdCode = orderList.get(i).getPdCode();
			String pdSize = orderList.get(i).getoSizeName();
			if( pdSize == null) {
				pdSize = "";
			} else if( pdSize.equals("R")) {
				pdSize = "레귤러컵";
			} else if( pdSize.equals("E")) {
				pdSize = "엑스트라컵";
			}//end if
			
			int pdQuantity = orderList.get(i).getoQuantity();
			
			//order_detail 테이블 insert
			//order_detail_seq 시퀀스	
			String selectOrderDetailSeq = "select order_detail_seq.nextval odseq from dual";
			
			pstmt = con.prepareStatement(selectOrderDetailSeq);
			
			rs = pstmt.executeQuery();
			
			int orderDetailNum = 0;
			
			if(rs.next()) {
			orderDetailNum = rs.getInt("odseq");
			}
			pstmt.close();
			
			StringBuilder insertOrderDetails = new StringBuilder();		
			insertOrderDetails
			.append("			insert into order_detail(order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)		")
			.append("			values ('order_detail_'||?,?,?,?,?,?)																				");
			
			pstmt = con.prepareStatement(insertOrderDetails.toString());
			
			//FK orderSerialNum
			StringBuilder orderSerialNum = new StringBuilder();
			orderSerialNum
			.append("order_serial_")
			.append(orderSerial)
			;
			
			pstmt.setInt(1, orderDetailNum);
			pstmt.setString(2, orderSerialNum.toString());
			pstmt.setString(3, pdCode);
			pstmt.setInt(4, orderList.get(i).getoQuantity());
			pstmt.setString(5, orderList.get(i).getoTempType());
			pstmt.setString(6, orderList.get(i).getoSizeName());
			
			cnt3 += pstmt.executeUpdate();
			
			pstmt.close();
			
			//inventory update
			StringBuilder invenUpdate = new StringBuilder();
			invenUpdate
			.append("	merge into inventory invenTable	")
			.append("	using (	")
			.append("	select useInven.inventory_code, inven.inventory_name, (inven.inventory_quantity - ( useInven.use_quantity * ? )) updateInven	")
			.append("	from use_inventory useInven, inventory inven	")
			.append("	where ( useInven.product_code =? ) and (useInven.inventory_code = inven.inventory_code) and (inven.inventory_name = ?  or not inven.inventory_name like '%컵')	")
			.append("	) useTable	")
			.append("	ON ( invenTable.inventory_code = useTable.inventory_code )	")
			.append("	when MATCHED then	")
			.append("	update set invenTable.inventory_quantity = useTable.updateInven	")
			;
			
			pstmt = con.prepareStatement(invenUpdate.toString());
			
			pstmt.setInt(1, pdQuantity);
			pstmt.setString(2, pdCode);
			pstmt.setString(3, pdSize);
			
			pstmt.executeUpdate();
			cnt5++;
			 
			pstmt.close();
			
			//선택한 옵션이 있다면 옵션개수만큼 order_option테이블에 추가
			if( !orderList.get(i).getoOptionName().isEmpty() ) {
				
				String[] optionArr = orderList.get(i).getoOptionName().split(",");
				//order_detail_num
				StringBuilder orderDetail = new StringBuilder();
				orderDetail
				.append("order_detail_")
				.append(orderDetailNum)
				;
				
				StringBuilder insertOption = new StringBuilder();
				insertOption
				.append("	insert into order_option(option_num, order_detail_num, option_name, option_price)	")
				.append("	values( 'opt_'||?, ?, ?, ?)		")
				;
				
				//opt_seq 시퀀스
				String optSeq = "select opt_seq.nextval optseq from dual";
				
				//option개수만큼 insert
				for( int j = 0; j < optionArr.length; j++) {
					
					pstmt = con.prepareStatement(optSeq);
					
					rs = pstmt.executeQuery();
					
					int optNum = 0;
					
					if(rs.next()) {
						optNum = rs.getInt("optseq");
					}//end if
					pstmt.close();
					
					pstmt = con.prepareStatement(insertOption.toString());
					
					pstmt.setInt(1, optNum);
					pstmt.setString(2, orderDetail.toString());
					pstmt.setString(3, optionArr[j]);
					if( optionArr[j].equals("샷추가") || optionArr[j].equals("시럽추가")) {
						pstmt.setInt(4, 500);
					} else if(optionArr[j].equals("휘핑추가")) {
						pstmt.setInt(4, 1000);
					} else {
						pstmt.setInt(4, 0);
					}// end else
	
					cnt4 += pstmt.executeUpdate();
					pstmt.close();
					
				}//end for
				
			}//end if
			
		}//end for
		
		//payment 테이블 insert
		StringBuilder insertPayment = new StringBuilder();
		insertPayment
		.append(	"insert into payment(payment_code,order_serial_num,payment_method_code,payment_status_code,order_price,discount_price,purchase_price,payment_date)	")
		.append(	"values ('pay_'||pay_seq.nextval,'order_serial_'||?,?,?,?,?,?,sysdate)																								");
		
		pstmt = con.prepareStatement(insertPayment.toString());
		
		pstmt.setInt(1, orderSerial);
		pstmt.setInt(2, payVO.getPmTypeCode());
		pstmt.setString(3, "Y");
		pstmt.setInt(4, payVO.getOrderPrice());
		pstmt.setInt(5, payVO.getDiscountPrice());
		pstmt.setInt(6, payVO.getTotalPrice());
		
		cnt6 = pstmt.executeUpdate();
		
		pstmt.close();
		
		result = cnt1 + cnt2 + cnt3 + cnt4 + cnt5 + cnt6;
		
		return result;
		
	}//insertPaymentTransaction
	
	
	//트랜잭션 실행
	public String useTransaction(List<OrderDetailVO> orderList, PaymentVO payVO, UserVO userVO) throws SQLException {
		
		int cnt = 0;
		//compareCnt 기본값
		//order_menu insert +1 , payment insert +1
		int compareCnt = 2;
		StringBuilder msg = new StringBuilder();
		
		DbConn db = DbConn.getInstance();
		
		//order_detail 하나당 insert +1, 재고 update +1
		compareCnt += orderList.size() * 2;
		for( OrderDetailVO vo : orderList ) {
			//option이 선택되었으면 + 그렇지 않다면 0
			if( !vo.getoOptionName().isEmpty()) {
				compareCnt += vo.getoOptionName().split(",").length;
			}//end if			
		}//end for
		
		try {
			cnt = insertPaymentTransaction(orderList, payVO, userVO);
			
			//회원조회 없이 결제시 user_info 테이블에 update가 안되기때문에 -1
			if( userVO != null ) {
				compareCnt++;
			}//end if
			
			//commit
			if( cnt == compareCnt) {
				msg
				.append("<html><div style='text-align: center; font-size: 16px;'>")
				.append("결제 성공<br>")
				.append("[대기번호]<br>")
				.append(orderNumber)
				.append("</div></html>")
				;
				
				con.commit();
			} else {
				msg.append("결제 실패");
				con.rollback();
			}//end else
				
		} finally {
			db.dbClose(rs, null, con);
		}//end finally
		
		return msg.toString();
	
	}//useTransaction
	
}//class
