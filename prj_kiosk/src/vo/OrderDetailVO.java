package vo;

import java.sql.Date;

public class OrderDetailVO {
	
	private String oDetailNum;
	private int oLineNum;
	private String pdCode;
	private int oMount;
	private String oTempType;
	private int oOptionNum;
	private int oSizeNum;
	private String oSizeName;
	private int oSizePrice;
	
	//getter, setter
	public String getoDetailNum() {
		return oDetailNum;
	}

	public void setoDetailNum(String oDetailNum) {
		this.oDetailNum = oDetailNum;
	}

	public int getoLineNum() {
		return oLineNum;
	}

	public void setoLineNum(int oLineNum) {
		this.oLineNum = oLineNum;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
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

	public String getoSizeName() {
		return oSizeName;
	}

	public void setoSizeName(String oSizeName) {
		this.oSizeName = oSizeName;
	}

	public int getoSizePrice() {
		return oSizePrice;
	}

	public void setoSizePrice(int oSizePrice) {
		this.oSizePrice = oSizePrice;
	}

	//OrderDetailVO() : void
	public OrderDetailVO() {
		
	}//OrderDetailVO
	
	//OrderDetailVO(String, Int, String, Int, String, Int, Int, String, Int) : void
	public OrderDetailVO(String oDetailNum, int oLineNum, String pdCode, 
						int oMount, String oTempType, int oOptionNum, 
						int oSizeNum, String oSizeName, int oSizePrice) {
		this.oDetailNum = oDetailNum;
		this.oLineNum = oLineNum;
		this.pdCode = pdCode;
		this.oMount = oMount;
		this.oTempType = oTempType;
		this.oOptionNum = oOptionNum;
		this.oSizeNum = oSizeNum;
		this.oSizeName = oSizeName;
		this.oSizePrice = oSizePrice;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [oDetailNum=" + oDetailNum + ", oLineNum=" + oLineNum + ", pdCode=" + pdCode + ", oMount="
				+ oMount + ", oTempType=" + oTempType + ", oOptionNum=" + oOptionNum + ", oSizeNum=" + oSizeNum
				+ ", oSizeName=" + oSizeName + ", oSizePrice=" + oSizePrice + "]";
	}

}//class
