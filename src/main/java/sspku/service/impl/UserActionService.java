package sspku.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sspku.dao.UserAction;
import sspku.dao.UserActionExample;
import sspku.mapper.UserActionMapper;
import sspku.service.IUserActionService;
import sspku.util.UserActionConstant;

@Service
public class UserActionService implements IUserActionService {

	@Autowired
	private UserActionMapper actionMapper;

	// private LocalTime localTime;

	public void setActionMapper(UserActionMapper actionMapper) {
		this.actionMapper = actionMapper;
	}

	@Override
	public boolean postJob(int userId, int jobId) {
		UserActionExample example = new UserActionExample();
		example.createCriteria().andUseridEqualTo(userId).andJobidEqualTo(jobId)
				.andActiontypeEqualTo(UserActionConstant.POST_JOB);
		int count = actionMapper.countByExample(example);
		if (count > 0) {
			return false;
		}
		UserAction action = new UserAction();
		action.setUserid(userId);
		action.setJobid(jobId);
		action.setActiontype(UserActionConstant.POST_JOB);
		action.setActiondate(convertLocatDate());
		actionMapper.insert(action);
		return true;
	}

	@Override
	public boolean collectJob(int userId, int jobId) {
		UserActionExample example = new UserActionExample();
		example.createCriteria().andUseridEqualTo(userId).andJobidEqualTo(jobId)
				.andActiontypeEqualTo(UserActionConstant.COLLECT_JOB);
		int count = actionMapper.countByExample(example);
		if (count > 0) {
			return false;
		}
		UserAction action = new UserAction();
		action.setUserid(userId);
		action.setJobid(jobId);
		action.setActiontype(UserActionConstant.COLLECT_JOB);
		action.setActiondate(convertLocatDate());
		actionMapper.insert(action);
		return true;
	}

	@Override
	public boolean collectCompany(int userId, int companyId) {
		UserActionExample example = new UserActionExample();
		example.createCriteria().andUseridEqualTo(userId).andCompanyidEqualTo(companyId)
				.andActiontypeEqualTo(UserActionConstant.COLLECT_COMPANY);
		int count = actionMapper.countByExample(example);
		if (count > 0) {
			return false;
		}
		UserAction action = new UserAction();
		action.setUserid(userId);
		action.setCompanyid(companyId);
		action.setActiontype(UserActionConstant.COLLECT_COMPANY);
		action.setActiondate(convertLocatDate());
		actionMapper.insert(action);
		return true;
	}

	@Override
	public boolean setEmailCompany(int userId, int companyId) {
		UserActionExample example = new UserActionExample();
		example.createCriteria().andUseridEqualTo(userId).andCompanyidEqualTo(companyId)
				.andActiontypeEqualTo(UserActionConstant.EMAIL_COMPANY);
		int count = actionMapper.countByExample(example);
		if (count > 0) {
			return false;
		}
		UserAction action = new UserAction();
		action.setUserid(userId);
		action.setCompanyid(companyId);
		action.setActiontype(UserActionConstant.EMAIL_COMPANY);
		action.setActiondate(convertLocatDate());
		actionMapper.insert(action);
		return true;
	}

	@Override
	public boolean removeAction(int actionId) {
		actionMapper.deleteByPrimaryKey(actionId);
		return true;
	}

	@Override
	public List<UserAction> getCollectorJobs(int userId, Date afterDate) {
		UserActionExample example = new UserActionExample();
		if (afterDate == null) {
			example.createCriteria().andUseridEqualTo(userId).andActiontypeEqualTo(UserActionConstant.COLLECT_JOB);
		} else {
			example.createCriteria().andUseridEqualTo(userId).andActiondateGreaterThan(afterDate)
					.andActiontypeEqualTo(UserActionConstant.COLLECT_JOB);
		}
		return actionMapper.selectByExample(example);
	}

	@Override
	public List<UserAction> getPostedJobs(int userId, Date afterDate) {
		UserActionExample example = new UserActionExample();
		if (afterDate == null) {
			example.createCriteria().andUseridEqualTo(userId).andActiontypeEqualTo(UserActionConstant.POST_JOB);
		} else {
			example.createCriteria().andUseridEqualTo(userId).andActiondateGreaterThan(afterDate)
					.andActiontypeEqualTo(UserActionConstant.POST_JOB);
		}
		return actionMapper.selectByExample(example);
	}

	@Override
	public List<UserAction> getAllJobs(Date afterDate, String actionType) {
		UserActionExample example = new UserActionExample();
		if (afterDate == null) {
			example.createCriteria().andActiontypeEqualTo(actionType);
		} else {
			example.createCriteria().andActiondateGreaterThan(afterDate).andActiontypeEqualTo(actionType);
		}
		return actionMapper.selectByExample(example);
	}

	private Date convertLocatDate() {
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
