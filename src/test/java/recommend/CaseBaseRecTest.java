package recommend;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sspku.dao.Job;
import sspku.recommendEngine.CaseBasedRecom;
import sspku.recommendEngine.ExtractCandidateJobUtil;
import sspku.recommendEngine.Record;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml",
		"classpath:spring-mvc.xml" })
public class CaseBaseRecTest {

	public CaseBaseRecTest() {
		// TODO Auto-generated constructor stub
	}

	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private CaseBasedRecom caseRec;
	@Autowired
	private ExtractCandidateJobUtil extractUtil;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		// caseRec.setExtractUtil(extractUtil);
	}

	@Test
	public void testPredict() {

		Job job1 = new Job();
		job1.setCity("北京");
		job1.setMinsalary(18);
		job1.setWorktype(1);
		job1.setName("java研发");
		job1.setId(1);

		Job job2 = new Job();
		job2.setCity("广州");
		job2.setMinsalary(8);
		job2.setWorktype(2);
		job2.setName("c语言");
		job2.setId(2);

		Job job3 = new Job();
		job3.setCity("北京");
		job3.setMinsalary(9);
		job3.setWorktype(2);
		job3.setName("电子通讯");
		job3.setId(3);

		List<Job> jobCandidate = new ArrayList<>();
		jobCandidate.add(job1);
		jobCandidate.add(job2);
		jobCandidate.add(job3);
		List<Record> recordsCandidate=extractUtil.tranformJobToRecord(jobCandidate, 1);

		ExtractCandidateJobUtil extractUtil = mock(ExtractCandidateJobUtil.class);
		when(extractUtil.getCandidatedJob(anyInt())).thenReturn(jobCandidate);
		when(extractUtil.tranformJobToRecord(anyListOf(Job.class), anyInt())).thenReturn(recordsCandidate);
		caseRec.setExtractUtil(extractUtil);
		
		Map<String,Double> predictId = caseRec.predict(1, 20);
		for(String k:predictId.keySet()){
			System.out.println(k+":"+predictId.get(k));
		}
		Assert.assertTrue(predictId.size()>0);
	}

}
