package vo;

import java.sql.Date;

public class UserVO {
	
	private String uTelNum;
	private int uRemainReward;
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

	//UserVO() : void
	public UserVO() {
		
	}//UserVO
	
	//UserVO(String, Int, Date) : void
	public UserVO(String uTelNum, int uRemainReward, Date uSignupDate) {
		this.uTelNum = uTelNum;
		this.uRemainReward = uRemainReward;
		this.uSignupDate = uSignupDate;
	}

	public static void main(String[] args) {

	}//main

}//class
