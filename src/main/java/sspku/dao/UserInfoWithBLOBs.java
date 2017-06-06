package sspku.dao;

public class UserInfoWithBLOBs extends UserInfo {
	private String basicinfo;

	private String workexp;

	private String eduexp;

	private String projectexp;

	private String expection;

	private String selfdesc;

	private String message;

	private String searchindex;

	private String remark;

	public String getBasicinfo() {
		return basicinfo;
	}

	public void setBasicinfo(String basicinfo) {
		this.basicinfo = basicinfo == null ? null : basicinfo.trim();
	}

	public String getWorkexp() {
		return workexp;
	}

	public void setWorkexp(String workexp) {
		this.workexp = workexp == null ? null : workexp.trim();
	}

	public String getEduexp() {
		return eduexp;
	}

	public void setEduexp(String eduexp) {
		this.eduexp = eduexp == null ? null : eduexp.trim();
	}

	public String getProjectexp() {
		return projectexp;
	}

	public void setProjectexp(String projectexp) {
		this.projectexp = projectexp == null ? null : projectexp.trim();
	}

	public String getExpection() {
		return expection;
	}

	public void setExpection(String expection) {
		this.expection = expection == null ? null : expection.trim();
	}

	public String getSelfdesc() {
		return selfdesc;
	}

	public void setSelfdesc(String selfdesc) {
		this.selfdesc = selfdesc == null ? null : selfdesc.trim();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	public String getSearchindex() {
		return searchindex;
	}

	public void setSearchindex(String searchindex) {
		this.searchindex = searchindex == null ? null : searchindex.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}