package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dao.PaymentDAO;
import dao.UserDAO;
import view.MainView;
import view.MenuView;
import view.PaymentView;
import vo.OrderDetailVO;
import vo.PaymentVO;
import vo.UserVO;

public class PaymentEvt extends WindowAdapter implements ActionListener{
	
	private PaymentView paymentView;
	private MenuView menuView;
	private int orderPrice;
	private int discountPrice;
	   
	   
	   public PaymentEvt(PaymentView paymentView, MenuView menuView) {
	      this.paymentView = paymentView;
	      this.menuView = menuView;
	      setOrederDetailList();
	   }//PaymentEvt
	   
	   @Override
	public void windowClosing(WindowEvent e) {
		   paymentView.dispose();
	}//windowClosing

	@Override
	   public void actionPerformed(ActionEvent e) {
	      if(e.getSource() == paymentView.getSearchUserBtn()) {
	    	  checkUser();
	      }//end if
	      if(e.getSource() == paymentView.getUsePointBtn()) {
	    	  usePoint();
	      }//end if
	      if(e.getSource() == paymentView.getCancelPointBtn()) {
	    	  cancelPoint();
	      }//end if
	      if(e.getSource() == paymentView.getBeforeBtn()) {
	    	  paymentView.dispose();
	      }//end if
	      if(e.getSource() == paymentView.getPaymentBtn()) {
	    	  setPayMethod();
	    	  if( !isPay() ) {
	    		  JOptionPane.showMessageDialog(paymentView, "결제방법을 선택해주세요.");
	    		  return;
	    	  }//end if
    		  setUserPoint();
	    	  payment();	    	  
	      }//end if
	      
	   }//actionPerformed
	   
	   //회원확인
	   public void checkUser() {
		   String inputTel = JOptionPane.showInputDialog("전화번호를 입력해주세요.");
		   
		   //입력창을 취소했을 때
		   if ( inputTel == null ) {
			   return;
		   }//end if
		   
		   //전화번호 패턴
		   Pattern pattern = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
		   Matcher match = pattern.matcher(inputTel);
		   
		   //핸드폰번호 형식에 맞지 않으면 Message
		   if( !match.find() ) {
			   JOptionPane.showMessageDialog(paymentView, "전화번호를 확인해주세요\n 예 : 010-0000-0000");
			   return;
		   }//end if
		   
		   UserDAO dao = UserDAO.getInstance();
		   
		   try {
			   UserVO vo = dao.selectUser(inputTel);
			   
			   //선택된 회원정보가 없으면 회원가입
			   if( vo == null ) {
				   int flag = JOptionPane.showConfirmDialog(paymentView,   "일치하는 회원정보가 없습니다\n회원가입 하시겠습니까?", "가입확인", JOptionPane.OK_CANCEL_OPTION);
				   
				   if( flag == 0 ) {
					   joinUser(inputTel);
					   setUser();
					   return;
				   }//end if
				   return;
			   }//end if
			   //입력한 회원정보가 삭제된 상태일때
			   if( vo.getuWithdrawal().equals("Y")) {
				   int flag = JOptionPane.showConfirmDialog(paymentView,   "일치하는 회원정보가 없습니다\n회원가입 하시겠습니까?", "가입확인", JOptionPane.OK_CANCEL_OPTION);
				   
				   if( flag == 0 ) {
					   reJoinUser(inputTel);
					   setUser();
					   return;
				   }//end if
			   }//end if
			   //선택된 회원정보가 있으면 포인트/현재사용자 세팅
			   if( vo != null ) {
				   paymentView.setCurrentUserVO(vo);
				   setUser();
			   }//end if
			   
		   } catch (SQLException e) {
			   JOptionPane.showMessageDialog(paymentView, "회원정보를 불러오는데 실패했습니다.");
		   }//end catch
		   
		   UserVO vo = new UserVO();
		   vo.setuTelNum(inputTel);
		   vo.setuRemainReward(discountPrice);

	   }//checkUser
	   
	   //선택된 회원 setting
	   public void setUser() {
		   
		   UserVO vo = paymentView.getCurrentUserVO();
		   String point = String.valueOf(vo.getuRemainReward());
		   
		   paymentView.getUseAvailablePointLabel().setText(point);
		   
	   }//set User
	   
	   //회원가입
	   public void joinUser( String tel) {
		   UserVO vo = new UserVO();
		   vo.setuTelNum(tel);
		   vo.setuRemainReward(0);
		   
		   UserDAO dao = UserDAO.getInstance();
		   
		   try {
			   dao.insertUser(vo);
			   
			   JOptionPane.showMessageDialog(paymentView, "가입되었습니다.");
			   
			   paymentView.setCurrentUserVO(vo);
		
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(paymentView, "회원가입에 실패했습니다.");
			}//end catch
		  	   
	   }//joinUser
	   
