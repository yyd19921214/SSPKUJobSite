package sspku.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import core.mapper.IBaseMapper;
import sspku.dao.Job;
import sspku.dao.JobExample;
import sspku.dao.JobWithBLOBs;

public interface JobMapper{
    int countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobWithBLOBs record);

    int insertSelective(JobWithBLOBs record);

    List<JobWithBLOBs> selectByExampleWithBLOBs(JobExample example);

    List<Job> selectByExample(JobExample example);

    JobWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobWithBLOBs record, @Param("example") JobExample example);

    int updateByExampleWithBLOBs(@Param("record") JobWithBLOBs record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(JobWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JobWithBLOBs record);

    int updateByPrimaryKey(Job record);

    
    
}