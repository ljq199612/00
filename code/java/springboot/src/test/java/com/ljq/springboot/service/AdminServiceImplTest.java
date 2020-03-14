package com.ljq.springboot.service;

import com.ljq.springboot.tools.CustomLogger;
import com.ljq.springboot.model.Admin;
import com.ljq.springboot.tools.Uuid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest  // @TestConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AdminServiceImplTest {

    @Resource
    public AdminService adminService;

    @Test
    @Rollback(false)
    public void selectAll(){
        CustomLogger.startMethodLog();   // 自定义的 log 类
        try {
            List<Admin> admins = adminService.selectAll();
        }catch (Exception e){
            CustomLogger.methodLogInCatch();
        }
        CustomLogger.endMethodLog();
    }

    @Test
    @Rollback(false)
    public void insert(){
        adminService.insert(
            new Admin( Uuid.getUUID(), "10", "10", Date.valueOf("2019-03-09") )
        );
    }



}
