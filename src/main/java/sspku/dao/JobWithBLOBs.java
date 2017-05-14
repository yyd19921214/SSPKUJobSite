package sspku.dao;

public class JobWithBLOBs extends Job {
	private String requires;

	private String description;

	private String remark;

	private String searchindex;

	public String getRequire() {
		return requires;
	}

	public void setRequire(String requires) {
		this.requires = requires == null ? null : requires.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getSearchindex() {
		return searchindex;
	}

	public void setSearchindex(String searchindex) {
		this.searchindex = searchindex == null ? null : searchindex.trim();
	}
}