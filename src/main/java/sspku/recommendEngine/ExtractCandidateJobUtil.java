package sspku.recommendEngine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import sspku.dao.Job;
import sspku.service.impl.JobService;
import sspku.util.JobConstant;
import sspku.util.RedisUtil;

@Service
public class ExtractCandidateJobUtil {

	private static final int DEFAULT_JOB_NUM = 1000;

	private static final String DATE_CANDIDATE_PREFIX = "candidate-raw-%s";

	private static final String DATE_RECORD_PREFIX = "candidate-record-%s";

	private static final String DEFAULT_CITY = "北京";

	@Autowired
	private JobService jobServ;

	public List<Job> getRecentlyJobs() {
		return null;
	}

	/**
	 * 暂时处理方式为抽取出最近抓取的<DEFAULT_JOB_NUM>个岗位
	 * 
	 * @param userId
	 * @return
	 */
	public List<Job> getCandidatedJob(int userId) {
		LocalDate today = LocalDate.now();
		String key = String.format(DATE_CANDIDATE_PREFIX, today.toString());
		List<Job> jobs;
		if (JobConstant.USE_REDIS_CACHE_RECOMMEND && RedisUtil.existsKey(key)) {
			jobs = JSON.parseArray(RedisUtil.getString(key), Job.class);
		} else {
			// TODO need to remove the job which posted by this user
			// TODO need to get the city
			jobs = jobServ.listLatestJob(DEFAULT_JOB_NUM, DEFAULT_CITY, JobConstant.WorkType.FullTime.getIndex());
			RedisUtil.setString(key, JSON.toJSONString(jobs));
		}
		return jobs;
	}

	public List<Record> tranformJobToRecord(List<Job> jobs, int userId) {
		LocalDate today = LocalDate.now();
		String key = String.format(DATE_RECORD_PREFIX, today.toString());
		List<Record> records;
		if (JobConstant.USE_REDIS_CACHE_RECOMMEND && RedisUtil.existsKey(key)) {
			System.out.println("use the redis!!!");
			records = JSON.parseArray(RedisUtil.getString(key), Record.class);
		} else {
			records = new ArrayList<>();
			jobs.forEach(j -> {
				records.add(Record.getRecordByJob(j));
			});
			RedisUtil.setString(key, JSON.toJSONString(records));
		}
		return records;
	}

}
