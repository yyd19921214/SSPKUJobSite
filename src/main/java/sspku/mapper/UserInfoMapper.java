package sspku.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import sspku.dao.UserInfo;
import sspku.dao.UserInfoExample;
import sspku.dao.UserInfoWithBLOBs;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer userid);
    
    int insertBase(UserInfo record);

    int insert(UserInfoWithBLOBs record);

    int insertSelective(UserInfoWithBLOBs record);

    List<UserInfoWithBLOBs> selectByExampleWithBLOBs(UserInfoExample example);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfoWithBLOBs selectByPrimaryKey(Integer userid);
    
    List<UserInfo> selectByUserName(String userName);

    int updateByExampleSelective(@Param("record") UserInfoWithBLOBs record, @Param("example") UserInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") UserInfoWithBLOBs record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserInfoWithBLOBs record);

    int updateByPrimaryKey(UserInfo record);
}