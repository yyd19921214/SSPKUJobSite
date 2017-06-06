package sspku.dto;

public class ProjectExp {

	public String projectName;
	public String responsibility;
	public String startTime;
	public String endTime;
	public String selfDesc;
	
	

	public ProjectExp() {
		super();
	}

	public ProjectExp(String projectName, String responsibility, String startTime, String endTime, String selfDesc) {
		super();
		this.projectName = projectName;
		this.responsibility = responsibility;
		this.startTime = startTime;
		this.endTime = endTime;
		this.selfDesc = selfDesc;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
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

	public String getSelfDesc() {
		return selfDesc;
	}

	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}

}
