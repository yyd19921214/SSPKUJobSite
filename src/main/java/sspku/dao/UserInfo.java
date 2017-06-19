package sspku.dao;

public class UserInfo {
	private Integer userid;

	private String username;

	private String passwd;

	private String email;

	private String phone;

	private String skill;

	private String resumelink;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd == null ? null : passwd.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill == null ? null : skill.trim();
	}

	public String getResumelink() {
		return resumelink;
	}

	public void setResumelink(String resumelink) {
		this.resumelink = resumelink == null ? null : resumelink.trim();
	}
}