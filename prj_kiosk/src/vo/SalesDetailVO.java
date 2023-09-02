package vo;

import java.sql.Date;

public class SalesDetailVO {
	
	private String pdTypeCode;
	private String pdName;
	private String oTempType;
	private int oOptionNum;
	private int oSizeNum;
	private int pdMount;
	private int pdPrice;
	private String pmType;
	private Date pmDate;
	
	//getter, setter
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

	public int getPdMount() {
		return pdMount;
	}

	public void setPdMount(int pdMount) {
		this.pdMount = pdMount;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}

	public String getPmType() {
		return pmType;
	}

	public void setPmType(String pmType) {
		this.pmType = pmType;
	}

	public Date getPmDate() {
		return pmDate;
	}

	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
	}

	//SalesDetailVO() : void
	public SalesDetailVO() {
		
	}//SalesDetailVO
	
	//SalesDetailVO(String, String, String, Int, Int, Int, Int, String, Date)
	public SalesDetailVO(String pdTypeCode, String pdName, String oTempType, int oOptionNum,
						int oSizeNum, int pdMount, int pdPrice, String pmType, Date pmDate) {
		this.pdTypeCode = pdTypeCode;
		this.pdName = pdName;
		this.oTempType = oTempType;
		this.oOptionNum = oOptionNum;
		this.oSizeNum = oSizeNum;
		this.pdMount = pdMount;
		this.pdPrice = pdPrice;
		this.pmType = pmType;
		this.pmDate = pmDate;
	}

	public static void main(String[] args) {

	}//main

}//class
