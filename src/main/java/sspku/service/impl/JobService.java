package sspku.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sspku.dao.Job;
import sspku.dao.JobExample;
import sspku.dao.JobWithBLOBs;
import sspku.mapper.JobMapper;
import sspku.service.IJobService;
import sspku.util.JobConstant;
import sspku.util.JobConstant.JobState;
import sspku.util.LuceneSearchJob;
import sspku.util.LuceneUtil;
import sspku.util.RedisClient;

@Service
public class JobService implements IJobService {

	// TODO 将setLimit orderBy 逻辑抽象出来 默认limit=100；orderBy:createTime
	// state=1(招聘尚未结束)利用AOP?
	@Autowired
	private JobMapper jobMapper;
	@Autowired
	private RedisClient redisUtil;

	public void setJobMapper(JobMapper jobMapper) {
		this.jobMapper = jobMapper;
	}

	@Override
	public List<Job> listLatestJob(int count, String city, int workType) {
		JobExample example = new JobExample();
		example.setOrderByClause(JobConstant.defaultOrder);
		example.createCriteria().andCityEqualTo(city).andStateEqualTo(JobState.HIRING.getIndex())
				.andWorktypeEqualTo(workType);
		example.setLimit(count);
		return jobMapper.selectByExample(example);
	}

	@Override
	public List<Job> listHotestJob(int count, String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobWithBLOBs getJobDetails(int jobId) {
		return jobMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public List<Job> listJobOfCompany(int companyId, int count) {
		JobExample example = new JobExample();
		example.createCriteria().andCompanyidEqualTo(companyId).andStateEqualTo(JobState.HIRING.getIndex());
		example.setLimit(count);
		example.setOrderByClause(JobConstant.defaultOrder);
		return null;
	}

	@Override
	public List<Job> searchJobByText(String text) {
		List<LuceneSearchJob> searchList = getLuceneJob(text);
		if (searchList != null) {
			List<Job> result = searchList.parallelStream().filter(i -> i.score > JobConstant.thresholdSearch)
					.map(i -> jobMapper.selectBaseByPrimaryKey(Integer.valueOf(i.joobId)))
					.filter(i -> i.getState() == JobState.HIRING.getIndex()).collect(Collectors.toList());
			return result;
		}
		return null;
	}

	@Override
	public List<Job> searchJobByText(String text, Predicate<Job> predicat) {
		List<LuceneSearchJob> searchList = getLuceneJob(text);
		if (searchList != null) {
			List<Job> list = searchList.parallelStream().filter(i -> i.score > JobConstant.thresholdSearch)
					.map(i -> jobMapper.selectBaseByPrimaryKey(Integer.valueOf(i.joobId)))
					.filter(i -> i.getState() == JobState.HIRING.getIndex() && predicat.test(i))
					.collect(Collectors.toList());
			return list;
		}
		return null;
	}

	@Override
	public List<Job> searchJobByText(String text, Predicate<Job> predicat, Comparator<Job> comparator) {
		List<LuceneSearchJob> searchList = getLuceneJob(text);
		if (searchList != null) {
			List<Job> list = searchList.parallelStream().filter(i -> i.score > JobConstant.thresholdSearch)
					.map(i -> jobMapper.selectBaseByPrimaryKey(Integer.valueOf(i.joobId)))
					.filter(i -> i.getState() == JobState.HIRING.getIndex() && predicat.test(i)).sorted(comparator)
					.collect(Collectors.toList());
			return list;
		}

		return null;
	}

	@Override
	public List<Job> filterJobs(List<Job> jobs, Predicate<Job> predicate, Comparator<Job> comparator) {
		if (predicate != null && comparator != null) {
			return jobs.parallelStream().filter(i -> predicate.test(i)).sorted(comparator).collect(Collectors.toList());
		} else if (predicate != null) {
			return jobs.parallelStream().filter(i -> predicate.test(i)).collect(Collectors.toList());
		} else if (comparator != null) {
			return jobs.parallelStream().sorted(comparator).collect(Collectors.toList());
		}
		return null;
	}

	private List<LuceneSearchJob> getLuceneJob(String text) {
		List<LuceneSearchJob> searchList = null;
		if (JobConstant.USE_CACHE && redisUtil.existsKey(text)) {
			searchList = JSONObject.parseArray(redisUtil.get(text), LuceneSearchJob.class);
		} else {
			try {
				searchList = LuceneUtil.getSearchJob(text, LuceneUtil.JOB_SEARCHINDEX);
				if (JobConstant.USE_CACHE)
					redisUtil.set(text, JSON.toJSONString(searchList));
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		return searchList;
	}

	@Override
	public List<Job> getSimilarJobs(Job job) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
