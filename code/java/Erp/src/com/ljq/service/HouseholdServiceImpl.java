package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.HouseholdDao;
import com.ljq.model.Household;

@Service("householdService")
public class HouseholdServiceImpl implements HouseholdService {
	@Resource(name = "householdDao")
	private HouseholdDao householdDao;
	
	public List<Household> obtainHouseholdList(Household household) throws SQLException{
		return householdDao.obtainHouseholdList(household);
	}
	
	public Household selectHousehold(String id) throws SQLException{
		return householdDao.selectHousehold(id);
	}
	
	public boolean deleteHousehold(String id) throws SQLException{
		return householdDao.deleteHousehold(id);
	}
	
	public boolean updateHousehold(Household household) throws SQLException{
		return householdDao.updateHousehold(household);
	}
	
	public boolean insertHousehold(Household household) throws SQLException{
		return householdDao.insertHousehold(household);
	}
}
