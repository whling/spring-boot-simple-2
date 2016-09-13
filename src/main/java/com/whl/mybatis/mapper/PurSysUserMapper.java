package com.whl.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whl.mybatis.pojo.PurSysUser;
import com.whl.mybatis.pojo.PurSysUserExample;

public interface PurSysUserMapper {
    int countByExample(PurSysUserExample example);

    int deleteByExample(PurSysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PurSysUser record);

    int insertSelective(PurSysUser record);

    List<PurSysUser> selectByExample(PurSysUserExample example);

    PurSysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PurSysUser record, @Param("example") PurSysUserExample example);

    int updateByExample(@Param("record") PurSysUser record, @Param("example") PurSysUserExample example);

    int updateByPrimaryKeySelective(PurSysUser record);

    int updateByPrimaryKey(PurSysUser record);
}