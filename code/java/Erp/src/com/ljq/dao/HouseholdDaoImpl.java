package com.ljq.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Household;

	
@Repository("householdDao")
public class HouseholdDaoImpl implements HouseholdDao{

	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
		
	public List<Household> obtainHouseholdList(Household household) throws SQLException {
		List<Household> householdList = null;
		try {
			householdList = sqlMapClient.queryForList("SQLMAP_HOUSEHOLD.select_household", household);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return householdList;
	}
	
	public Household selectHousehold(String id) throws SQLException{
		Household household = null;
		try {
			household = (Household)sqlMapClient.queryForObject("SQLMAP_HOUSEHOLD.select_a_household", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return household;
	}
	
	public boolean deleteHousehold(String id) throws SQLException {
		try {
			sqlMapClient.delete("SQLMAP_HOUSEHOLD.delete_a_household", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateHousehold(Household household) throws SQLException{
		try {
			sqlMapClient.update("SQLMAP_HOUSEHOLD.update_a_household", household);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertHousehold(Household household) throws SQLException{
		try {
			sqlMapClient.insert("SQLMAP_HOUSEHOLD.insert_a_household", household);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
