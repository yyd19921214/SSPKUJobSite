package sspku.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.lionsoul.jcseg.analyzer.v5x.JcsegAnalyzer5X;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Strings;

import sspku.dao.CompanyWithBLOBs;
import sspku.dao.JobExample;
import sspku.dao.JobWithBLOBs;
import sspku.mapper.JobMapper;

public class LuceneUtil {

	public LuceneUtil() {
		// TODO Auto-generated constructor stub
	}

	private static volatile Directory directory = null;
	private static volatile IndexSearcher isearcher = null;

	private static final boolean diskIndex = true;

	private static final String indexPath = "/tmp/luceneIndex";

	public static void createJobIndex(List<JobWithBLOBs> jobs) throws IOException {
		Directory directory = getDirectory();
		Analyzer analyzer = new JcsegAnalyzer5X(1);
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);

		for (JobWithBLOBs job : jobs) {
			Document doc = new Document();
			if (!Strings.isNullOrEmpty(job.getName())) {
				doc.add(new Field("jobName", job.getName(), TextField.TYPE_STORED));
			}
			if (!Strings.isNullOrEmpty(job.getSearchindex())) {
				doc.add(new Field("jobSearchIndex", job.getSearchindex(), TextField.TYPE_STORED));
			}
			doc.add(new Field("jobId", job.getId().toString(), StringField.TYPE_STORED));
			iwriter.addDocument(doc);
		}
		iwriter.close();
	}
	
	public static void createCompanyIndex(List<CompanyWithBLOBs> companys) throws IOException{
		Directory directory = getDirectory();
		Analyzer analyzer = new JcsegAnalyzer5X(1);
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);
		for(CompanyWithBLOBs company:companys){
			Document doc=new Document();
			if(!Strings.isNullOrEmpty(company.getName())){
				doc.add(new Field("simpleName",company.getName(),TextField.TYPE_STORED));
			}
			if(!Strings.isNullOrEmpty(company.getAlias())){
				String alias=String.join(" ",Arrays.asList(company.getAlias().split(",")));
				doc.add(new Field("alias",alias,TextField.TYPE_STORED));
			}
			doc.add(new Field("companyId", company.getId().toString(), StringField.TYPE_STORED));
			iwriter.addDocument(doc);
		}
		iwriter.close();
	}

	private static Directory getDirectory() throws IOException {
		if (directory == null) {
			if (diskIndex) {
				directory = FSDirectory.open(Paths.get(indexPath));
			} else {
				directory = new RAMDirectory();
			}
		}
		return directory;
	}

	private static IndexSearcher getIndexSearch() throws IOException {
		if (isearcher == null) {
			synchronized (LuceneUtil.class) {
				if (isearcher == null) {
					Directory directory = getDirectory();
					DirectoryReader ireader = DirectoryReader.open(directory);
					isearcher = new IndexSearcher(ireader);
				}
			}
		}
		return isearcher;
	}

	public static List<LuceneSearchJob> getSearchJobId(String text, String field) throws IOException, ParseException {
		Analyzer analyzer = new JcsegAnalyzer5X(1);
		IndexSearcher isearcher = getIndexSearch();
		// 瑙ｆ瀽涓�涓畝鍗曠殑鏌ヨ
		QueryParser parser = new QueryParser(field, analyzer);
		Query query = parser.parse(text);
		ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
		List<LuceneSearchJob> result = new ArrayList<>();
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			result.add(new LuceneSearchJob(hitDoc.get("jobId"), hitDoc.get("jobName"), hitDoc.get("jobSearchIndex"),
					hits[i].score));
		}
		return result;
	}

	public static void indexJob() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml",
				"classpath:spring-mybatis.xml", "classpath:spring-mvc.xml");
		JobMapper mapper = (JobMapper) ac.getBean("jobMapper");
		JobExample ex = new JobExample();
		List<JobWithBLOBs> jobs = mapper.selectByExampleWithBLOBs(ex);
		try {
			createJobIndex(jobs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
//		indexJob();
		 String text = "java 鐏荆鍋ヨ韩";
		 String field = "jobSearchIndex";
		 try {
		 List<LuceneSearchJob> ids = getSearchJobId(text, field);
		 ids.forEach(i -> System.out.println(i.JobSearchIndex));
		 // System.out.println(id.size());
		 } catch (IOException e) {
		 e.printStackTrace();
		 } catch (ParseException e) {
		 e.printStackTrace();
		 }

	}

}
