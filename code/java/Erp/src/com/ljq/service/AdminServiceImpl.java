package com.ljq.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.AdminDao;
import com.ljq.model.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Resource(name = "adminDao")
	private AdminDao adminDao;
	
	//查询单条用户信息
	public Admin selectAdmin(String userName,String password) throws SQLException{
		return adminDao.selectAdmin(userName, password);
	}
	//修改用户密码
	public boolean updateAdmin(String userName,String password)throws SQLException{
		return adminDao.updateAdmin(userName, password);
	}
	//添加用户
	public void addAdmin(int id,  String userName, String password) {
		adminDao.addAdmin(id, userName, password);
	}
	
}
