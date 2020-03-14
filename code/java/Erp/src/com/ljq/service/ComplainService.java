package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import com.ljq.model.Complain;

public interface ComplainService {
	public List<Complain> obtainComplainList(Complain complain) throws SQLException;
	public Complain selectComplain(String id) throws SQLException;
	public boolean deleteComplain(String id) throws SQLException;
	public boolean updateComplain(Complain complain) throws SQLException;
	public void insertComplain(Complain complain) throws SQLException;
}


