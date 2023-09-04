package vo;

import java.sql.Date;

public class PaymentVO {
	
	private int pmCode;
	private int pmTypeCode;
	private String pmStatusCode;
	private int orderPrice;
	private int discountPrice;
	private int totalPrice;
	private Date pmDate;
	
	//getter, setter
	public int getPmCode() {
		return pmCode;
	}

	public void setPmCode(int pmCode) {
		this.pmCode = pmCode;
	}

	public int getPmTypeCode() {
		return pmTypeCode;
	}

	public void setPmTypeCode(int pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getPmStatusCode() {
		return pmStatusCode;
	}

	public void setPmStatusCode(String pmStatusCode) {
		this.pmStatusCode = pmStatusCode;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getPmDate() {
		return pmDate;
	}

	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
	}

	//PaymentVO() : void
	public PaymentVO() {
		
	}//PaymentVO

	//PaymentVO(Int, Int, String, Int, Int, Int, Data) : void
	public PaymentVO(int pmCode, int pmTypeCode, String pmStatusCode, int orderPrice, 
					int discountPrice, int totalPrice, Date pmDate) {
		this.pmCode = pmCode;
		this.pmTypeCode = pmTypeCode;
		this.pmStatusCode = pmStatusCode;
		this.orderPrice = orderPrice;
		this.discountPrice = discountPrice;
		this.totalPrice = totalPrice;
		this.pmDate = pmDate;
	}

	@Override
	public String toString() {
		return "PaymentVO [pmCode=" + pmCode + ", pmTypeCode=" + pmTypeCode + ", pmStatusCode=" + pmStatusCode
				+ ", orderPrice=" + orderPrice + ", discountPrice=" + discountPrice + ", totalPrice=" + totalPrice
				+ ", pmDate=" + pmDate + "]";
	}
	
}//class
