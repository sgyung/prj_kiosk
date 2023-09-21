package vo;

public class MenuListVO {
	private String pdCode;
	private String pdName;
	private int pdPrice;
	private String imgName;
	
	public MenuListVO() {
		
	}
	
	public MenuListVO(String pdCode, String pdName, int pdPrice, String imgName) {
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.imgName = imgName;
	}

	public String getPdName() {
		return pdName;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public String getImgName() {
		return imgName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	@Override
	public String toString() {
		return "MenuListVO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", imgName=" + imgName
				+ "]";
	}
	
}
