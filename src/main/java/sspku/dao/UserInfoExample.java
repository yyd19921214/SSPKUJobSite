package sspku.dao;

import java.util.ArrayList;
import java.util.List;

public class UserInfoExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public UserInfoExample() {
		oredCriteria = new ArrayList<Criteria>();
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

		public Criteria andUseridIsNull() {
			addCriterion("userId is null");
			return (Criteria) this;
		}

		public Criteria andUseridIsNotNull() {
			addCriterion("userId is not null");
			return (Criteria) this;
		}

		public Criteria andUseridEqualTo(Integer value) {
			addCriterion("userId =", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotEqualTo(Integer value) {
			addCriterion("userId <>", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThan(Integer value) {
			addCriterion("userId >", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
			addCriterion("userId >=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThan(Integer value) {
			addCriterion("userId <", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThanOrEqualTo(Integer value) {
			addCriterion("userId <=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridIn(List<Integer> values) {
			addCriterion("userId in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotIn(List<Integer> values) {
			addCriterion("userId not in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridBetween(Integer value1, Integer value2) {
			addCriterion("userId between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotBetween(Integer value1, Integer value2) {
			addCriterion("userId not between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNull() {
			addCriterion("userName is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("userName is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("userName =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("userName <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("userName >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("userName >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("userName <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("userName <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("userName like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("userName not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("userName in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("userName not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("userName between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("userName not between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andPasswdIsNull() {
			addCriterion("passwd is null");
			return (Criteria) this;
		}

		public Criteria andPasswdIsNotNull() {
			addCriterion("passwd is not null");
			return (Criteria) this;
		}

		public Criteria andPasswdEqualTo(String value) {
			addCriterion("passwd =", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdNotEqualTo(String value) {
			addCriterion("passwd <>", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdGreaterThan(String value) {
			addCriterion("passwd >", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdGreaterThanOrEqualTo(String value) {
			addCriterion("passwd >=", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdLessThan(String value) {
			addCriterion("passwd <", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdLessThanOrEqualTo(String value) {
			addCriterion("passwd <=", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdLike(String value) {
			addCriterion("passwd like", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdNotLike(String value) {
			addCriterion("passwd not like", value, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdIn(List<String> values) {
			addCriterion("passwd in", values, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdNotIn(List<String> values) {
			addCriterion("passwd not in", values, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdBetween(String value1, String value2) {
			addCriterion("passwd between", value1, value2, "passwd");
			return (Criteria) this;
		}

		public Criteria andPasswdNotBetween(String value1, String value2) {
			addCriterion("passwd not between", value1, value2, "passwd");
			return (Criteria) this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("email is null");
			return (Criteria) this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("email is not null");
			return (Criteria) this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("email =", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("email <>", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("email >", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("email >=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("email <", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("email <=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("email like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("email not like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailIn(List<String> values) {
			addCriterion("email in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotIn(List<String> values) {
			addCriterion("email not in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("email between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("email not between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNull() {
			addCriterion("phone is null");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("phone is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("phone =", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("phone <>", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("phone >", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("phone >=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("phone <", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("phone <=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("phone like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("phone not like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneIn(List<String> values) {
			addCriterion("phone in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotIn(List<String> values) {
			addCriterion("phone not in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("phone between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("phone not between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andSkillIsNull() {
			addCriterion("skill is null");
			return (Criteria) this;
		}

		public Criteria andSkillIsNotNull() {
			addCriterion("skill is not null");
			return (Criteria) this;
		}

		public Criteria andSkillEqualTo(String value) {
			addCriterion("skill =", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillNotEqualTo(String value) {
			addCriterion("skill <>", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillGreaterThan(String value) {
			addCriterion("skill >", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillGreaterThanOrEqualTo(String value) {
			addCriterion("skill >=", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillLessThan(String value) {
			addCriterion("skill <", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillLessThanOrEqualTo(String value) {
			addCriterion("skill <=", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillLike(String value) {
			addCriterion("skill like", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillNotLike(String value) {
			addCriterion("skill not like", value, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillIn(List<String> values) {
			addCriterion("skill in", values, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillNotIn(List<String> values) {
			addCriterion("skill not in", values, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillBetween(String value1, String value2) {
			addCriterion("skill between", value1, value2, "skill");
			return (Criteria) this;
		}

		public Criteria andSkillNotBetween(String value1, String value2) {
			addCriterion("skill not between", value1, value2, "skill");
			return (Criteria) this;
		}

		public Criteria andResumelinkIsNull() {
			addCriterion("resumeLink is null");
			return (Criteria) this;
		}

		public Criteria andResumelinkIsNotNull() {
			addCriterion("resumeLink is not null");
			return (Criteria) this;
		}

		public Criteria andResumelinkEqualTo(String value) {
			addCriterion("resumeLink =", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkNotEqualTo(String value) {
			addCriterion("resumeLink <>", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkGreaterThan(String value) {
			addCriterion("resumeLink >", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkGreaterThanOrEqualTo(String value) {
			addCriterion("resumeLink >=", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkLessThan(String value) {
			addCriterion("resumeLink <", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkLessThanOrEqualTo(String value) {
			addCriterion("resumeLink <=", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkLike(String value) {
			addCriterion("resumeLink like", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkNotLike(String value) {
			addCriterion("resumeLink not like", value, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkIn(List<String> values) {
			addCriterion("resumeLink in", values, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkNotIn(List<String> values) {
			addCriterion("resumeLink not in", values, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkBetween(String value1, String value2) {
			addCriterion("resumeLink between", value1, value2, "resumelink");
			return (Criteria) this;
		}

		public Criteria andResumelinkNotBetween(String value1, String value2) {
			addCriterion("resumeLink not between", value1, value2, "resumelink");
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