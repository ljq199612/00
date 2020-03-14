package com.ljq.dao;

import java.sql.SQLException;
import java.util.List;

import com.ljq.model.Environment;

public interface EnvironmentDao {
	public List<Environment> obtainEnvironmentList(Environment environment) throws SQLException;	
	public Environment selectEnvironment(String id) throws SQLException;
	public boolean deleteEnvironment(String id) throws SQLException;
	public boolean updateEnvironment(Environment environment) throws SQLException;
	public boolean insertEnvironment(Environment environment) throws SQLException;
}
