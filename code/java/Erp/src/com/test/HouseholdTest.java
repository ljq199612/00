package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljq.dao.HouseholdDao;
import com.ljq.model.Household;

public class HouseholdTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	HouseholdDao householdDao = (HouseholdDao) applicationContext.getBean("householdDao");
	
	@Test
	public void daoTest() throws Exception{
		
		Household household = new Household();
		/********* START  查询数据  START **************/ 
		household.setName("刘");
		List<Household> householdList = householdDao.obtainHouseholdList(household);
		for(Household hold : householdList ) {
			System.out.println(hold);
		}
		
		/********* START  添加数据  START **************/ 
		household.setId("6");
		household.setName("里斯");
		household.setSex("男");
		household.setAge("15");
		household.setTelephone("1892903998");
		household.setAddress("一号楼");
		household.setPassword("123456");
		household.setRegdate("2018-11-02");
		household.setRemarks("无");
		householdDao.insertHousehold(household);
		
		/********* START  删除数据  START **************/ 
		householdDao.deleteHousehold("1");
		
		/********* START  修改数据  START **************/ 
		household.setId("2");
		householdDao.updateHousehold(household);
		
		
	}
}
