package sspku.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import sspku.dao.Job;
import sspku.dao.JobWithBLOBs;

public interface IJobService {

	/**
	 * list latest jobs  used in homePage
	 * @param count
	 * @param city
	 * @param workType
	 * @return
	 */
	List<Job> listLatestJob(int count, String city,int workType);
	
	/**
	 * list hotest job
	 */
	List<Job> listHotestJob(int count, String city);
	
	/**
	 * get job detail info using jobId
	 * @param jobId
	 * @return
	 */
	JobWithBLOBs getJobDetails(int jobId);
	

	/**
	 * list job of given company
	 * @param companyId
	 * @param count
	 * @return
	 */
	List<Job> listJobOfCompany(int companyId,int count);
	
	/**
	 * search Jobs by text
	 * @param text
	 * @return
	 */
	List<Job> searchJobByText(String text);
	
	/**
	 * search and filter Jobs by text
	 * @param text
	 * @param predicat
	 * @return
	 */
	List<Job> searchJobByText(String text,Predicate<Job> predicat);
	
	
	/**
	 * search and filter and sort Jobs
	 * @param text
	 * @param predicat
	 * @param comparator
	 * @return
	 */
	List<Job> searchJobByText(String text,Predicate<Job> predicat,Comparator<Job> comparator);
	
	
	/**
	 * filter and sort a jobList
	 * @param jobs
	 * @param predicate
	 * @param comparator
	 * @return
	 */
	List<Job> filterJobs(List<Job> jobs,Predicate<Job> predicate,Comparator<Job> comparator);
	
	/**
	 * get similar job of certain job
	 * @param job
	 * @return
	 */
	List<Job> getSimilarJobs(Job job);
	

	
	

}
