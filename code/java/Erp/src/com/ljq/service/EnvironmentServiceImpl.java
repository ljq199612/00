package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.EnvironmentDao;
import com.ljq.model.Environment;

@Service("environmentService")
public class EnvironmentServiceImpl implements EnvironmentService {
	@Resource(name = "environmentDao")
	private EnvironmentDao environmentDao;
	
	public List<Environment> obtainEnvironmentList(Environment environment) throws SQLException{
		return environmentDao.obtainEnvironmentList(environment);
	}
	
	public Environment selectEnvironment(String id) throws SQLException{
		return environmentDao.selectEnvironment(id);
	}
	
	public boolean deleteEnvironment(String id) throws SQLException{
		return environmentDao.deleteEnvironment(id);
	}
	
	public boolean updateEnvironment(Environment environment) throws SQLException{
		return environmentDao.updateEnvironment(environment);
	}
	
	public boolean insertEnvironment(Environment environment) throws SQLException{
		return environmentDao.insertEnvironment(environment);
	}
}
