package sspku.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import core.ajaxResult.ErrorCode;
import core.ajaxResult.Result;
import sspku.dao.UserInfo;
import sspku.dao.UserInfoWithBLOBs;
import sspku.dto.BasicInfo;
import sspku.dto.EduExp;
import sspku.dto.Expection;
import sspku.dto.ProjectExp;
import sspku.dto.WorkExp;
import sspku.mapper.UserInfoMapper;
import sspku.service.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService {

	@Autowired
	private UserInfoMapper userMapper;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public UserInfo login(String userName, String passwd) {
		List<UserInfo> user=userMapper.selectByUserName(userName);
		if(user.get(0).getPasswd().equals(passwd)){
			return user.get(0);
		}
		return null;
	}

	@Override
	public UserInfo register(UserInfo user) {
		List<UserInfo> users=userMapper.selectByUserName(user.getUsername());
		if(!CollectionUtils.isEmpty(users)){
			return null;
		}
		userMapper.insertBase(user);
		return user;
	}

	@Override
	public Boolean changePasswd(String nickName, String oldPasswd, String newPasswd) {
		List<UserInfo> users=userMapper.selectByUserName(nickName);
		if(!users.get(0).getPasswd().equals(oldPasswd)){
			return false;
		}
		else{
			users.get(0).setPasswd(newPasswd);
			userMapper.updateByPrimaryKey(users.get(0));
			return true;
		}
	}

	@Override
	public BasicInfo dispBasicInfo(int id) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if (user.getBasicinfo() != null) {
			BasicInfo info = JSON.parseObject(user.getBasicinfo(), BasicInfo.class);
			return info;
		}
		return null;
	}

	@Override
	public Boolean saveBasicInfo(int id, BasicInfo basic) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		user.setEmail(basic.getEmail());
		user.setPhone(basic.getTelephone());
		user.setBasicinfo(JSON.toJSONString(basic));
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public List<EduExp> dispEduExp(int id) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if (user.getEduexp() != null) {
			List<EduExp> edu = JSON.parseArray(user.getEduexp(), EduExp.class);
			return edu;
		}
		return null;
	}

	@Override
	public Boolean saveEduExp(int id, List<EduExp> eduExp) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		user.setEduexp(JSON.toJSONString(eduExp));
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public List<ProjectExp> dispProjectExp(int id) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if (user.getProjectexp() != null) {
			List<ProjectExp> projects = JSON.parseArray(user.getProjectexp(), ProjectExp.class);
			return projects;
		}
		return null;
	}

	@Override
	public Boolean saveProjectExp(int id, List<ProjectExp> projectExp) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		user.setProjectexp(JSON.toJSONString(projectExp));
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public List<WorkExp> dispWorkExp(int id) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if (user.getWorkexp() != null) {
			List<WorkExp> workExp = JSON.parseArray(user.getWorkexp(), WorkExp.class);
			return workExp;
		}
		return null;
	}

	@Override
	public Boolean saveWorkExp(int id, List<WorkExp> workExp) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		user.setWorkexp(JSON.toJSONString(workExp));
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public List<Expection> dispExpection(int id) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if (user.getExpection()!= null) {
			List<Expection> workExp = JSON.parseArray(user.getExpection(), Expection.class);
			return workExp;
		}
		return null;
	}

	@Override
	public Boolean saveExpection(int id, List<Expection> expect) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(id);
		user.setExpection(JSON.toJSONString(expect));
		userMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public Boolean changeSelfDesc(int id, String selfDesc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUserCounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUsers(int startNum, int limitNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
