package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Assert;
import org.junit.Test;

import sspku.util.LuceneSearchCompany;
import sspku.util.LuceneSearchJob;
import sspku.util.LuceneUtil;

public class LuceneIndexTest {

	public LuceneIndexTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testJobSearch() {
		String text = "京东";
		String field = "jobSearchIndex";
		try {
			List<LuceneSearchJob> ids = LuceneUtil.getSearchJob(text, field);
			ids.forEach(i -> System.out.println(i.JobSearchIndex + i.score));
			Assert.assertTrue(ids.size() > 0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCompanySearch() {
		String text = "阿里巴巴";
		String field = "simpleName";
		try {
			List<LuceneSearchCompany> ids = LuceneUtil.getSearchCompany(text, field);
			ids.forEach(i -> System.out.println(i.name + i.score));
			Assert.assertTrue(ids.size() > 0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultiTaskSearch() {
		String field = "jobSearchIndex";
		List<String> jobNames = Arrays.asList("java研发", "大数据建模", "数据分析", "c++开发工程师", "产品经理", "市场销售");
		long t1 = System.currentTimeMillis();
		jobNames.parallelStream().forEach(text -> {
			try {
				System.out.println(LuceneUtil.getSearchJob(text, field));
				System.out.println("-------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

	}

}
