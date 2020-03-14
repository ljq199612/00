package com.ljq.springboot.service;

import com.ljq.springboot.model.Admin;

import java.util.List;

public interface AdminService {

    public List<Admin> selectAll();
    public Boolean insert(Admin admin);
    public Boolean update(Admin admin);
}
