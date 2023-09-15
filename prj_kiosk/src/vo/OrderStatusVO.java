package vo;

import java.sql.Date;

public class OrderStatusVO {

	private String oSerialNum;
	private String oDetailNum;
	private String oNum;
	private String oStatus;
	private Date oDate;
	private String pdName;
	private String oSize;
	private String oTempType;
	private String opName;
	private int oMount;
	private int opPrice;
	private int pdPrice;
	private int oDetailPrice;
	private int totalPrice;
	
	public String getoDetailNum() {
		return oDetailNum;
	}
	public void setoDetailNum(String oDetailNum) {
		this.oDetailNum = oDetailNum;
	}
	public int getoDetailPrice() {
		return oDetailPrice;
	}
	public void setoDetailPrice(int oDetailPrice) {
		this.oDetailPrice = oDetailPrice;
	}
	public String getoSerialNum() {
		return oSerialNum;
	}
	public void setoSerialNum(String oSerialNum) {
		this.oSerialNum = oSerialNum;
	}
	public String getoNum() {
		return oNum;
	}
	public void setoNum(String oNum) {
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
	public String getoSize() {
		return oSize;
	}
	public void setoSize(String oSize) {
		this.oSize = oSize;
	}
	public String getoTempType() {
		return oTempType;
	}
	public void setoTempType(String oTempType) {
		this.oTempType = oTempType;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public int getoMount() {
		return oMount;
	}
	public void setoMount(int oMount) {
		this.oMount = oMount;
	}
	public int getOpPrice() {
		return opPrice;
	}
	public void setOpPrice(int opPrice) {
		this.opPrice = opPrice;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public OrderStatusVO(String oSerialNum, String oDetailNum, String oNum, String oStatus, Date oDate, String pdName,
			String oSize, String oTempType, String opName, int oMount, int opPrice, int pdPrice, int oDetailPrice,
			int totalPrice) {
		super();
		this.oSerialNum = oSerialNum;
		this.oDetailNum = oDetailNum;
		this.oNum = oNum;
		this.oStatus = oStatus;
		this.oDate = oDate;
		this.pdName = pdName;
		this.oSize = oSize;
		this.oTempType = oTempType;
		this.opName = opName;
		this.oMount = oMount;
		this.opPrice = opPrice;
		this.pdPrice = pdPrice;
		this.oDetailPrice = oDetailPrice;
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "OrderStatusVO [oSerialNum=" + oSerialNum + ", oDetailNum=" + oDetailNum + ", oNum=" + oNum
				+ ", oStatus=" + oStatus + ", oDate=" + oDate + ", pdName=" + pdName + ", oSize=" + oSize
				+ ", oTempType=" + oTempType + ", opName=" + opName + ", oMount=" + oMount + ", opPrice=" + opPrice
				+ ", pdPrice=" + pdPrice + ", oDetailPrice=" + oDetailPrice + ", totalPrice=" + totalPrice + "]";
	}
	
	
	public OrderStatusVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}//class
