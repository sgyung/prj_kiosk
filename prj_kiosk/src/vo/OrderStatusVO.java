package vo;

import java.sql.Date;

public class OrderStatusVO {

	private int oNum;
	private String oStatus;
	private Date oDate;
	private String pdName;
	private int oMount;
	private String oTempType;
	private int oOptionNum;
	private int oSizeNum;
	private int pdPrice;
	
	//getter, setter	
	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public int getoMount() {
		return oMount;
	}

	public void setoMount(int oMount) {
		this.oMount = oMount;
	}

	public String getoTempType() {
		return oTempType;
	}

	public void setoTempType(String oTempType) {
		this.oTempType = oTempType;
	}

	public int getoOptionNum() {
		return oOptionNum;
	}

	public void setoOptionNum(int oOptionNum) {
		this.oOptionNum = oOptionNum;
	}

	public int getoSizeNum() {
		return oSizeNum;
	}

	public void setoSizeNum(int oSizeNum) {
		this.oSizeNum = oSizeNum;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}

	//OrderStatusVO() : void
	public OrderStatusVO() {
		
	}//OrderStatusVO
	
	//OrderStatusVO(Int, String, Date, String, Int, String, Int, Int, Int) : void
	public OrderStatusVO(int oNum, String oStatus, Date oDate, String pdName, int oMount, 
						String oTempType, int oOptionNum, int oSizeNum, int pdPrice) {
		this.oNum = oNum;
		this.oStatus = oStatus;
		this.oDate = oDate;
		this.pdName = pdName;
		this.oMount = oMount;
		this.oTempType = oTempType;
		this.oOptionNum = oOptionNum;
		this.oSizeNum = oSizeNum;
		this.pdPrice = pdPrice;
	}

	@Override
	public String toString() {
		return "OrderStatusVO [oNum=" + oNum + ", oStatus=" + oStatus + ", oDate=" + oDate + ", pdName=" + pdName
				+ ", oMount=" + oMount + ", oTempType=" + oTempType + ", oOptionNum=" + oOptionNum + ", oSizeNum="
				+ oSizeNum + ", pdPrice=" + pdPrice + "]";
	}
	
}//class