	   public void reJoinUser(String tel) {
		   UserVO vo = new UserVO();
		   vo.setuTelNum(tel);
		   vo.setuRemainReward(0);
		   
		   UserDAO dao = UserDAO.getInstance();
		   
		   try {
			   dao.deleteCancelUser(vo);
			   
			   paymentView.setCurrentUserVO(vo);
			   
			   JOptionPane.showMessageDialog(paymentView, "가입되었습니다.");
		   } catch (SQLException e) {
			   JOptionPane.showMessageDialog(paymentView, "회원가입 실패");
		   }//end catch
		   
	   }//reJoinUser
	   
	   //포인트 사용
	   public void usePoint() {
		   
		   UserVO currentUser = paymentView.getCurrentUserVO();
		 
		   //회원 선택을 하지않고 눌렀을 경우
		   if( currentUser == null ) {
			   JOptionPane.showMessageDialog(paymentView, "회원 조회를 해주세요.");
			   return;
		   }//end if
		   
		   try {
			   String pointText = paymentView.getUsePointTextField().getText();
			   
			   //입력값이 없을때 Message
			   if( pointText.isEmpty() ) {
				   JOptionPane.showMessageDialog(paymentView, "사용할 포인트를 입력해주세요.");
				   return;
			   }//end if
			   
			   int point = Integer.parseInt(paymentView.getUsePointTextField().getText());
			   
			   //입력포인트가 보유포인트보다 더 클 때
			   if( currentUser.getuRemainReward() < point ) {
				   JOptionPane.showMessageDialog(paymentView, "보유한 포인트내에서 사용 가능합니다.");
				   return;
			   }//end if
			   
			   //포인트가 주문가격 보다 더 클 경우
			   if( paymentView.getCurrentPayVO().getOrderPrice() < point ) {
				   JOptionPane.showMessageDialog(paymentView, "포인트는 주문가격보다 클 수 없습니다.");
				   return;
			   }//end if
			   
			   //VO에 세팅
			   @SuppressWarnings("unused")
			int currentPoint = currentUser.getuRemainReward();
			   
			   //currentUserVO에 setting
			   //currentUser.setuRemainReward(currentPoint - point);
			   //currentPayVO에 setting
			   paymentView.getCurrentPayVO().setDiscountPrice(point);
			   
			   //TextField에 setting
			   setPrice();
			   
			   StringBuilder msg = new StringBuilder();
			   msg
			   .append(point)
			   .append(" 포인트 사용되었습니다.")
			   ;
			   
			   JOptionPane.showMessageDialog(paymentView, msg );
			   //getCurrentPayVO setting
			   //paymentView.getCurrentPayVO().setDiscountPrice(point);
		   
		   } catch(NumberFormatException nfe ) {
			   JOptionPane.showMessageDialog(paymentView, "숫자만 입력 가능합니다.");
		   }//end catch
		   
	   }//usePoint
	   
