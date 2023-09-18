package vo;

public class OrderDetailVO {
	
	private String pdCode;
	private String pdName;
	private int pdPrice;
	private int oQuantity;
	private String oTempType;
	private String oOptionName;
	private String oKnifeOption; // 0916 수정
	private int oOptionPrice;
	private String oSizeName;
	private int oSizePrice;
	
	public OrderDetailVO() {
		super();
	}
	
	public OrderDetailVO(String pdCode, String pdName, int pdPrice, int oQuantity, String oTempType, String oOptionName,
			String oKnifeOption, int oOptionPrice, String oSizeName, int oSizePrice) {
		super();
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.oQuantity = oQuantity;
		this.oTempType = oTempType;
		this.oOptionName = oOptionName;
		this.oKnifeOption = oKnifeOption;
		this.oOptionPrice = oOptionPrice;
		this.oSizeName = oSizeName;
		this.oSizePrice = oSizePrice;
	}
	
	//getter, setter
	public String getPdCode() {
		return pdCode;
	}
	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public int getoQuantity() {
		return oQuantity;
	}
	public void setoQuantity(int oQuantity) {
		this.oQuantity = oQuantity;
	}
	public String getoTempType() {
		return oTempType;
	}
	public void setoTempType(String oTempType) {
		this.oTempType = oTempType;
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
	
	public String getoKnifeOption() {
		return oKnifeOption;
	}
	
	public void setoKnifeOption(String oKnifeOption) {
		this.oKnifeOption = oKnifeOption;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", oQuantity="
				+ oQuantity + ", oTempType=" + oTempType + ", oOptionName=" + oOptionName + ", oKnifeOption="
				+ oKnifeOption + ", oOptionPrice=" + oOptionPrice + ", oSizeName=" + oSizeName + ", oSizePrice="
				+ oSizePrice + "]";
	}
	
	
}//class
