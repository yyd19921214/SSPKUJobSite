package sspku.dto;

/**
 * 用来记录某个用户的工作经历 序列化为字符串后保存
 * 
 * @author yangyudong
 *
 */
public class WorkExp {

	public String companyName;
	public String position;
	public String startTime;
	public String endTime;
	
	

	public WorkExp() {
		super();
	}

	public WorkExp(String companyName, String position, String startTime, String endTime) {
		super();
		this.companyName = companyName;
		this.position = position;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
