package sspku.dao;

import java.util.Date;

public class Company {
	private Integer id;

	private String name;

	private String source;

	private String sourceid;

	private String alias;

	private String city;

	private Integer type;

	private String keyword;

	private String financestage;

	private String industry;

	private Date createtime;

	private String reflink;

	private String doclink;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid == null ? null : sourceid.trim();
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias == null ? null : alias.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	public String getFinancestage() {
		return financestage;
	}

	public void setFinancestage(String financestage) {
		this.financestage = financestage == null ? null : financestage.trim();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getReflink() {
		return reflink;
	}

	public void setReflink(String reflink) {
		this.reflink = reflink == null ? null : reflink.trim();
	}

	public String getDoclink() {
		return doclink;
	}

	public void setDoclink(String doclink) {
		this.doclink = doclink == null ? null : doclink.trim();
	}
}