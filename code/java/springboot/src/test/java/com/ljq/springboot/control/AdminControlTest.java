package com.ljq.springboot.control;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControlTest {
    @Resource
    private MockMvc mockMvc;

    @Resource
    private MockHttpSession session;

    // 模拟登录,只有先登录才能测试，否则测试的内容会被拦截
    @Before
    public void login() throws Exception{
        session = (MockHttpSession) mockMvc
                    .perform(
                        post("/_check")
                            .param("user", "ljq"))
                    .andReturn().getRequest().getSession();
    }

    @Test
    public void courseListTest() throws Exception{
        mockMvc.perform(                //执行请求
                 get("/admin/1/3", 1).session(session))
           .andExpect(model().attributeExists("admin")) //验证存储模型数据
           .andExpect(view().name("admin/index")) //验证viewName
           .andExpect(status().isOk())//验证状态码
           .andDo(print()); //输出MvcResult到控制台
    }

}
