package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import dao.ProductDAO;
import view.AdProductView;
import vo.ProductVO;

public class AdProductEvt extends MouseAdapter implements ActionListener {
	
	private AdProductView adPdView;
	
	public AdProductEvt( AdProductView adPdView ) {
		this.adPdView = adPdView;
		setProductInfo();
		setProductType();
	}//AdProductEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adPdView.getChangeBtn()) {
			modifyProduct();
		}//end if
		if(e.getSource() == adPdView.getDeleteBtn()) {
			removeProduct();
		}//end if
		if(e.getSource() == adPdView.getNewBtn()) {
			try {
				addNewProduct();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(adPdView, "이미지 업로드 실패");
			}
		}//end if
		
	
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		selectionProduct();
	}//mouseClicked
	
	
	public void setProductInfo() {
		adPdView.getProductInfoDtm().setRowCount(0);
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		String[] rows = new String[6]; 
		
		ProductDAO dao = ProductDAO.getInstance();
		
		try {
			list = dao.selectAllProduct();
			
			for( ProductVO vo : list ) {
				rows[0] = vo.getPdCode();
				rows[1] = vo.getPdTypeCode();
				rows[2] = vo.getPdName();
				rows[3] = String.valueOf(vo.getPdPrice());
				rows[4] = String.valueOf(vo.getPdInputDate());
				rows[5] = vo.getPdDelete();
				
				adPdView.getProductInfoDtm().addRow(rows);
			}//end for	
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adPdView, "상품 리스트를 받아오는데 실패하였습니다.");
		}//end catch
		
	}//setProductInfo
	
	public void setProductType() {
		List<String> list = new ArrayList<String>();
		String defaultType = "상품 종류를 선택해주세요.";
		
		ProductDAO dao = ProductDAO.getInstance();
		
		list.add(defaultType);
		
		try {
			list = dao.selectProductType();
			
			adPdView.getProductType().addAll(list);
			adPdView.getProductType().setSelectedItem(defaultType);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adPdView, "상품 종류를 불러오는데 실패했습니다.");
		}//end catch
		
		
	}// setProductType
	
	public void selectionProduct(){
		int row = adPdView.getProductInfo().getSelectedRow();
		
		String productCode = String.valueOf(adPdView.getProductInfoDtm().getValueAt(row, 0));
		
		ProductDAO dao = ProductDAO.getInstance();
		
		try {
			ProductVO vo =  dao.selectProduct(productCode);
			adPdView.setCurrentProduct(vo);
			
			
			String imgName = vo.getPdImageName();
			ImageIcon img = new ImageIcon("C:\\kiosk\\images\\products\\"+imgName);
			adPdView.getPhotoJlb().setIcon(img);
			adPdView.getCodeJtf().setText(productCode);
			adPdView.getProductType().setSelectedItem(vo.getPdTypeCode());
			adPdView.getNameJtf().setText(vo.getPdName()); 
			adPdView.getPriceJtf().setText(String.valueOf(vo.getPdPrice()));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adPdView, "상품을 불러오는데 실패했습니다.");
		}//end catch
		
	}//selectionProduct
	
	public void modifyProduct() {
		ProductDAO dao = ProductDAO.getInstance();
		ProductVO currentVO = adPdView.getCurrentProduct();
		
		String deleteState = currentVO.getPdDelete();
		
		if(deleteState.equals("Y")) {
			JOptionPane.showMessageDialog(adPdView, "해당 상품은 삭제된 상태입니다.");
			return;
		}//end if
		if(adPdView.getNameJtf().getText().isEmpty()){
			JOptionPane.showMessageDialog(adPdView, "상품 이름을 입력해주세요.");
			return;
		}//end if
		
		try {
			currentVO.setPdImageName(adPdView.getPhotoJlb().getText());
			currentVO.setPdCode(adPdView.getCodeJtf().getText());
			currentVO.setPdTypeCode(String.valueOf(adPdView.getProductType().getSelectedItem()));
			currentVO.setPdName(adPdView.getNameJtf().getText());
			currentVO.setPdPrice(Integer.parseInt(adPdView.getPriceJtf().getText()));
			
			int flag = dao.updateProduct(currentVO);
				
			if( flag == 1 ) {
				JOptionPane.showMessageDialog(adPdView, "상품을 수정하였습니다.");
				setProductInfo();
				return;
			}//end if
		} catch(NumberFormatException numE ) {
			JOptionPane.showMessageDialog(adPdView, "가격은 숫자만 입력 가능합니다.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adPdView, "상품을 수정하는데 실패했습니다.");
		}//end catch
		
	}//modifyProduct
	
	public void removeProduct() {
		ProductVO currentVO = adPdView.getCurrentProduct();
		ProductDAO dao = ProductDAO.getInstance();
		String deleteState = currentVO.getPdDelete();
		
		int flag = JOptionPane.showConfirmDialog(adPdView,"정말 삭제 하시겠습니까?","삭제", JOptionPane.OK_CANCEL_OPTION);
		
		if( deleteState.equals("Y")) {
			JOptionPane.showMessageDialog(adPdView, "이미 삭제된 상태입니다.");
			return;
		}//end if
		
		if( flag == 0 ) {
			try {
				currentVO.setPdDelete("Y");
				int state = dao.deleteProduct(currentVO);
				if( state == 1) {
					JOptionPane.showMessageDialog(adPdView, "상품을 삭제하였습니다.");
					setProductInfo();
				}//end if
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(adPdView, "상품을 삭제하는데 실패했습니다.");
			}//end catch
			return;
		}//end if 
	}//removeProduct
	
	public void addNewProduct() throws IOException {
		
		if(adPdView.getCurrentProduct() == null) {
			//등록 예외
			if( adPdView.getPhotoJlb().getIcon() == null ) {
				JOptionPane.showMessageDialog(adPdView, "상품 사진을 등록해주세요.");
				return;
			}//end if
			if( String.valueOf(adPdView.getProductType().getSelectedItem()).equals("상품 종류를 선택해주세요.") ) {
				JOptionPane.showMessageDialog(adPdView, "상품 종류를 선택해주세요.");
				return;
			}//end if
			if( adPdView.getNameJtf().getText().isEmpty() ) {
				JOptionPane.showMessageDialog(adPdView, "상품명을 입력해주세요.");
				return;
			}//end if
			if( adPdView.getPriceJtf().getText().isEmpty() ) {
				JOptionPane.showMessageDialog(adPdView, "가격을 입력해주세요.");
				return;
			}//end if
			
			ProductDAO dao = ProductDAO.getInstance();
			ProductVO vo = new ProductVO();
			
			
			try {
				String pdTypeCode = String.valueOf(adPdView.getProductType().getSelectedItem());
				StringBuilder pdCode = new StringBuilder();
				pdCode
				.append(pdTypeCode)
//				.append("_")
				;
				
				String imgPath = adPdView.getPhotoJlb().getIcon().toString();
				String imgName =imgPath.substring(imgPath.lastIndexOf("\\")+1, imgPath.length()); 
				
				System.out.println("imgpath : " + imgPath);
				
				vo.setPdCode(pdCode.toString());
				vo.setPdTypeCode(pdTypeCode);
				vo.setPdImageName(imgName);
				vo.setPdName(adPdView.getNameJtf().getText());
				vo.setPdPrice(Integer.parseInt(adPdView.getPriceJtf().getText()));
				
				uploadImg(imgPath);
				dao.insertProduct(vo);
				JOptionPane.showMessageDialog(adPdView, "상품을 추가하였습니다.");
				resetData();
				setProductInfo();
				
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(adPdView, "가격은 숫자만 입력 가능합니다.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(adPdView, "상품을 추가하는데 실패하였습니다.");
				e.printStackTrace();
			}
			
			return;
			
		}//end if
		if(adPdView.getCurrentProduct() != null) {
			adPdView.setCurrentProduct(null);
			resetData();
			return;
		}//end if
		
	}//addNewProduct
	
	public void resetData() {
		adPdView.getPhotoJlb().setIcon(null);
		adPdView.getCodeJtf().setText("자동생성");
		adPdView.getProductType().setSelectedItem("상품 종류를 선택해주세요.");
		adPdView.getNameJtf().setText("");
		adPdView.getPriceJtf().setText("");
	}//resetData
	
	public void uploadImg( String imgPath ) throws IOException {
		String ip = "192.168.10.132";
		Socket client = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		String requestMsg = "upload";
		
		try {
			
			client = new Socket(ip, 65000);
			System.out.println("서버접속");
			
			dis = new DataInputStream( client.getInputStream() );
			dos = new DataOutputStream( client.getOutputStream() );
			
			//선택파일
			File imgFile = new File(imgPath);
			//파일이름 ( 자를 기준 문자열 확인 )
			String fileName = imgPath.substring(imgPath.lastIndexOf("\\")+1, imgPath.length()); 
			byte[] imgByte = Files.readAllBytes(imgFile.toPath());
			
			//서버에서 파일 이름을 알 수 없기때문에 파일 이름을 함께 전송
			dos.writeUTF( requestMsg );
			dos.writeUTF( fileName );
			dos.write(imgByte);
			
			dos.flush();
		
			
		} finally {
			if( dos != null ) { dos.close(); }
			if( dis != null ) { dis.close(); }
			if( oos != null ) { oos.close(); }
			if( ois != null ) { ois.close(); }
			if( client != null ) { client.close(); }
		}
	}//TestClient
	
}//class