	   public void cancelPoint() {
		   
		   if( paymentView.getCurrentUserVO() == null ) {
			   JOptionPane.showMessageDialog(paymentView, "회원조회를 해주세요.");
			   return;
		   }//end if
		   
		   if( paymentView.getCurrentPayVO().getDiscountPrice() == 0 ) {
			   JOptionPane.showMessageDialog(paymentView, "취소할 포인트가 없습니다.");
			   return;
		   }//end if
		   
		   int flag = JOptionPane.showConfirmDialog(paymentView, "포인트 사용을 취소하시겠습니까?" ,"취소", JOptionPane.OK_CANCEL_OPTION);
		   
		   if( flag == 0 ) {
			   PaymentVO currentPay = paymentView.getCurrentPayVO();
			   UserVO currentUser = paymentView.getCurrentUserVO();
			   
			   @SuppressWarnings("unused")
			int discoundPrice = currentPay.getDiscountPrice();
			   
			   //CurrentPayVO setting
			   currentPay.setDiscountPrice(0);
			   //CurrentUserVO setting
			   currentUser.setuRemainReward(currentUser.getuRemainReward() );
//			   + discoundPrice
			   setPrice();
			   
			   paymentView.getUsePointTextField().setText("");
			   JOptionPane.showMessageDialog(paymentView, "취소되었습니다.");
		   }//end if
		   
		   if( flag == 1 ) {
			   return;
		   }//end if
		   
	   }//cancelPoint
	   
	   
	   public void setOrederDetailList() {
	      List<OrderDetailVO> list = MenuView.menuSelectedList;
	      orderPrice = 0;
	      discountPrice = 0;

	      paymentView.getOrderDetaildtm().setRowCount(0);
	      String[] rowData = null;
	      
	      for(int i = 0; i < list.size(); i++) {
	         rowData = new String[7];
	         
	         rowData[0] = String.valueOf(i+1);
	         rowData[1] = list.get(i).getPdName();
	         rowData[2] = String.valueOf(list.get(i).getoQuantity());
	         if("I".equals(list.get(i).getoTempType())) {
	            rowData[3] = "ice";
	         }else if("H".equals(list.get(i).getoTempType())) {
	            rowData[3] = "hot";
	         }else {
	            rowData[3] = "";
	         }
	         if(!list.get(i).getoOptionName().isEmpty() && list.get(i).getoKnifeOption() == null) {
	        	 rowData[4] = list.get(i).getoOptionName();
	         }else if(list.get(i).getoOptionName().isEmpty() && list.get(i).getoKnifeOption() != null) {
	        	 rowData[4] = list.get(i).getoKnifeOption();
	         }else if(list.get(i).getoOptionName().isEmpty() && list.get(i).getoKnifeOption() == null) {
	        	 rowData[4] = "";
	         }
	         if("R".equals(list.get(i).getoSizeName())) {
	            rowData[5] = "Regular";
	         }else if("E".equals(list.get(i).getoSizeName())) {
	            rowData[5] = "Extra";
	         }else {
	            rowData[5] = "";
	         }
	         rowData[6] = String.valueOf(list.get(i).getPdPrice());   
	         
	         orderPrice += list.get(i).getPdPrice();
	         
	         paymentView.getOrderDetaildtm().addRow(rowData);
	      }
	      
	      paymentView.getOrderPrice().setText(String.valueOf(orderPrice));
	      
	      paymentView.getCurrentPayVO().setOrderPrice(orderPrice);
	      setPrice();
	      
	   }//end setOrederDetailList
	   
	   
	   //가격 세팅하는 메소드 제안
	   public void setPrice() {
		   
		   PaymentVO currentPay = paymentView.getCurrentPayVO();
		   int orderPrice = currentPay.getOrderPrice();
		   int point = currentPay.getDiscountPrice();
		   
		   paymentView.getDiscount().setText(String.valueOf(point));
		   paymentView.getPurchasePrice().setText(String.valueOf(orderPrice - point));
		   
	   }//setPrice
	
	   public void setPayMethod() {
		   PaymentVO currentPay = paymentView.getCurrentPayVO();
		      
	      if(paymentView.getCardBtn().isSelected()) {
	    	  currentPay.setPmTypeCode(0);
	    	  currentPay.setPmCode("카드");
	      }else if(paymentView.getCashBtn().isSelected()) {
	    	  currentPay.setPmTypeCode(1);
	    	  currentPay.setPmCode("현금");
	      }//end if
	      
	      currentPay.setOrderPrice(Integer.parseInt(paymentView.getOrderPrice().getText()));
	      currentPay.setDiscountPrice(Integer.parseInt(paymentView.getDiscount().getText()));
	      currentPay.setTotalPrice(Integer.parseInt(paymentView.getPurchasePrice().getText()));
	      
	   }//setPayMethod
	   
	   //결제
	   public void payment(){
		   String msg = "";
		   
		   PaymentDAO dao = PaymentDAO.getInstance();
		   
		   List<OrderDetailVO> list = MenuView.menuSelectedList;
		   
		   try {
			   msg = dao.useTransaction(list, paymentView.getCurrentPayVO(), paymentView.getCurrentUserVO());
		   } catch (SQLException e) {
			   e.printStackTrace();
		   }
			
		   JOptionPane.showMessageDialog(paymentView, msg, "결제", JOptionPane.INFORMATION_MESSAGE);
		   
		   //결제완료시 paymentView, menuView 종료 후 mainView열기
		   paymentView.dispose();
		   menuView.dispose();
		   new MainView();
		   
	   }//payment
	   
	   public void setUserPoint() {
	      //사용자 기존포인트 - 사용포인트 세팅
		   if( paymentView.getCurrentUserVO() != null ) {
			   int point =paymentView.getCurrentUserVO().getuRemainReward() - Integer.valueOf(paymentView.getDiscount().getText());
			   paymentView.getCurrentUserVO().setuRemainReward(point);
			   return;
		   }//end if
	   }//setUserPoint
	   
	   //결제방법 선택유무
	   public boolean isPay() {
		   
		   boolean flag = false;
		   
		   if( paymentView.getCurrentPayVO().getPmCode() == null ) {
			   flag = false;
		   } else {
			   flag = true;
		   }//end if
		   
		   return flag;
		   
	   }//isPay
	   
}//class
