package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vo.SalesConditionVO;
import vo.SalesDetailVO;

public class SalesDAO {
	
	private static SalesDAO salesDAO;
	
	public SalesDAO() {
	}//SalesDAO
	
	static public SalesDAO getInstance() {
		if( salesDAO == null ) {
			salesDAO = new SalesDAO();
		}
		return salesDAO;
	}//getInstance
	
	public List<SalesDetailVO> selectAllSalesDetail() throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectAllSalesDetail = new StringBuilder();
			selectAllSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectAllSalesDetail.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SalesDetailVO salesDetailVO = new SalesDetailVO();
				salesDetailVO.setOrderDetailNum(rs.getString("order_detail_num"));
				salesDetailVO.setPdTypeCode(rs.getString("product_type_code"));
				salesDetailVO.setPdName(rs.getString("product_name"));
				salesDetailVO.setPdQuantity(rs.getInt("product_quantity"));
				salesDetailVO.setoTempType(rs.getString("ICE_HOT"));
				salesDetailVO.setoSizeName(rs.getString("cup_size"));
				salesDetailVO.setoOptionName(rs.getString("option_name"));
				salesDetailVO.setoOptionPrice(rs.getInt("option_price"));
				salesDetailVO.setOrderDetailPrice(rs.getInt("order_detail_price"));
				salesDetailVO.setPmDate(rs.getTimestamp("payment_date"));
				
