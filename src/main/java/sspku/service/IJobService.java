package sspku.service;

import java.util.List;

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
	

	
	

}
