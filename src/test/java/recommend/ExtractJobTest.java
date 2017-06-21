package recommend;

import java.util.List;

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
import sspku.recommendEngine.ExtractCandidateJobUtil;
import sspku.recommendEngine.Record;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml",
		"classpath:spring-mvc.xml" })
public class ExtractJobTest {

	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ExtractCandidateJobUtil extractUtil;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public void setExtractUtil(ExtractCandidateJobUtil extractUtil) {
		this.extractUtil = extractUtil;
	}
	
	@Test
	public void testGetCandidatedJob() {
		Integer id = 1;
		List<Job> list=extractUtil.getCandidatedJob(id);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testTransformJob(){
		List<Job> list=extractUtil.getCandidatedJob(1);
		List<Record> records=extractUtil.tranformJobToRecord(list, 1);
		Assert.assertTrue(records.size()>0);
	}

}
