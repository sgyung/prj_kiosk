package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.ProductVO;

public class ProductDAO {

	private static ProductDAO productDAO;
	
	private ProductDAO() {
		
	}
	
	public static ProductDAO getInstance() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}
	
	public List<ProductVO> selectAllProduct() throws SQLException{
		List<ProductVO> productList = new ArrayList<ProductVO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ProductVO pVO = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			stmt = con.createStatement();
			
			StringBuilder selecAllProduct = new StringBuilder();
			selecAllProduct
			.append("select product_code, product_type_code, image, product_name, product_price, product_delete, product_date from product");
			
			rs = stmt.executeQuery(selecAllProduct.toString());
			
			while(rs.next()) {
				pVO = new ProductVO(rs.getString("product_code"),rs.getString("product_type_code"),rs.getString("image"),
						rs.getString("product_name"),rs.getInt("product_price"), rs.getString("product_delete"),rs.getDate("product_date"));
				productList.add(pVO);
			}
	
		}finally {
			db.dbClose(rs, stmt, con);
		}
		return productList;
	}
	
	public ProductVO selectProduct(String productCode) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pVO = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");

			StringBuilder selectProduct = new StringBuilder();
			selectProduct
			.append("	select product_code, product_type_code, image, product_name, product_price, product_delete, product_date ")
			.append("	from product                                                                    						 ")
			.append("	where product_code= ? 																					 ");
			
			pstmt = con.prepareStatement(selectProduct.toString());
			
			pstmt.setString(1, productCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pVO = new ProductVO(rs.getString("product_code"),rs.getString("product_type_code"),rs.getString("image"),
					rs.getString("product_name"),rs.getInt("product_price"), rs.getString("product_delete"),rs.getDate("product_date"));
			}
		}finally {
			db.dbClose(rs, pstmt, con);
		}
		return pVO;	
	}
	
	public List<String> selectProductType() throws SQLException {// 메소드명 변경 예정 체크, String[] -> list로 변경
		List<String> productTypeList = new ArrayList<String>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			stmt = con.createStatement();
			
			StringBuilder selectProductType = new StringBuilder();
			selectProductType
			.append("select product_type_name from product_type");

			rs = stmt.executeQuery(selectProductType.toString());
			
			while(rs.next()) {
				productTypeList.add(rs.getString("product_type_name"));
			}
		}finally {
			db.dbClose(rs, stmt, con);
		}
		return productTypeList;
	}
	
	public void insertProduct(ProductVO pVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder insertProduct = new StringBuilder();
			insertProduct
			.append("insert into product(product_code, product_type_code, image, product_name, product_price, product_delete, product_date)")
			.append("values ( ? || cof_seq.nextval,?,?,?,?,?,?) 																								");
			
			pstmt = con.prepareStatement(insertProduct.toString());
			
			pstmt.setString(1, pVO.getPdCode());
			pstmt.setString(2, pVO.getPdTypeCode());
			pstmt.setString(3, pVO.getPdImageName());
			pstmt.setString(4, pVO.getPdName());
			pstmt.setInt(5, pVO.getPdPrice());
			pstmt.setString(6, pVO.getPdDelete());
			pstmt.setDate(7, pVO.getPdInputDate());
			
			pstmt.executeUpdate();
		}finally {
			db.dbClose(null, pstmt, con);
		}
	}
	
	public int updateProduct(ProductVO pVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCnt = 0;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder updateProduct = new StringBuilder();
			updateProduct
			.append("	update product									")
			.append("	set product_name = ? , product_price = ? 		")
			.append("	where product_code = ?							");
		
			pstmt = con.prepareStatement(updateProduct.toString());
			
			pstmt.setString(1, pVO.getPdName());
			pstmt.setInt(2, pVO.getPdPrice());
			pstmt.setString(3, pVO.getPdCode());
			
			rowCnt = pstmt.executeUpdate();  
		}finally {
			db.dbClose(null, pstmt, con);
		}
		return rowCnt;
	}
	
	public int deleteProduct(ProductVO pVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCnt = 0;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder deleteProduct = new StringBuilder();
			deleteProduct
			.append("	delete from product			")
			.append("	where product_code = ?		");
			
			pstmt = con.prepareStatement(deleteProduct.toString());
			
			pstmt.setString(1, pVO.getPdCode());
			
			rowCnt = pstmt.executeUpdate();
		}finally {
			db.dbClose(null, pstmt, con);
		}
		return rowCnt;
	}
	
	
//	public static void main(String[] args) {
//		ProductDAO productDAO = ProductDAO.getInstance();
//		
//		try {
//			List<String>list = productDAO.selectProductType();
//			for(String product : list) {
//				System.out.println(product);
//			
//			}
//			ProductVO pVO = new ProductVO("cof_", "cof", "coffee.jpg", "카페라떼", 4000, "N", new Date(System.currentTimeMillis()));
//			productDAO.insertProduct(pVO);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}//main
	
	
}

