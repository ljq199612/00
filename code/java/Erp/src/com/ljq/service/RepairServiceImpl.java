package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.RepairDao;
import com.ljq.dao.HouseholdDao;
import com.ljq.model.Repair;
import com.ljq.model.Household;

@Service("repairService")
public class RepairServiceImpl implements RepairService {
	@Resource(name = "repairDao")
	private RepairDao repairDao;
	
	@Resource(name = "householdDao")
	private HouseholdDao householdDao;
	
	public List<Repair> obtainRepairList(Repair repair) throws SQLException{
		List<Repair> repairList = repairDao.obtainRepairList(repair);
		for(Repair _repair: repairList) {
			Household household = householdDao.selectHousehold(_repair.getHousehold_id());
			_repair.setHousehold_name(household.getName());
		}
		return repairList;
	}
	
	public Repair selectRepair(String id) throws SQLException{
		return repairDao.selectRepair(id);
	}
	
	public boolean updateRepair(Repair repair) throws SQLException{
		return repairDao.updateRepair(repair);
	}
	
	public boolean deleteRepair(String id) throws SQLException{
		return repairDao.deleteRepair(id);
	}
	public void insertRepair(Repair repair) throws SQLException{
		repairDao.insertRepair(repair);
	}

}
