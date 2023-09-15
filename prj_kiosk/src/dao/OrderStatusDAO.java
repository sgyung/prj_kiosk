package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderStatusVO;

public class OrderStatusDAO {

	private static OrderStatusDAO orderStatusDAO;
	
	public OrderStatusDAO() {
		
	}
	
	public static OrderStatusDAO getInstance() {
		if(orderStatusDAO == null) {
			orderStatusDAO = new OrderStatusDAO();
		}
		return orderStatusDAO;
	}
	
	public List<OrderStatusVO> selectAllOrderStatus() throws SQLException {
		List<OrderStatusVO> list = new ArrayList<OrderStatusVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		StringBuilder selectAllOrderStatus= new StringBuilder();
		selectAllOrderStatus
		.append("	select  	order_num, order_status, order_time	")
		.append("	from		order_menu								")
		.append("	order by 	order_num			");
		
		
		pstmt=con.prepareStatement(selectAllOrderStatus.toString());
		
		rs=pstmt.executeQuery();
		
		
		while (rs.next()) {
			OrderStatusVO orderStatusVO = new OrderStatusVO();
			orderStatusVO.setoNum(rs.getString("order_num"));
			orderStatusVO.setoStatus(rs.getString("order_status"));
			orderStatusVO.setoDate(rs.getDate("order_time"));
			
			list.add(orderStatusVO);
		}
		
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}
	
	public OrderStatusVO selectOrderStatus(String oNum ) throws SQLException {
		OrderStatusVO orderStatusVO = null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectOrderStatus=new StringBuilder();
			selectOrderStatus
			.append("	select  	o_m.order_num,o_m.order_status,o_m.order_time,pd.product_name,o_d.cup_size,o_d.ice_hot,o_o.option_name,o_d.product_quantity,pd.product_price,pm.purchase_price	")
			.append("	from		product pd,order_detail o_d,order_option o_o,order_menu o_m,payment pm								")
			.append("	where		(o_m.order_serial_num = pm.order_serial_num) and (o_m.order_serial_num = o_d.order_serial_num) and (o_d.order_detail_num = o_o.order_detail_num(+)) and (o_d.product_code = pd.product_code) and o_m.order_num = ? 		")
			.append("	order by 	o_m.order_num			");
			
			pstmt=con.prepareStatement(selectOrderStatus.toString());
			
			pstmt.setString(1, oNum);
			
			rs=pstmt.executeQuery();
			
			if( rs.next() ) { 
				orderStatusVO = new OrderStatusVO();
				orderStatusVO.setoNum(rs.getString("order_num"));
				orderStatusVO.setoStatus(rs.getString("order_status"));
				orderStatusVO.setoDate(rs.getDate("order_time"));
				orderStatusVO.setPdName(rs.getString("product_name"));
				orderStatusVO.setoSize(rs.getString("cup_size"));
				orderStatusVO.setoTempType(rs.getString("ice_hot"));
				orderStatusVO.setOpName(rs.getString("option_name"));
				orderStatusVO.setoMount(rs.getInt("product_quantity"));
				orderStatusVO.setPdPrice(rs.getInt("product_price"));
				orderStatusVO.setTotalPrice(rs.getInt("purchase_price"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return orderStatusVO;
	}
	
	public OrderStatusVO selectDetailStatus( String oNum ) throws SQLException {
		OrderStatusVO orderStatusVO = null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectDetailStatus=new StringBuilder();
			selectDetailStatus
			.append("	select  	order_d.order_detail_num, pd.product_name, order_d.ice_hot, order_d.cup_size, listagg(order_o.option_name, ', ') within group(order by order_d.order_detail_num) option_name, order_d.product_quantity, pd.product_price+nvl(sum(order_o.option_price),0) order_detail_price")
			.append("	from		order_detail order_d, product pd, order_option order_o, product_type pd_type, payment pay, order_menu order_m")
			.append("	where		(order_m.order_serial_num = order_d.order_serial_num)and( order_m.order_serial_num = pay.order_serial_num ) and ( pay.payment_status_code = 'Y') and ( order_d.product_code = pd.product_code) and ( order_d.order_detail_num = order_o.order_detail_num(+) ) and (pd_type.product_type_code = pd.product_type_code) and od.order_num=? 	")
			.append("	group by 	order_d.order_detail_num, pd.product_name, order_d.ice_hot, order_d.cup_size, pd.product_name, order_d.product_quantity, pd.product_price			");
			
			pstmt=con.prepareStatement(selectDetailStatus.toString());
			
			pstmt.setString(1, oNum);
			
			rs=pstmt.executeQuery();
			
			if( rs.next() ) { 
				orderStatusVO = new OrderStatusVO();
				orderStatusVO.setoDetailNum(rs.getString("order_detail_num"));
				orderStatusVO.setPdName(rs.getString("product_name"));
				orderStatusVO.setoTempType(rs.getString("ice_hot"));
				orderStatusVO.setoSize(rs.getString("cup_size"));
				orderStatusVO.setOpName(rs.getString("option_name"));
				orderStatusVO.setoMount(rs.getInt("product_quantity"));
				orderStatusVO.setoDetailPrice(rs.getInt("order_detail_price"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		return orderStatusVO;
	}
	
	public void updateOrderStatus( OrderStatusVO orderStatusVO ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt=null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con=db.getConnection("localhost", "scott", "tiger");
		
			StringBuilder updateStatus = new StringBuilder();
			updateStatus
			.append("	update	order_menu				")
			.append("	set 	order_status = '제조완료'	")
			.append("	where 	order_num = ?			")
			;
			
			pstmt=con.prepareStatement(updateStatus.toString());
			
			pstmt.setString(1, orderStatusVO.getoNum());

			pstmt.execute();

		} finally {
		
			db.dbClose(null, pstmt, con);
		}//end finally
	}
	
	public static void main(String[] args) {
	OrderStatusDAO o = new OrderStatusDAO();
	try {
		System.out.println(o.selectAllOrderStatus());
//		System.out.println(o.selectOrderStatus("3"));
		System.out.println(o.selectDetailStatus("3"));
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
}
