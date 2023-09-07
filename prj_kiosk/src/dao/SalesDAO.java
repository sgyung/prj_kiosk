package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			con = db.getConnection("localhost", "scott", "tiger");
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}//selectAllSalesDetail
	
	//overloading
	//상품종류만 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( String pdType ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pd.product_type_code= ? )	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			pstmt.setString(1, pdType);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//overloading
	//상품종류, 상품이름 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( String pdType, String pdName ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pd.product_type_code= ? ) and ( pd.product_name= ? )		")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			pstmt.setString(1, pdType);
			pstmt.setString(2, pdName);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//overloading
	//날짜만 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( Date start, Date end ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? )		")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
			java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
			
			pstmt.setDate(1, sqlStartDate);
			pstmt.setDate(2, sqlEndDate);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//overloading
	//날짜, 상품종류만 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( Date start, Date end, String pdType ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? ) and ( pd.product_type_code= ? )		")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
			java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
			
			pstmt.setDate(1, sqlStartDate);
			pstmt.setDate(2, sqlEndDate);
			pstmt.setString(3, pdType);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//overloading
	//날짜, 상품종류, 상품명 선택했을 때
	public List<SalesDetailVO> selectSalesDetail( Date start, Date end, String pdType,  String pdName ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( pay.payment_date between ? and ? ) and ( pd.product_type_code= ? )	and ( pd.product_name= ? )	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			java.sql.Date sqlStartDate = new java.sql.Date( start.getTime() );
			java.sql.Date sqlEndDate = new java.sql.Date( end.getTime() );
			
			pstmt.setDate(1, sqlStartDate);
			pstmt.setDate(2, sqlEndDate);
			pstmt.setString(3, pdType);
			pstmt.setString(4, pdName);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
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
			
			con = db.getConnection("localhost", "scott", "tiger");
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	} 
	
	
	//selectMonthSalesDetail
	public List<SalesDetailVO> selectMonthSalesDetail( int month ) throws SQLException{
		List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectSalesDetail = new StringBuilder();
			selectSalesDetail
			.append("	select order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, nvl(sum(order_o.option_price),0) option_price, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price, pay.payment_date	")
			.append("	from order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m		")
			.append("	where (order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code)	and ( TO_CHAR(pay.payment_date, 'MM') = ? )	")
			.append("	group by order_d.order_detail_num, pd.product_type_code, pd.product_name, order_d.product_quantity, order_d.ice_hot, order_d.cup_size, pd.product_name, pd.product_price, pay.payment_date	")
			;
			
			pstmt = con.prepareStatement(selectSalesDetail.toString());
			
			pstmt.setInt(1, month);
			
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
				salesDetailVO.setPmDate(rs.getDate("payment_date"));
				
				list.add(salesDetailVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	//TEST
	public static void main(String[] args) {
		SalesDAO dao = SalesDAO.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = "2023-09-01";
		String endDate = "2023-09-09";
		String pdType = "";
		String pdName = "";
		
		
//		if( !startDate.isEmpty() || !endDate.isEmpty() ) {
//			
//		}
		
		try {
			
//			Date startUtilDate = sdf.parse(startDate);
//			Date startUtilEnd = sdf.parse(endDate);
			List<SalesDetailVO> list = new ArrayList<SalesDetailVO>();
			
			//조회조건
//			if( ( !startDate.isEmpty() || !endDate.isEmpty() ) && !pdType.isEmpty() && !pdName.isEmpty() ) {
//				Date startUtilDate = sdf.parse(startDate);
//				Date startUtilEnd = sdf.parse(endDate);
//				list = dao.selectSalesDetail(startUtilDate, startUtilEnd, pdType, pdName);
//				System.out.println("1");
//			} else if( ( !startDate.isEmpty() || !endDate.isEmpty() ) && !pdType.isEmpty() && pdName.isEmpty() ) {
//				Date startUtilDate = sdf.parse(startDate);
//				Date startUtilEnd = sdf.parse(endDate);
//				list = dao.selectSalesDetail(startUtilDate, startUtilEnd, pdType );
//				System.out.println("2");
//			} else if( ( !startDate.isEmpty() || !endDate.isEmpty() ) && pdType.isEmpty() && pdName.isEmpty() ) {
//				Date startUtilDate = sdf.parse(startDate);
//				Date startUtilEnd = sdf.parse(endDate);
//				list = dao.selectSalesDetail(startUtilDate, startUtilEnd );
//				System.out.println("3");
//			} else if( ( startDate.isEmpty() || endDate.isEmpty() ) && !pdType.isEmpty() && pdName.isEmpty() ) {
//				list = dao.selectSalesDetail(pdType);
//				System.out.println("4");
//			} else if( ( startDate.isEmpty() || endDate.isEmpty() ) && !pdType.isEmpty() && !pdName.isEmpty() ) {
//				list = dao.selectSalesDetail(pdType, pdName);
//				System.out.println("5");
//			}
			
//			list = dao.selectDaySalesDetail();
			list = dao.selectMonthSalesDetail(9);
			
			for( SalesDetailVO vo : list) {
				System.out.println(vo);
			}
			
//		} catch (ParseException e1) {
//			System.out.println("옳바른 날짜 형식이 아닙니다.");
//			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}//class
