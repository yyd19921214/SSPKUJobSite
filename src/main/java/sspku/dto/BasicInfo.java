package sspku.dto;

public class BasicInfo {

	public String trueName;
	public String gender;
	public String highestDegree;
	public String major;
	public String school;
	public String currentLocation;
	public String telephone;
	public String email;
	public String selfDesc;

	public BasicInfo() {
		super();
	}

	public BasicInfo(String trueName, String highestDegree, String gender, String major, String school,
			String currentLocation, String telephone, String email, String selfDesc) {
		super();
		this.trueName = trueName;
		this.highestDegree = highestDegree;
		this.gender = gender;
		this.major = major;
		this.school = school;
		this.currentLocation = currentLocation;
		this.telephone = telephone;
		this.email = email;
		this.selfDesc = selfDesc;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSelfDesc() {
		return selfDesc;
	}

	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
