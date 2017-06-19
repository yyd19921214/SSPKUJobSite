package sspku.dto;

/**
 * 期望工作
 * 
 * @author yangyudong
 *
 */
public class Expection {

	public String expJob;
	public Integer jobType;// 工作类型(全职 or 兼职)
	public String expCity;
	public Integer expMinSalary;
	public Integer expMaxSalary;
	public String otherRequire;
	
	public Expection(){
		super();
	}
	
	public Expection(String expJob, Integer jobType, String expCity, Integer expMinSalary, Integer expMaxSalary,
			String otherRequire) {
		super();
		this.expJob = expJob;
		this.jobType = jobType;
		this.expCity = expCity;
		this.expMinSalary = expMinSalary;
		this.expMaxSalary = expMaxSalary;
		this.otherRequire = otherRequire;
	}

	public String getExpJob() {
		return expJob;
	}

	public void setExpJob(String expJob) {
		this.expJob = expJob;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public String getExpCity() {
		return expCity;
	}

	public void setExpCity(String expCity) {
		this.expCity = expCity;
	}

	public Integer getExpMinSalary() {
		return expMinSalary;
	}

	public void setExpMinSalary(Integer expMinSalary) {
		this.expMinSalary = expMinSalary;
	}

	public Integer getExpMaxSalary() {
		return expMaxSalary;
	}

	public void setExpMaxSalary(Integer expMaxSalary) {
		this.expMaxSalary = expMaxSalary;
	}

	public String getOtherRequire() {
		return otherRequire;
	}

	public void setOtherRequire(String otherRequire) {
		this.otherRequire = otherRequire;
	}

}
