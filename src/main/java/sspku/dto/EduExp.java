package sspku.dto;

/**
 * 用来记录某个用户的受教育经历 序列化为字符串后保存
 * 
 * @author yangyudong
 *
 */
public class EduExp {

	public String schoolName;
	public String major;
	public Integer degree;
	public String startTime;
	public String graduateTime;

	public EduExp() {
		super();
	}
	
	public EduExp(String schoolName, String major, Integer degree, String startTime, String graduateTime) {
		super();
		this.schoolName = schoolName;
		this.major = major;
		this.degree = degree;
		this.startTime = startTime;
		this.graduateTime = graduateTime;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	

	

}
