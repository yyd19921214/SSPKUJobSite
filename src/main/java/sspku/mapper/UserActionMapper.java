package sspku.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sspku.dao.UserAction;
import sspku.dao.UserActionExample;

public interface UserActionMapper {
	int countByExample(UserActionExample example);

	int deleteByExample(UserActionExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserAction record);

	int insertSelective(UserAction record);

	List<UserAction> selectByExampleWithBLOBs(UserActionExample example);

	List<UserAction> selectByExample(UserActionExample example);

	UserAction selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserAction record, @Param("example") UserActionExample example);

	int updateByExampleWithBLOBs(@Param("record") UserAction record, @Param("example") UserActionExample example);

	int updateByExample(@Param("record") UserAction record, @Param("example") UserActionExample example);

	int updateByPrimaryKeySelective(UserAction record);

	int updateByPrimaryKeyWithBLOBs(UserAction record);

	int updateByPrimaryKey(UserAction record);
}