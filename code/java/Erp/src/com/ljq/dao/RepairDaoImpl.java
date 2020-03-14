package com.ljq.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ljq.model.Household;
import com.ljq.model.Repair;

@Repository("repairDao")
public class RepairDaoImpl implements RepairDao {
	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public List<Repair> obtainRepairList(Repair repair) throws SQLException {
		List<Repair> repairList = null;
		repair = new Repair();
		try {
			repairList = sqlMapClient.queryForList("SQLMAP_REPAIR.select_repair", repair);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repairList;
	}
	
	public Repair selectRepair(String id) throws SQLException{
		Repair repair = null;
		try {
			repair = (Repair)sqlMapClient.queryForObject("SQLMAP_REPAIR.select_a_repair", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repair;
	}
	
	
	//修改数据
	public boolean deleteRepair(String id) throws SQLException {
		try {
			sqlMapClient.delete("SQLMAP_REPAIR.delete_a_repair", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateRepair(Repair repair) throws SQLException{
		try {
			sqlMapClient.update("SQLMAP_REPAIR.update_a_repair", repair);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void insertRepair(Repair repair) {
			
	}
}
