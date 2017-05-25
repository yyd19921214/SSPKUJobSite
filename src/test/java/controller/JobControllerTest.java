package controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.controller.JobController;
import sspku.dao.Job;
import sspku.service.impl.JobService;

public class JobControllerTest extends BaseControllerTest {

	@Autowired
	private JobController controller;

	public JobControllerTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testGetLatestJob() {
		int num = 10;
		String city = "北京";
		int workType = 2;
		JobService mock = mock(JobService.class);
		List<Job> jobs = new ArrayList<>();
		Job j = new Job();
		j.setCity(city);
		j.setWorktype(workType);
		jobs.add(j);
		when(mock.listLatestJob(anyInt(), anyString(), anyInt())).thenReturn(jobs);
		controller.setJobService(mock);
		Assert.assertTrue(controller.getLatestJob(num, workType, city).getCode().equals("1"));

	}

}
