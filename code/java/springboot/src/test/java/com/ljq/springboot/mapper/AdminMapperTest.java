package com.ljq.springboot.mapper;

import com.ljq.springboot.model.Admin;
import com.ljq.springboot.tools.Uuid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

// Spring 4.3 起提供了一个等同于 SpringJUnit4ClassRunner 的类 SpringRunner，因此可以简写成：@RunWith(SpringRunner.class)。
// 该注解能够改变 Junit 并让其运行在 Spring 的测试环境，以得到 Spring 测试环境的上下文支持。
// 否则，在 Junit 测试中，Bean 的自动装配等注解将不起作用。
@RunWith(SpringRunner.class)   //[1]
@MybatisTest  //@DataJpaTest   // [2]
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)   // 不回滚
public class AdminMapperTest {

    @Resource
    public AdminMapper adminMapper;

    @Test
    //@Rollback(false)
    public void insert(){
        Boolean ret = false;
        for(int i=1; i<10; i++) {
            ret = adminMapper.insert(new Admin(Uuid.getUUID(), i+"", i+"", Date.valueOf("2019-03-09")));
        }
    }

    @Test
    public void selectAdminsLike(){
        Admin admin = new Admin();
        admin.setUserName("8");
        //admin.setRegistDate(Date.valueOf("2019-02-05"))
        List<Admin> selectedAdmins = adminMapper.selectAdminLike(admin);
        System.out.println("-----");
    }

    @Test
    public void selectAll(){
        List<Admin> adminList = adminMapper.selectAll();
        System.out.println("-----");
    }


}


/*------------------------------------------------------------------------------
  					              Note
--------------------------------------------------------------------------------
[2] @SpringBootTest 是把整个 spring 容器启动起来，然后把测试环境给你准备好，这样显得有点有点
笨重，单元测试不一定需要启动整个应用，层与层之间也可以并且应该隔离测试。如果上层测试需要用到下层的
依赖，就使用 mock 的方式构造一个依赖。比如
    [FIXME] 测试 DAO 层可以使用 @DataJpaTest 注解；
    [FIXME] 测试controller层可以使用 @WebMvcTest ；
    [FIXME] 测试 Service 层可以使用 @TestConfiguration
注意: Mybatis 提供专门的注解 @MybatisTest 来测试 Dao 层。

[2]



 */