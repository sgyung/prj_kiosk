package vo;

import java.sql.Date;

public class InventoryVO {
	
	private String iCode;
	private String iTypeCode;
	private String iName;
	private int iMount;
	private String iDelete;
	private Date iInputDate;
	
	//getter, setter
	public String getiCode() {
		return iCode;
	}

	public void setiCode(String iCode) {
		this.iCode = iCode;
	}

	public String getiTypeCode() {
		return iTypeCode;
	}

	public void setiTypeCode(String iTypeCode) {
		this.iTypeCode = iTypeCode;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public int getiMount() {
		return iMount;
	}

	public void setiMount(int iMount) {
		this.iMount = iMount;
	}

	public String getiDelete() {
		return iDelete;
	}

	public void setiDelete(String iDelete) {
		this.iDelete = iDelete;
	}

	public Date getiInputDate() {
		return iInputDate;
	}

	public void setiInputDate(Date iInputDate) {
		this.iInputDate = iInputDate;
	}

	//InventoryVO() : void
	public InventoryVO() {
		
	}//InventoryVO
	
	@Override
	public String toString() {
		return "InventoryVO [iCode=" + iCode + ", iTypeCode=" + iTypeCode + ", iName=" + iName + ", iMount=" + iMount
				+ ", iDelete=" + iDelete + ", iInputDate=" + iInputDate + "]";
	}

	//InventoryVO(String, String, String, Int, String, Date) : void
	public InventoryVO(String iCode, String iTypeCode, String iName, int iMount, 
					String iDelete, Date iInputDate) {
		this.iCode = iCode;
		this.iTypeCode = iTypeCode;
		this.iName = iName;
		this.iMount = iMount;
		this.iDelete = iDelete;
		this.iInputDate = iInputDate;
	}
	
}//class
