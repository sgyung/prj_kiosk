package server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;

public class ServerRequest {
	//이미지 경로
	public static final String PATH = "C:\\kiosk\\images\\products";
	
	public ServerRequest() {
		
	}
	
	//서버로부터 이미지 불러오기
	public void loadImg() {
		ProductDAO dao = ProductDAO.getInstance();
		List<String> serverList = new ArrayList<String>();
		List<String> clientList = new ArrayList<String>();
		
		try {
			serverList = dao.selectImages();
			clientList = clientImages();
			
			System.out.println("serverList : " + serverList.toString());
			System.out.println("clientList : " + clientList.toString());
			//server와 client 이미지 비교 후 일치하는 이미지 삭제
			List<String> filteredImgList = new ArrayList<String>(serverList);
			filteredImgList.removeAll(clientList);
			
			System.out.println(filteredImgList.toString());
			
			String[] imgArr = new String[filteredImgList.size()];
			for( int i = 0; i<filteredImgList.size(); i++) {
				imgArr[i] = filteredImgList.get(i);
			}
			
			System.out.println(imgArr.toString());
			
			if( filteredImgList.isEmpty() ) {
				//실행
				System.out.println("데이터 모두 있음 바로실행");
				return;
			}
			
			//server로 이미지 리스트 전송
			try {
				requestImages(imgArr, filteredImgList);
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			}
		
		} catch (SQLException e) {
		}
		
	}//loadImg
	
	//local에 저장된 파일 목록 List에 저장
	public List<String> clientImages() {
		List<String> list = new ArrayList<String>();
		
		//images 폴더 경로 추후에 수정
		File imgFolder = new File("C:\\kiosk\\images\\products");
		if( imgFolder.exists() && imgFolder.isDirectory() ) {
			File[] imgFiles = imgFolder.listFiles();
			
			if( imgFiles != null ) {
				for( File img : imgFiles ) {
					list.add(img.getName());
				}//end for
			}//end if
		}//end if
		
		return list;
	}//clientImages
	
	@SuppressWarnings("unchecked")
	public void requestImages(String[] imgArr, List<String> filteredImgList) throws IOException, ClassNotFoundException {
		
		String ip = "192.168.10.132";
//		String ip = "192.168.10.133";
		Socket client = null;
		DataOutputStream dos = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
	
		String requestMsg = "download";
		
		try {
			
			client = new Socket(ip, 65000);
			
			dos = new DataOutputStream( client.getOutputStream() );
			
			dos.writeUTF(requestMsg);
			dos.flush();
			
			ois = new ObjectInputStream( client.getInputStream() );
			oos = new ObjectOutputStream( client.getOutputStream() );
			
			oos.writeObject(imgArr);

			//서버 요청 후 이미지 파일 받
			String downloadPath = "C:\\kiosk\\images\\products\\";
			
//			List<File> receivedImgList = new ArrayList<File>();
//			receivedImgList =	(List<File>) ois.readObject();
			
			List<byte[]> receivedImgList = new ArrayList<byte[]>();
			receivedImgList =	(List<byte[]>) ois.readObject();
//			File imgFile = (File) ois.readObject();
//			saveFile(imgFile, downloadPath);		
			System.out.println( "receive : " + receivedImgList.size());
			System.out.println( "filter : " + filteredImgList.size());
			if( receivedImgList.size() == filteredImgList.size()) {
				for(int i =0; i<receivedImgList.size(); i++) {
					saveFile(receivedImgList.get(i), downloadPath, filteredImgList.get(i));		
				}//end for
				return;
			}//end if
			
		} finally {
			if( dos != null ) { dos.close(); }
			if( oos != null ) { oos.close(); }
			if( ois != null ) { ois.close(); }
			if( client != null ) { client.close(); }
		}
		
	}//requestImages
	
	public void saveFile(byte[] file, String savePath, String imgName) throws IOException {
//		File saveDirectory = new File(savePath);
		
		
		
//		System.out.println("savePath : " + savePath);
//		System.out.println("fileName : " + file.getName().toLowerCase());
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
//		if ( !saveDirectory.exists() ) {
//			saveDirectory.mkdir();
//		}//end if
		try {
//			File outputFile = new File(saveDirectory, file.getName().toLowerCase());
//			fis = new FileInputStream(file);
			fos = new FileOutputStream(savePath + imgName);
			fos.write(file);
//		
//		byte[] buffer = new byte[1024];
//		int bytesRead;
//		
//		while( (bytesRead = fis.read(buffer)) != -1 ) {
//			fos.write(buffer, 0, bytesRead);
//		}//end while
//		
//		fos.flush();
		} finally {
			if( fis != null ) { fis.close(); }
			if( fos != null ) { fos.close(); }
		} 
		
	}//saveFile
	
}//class
