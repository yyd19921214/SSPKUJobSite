package sspku.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobExample {
    protected String orderByClause;

    protected boolean distinct;
    
    protected int start;
    
    protected int limit;

    protected List<Criteria> oredCriteria;

    public JobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    
    public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNull() {
            addCriterion("sourceId is null");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNotNull() {
            addCriterion("sourceId is not null");
            return (Criteria) this;
        }

        public Criteria andSourceidEqualTo(String value) {
            addCriterion("sourceId =", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotEqualTo(String value) {
            addCriterion("sourceId <>", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThan(String value) {
            addCriterion("sourceId >", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThanOrEqualTo(String value) {
            addCriterion("sourceId >=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThan(String value) {
            addCriterion("sourceId <", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThanOrEqualTo(String value) {
            addCriterion("sourceId <=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLike(String value) {
            addCriterion("sourceId like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotLike(String value) {
            addCriterion("sourceId not like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidIn(List<String> values) {
            addCriterion("sourceId in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotIn(List<String> values) {
            addCriterion("sourceId not in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidBetween(String value1, String value2) {
            addCriterion("sourceId between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotBetween(String value1, String value2) {
            addCriterion("sourceId not between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNull() {
            addCriterion("companyId is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("companyId is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(Integer value) {
            addCriterion("companyId =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(Integer value) {
            addCriterion("companyId <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(Integer value) {
            addCriterion("companyId >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("companyId >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(Integer value) {
            addCriterion("companyId <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(Integer value) {
            addCriterion("companyId <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<Integer> values) {
            addCriterion("companyId in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<Integer> values) {
            addCriterion("companyId not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(Integer value1, Integer value2) {
            addCriterion("companyId between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(Integer value1, Integer value2) {
            addCriterion("companyId not between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(Integer value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(Integer value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(Integer value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(Integer value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(Integer value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(Integer value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<Integer> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<Integer> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(Integer value1, Integer value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(Integer value1, Integer value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andMinexperienceIsNull() {
            addCriterion("minExperience is null");
            return (Criteria) this;
        }

        public Criteria andMinexperienceIsNotNull() {
            addCriterion("minExperience is not null");
            return (Criteria) this;
        }

        public Criteria andMinexperienceEqualTo(Integer value) {
            addCriterion("minExperience =", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceNotEqualTo(Integer value) {
            addCriterion("minExperience <>", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceGreaterThan(Integer value) {
            addCriterion("minExperience >", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("minExperience >=", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceLessThan(Integer value) {
            addCriterion("minExperience <", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceLessThanOrEqualTo(Integer value) {
            addCriterion("minExperience <=", value, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceIn(List<Integer> values) {
            addCriterion("minExperience in", values, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceNotIn(List<Integer> values) {
            addCriterion("minExperience not in", values, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceBetween(Integer value1, Integer value2) {
            addCriterion("minExperience between", value1, value2, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMinexperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("minExperience not between", value1, value2, "minexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceIsNull() {
            addCriterion("maxExperience is null");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceIsNotNull() {
            addCriterion("maxExperience is not null");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceEqualTo(Integer value) {
            addCriterion("maxExperience =", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceNotEqualTo(Integer value) {
            addCriterion("maxExperience <>", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceGreaterThan(Integer value) {
            addCriterion("maxExperience >", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxExperience >=", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceLessThan(Integer value) {
            addCriterion("maxExperience <", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceLessThanOrEqualTo(Integer value) {
            addCriterion("maxExperience <=", value, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceIn(List<Integer> values) {
            addCriterion("maxExperience in", values, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceNotIn(List<Integer> values) {
            addCriterion("maxExperience not in", values, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceBetween(Integer value1, Integer value2) {
            addCriterion("maxExperience between", value1, value2, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMaxexperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("maxExperience not between", value1, value2, "maxexperience");
            return (Criteria) this;
        }

        public Criteria andMinsalaryIsNull() {
            addCriterion("minSalary is null");
            return (Criteria) this;
        }

        public Criteria andMinsalaryIsNotNull() {
            addCriterion("minSalary is not null");
            return (Criteria) this;
        }

        public Criteria andMinsalaryEqualTo(Integer value) {
            addCriterion("minSalary =", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryNotEqualTo(Integer value) {
            addCriterion("minSalary <>", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryGreaterThan(Integer value) {
            addCriterion("minSalary >", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("minSalary >=", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryLessThan(Integer value) {
            addCriterion("minSalary <", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("minSalary <=", value, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryIn(List<Integer> values) {
            addCriterion("minSalary in", values, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryNotIn(List<Integer> values) {
            addCriterion("minSalary not in", values, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryBetween(Integer value1, Integer value2) {
            addCriterion("minSalary between", value1, value2, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMinsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("minSalary not between", value1, value2, "minsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryIsNull() {
            addCriterion("maxSalary is null");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryIsNotNull() {
            addCriterion("maxSalary is not null");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryEqualTo(Integer value) {
            addCriterion("maxSalary =", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryNotEqualTo(Integer value) {
            addCriterion("maxSalary <>", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryGreaterThan(Integer value) {
            addCriterion("maxSalary >", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxSalary >=", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryLessThan(Integer value) {
            addCriterion("maxSalary <", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("maxSalary <=", value, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryIn(List<Integer> values) {
            addCriterion("maxSalary in", values, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryNotIn(List<Integer> values) {
            addCriterion("maxSalary not in", values, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryBetween(Integer value1, Integer value2) {
            addCriterion("maxSalary between", value1, value2, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andMaxsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("maxSalary not between", value1, value2, "maxsalary");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andWorktypeIsNull() {
            addCriterion("workType is null");
            return (Criteria) this;
        }

        public Criteria andWorktypeIsNotNull() {
            addCriterion("workType is not null");
            return (Criteria) this;
        }

        public Criteria andWorktypeEqualTo(Integer value) {
            addCriterion("workType =", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotEqualTo(Integer value) {
            addCriterion("workType <>", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeGreaterThan(Integer value) {
            addCriterion("workType >", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("workType >=", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeLessThan(Integer value) {
            addCriterion("workType <", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeLessThanOrEqualTo(Integer value) {
            addCriterion("workType <=", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeIn(List<Integer> values) {
            addCriterion("workType in", values, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotIn(List<Integer> values) {
            addCriterion("workType not in", values, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeBetween(Integer value1, Integer value2) {
            addCriterion("workType between", value1, value2, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotBetween(Integer value1, Integer value2) {
            addCriterion("workType not between", value1, value2, "worktype");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andReflinkIsNull() {
            addCriterion("refLink is null");
            return (Criteria) this;
        }

        public Criteria andReflinkIsNotNull() {
            addCriterion("refLink is not null");
            return (Criteria) this;
        }

        public Criteria andReflinkEqualTo(String value) {
            addCriterion("refLink =", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkNotEqualTo(String value) {
            addCriterion("refLink <>", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkGreaterThan(String value) {
            addCriterion("refLink >", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkGreaterThanOrEqualTo(String value) {
            addCriterion("refLink >=", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkLessThan(String value) {
            addCriterion("refLink <", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkLessThanOrEqualTo(String value) {
            addCriterion("refLink <=", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkLike(String value) {
            addCriterion("refLink like", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkNotLike(String value) {
            addCriterion("refLink not like", value, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkIn(List<String> values) {
            addCriterion("refLink in", values, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkNotIn(List<String> values) {
            addCriterion("refLink not in", values, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkBetween(String value1, String value2) {
            addCriterion("refLink between", value1, value2, "reflink");
            return (Criteria) this;
        }

        public Criteria andReflinkNotBetween(String value1, String value2) {
            addCriterion("refLink not between", value1, value2, "reflink");
            return (Criteria) this;
        }

        public Criteria andDoclinkIsNull() {
            addCriterion("docLink is null");
            return (Criteria) this;
        }

        public Criteria andDoclinkIsNotNull() {
            addCriterion("docLink is not null");
            return (Criteria) this;
        }

        public Criteria andDoclinkEqualTo(String value) {
            addCriterion("docLink =", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkNotEqualTo(String value) {
            addCriterion("docLink <>", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkGreaterThan(String value) {
            addCriterion("docLink >", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkGreaterThanOrEqualTo(String value) {
            addCriterion("docLink >=", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkLessThan(String value) {
            addCriterion("docLink <", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkLessThanOrEqualTo(String value) {
            addCriterion("docLink <=", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkLike(String value) {
            addCriterion("docLink like", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkNotLike(String value) {
            addCriterion("docLink not like", value, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkIn(List<String> values) {
            addCriterion("docLink in", values, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkNotIn(List<String> values) {
            addCriterion("docLink not in", values, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkBetween(String value1, String value2) {
            addCriterion("docLink between", value1, value2, "doclink");
            return (Criteria) this;
        }

        public Criteria andDoclinkNotBetween(String value1, String value2) {
            addCriterion("docLink not between", value1, value2, "doclink");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}