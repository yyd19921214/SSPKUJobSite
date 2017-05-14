package sspku.dao;


import java.util.Date;

public class Job {
    private Integer id;

    private String name;

    private String source;

    private String sourceid;

    private Integer companyid;

    private String city;

    private String location;

    private Integer education;

    private Integer minexperience;

    private Integer maxexperience;

    private Integer minsalary;

    private Integer maxsalary;

    private Integer state;

    private Integer worktype;

    private Date createtime;

    private Date endtime;

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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getMinexperience() {
        return minexperience;
    }

    public void setMinexperience(Integer minexperience) {
        this.minexperience = minexperience;
    }

    public Integer getMaxexperience() {
        return maxexperience;
    }

    public void setMaxexperience(Integer maxexperience) {
        this.maxexperience = maxexperience;
    }

    public Integer getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(Integer minsalary) {
        this.minsalary = minsalary;
    }

    public Integer getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(Integer maxsalary) {
        this.maxsalary = maxsalary;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getWorktype() {
        return worktype;
    }

    public void setWorktype(Integer worktype) {
        this.worktype = worktype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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