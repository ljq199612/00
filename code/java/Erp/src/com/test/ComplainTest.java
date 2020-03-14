package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljq.dao.ComplainDao;
import com.ljq.dao.HouseholdDao;
import com.ljq.model.Complain;
import com.ljq.model.Household;

public class ComplainTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	ComplainDao complainDao = (ComplainDao) applicationContext.getBean("complainDao");
	
	@Test
	public void daoTest() throws Exception{
		Complain complain = new Complain();
		/********* START  查询数据  START **************/ 
		List<Complain> complainList = complainDao.obtainComplainList(complain);
		for(Complain com : complainList ) {
			System.out.println(com);
		}
	}
}
