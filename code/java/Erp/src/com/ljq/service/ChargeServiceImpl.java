package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.ChargeDao;
import com.ljq.dao.HouseholdDao;
import com.ljq.model.Charge;
import com.ljq.model.Household;

@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {
	@Resource(name = "chargeDao")
	private ChargeDao chargeDao;
	
	@Resource(name = "householdDao")
	private HouseholdDao householdDao;
	
	public List<Charge> obtainChargeList(Charge charge) throws SQLException{
		List<Charge> chargeList = chargeDao.obtainChargeList(charge);
		for(Charge comp: chargeList) {
			Household household = householdDao.selectHousehold(comp.getHousehold_id());
			comp.setHousehold_name(household.getName());
		}
		return chargeList;
	}
	
	public Charge selectCharge(String id) throws SQLException{
		return chargeDao.selectCharge(id);
	}
	
	public boolean deleteCharge(String id) throws SQLException{
		return chargeDao.deleteCharge(id);
	}
	
	public boolean updateCharge(Charge charge) throws SQLException{
		return chargeDao.updateCharge(charge);
	}
	public void insertCharge(Charge charge) throws SQLException{
		chargeDao.insertCharge(charge);
	}

}
