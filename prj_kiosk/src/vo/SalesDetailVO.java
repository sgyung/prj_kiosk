package vo;

import java.sql.Timestamp;

public class SalesDetailVO {
	
	private String orderDetailNum;
	private String pdTypeCode;
	private String pdName;
	private int pdQuantity;
	private String oTempType;
	private String oSizeName;
	private String oOptionName;
	private int oOptionPrice;
	private int orderDetailPrice;
	private Timestamp pmDate;
	
	public SalesDetailVO(String orderDetailNum, String pdTypeCode, String pdName, int pdQuantity, String oTempType,
			String oSizeNum, String oOptionName, int oOptionPrice, int orderDetailPrice, Timestamp pmDate) {
		super();
		this.orderDetailNum = orderDetailNum;
		this.pdTypeCode = pdTypeCode;
		this.pdName = pdName;
		this.pdQuantity = pdQuantity;
		this.oTempType = oTempType;
		this.oSizeName = oSizeNum;
		this.oOptionName = oOptionName;
		this.oOptionPrice = oOptionPrice;
		this.orderDetailPrice = orderDetailPrice;
		this.pmDate = pmDate;
	}//SalesDetailVO
	
	
	public SalesDetailVO() {
		super();
	}//SalesDetailVO
	

	//getter, setter
	public String getOrderDetailNum() {
		return orderDetailNum;
	}
	public void setOrderDetailNum(String orderDetailNum) {
		this.orderDetailNum = orderDetailNum;
	}
	public String getPdTypeCode() {
		return pdTypeCode;
	}
	public void setPdTypeCode(String pdTypeCode) {
		this.pdTypeCode = pdTypeCode;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public int getPdQuantity() {
		return pdQuantity;
	}
	public void setPdQuantity(int pdQuantity) {
		this.pdQuantity = pdQuantity;
	}
	public String getoTempType() {
		return oTempType;
	}
	public void setoTempType(String oTempType) {
		this.oTempType = oTempType;
	}
	public String getoSizeName() {
		return oSizeName;
	}
	public void setoSizeName(String oSizeName) {
		this.oSizeName = oSizeName;
	}
	public String getoOptionName() {
		return oOptionName;
	}
	public void setoOptionName(String oOptionName) {
		this.oOptionName = oOptionName;
	}
	public int getoOptionPrice() {
		return oOptionPrice;
	}
	public void setoOptionPrice(int oOptionPrice) {
		this.oOptionPrice = oOptionPrice;
	}
	public int getOrderDetailPrice() {
		return orderDetailPrice;
	}
	public void setOrderDetailPrice(int orderDetailPrice) {
		this.orderDetailPrice = orderDetailPrice;
	}
	public Timestamp getPmDate() {
		return pmDate;
	}
	public void setPmDate(Timestamp pmDate) {
		this.pmDate = pmDate;
	}


	@Override
	public String toString() {
		return "SalesDetailVO [orderDetailNum=" + orderDetailNum + ", pdTypeCode=" + pdTypeCode + ", pdName=" + pdName
				+ ", pdQuantity=" + pdQuantity + ", oTempType=" + oTempType + ", oSizeName=" + oSizeName
				+ ", oOptionName=" + oOptionName + ", oOptionPrice=" + oOptionPrice + ", orderDetailPrice="
				+ orderDetailPrice + ", pmDate=" + pmDate + "]";
	}

	

}//class
