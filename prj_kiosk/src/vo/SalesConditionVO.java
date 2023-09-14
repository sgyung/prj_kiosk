package vo;

import java.util.Date;

public class SalesConditionVO {
	
	private Date startDate;
	private Date endDate;
	private String pdType;
	private String pdName;
	
	public SalesConditionVO() {
		super();
	}

	public SalesConditionVO(Date startDate, Date endDate, String pdType, String pdName) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.pdType = pdType;
		this.pdName = pdName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPdType() {
		return pdType;
	}

	public void setPdType(String pdType) {
		this.pdType = pdType;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	@Override
	public String toString() {
		return "SalesConditionVO [startDate=" + startDate + ", endDate=" + endDate + ", pdType=" + pdType + ", pdName="
				+ pdName + "]";
	}

	
}//class
