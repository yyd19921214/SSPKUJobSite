package sspku.recommendEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Strings;

import sspku.dao.Job;
import sspku.dto.Expection;

public class Record {

	private String key;
	private Map<String, RecommendValue<Integer>> valueMIB;
	private Map<String, RecommendValue<Integer>> valueLIB;
	private Map<String, RecommendValue<String>> valueStrEq;
	private Map<String, RecommendValue<String>> valueStrContains;
	private Map<String, RecommendValue<String>> valueStrLeven;
	private boolean ifResult;
	private double score;

	private static final String SALARY_KEY = "salary";
	private static final String JOB_NAME_KEY = "expJob";
	private static final String CITY_NAME_KEY = "expCity";
	private static final String JOB_TYPE_KEY = "jobType";

	public Record() {
		this.valueLIB = new HashMap<>();
		this.valueMIB = new HashMap<>();
		this.valueStrEq = new HashMap<>();
		this.valueStrContains = new HashMap<>();
		this.valueStrLeven = new HashMap<>();
		this.ifResult = false;
	}

	public static Record getRecordByExpection(Expection ex) {
		Record r = new Record();
		if (ex.expMinSalary != null) {
			r.valueMIB.put(SALARY_KEY, new RecommendValue<Integer>(ex.expMinSalary));
		}
		if (!Strings.isNullOrEmpty(ex.expJob)) {
			r.valueStrLeven.put(JOB_NAME_KEY, new RecommendValue<String>(ex.getExpJob()));
		}
		if (!Strings.isNullOrEmpty(ex.getExpCity())) {
			r.valueStrEq.put(CITY_NAME_KEY, new RecommendValue<String>(ex.getExpCity()));
		}
		if (ex.getJobType() != null) {
			r.valueStrEq.put(JOB_TYPE_KEY, new RecommendValue<String>(String.valueOf(ex.getJobType())));
		}
		r.key = UUID.randomUUID().toString();
		return r;
	}

	public static Record getRecordByJob(Job job) {
		Record r = new Record();
		if (job.getMinsalary() != null) {
			r.valueMIB.put(SALARY_KEY, new RecommendValue<Integer>(job.getMinsalary()));
		}
		r.valueStrLeven.put(JOB_NAME_KEY, new RecommendValue<String>(job.getName()));
		r.valueStrEq.put(CITY_NAME_KEY, new RecommendValue<String>(job.getCity()));
		r.valueStrEq.put(JOB_TYPE_KEY, new RecommendValue<String>(String.valueOf(job.getWorktype())));
		r.key = String.valueOf(job.getId());
		return r;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<String, RecommendValue<Integer>> getValueMIB() {
		return valueMIB;
	}

	public void setValueMIB(Map<String, RecommendValue<Integer>> valueMIB) {
		this.valueMIB = valueMIB;
	}

	public Map<String, RecommendValue<Integer>> getValueLIB() {
		return valueLIB;
	}

	public void setValueLIB(Map<String, RecommendValue<Integer>> valueLIB) {
		this.valueLIB = valueLIB;
	}

	public Map<String, RecommendValue<String>> getValueStrEq() {
		return valueStrEq;
	}

	public void setValueStrEq(Map<String, RecommendValue<String>> valueStrEq) {
		this.valueStrEq = valueStrEq;
	}

	public Map<String, RecommendValue<String>> getValueStrContains() {
		return valueStrContains;
	}

	public void setValueStrContains(Map<String, RecommendValue<String>> valueStrContains) {
		this.valueStrContains = valueStrContains;
	}

	public Map<String, RecommendValue<String>> getValueStrLeven() {
		return valueStrLeven;
	}

	public void setValueStrLeven(Map<String, RecommendValue<String>> valueStrLeven) {
		this.valueStrLeven = valueStrLeven;
	}

	public boolean isIfResult() {
		return ifResult;
	}

	public void setIfResult(boolean ifResult) {
		this.ifResult = ifResult;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
		this.ifResult = true;
	}
}

class RecommendValue<T> {
	T value;
	double weight;

	public RecommendValue(T value, double weight) {
		super();
		this.value = value;
		this.weight = weight;
	}

	public RecommendValue(T value) {
		super();
		this.value = value;
		this.weight = 1.0;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
