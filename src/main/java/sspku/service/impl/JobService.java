package sspku.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import sspku.dao.Job;
import sspku.dao.JobExample;
import sspku.dao.JobWithBLOBs;
import sspku.mapper.JobMapper;
import sspku.service.IJobService;
import sspku.util.JobConstant;
import sspku.util.JobConstant.JobState;
import sspku.util.LuceneUtil;
import sspku.util.RedisClient;

@Service
public class JobService implements IJobService {

	// TODO 将setLimit orderBy 逻辑抽象出来 默认limit=100；orderBy:createTime
	// state=1(招聘尚未结束)利用AOP?
	@Autowired
	private JobMapper jobMapper;

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
		try {
			// TODO Add more detail logic
			List<Job> list = LuceneUtil.getSearchJob(text, LuceneUtil.JOB_SEARCHINDEX).parallelStream()
					.filter(i -> i.score > JobConstant.thresholdSearch)
					.map(i -> jobMapper.selectBaseByPrimaryKey(Integer.valueOf(i.joobId)))
					.filter(i -> i.getState() == JobState.HIRING.getIndex()).collect(Collectors.toList());
			return list;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Job> searchJobByText(String text, Predicate<Job> predicat) {
		// TODO Auto-generated method stub
		return null;
	}

}
