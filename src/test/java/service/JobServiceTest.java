package service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.dao.Job;
import sspku.dao.JobWithBLOBs;
import sspku.service.impl.JobService;

public class JobServiceTest extends BaseControllerTest {

	@Autowired
	private JobService jobSer;

	public void setJobSer(JobService jobSer) {
		this.jobSer = jobSer;
	}
	
	@Test
	public void testListLatestJob() {
		String city = "成都";
		int count = 100;
		List<Job> res = jobSer.listLatestJob(count, city, 1);
		Assert.assertTrue(res.size() == count);
	}
	
	@Test
	public void testGetDetail(){
		Integer id=1;
		JobWithBLOBs job=jobSer.getJobDetails(id);
		Assert.assertNotNull(job);
	}
	
	@Test
	public void testSearchJobByText(){
		String text="java研发";
		List<Job> res=jobSer.searchJobByText(text);
		Assert.assertTrue(res.size()>0);
	}
	


}
