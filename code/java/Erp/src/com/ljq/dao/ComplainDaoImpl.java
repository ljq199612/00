package com.ljq.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Admin;
import com.ljq.model.Complain;
import com.ljq.model.Household;
import com.ljq.model.Complain;

@Repository("complainDao")
public class ComplainDaoImpl implements ComplainDao {
	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public List<Complain> obtainComplainList(Complain complain) throws SQLException {
		List<Complain> complainList = null;
		complain = new Complain();
		try {
			complainList = sqlMapClient.queryForList("SQLMAP_COMPLAIN.select_complain", complain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return complainList;
	}
	
	public Complain selectComplain(String id) throws SQLException{
		Complain complain = null;
		try {
			complain = (Complain)sqlMapClient.queryForObject("SQLMAP_COMPLAIN.select_a_complain", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return complain;
	}
	
	//修改数据
	public boolean deleteComplain(String id) throws SQLException {
		try {
			sqlMapClient.delete("SQLMAP_COMPLAIN.delete_a_complain", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public boolean updateComplain(Complain complain) throws SQLException{
		try {
			sqlMapClient.update("SQLMAP_COMPLAIN.update_a_complain", complain);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void insertComplain(Complain complain) {
			
	}

}
