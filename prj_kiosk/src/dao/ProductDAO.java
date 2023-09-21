package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import vo.MenuListVO;
import vo.ProductVO;
import vo.UseInventoryVO;

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
			} else if(!rs.next()){
				System.out.println("없음");
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
			//수정 product_type_name -> product_type_code
			.append("select product_type_code from product_type");

			rs = stmt.executeQuery(selectProductType.toString());
			
			while(rs.next()) {
				//수정 product_type_name -> product_type_code
				productTypeList.add(rs.getString("product_type_code"));
			}
		}finally {
			db.dbClose(rs, stmt, con);
		}
		return productTypeList;
	}
	
	//수정
	public void insertProduct(ProductVO pVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String pdTypeCode = pVO.getPdTypeCode();
			
			StringBuilder insertProduct = new StringBuilder();
			insertProduct
			.append("	insert into product(product_code, product_type_code, image, product_name, product_price, product_delete, product_date)	");
			if( pdTypeCode.equals("cof")) {
				insertProduct.append("	values ( ? || cof_seq.nextval, ?, ?, ?, ?, ?, sysdate) 																									");
			}//end if
			if( pdTypeCode.equals("bev")) {
				insertProduct.append("	values ( ? || bev_seq.nextval, ?, ?, ?, ?, ?, sysdate) 																									");
			}//end if
			if( pdTypeCode.equals("des")) {
				insertProduct.append("	values ( ? || des_seq.nextval, ?, ?, ?, ?, ?, sysdate) 																									");
			}//end if

			pstmt = con.prepareStatement(insertProduct.toString());
			
			StringBuilder pdCode = new StringBuilder();
			pdCode.append(pdTypeCode).append("_");
			
			pstmt.setString(1,pdCode.toString());
			pstmt.setString(2, pdTypeCode);
			pstmt.setString(3, pVO.getPdImageName());
			pstmt.setString(4, pVO.getPdName());
			pstmt.setInt(5, pVO.getPdPrice());
			pstmt.setString(6, "N");
			
			pstmt.executeUpdate();
			
		}finally {
			db.dbClose(null, pstmt, con);
		}
	}//insertProduct
	
	
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
	
	//delete 수정
	public int deleteProduct(ProductVO pVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCnt = 0;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder deleteProduct = new StringBuilder();
			deleteProduct
			.append("	update product			")
			.append("	set product_delete= ?	")
			.append("	where product_code= ?	")
			;
			
			pstmt = con.prepareStatement(deleteProduct.toString());
			
			pstmt.setString(1, pVO.getPdDelete());
			pstmt.setString(2, pVO.getPdCode());
			
			rowCnt = pstmt.executeUpdate();
		}finally {
			db.dbClose(null, pstmt, con);
		}
		return rowCnt;
	}
	
	public List<MenuListVO> selectMenuList(String type) throws SQLException{
		List<MenuListVO> list = new ArrayList<MenuListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuListVO mlVO = null; 
		
		DbConn db = DbConn.getInstance();
		try{
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectMenuList = new StringBuilder();
			selectMenuList
			.append(	"select product_code, product_name, product_price, image		")
			.append(	"from product									")
			.append(	"where product_type_code = ?					");
			
			pstmt=con.prepareStatement(selectMenuList.toString());
			
			pstmt.setString(1, type);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mlVO = new MenuListVO(rs.getString("product_code") ,rs.getString("product_name"),rs.getInt("product_price"),rs.getString("image"));
				
				list.add(mlVO);
			}
			
		}finally {
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}//selectMenuList

	public List<String> selectImages() throws SQLException{
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			String selectImage = "	select image from product	";
			
			pstmt = con.prepareStatement(selectImage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(rs.getString("image"));
				
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
	}//selectImages
	
	// selectDeleteCheck
		public List<String> selectDeleteCheck(String productType) throws SQLException{
			List<String> list = new ArrayList<String>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			DbConn db = DbConn.getInstance();
			
			try {
				
				con = db.getConnection("localhost", "scott", "tiger");
				
				StringBuilder selectDeleteCheck = new StringBuilder();
				selectDeleteCheck
				.append("	select product_delete		")
				.append("	from product				")
				.append("	where product_type_code = ? ");
				
				
				pstmt = con.prepareStatement(selectDeleteCheck.toString());
				
				pstmt.setString(1, productType);
					
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					list.add(rs.getString("product_delete"));
					
				}//end while
				
			} finally {
				db.dbClose(rs, pstmt, con);
			}
			
			return list;
		}// selectDeleteCheck
		
		public List<UseInventoryVO> selectUseInventory(String pdCode) throws SQLException{
			List<UseInventoryVO> list = new ArrayList<UseInventoryVO>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			DbConn db = DbConn.getInstance();
			
			try {
				con = db.getConnection("localhost", "scott", "tiger");
				
				StringBuilder useInventory = new StringBuilder();
				useInventory
				.append("	select prod.product_name, prod.product_code, inv.inventory_code, inv.inventory_name, uinv.use_quantity, inv.inventory_quantity		")
				.append("	from product prod, use_inventory uinv, inventory inv																				")
				.append("	where( prod.product_code = uinv.product_code) and (inv.inventory_code = uinv.inventory_code) and substr(prod.product_code,1,3) = ?	");
				
				pstmt = con.prepareStatement(useInventory.toString());
				
				pstmt.setString(1, pdCode);
				
				rs = pstmt.executeQuery();
				
				UseInventoryVO uiVO = null;
				while(rs.next()) {
					uiVO = new UseInventoryVO(rs.getString("product_name"), rs.getString("product_code"), rs.getString("inventory_code"),
							rs.getString("inventory_name"), rs.getInt("use_quantity"), rs.getInt("inventory_quantity"));
					
					list.add(uiVO);
				}
				
			}finally {
				db.dbClose(rs, pstmt, con);
			}
			return list;
		}
	
}//class

