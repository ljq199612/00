package com.ljq.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Environment;

	
@Repository("environmentDao")
public class EnvironmentDaoImpl implements EnvironmentDao{

	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
		
	public List<Environment> obtainEnvironmentList(Environment environment) throws SQLException {
		List<Environment> environmentList = null;
		try {
			environmentList = sqlMapClient.queryForList("SQLMAP_ENVIRONMENT.select_environment", environment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return environmentList;
	}
	
	public Environment selectEnvironment(String id) throws SQLException{
		Environment environment = null;
		try {
			environment = (Environment)sqlMapClient.queryForObject("SQLMAP_ENVIRONMENT.select_a_environment", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return environment;
	}
	
	public boolean deleteEnvironment(String id) throws SQLException {
		try {
			sqlMapClient.delete("SQLMAP_ENVIRONMENT.delete_a_environment", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateEnvironment(Environment environment) throws SQLException{
		try {
			sqlMapClient.update("SQLMAP_ENVIRONMENT.update_a_environment", environment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertEnvironment(Environment environment) throws SQLException{
		try {
			sqlMapClient.insert("SQLMAP_ENVIRONMENT.insert_a_environment", environment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
