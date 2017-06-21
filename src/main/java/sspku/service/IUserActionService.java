package sspku.service;

import java.util.Date;
import java.util.List;

import sspku.dao.UserAction;

public interface IUserActionService {

	public boolean postJob(int userId, int jobId);

	public boolean collectJob(int userId, int jobId);

	public boolean collectCompany(int userId, int companyId);

	public boolean setEmailCompany(int userId, int companyId);

	public boolean removeAction(int actionId);

	public List<UserAction> getCollectorJobs(int userId, Date afterDate);

	public List<UserAction> getPostedJobs(int userId, Date afterDate);
	
	public List<UserAction> getAllJobs(Date afterDate, String actionType);

}
