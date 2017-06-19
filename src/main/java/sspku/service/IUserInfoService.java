package sspku.service;

import java.util.List;

import core.ajaxResult.Result;
import sspku.dao.UserInfo;
import sspku.dto.BasicInfo;
import sspku.dto.EduExp;
import sspku.dto.Expection;
import sspku.dto.ProjectExp;
import sspku.dto.WorkExp;

public interface IUserInfoService {

	public UserInfo login(String userName, String passwd);

	public UserInfo register(UserInfo user);

	public Boolean changePasswd(String nickName, String oldPasswd, String newPasswd);

	public BasicInfo dispBasicInfo(int id);

	public Boolean saveBasicInfo(int id, BasicInfo basic);

	public List<EduExp> dispEduExp(int id);

	public Boolean saveEduExp(int id, List<EduExp> eduExp);

	public List<ProjectExp> dispProjectExp(int id);

	public Boolean saveProjectExp(int id, List<ProjectExp> projectExp);

	public List<WorkExp> dispWorkExp(int id);

	public Boolean saveWorkExp(int id, List<WorkExp> workExp);

	public List<Expection> dispExpection(int id);

	public Boolean saveExpection(int id, List<Expection> expect);

	public Boolean changeSelfDesc(int id, String selfDesc);

	public Integer getUserCounts();

	public List<UserInfo> getUsers(int startNum, int limitNum);

	public UserInfo getUserByName(String name);

}
