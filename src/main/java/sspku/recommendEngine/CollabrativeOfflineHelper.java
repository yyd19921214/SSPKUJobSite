package sspku.recommendEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class CollabrativeOfflineHelper {

	private Map<String, Double> postPairSimilarity;
	private Map<String, Double> collectPairSimilarity;
	private Map<String, Double> weightedSimilarity;
	private Date finishTime;
	private Date TimeRange;

	private static String KEY_FORMAT = "JobPair_%d_%d";
	
	private static double WEIGHT_POST = 0.6;

	private static double WEIGHT_COLLECT = 0.4;

	public CollabrativeOfflineHelper() {
		super();
		this.postPairSimilarity = new HashMap<>();
		this.collectPairSimilarity = new HashMap<>();
		this.weightedSimilarity = new HashMap<>();
	}

	public Map<String, Double> getPostPairSimilarity() {
		return postPairSimilarity;
	}

	public void setPostPairSimilarity(Map<String, Double> postPairSimilarity) {
		this.postPairSimilarity = postPairSimilarity;
	}

	public Map<String, Double> getCollectPairSimilarity() {
		return collectPairSimilarity;
	}

	public void setCollectPairSimilarity(Map<String, Double> collectPairSimilarity) {
		this.collectPairSimilarity = collectPairSimilarity;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getTimeRange() {
		return TimeRange;
	}

	public void setTimeRange(Date timeRange) {
		TimeRange = timeRange;
	}

	public void addPairPostJob(int job1Id, int job2Id, double score) {
		String key = job1Id <= job2Id ? String.format(KEY_FORMAT, job1Id, job2Id)
				: String.format(KEY_FORMAT, job2Id, job1Id);
		this.postPairSimilarity.put(key, score);
	}

	public void addPairCollectJob(int job1Id, int job2Id, double score) {
		String key = job1Id <= job2Id ? String.format(KEY_FORMAT, job1Id, job2Id)
				: String.format(KEY_FORMAT, job2Id, job1Id);
		this.collectPairSimilarity.put(key, score);
	}

	public void calculateWeightedScore() {
		for (String k : this.postPairSimilarity.keySet()) {
			if (this.collectPairSimilarity.containsKey(k)) {
				double weightScore = WEIGHT_POST * postPairSimilarity.get(k)
						+ WEIGHT_COLLECT * collectPairSimilarity.get(k);
				weightedSimilarity.put(k, weightScore);
			}
		}
	}

}
