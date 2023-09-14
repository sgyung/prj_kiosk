package vo;

public class OptionVO {

	private String optionName;
	private int optionPrice;
	
	public OptionVO() {
		
	}
	
	public OptionVO(String optionName, int optionPrice) {
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	public String getOptionName() {
		return optionName;
	}

	public int getOptionPrice() {
		return optionPrice;
	}

	@Override
	public String toString() {
		return "OptionVO [optionName=" + optionName + ", optionPrice=" + optionPrice + "]";
	}
	
	
	
	
	
}
