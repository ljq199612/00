package com.ljq.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Admin;


@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public Admin selectAdmin(String userName,String password) throws SQLException {
		Admin up=new Admin();
		Map<String, String> map=new HashMap<String, String>();
		map.put("userName", userName);
		map.put("password", password);
		try {
			up=(Admin) sqlMapClient.queryForObject("Typemodule_sqlMap.select_a_user",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return up;
	}
	//修改数据
	public boolean updateAdmin(String userName,String password) throws SQLException {
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("userName", userName);
		map.put("password", password);
		try {
			sqlMapClient.update("Typemodule_sqlMap.update_by_userName",map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addAdmin(int id,  String userName, String password) {
		try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("userId", id);
				map.put("userName", userName);
				map.put("password", password);
			   sqlMapClient.insert("Typemodule_sqlMap.addUser",map);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
	}

}
