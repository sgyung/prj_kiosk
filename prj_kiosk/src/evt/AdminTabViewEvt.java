package evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import view.AdLoginView;
import view.AdminTabView;

public class AdminTabViewEvt extends WindowAdapter implements ActionListener {

	public AdminTabView adTabView;
	
	public AdminTabViewEvt(AdminTabView adTabView) {
		this.adTabView=adTabView;
	}//AdminTabViewEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == adTabView.getAdPdView().getPhotoBtn()) {
			selectionImage();
		}//end if
		if( e.getSource() == adTabView.getHomeBtn()) {
			adTabView.setVisible(false);
			new AdLoginView();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		adTabView.dispose();
	}//windowClosing
	

	public void selectionImage() {
		FileDialog fdOpen = new FileDialog(adTabView, "파일 선택", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String imgName = fdOpen.getFile();
		
		StringBuilder selectionFile = new StringBuilder();
		selectionFile
		.append(fdOpen.getDirectory())
		.append(imgName);
		
		if( imgName != null ) {
				//이미지 세팅
				ImageIcon newImg = new ImageIcon(selectionFile.toString());
				adTabView.getAdPdView().getPhotoJlb().setIcon(newImg);
		}//end if
				
	}//selectionImage
	
	public void copyImage(String selectionFile, String copyFile ) throws IOException{
		File imgFile = new File(selectionFile);
		File copyImgFile = new File(copyFile); 
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(imgFile);
			fos = new FileOutputStream(copyImgFile);
			
			int readCnt = 0;
			byte[] readData = new byte[512];
			
			while( (readCnt = fis.read(readData)) != -1 ) {
				fos.write(readData, 0, readCnt);
			}//end while
			
			fos.flush();
			
		} finally {
			if( fis != null ) { fis.close(); }
			if( fos != null ) { fos.close(); }
		}//end finally
	}//coptImage

}//class
