package sspku.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sspku.dao.UserAction;
import sspku.dao.UserActionExample;
import sspku.mapper.UserActionMapper;
import sspku.service.IUserActionService;
import sspku.util.UserActionConstant;

@Service
public class ActionService implements IUserActionService {

	@Autowired
	private UserActionMapper actionMapper;
	
//	private LocalTime localTime;

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
	
	
	private Date convertLocatDate(){
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	

}
