package com.whl.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whl.config.datasource.dynamic.TargetDataSource;
import com.whl.demo.bean.User;
import com.whl.mybatis.mapper.PurSysUserMapper;
import com.whl.mybatis.pojo.PurSysUser;
import com.whl.user.dao.TestDao;

@Service
public class UserService {

	@Autowired
	private TestDao testDao;
	
	/*springboot整合mybatis相当简单，只要配置@Mapperscan扫描包就行了*/
	@Autowired
	private PurSysUserMapper userMapper;

	/**
	 * 不指定数据源使用默认数据源
	 * 
	 * @return
	 */
	public List<User> getList() {
		
//		PurSysUser sysUser = userMapper.selectByPrimaryKey("lisi");
//		System.out.println(sysUser.getUsername());
		return testDao.getList();
//		return null;
	}

	/**
	 * 指定数据源
	 * 
	 * @return
	 */
	@TargetDataSource("ds1")
	public List<User> getListByDs1() {
		return testDao.getListByDs1();
	}
}