				list.add(salesDetailVO);
			}
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}//selectAllSalesDetail
	
	
	//날짜, 상품종류, 상품명 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( SalesConditionVO vo ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Date end = vo.getEndDate();
		Date start = vo.getStartDate();
		String pdType  = vo.getPdType();
		String pdName = vo.getPdName();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			;
		
			
			//dynamic query
			if( ( vo.getStartDate() !=null  || vo.getEndDate() != null ) && !vo.getPdType().equals("null") && !vo.getPdName().equals("null")  ) {
				selectSalesDetail
				.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? ) and ( pd.product_type_code= ? )	and ( pd.product_name= ? )	 ")
				.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
				;
				
				java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
				java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
				
				pstmt = con.prepareStatement(selectSalesDetail.toString());
				
				pstmt.setDate(1, sqlStartDate);
				pstmt.setDate(2, sqlEndDate);
				pstmt.setString(3, pdType);
				pstmt.setString(4, pdName);
				
			} else if ( ( vo.getStartDate() !=null  || vo.getEndDate() != null ) && !vo.getPdType().equals("null") && vo.getPdName().equals("null") ) {
				selectSalesDetail
				.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? ) and ( pd.product_type_code= ? )		")
				.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
				;
				
				java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
				java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
				
				pstmt = con.prepareStatement(selectSalesDetail.toString());
				
				pstmt.setDate(1, sqlStartDate);
				pstmt.setDate(2, sqlEndDate);
				pstmt.setString(3, pdType);
				
			} else if ( ( vo.getStartDate() !=null  || vo.getEndDate() != null ) && vo.getPdType().equals("null") && vo.getPdName().equals("null")  ) {
				selectSalesDetail
				.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? )		")
				.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
				;
				
				java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
				java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
				
				pstmt = con.prepareStatement(selectSalesDetail.toString());
				
				pstmt.setDate(1, sqlStartDate);
				pstmt.setDate(2, sqlEndDate);
				
			} else if ( ( vo.getStartDate() == null  || vo.getEndDate() == null ) && !vo.getPdType().equals("null") && !vo.getPdName().equals("null") ) {
				selectSalesDetail
				.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pd.product_type_code= ? ) and ( pd.product_name= ? )		")
				.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
				;
				
				pstmt = con.prepareStatement(selectSalesDetail.toString());
				
				pstmt.setString(1, pdType);
				pstmt.setString(2, pdName);
				
			} else if ( ( vo.getStartDate() == null  || vo.getEndDate() == null ) && !vo.getPdType().equals("null") && vo.getPdName().equals("null") ) {
				selectSalesDetail
				.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pd.product_type_code= ? )	")
				.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
				;
				
				pstmt = con.prepareStatement(selectSalesDetail.toString());
				
				pstmt.setString(1, pdType);
				
			}//end if
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SalesDetailVO salesDetailVO = new SalesDetailVO();
				salesDetailVO.setOrderDetailNum(rs.getString("order_detail_num"));
				salesDetailVO.setPdTypeCode(rs.getString("product_type_code"));
				salesDetailVO.setPdName(rs.getString("product_name"));
				salesDetailVO.setPdQuantity(rs.getInt("product_quantity"));
				salesDetailVO.setoTempType(rs.getString("ICE_HOT"));
				salesDetailVO.setoSizeName(rs.getString("cup_size"));
				salesDetailVO.setoOptionName(rs.getString("option_name"));
				salesDetailVO.setoOptionPrice(rs.getInt("option_price"));
				salesDetailVO.setOrderDetailPrice(rs.getInt("order_detail_price"));
				salesDetailVO.setPmDate(rs.getTimestamp("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//selectDaySalesDetail
	public List<SalesDetailVO> selectDaySalesDetail() throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( to_date(pay.payment_date)=trunc(sysdate) )	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SalesDetailVO salesDetailVO = new SalesDetailVO();
				salesDetailVO.setOrderDetailNum(rs.getString("order_detail_num"));
				salesDetailVO.setPdTypeCode(rs.getString("product_type_code"));
				salesDetailVO.setPdName(rs.getString("product_name"));
				salesDetailVO.setPdQuantity(rs.getInt("product_quantity"));
				salesDetailVO.setoTempType(rs.getString("ICE_HOT"));
				salesDetailVO.setoSizeName(rs.getString("cup_size"));
				salesDetailVO.setoOptionName(rs.getString("option_name"));
				salesDetailVO.setoOptionPrice(rs.getInt("option_price"));
				salesDetailVO.setOrderDetailPrice(rs.getInt("order_detail_price"));
				salesDetailVO.setPmDate(rs.getTimestamp("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	} 
	
	
	//selectMonthSalesDetail
	public List<SalesDetailVO> selectMonthSalesDetail( int month, int year) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( TO_CHAR(pay.payment_date, 'MM') = ? ) and ( TO_CHAR(pay.payment_date, 'yyyy') = ? )	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SalesDetailVO salesDetailVO = new SalesDetailVO();
				salesDetailVO.setOrderDetailNum(rs.getString("order_detail_num"));
				salesDetailVO.setPdTypeCode(rs.getString("product_type_code"));
				salesDetailVO.setPdName(rs.getString("product_name"));
				salesDetailVO.setPdQuantity(rs.getInt("product_quantity"));
				salesDetailVO.setoTempType(rs.getString("ICE_HOT"));
				salesDetailVO.setoSizeName(rs.getString("cup_size"));
				salesDetailVO.setoOptionName(rs.getString("option_name"));
				salesDetailVO.setoOptionPrice(rs.getInt("option_price"));
				salesDetailVO.setOrderDetailPrice(rs.getInt("order_detail_price"));
				salesDetailVO.setPmDate(rs.getTimestamp("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectMonthSalesDetail
	
	public List<String> selectProductType() throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectProductType = new StringBuilder();
			selectProductType
			.append("	select product_type_code from product_type	")
			;
			
			pstmt = con.prepareStatement(selectProductType.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add( rs.getString("product_type_code") );
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
	}//selectProductType
	
	public List<String> selectProductName( String pdName) throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			StringBuilder selectProductName = new StringBuilder();
			selectProductName
			.append("	select product_name from product where product_type_code = ?	")
			;
			
			pstmt = con.prepareStatement(selectProductName.toString());
			pstmt.setString(1, pdName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add( rs.getString("product_name") );
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
		
	}//selectProductName
	
	public List<String> selectSalesYear() throws SQLException {
		List<String> yearArr = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.133", "prj2_kiosk", "kiosk1234");
			
			String selectYear = "	select DISTINCT to_char( payment_date, 'yyyy' ) year from payment		";
			
			pstmt = con.prepareStatement(selectYear);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				yearArr.add(rs.getString("year"));
			}
			
			return yearArr;
			
		} finally {
			
		}//end finally
		
	}//selectSalesYear

	
}//class
