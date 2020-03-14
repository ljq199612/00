package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljq.dao.AdminDao;

public class AdminTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	AdminDao admin=(AdminDao) applicationContext.getBean("adminDao");
	@Test
	public void daoTest() throws Exception{
		System.out.println("------");
		admin.updateAdmin("b", "b-b-b-b-b");
	}
}
