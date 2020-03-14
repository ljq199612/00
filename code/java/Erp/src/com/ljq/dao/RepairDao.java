package com.ljq.dao;

import java.sql.SQLException;
import java.util.List;

import com.ljq.model.Repair;
import com.ljq.model.Repair;

public interface RepairDao {
	public List<Repair> obtainRepairList(Repair repair) throws SQLException;
	public Repair selectRepair(String id) throws SQLException;
	public boolean deleteRepair(String id) throws SQLException;
	public boolean updateRepair(Repair repair) throws SQLException;
	public void insertRepair(Repair repair) throws SQLException;
	

}

