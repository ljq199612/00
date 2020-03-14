package com.ljq.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Charge;

	
@Repository("chargeDao")
public class ChargeDaoImpl implements ChargeDao{

	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
		
	public List<Charge> obtainChargeList(Charge charge) throws SQLException {
		List<Charge> chargeList = null;
		try {
			chargeList = sqlMapClient.queryForList("SQLMAP_CHARGE.select_charge", charge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeList;
	}
	
	public Charge selectCharge(String id) throws SQLException{
		Charge charge = null;
		try {
			charge = (Charge)sqlMapClient.queryForObject("SQLMAP_CHARGE.select_a_charge", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	}
	
	public boolean deleteCharge(String id) throws SQLException {
		try {
			sqlMapClient.delete("SQLMAP_CHARGE.delete_a_charge", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateCharge(Charge charge) throws SQLException{
		try {
			sqlMapClient.update("SQLMAP_CHARGE.update_a_charge", charge);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void insertCharge(Charge charge) throws SQLException{
		
	}
	
}
