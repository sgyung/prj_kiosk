package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dao.UserDAO;
import view.AdUserView;
import vo.UserVO;

public class AdUserEvt extends MouseAdapter implements ActionListener{
	
	private AdUserView adUserView;

	public AdUserEvt( AdUserView adUserView ) {
		this.adUserView = adUserView;
		setUserInfo();
	}//AdUserEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adUserView.getAddBtn()) {
			addUser();
		}//end if
		if(e.getSource() ==  adUserView.getChangeBtn()) {
			modifyUserInfo();
		}//end if
		if(e.getSource() == adUserView.getDeleteBtn()) {
			int confirm = JOptionPane.showConfirmDialog(adUserView, "정말 삭제하시겠습니까?", "확인" ,JOptionPane.OK_CANCEL_OPTION);
			
			if( confirm == 0 ) {
				removeUser();
				return;
			}//end if
		}//end if
	}//actionPerformed
	
	@Override
	public void mouseClicked(MouseEvent e) {
		selectionUser();
	}//mouseClicked

	public void setUserInfo() {
		adUserView.getUserInfoTm().setRowCount(0);
		
		UserDAO dao = UserDAO.getInstance();
		List<UserVO> list = new ArrayList<UserVO>();
		
		try {
			list = dao.selectAllUser();
			String[] rows = new String[4];
			for( UserVO vo : list ) {
				rows[0] = vo.getuTelNum();
				rows[1] = String.valueOf(vo.getuRemainReward());
				rows[2] = vo.getuWithdrawal();
 				rows[3] = String.valueOf(vo.getuSignupDate());
 				
 				adUserView.getUserInfoTm().addRow(rows);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adUserView, "User정보를 불러오는데 실패했습니다.");
		}//end catch
		
	}//setUserInfo
	
	public void selectionUser() {
		int row = adUserView.getUserInfo().getSelectedRow();
		String userTel = String.valueOf(adUserView.getUserInfoTm().getValueAt(row, 0));
		
		UserDAO dao = UserDAO.getInstance();
		
		try {
			UserVO vo =  dao.selectUser(userTel);
			
			adUserView.setCurrentUser(vo);
			
			adUserView.getNumJtf().setText(vo.getuWithdrawal());
			adUserView.getTelJtf().setText(vo.getuTelNum());
			adUserView.getPointJtf().setText(String.valueOf(vo.getuRemainReward()));
			adUserView.getDateJtf().setText(String.valueOf(vo.getuSignupDate()));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adUserView, "회원 정보를 불러오는데 실패했습니다.");
		}
	}//selectionUser

	public void modifyUserInfo() {
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = new UserVO();
		
		if(adUserView.getCurrentUser() == null) {
			JOptionPane.showMessageDialog(adUserView, "회원을 선택해주세요.");
			return;
		}//end if
		
		try {
			vo.setuTelNum(adUserView.getTelJtf().getText());
			vo.setuRemainReward(Integer.parseInt(adUserView.getPointJtf().getText()));
			vo.setuWithdrawal(adUserView.getNumJtf().getText());
			
			int flag = dao.updateUser(vo);
			
			if( flag == 1 ) {
				JOptionPane.showMessageDialog(adUserView, "회원정보가 수정되었습니다.");
				setUserInfo();
				return;
			}//end if
		} catch( NumberFormatException nfe) {
			JOptionPane.showMessageDialog(adUserView, "적립금은 숫자만 입력 가능합니다.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adUserView, "사용자 정보를 수정하는데 실패했습니다.");
		}//catch
		
	}//modifyUserInfo
	
	public void addUser() {
		
		if( adUserView.getCurrentUser() == null ) {
			
			try {
				
				UserDAO dao = UserDAO.getInstance();
				UserVO vo = new UserVO();
				
				adUserView.getTelJtf().setEnabled(true);
				
				String userTel = adUserView.getTelJtf().getText();
				String userPoint = adUserView.getPointJtf().getText();
			
				//전화번호 패턴
				Pattern pattern = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
				Matcher match = pattern.matcher(userTel);
			
				
				if( userTel.isEmpty() ) {
					JOptionPane.showMessageDialog(adUserView, "전화번호를 입력해주세요.");
					return;
				}//end if
				if( !match.find() ) {
					JOptionPane.showMessageDialog(adUserView, "올바른 전화번호 형식을 입력해주세요.");
					return;
				}//end if
				if( userPoint.isEmpty() ) {
					JOptionPane.showMessageDialog(adUserView, "포인트를 입력해주세요.");
					return;
				}//end if
				
				vo.setuTelNum(userTel);
				vo.setuRemainReward(Integer.parseInt(userPoint));
				
				dao.insertUser(vo);
				setUserInfo();
				resetData();
				
			} catch( NumberFormatException nfe	) {
				JOptionPane.showMessageDialog(adUserView, "적립금은 숫자만 입력 가능합니다.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(adUserView, "회원 정보를 추가하는데 실패했습니다.");
			}
			
			return;
		}//end if
		if( adUserView.getCurrentUser() != null ) {
			adUserView.setCurrentUser(null);
			adUserView.getTelJtf().setEnabled(true);
			resetData();
			
			return;
		}//end if
		
	}//newUser
	
	public void removeUser() {
		UserVO vo = adUserView.getCurrentUser();
		
		if( vo == null ) {
			JOptionPane.showMessageDialog(adUserView, "회원을 선택해주세요.");
			return;
		}//end if
		if( vo.getuWithdrawal().equals("Y")) {
			JOptionPane.showMessageDialog(adUserView, "이미 삭제된 회원입니다.");
			return;
		}//end if
		
		UserDAO dao = UserDAO.getInstance();
		
		try {
			
			int flag = dao.deleteUser( vo.getuTelNum() );
			setUserInfo();
			if( flag == 1 ) {
				JOptionPane.showMessageDialog(adUserView, "회원을 삭제했습니다.");
				setUserInfo();
				resetData();
			}//end if
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(adUserView, "회원을 삭제하는데 실패했습니다.");
		}//catch
		
	}//removeUser
	
	public void resetData() {
		adUserView.getNumJtf().setText("");
		adUserView.getTelJtf().setText("");
		adUserView.getPointJtf().setText("");
		adUserView.getDateJtf().setText("");
	}//end resetData

	
}//class
