package sspku.util;

public  class LuceneSearchJob{
	public String joobId;
	public String jobName;
	public String JobSearchIndex;
	public double score;
	public LuceneSearchJob(String joobId, String jobName, String jobSearchIndex, double score) {
		super();
		this.joobId = joobId;
		this.jobName = jobName;
		JobSearchIndex = jobSearchIndex;
		this.score = score;
	}
}
