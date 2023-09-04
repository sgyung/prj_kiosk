package vo;

import java.sql.Date;

public class InventoryVO {
	
	private int iNum;
	private int iTypeCode;
	private String iName;
	private int iMount;
	private String iDelete;
	private Date iInputDate;

	//getter, setter
	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public int getiTypeCode() {
		return iTypeCode;
	}

	public void setiTypeCode(int iTypeCode) {
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
	
	//InventoryVO(Int, Int, String, Int, String, Date) : void
	public InventoryVO(int iNum, int iTypeCode, String iName, int iMount, 
					String iDelete, Date iInputDate) {
		this.iNum = iNum;
		this.iTypeCode = iTypeCode;
		this.iName = iName;
		this.iMount = iMount;
		this.iDelete = iDelete;
		this.iInputDate = iInputDate;
	}
	
	@Override
	public String toString() {
		return "InventoryVO [iNum=" + iNum + ", iTypeCode=" + iTypeCode + ", iName=" + iName + ", iMount=" + iMount
				+ ", iDelete=" + iDelete + ", iInputDate=" + iInputDate + "]";
	}

}//class
