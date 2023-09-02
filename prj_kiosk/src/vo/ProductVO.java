package vo;

import java.sql.Date;

public class ProductVO {
	
	private String pdCode;
	private String pdTypeCode;
	private String pdImageName;
	private String pdName;
	private int pdPrice;
	private String pdDelete;
	private Date pdInputDate;
	
	//getter, setter
	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getPdTypeCode() {
		return pdTypeCode;
	}

	public void setPdTypeCode(String pdTypeCode) {
		this.pdTypeCode = pdTypeCode;
	}

	public String getPdImageName() {
		return pdImageName;
	}

	public void setPdImageName(String pdImageName) {
		this.pdImageName = pdImageName;
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

	public String getPdDelete() {
		return pdDelete;
	}

	public void setPdDelete(String pdDelete) {
		this.pdDelete = pdDelete;
	}

	public Date getPdInputDate() {
		return pdInputDate;
	}

	public void setPdInputDate(Date pdInputDate) {
		this.pdInputDate = pdInputDate;
	}

	//productVO() : void
	public ProductVO() {
		
	}//ProductVO
	
	//ProductVO(String, String, String, String, Int, String, Date) : void
	public ProductVO(String pdCode, String pdTypeCode, String pdImageName, String pdName, 
			int pdPrice, String pdDelete, Date pdInputDate) {
		this.pdCode = pdCode;
		this.pdTypeCode = pdTypeCode;
		this.pdImageName = pdImageName;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDelete = pdDelete;
		this.pdInputDate = pdInputDate;
	}

	public static void main(String[] args) {

	}//main

}//class
