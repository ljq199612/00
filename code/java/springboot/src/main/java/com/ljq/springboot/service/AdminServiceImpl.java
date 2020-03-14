package com.ljq.springboot.service;

import com.ljq.springboot.mapper.AdminMapper;
import com.ljq.springboot.model.Admin;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<Admin> selectAll(){
        return adminMapper.selectAll();
    }

    @Override
    @Transactional  // 如果该方法抛出异常事务就会回滚
    public Boolean insert(Admin admin) {
        Boolean ret = adminMapper.insert(admin);
        // int a = 1/0; // 模拟异常
        return ret;
    }

    @Override
    public Boolean update(Admin admin) {
        return null;
    }


}
