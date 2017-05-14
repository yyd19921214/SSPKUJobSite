package util;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;

import sspku.util.LuceneSearchJob;
import sspku.util.LuceneUtil;

public class LuceneIndexTest {

	public LuceneIndexTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testJobSearch(){
		String text = "java 火辣健身";
		String field = "jobSearchIndex";
		try {
			List<LuceneSearchJob> ids = LuceneUtil.getSearchJobId(text, field);
			ids.forEach(i -> System.out.println(i.jobName));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
