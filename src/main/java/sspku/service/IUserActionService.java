package sspku.service;

public interface IUserActionService {

	public boolean postJob(int userId, int jobId);

	public boolean collectJob(int userId, int jobId);

	public boolean collectCompany(int userId, int companyId);

	public boolean setEmailCompany(int userId, int companyId);

	public boolean removeAction(int actionId);

}
