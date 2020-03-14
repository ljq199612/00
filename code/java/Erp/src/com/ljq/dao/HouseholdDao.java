package com.ljq.dao;

import java.sql.SQLException;
import java.util.List;

import com.ljq.model.Household;

public interface HouseholdDao {
	public List<Household> obtainHouseholdList(Household household) throws SQLException;	
	public Household selectHousehold(String id) throws SQLException;
	public boolean deleteHousehold(String id) throws SQLException;
	public boolean updateHousehold(Household household) throws SQLException;
	public boolean insertHousehold(Household household) throws SQLException;
}
