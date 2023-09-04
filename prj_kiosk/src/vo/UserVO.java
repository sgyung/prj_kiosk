package vo;

import java.sql.Date;

public class UserVO {
	
	private String uTelNum;
	private int uRemainReward;
	private String uWithdrawal;
	private Date uSignupDate;

	//getter, setter
	public String getuTelNum() {
		return uTelNum;
	}

	public void setuTelNum(String uTelNum) {
		this.uTelNum = uTelNum;
	}

	public int getuRemainReward() {
		return uRemainReward;
	}

	public void setuRemainReward(int uRemainReward) {
		this.uRemainReward = uRemainReward;
	}

	public Date getuSignupDate() {
		return uSignupDate;
	}

	public void setuSignupDate(Date uSignupDate) {
		this.uSignupDate = uSignupDate;
	}
	
	public String getuWithdrawal() {
		return uWithdrawal;
	}

	public void setuWithdrawal(String uWithdrawal) {
		this.uWithdrawal = uWithdrawal;
	}

	//UserVO() : void
	public UserVO() {
		
	}//UserVO
	
	//UserVO(String, Int, Date) : void
	public UserVO(String uTelNum, int uRemainReward, String uWithdrawal, Date uSignupDate) {
		this.uTelNum = uTelNum;
		this.uRemainReward = uRemainReward;
		this.uWithdrawal = uWithdrawal;
		this.uSignupDate = uSignupDate;
	}

	@Override
	public String toString() {
		return "UserVO [uTelNum=" + uTelNum + ", uRemainReward=" + uRemainReward + ", uWithdrawal=" + uWithdrawal
				+ ", uSignupDate=" + uSignupDate + "]";
	}

}//class
