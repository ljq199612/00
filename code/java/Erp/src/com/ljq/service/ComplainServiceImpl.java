package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljq.dao.ComplainDao;
import com.ljq.dao.HouseholdDao;
import com.ljq.model.Complain;
import com.ljq.model.Household;

@Service("complainService")
public class ComplainServiceImpl implements ComplainService {
	@Resource(name = "complainDao")
	private ComplainDao complainDao;
	
	@Resource(name = "householdDao")
	private HouseholdDao householdDao;
	
	public List<Complain> obtainComplainList(Complain complain) throws SQLException{
		List<Complain> complainList = complainDao.obtainComplainList(complain);
		for(Complain comp: complainList) {
			Household household = householdDao.selectHousehold(comp.getHousehold_id());
			comp.setHousehold_name(household.getName());
		}
		return complainList;
	}
	
	public Complain selectComplain(String id) throws SQLException{
		return complainDao.selectComplain(id);
	}
	
	public boolean deleteComplain(String id) throws SQLException{
		return complainDao.deleteComplain(id);
	}
	
	public boolean updateComplain(Complain complain) throws SQLException{
		return complainDao.updateComplain(complain);
	}
	public void insertComplain(Complain complain) throws SQLException{
		complainDao.insertComplain(complain);
	}

}
