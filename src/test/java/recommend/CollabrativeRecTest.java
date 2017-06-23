package recommend;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import base.BaseControllerTest;
import sspku.dao.Job;
import sspku.mapper.JobMapper;
import sspku.recommendEngine.CollabrativeBasedRecom;
import sspku.recommendEngine.ExtractCandidateJobUtil;
import sspku.util.RedisUtil;

public class CollabrativeRecTest extends BaseControllerTest {

	@Autowired
	private CollabrativeBasedRecom recSer;
	@Autowired
	private ExtractCandidateJobUtil extract;
	@Autowired
	private JobMapper jobMapper;

	public CollabrativeRecTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCalculateSimilarity() {
		boolean b = recSer.calculateSimilarity();
		Assert.assertTrue(b);
		String s = RedisUtil.getString("CollabrativeKey");
		System.out.println(s);
	}

	@Test
	public void testCalculateSimilarityMultiThread() {
		try {
			boolean b = recSer.calculateSimilarityMultiThread();
			Assert.assertTrue(b);
			String s = RedisUtil.getString("CollabrativeKey");
			System.out.println(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCollabrative() {

		Job job1 = jobMapper.selectByPrimaryKey(17);
		Job job2 = jobMapper.selectBaseByPrimaryKey(21);
		Job job3 = jobMapper.selectBaseByPrimaryKey(12);
		List<Job> jobs = Lists.newArrayList(job1, job2, job3);

		ExtractCandidateJobUtil extractUtil = mock(ExtractCandidateJobUtil.class);
		when(extractUtil.getCandidatedJob(anyInt())).thenReturn(jobs);
		recSer.setExtractUtil(extractUtil);

		Map<String, Double> map = recSer.predict(1, 2);
		Assert.assertTrue(map != null);
		System.out.println("answer is:" + map);
	}

}
