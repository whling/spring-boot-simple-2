package com.whl.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.whl.demo.bean.User;

@Repository
public class TestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
     * 不指定数据源使用默认数据源
     * @return
     */
    public List<User> getList(){
       String sql = "select * from User";
        return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User User = new User();
                User.setId(rs.getLong("id"));
                User.setName(rs.getString("name"));;
                return User;
            }
        });
    }

	/**
     * 指定数据源
     * 在对应的service进行指定;
     * @return
     * @author SHANHY
     * @create  2016年1月24日
     */
    public List<User> getListByDs1(){
        /*
         * 这张表示复制的主库到ds1的，在ds中并没有此表.
         * 需要自己自己进行复制，不然会报错：Table 'test1.User1' doesn't exist
         */
       String sql = "select * from User1";
        return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>(){
 
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User User = new User();
                User.setId(rs.getLong("id"));
                User.setName(rs.getString("name"));;
                return User;
            }
 
        });
    }
}
