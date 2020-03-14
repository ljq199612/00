package com.ljq.dao;

import java.sql.SQLException;

import com.ljq.model.Admin;

public interface AdminDao {
	//查询单条用户信息
	public Admin selectAdmin(String userName,String password)throws SQLException;
	//修改用户密码
	public boolean updateAdmin(String userName,String password)throws SQLException;
	//添加用户
	public void addAdmin(int id,  String userName, String password);
}
