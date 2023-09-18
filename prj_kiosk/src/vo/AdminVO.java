package vo;

public class AdminVO {

	private String adminNum;
	private String adminPW;
	
	//getter, setter
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getAdminPW() {
		return adminPW;
	}
	public void setAdminPW(String adminPW) {
		this.adminPW = adminPW;
	}
	
	//AdminVO : void
	public AdminVO() {
		
	}//AdminVO
	
	//AdminVO(String) : void
	public AdminVO(String adminNum, String adminPW) {
		super();
		this.adminNum = adminNum;
		this.adminPW = adminPW;
	}
	@Override
	public String toString() {
		return "AdminVO [adminNum=" + adminNum + ", adminPW=" + adminPW + "]";
	}
	
}//class
