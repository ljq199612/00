package com.ljq.control;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ljq.model.Admin;
import com.ljq.model.Household;
import com.ljq.service.HouseholdService;
import com.ljq.tools.Uuid;

@Controller
@RequestMapping(value="/household")
public class HouseholdControl {
	@Resource(name = "householdService")
	private HouseholdService householdService;
	
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Household household = new Household();
		List<Household> householdList = householdService.obtainHouseholdList(household);
		return new ModelAndView("admin/household/manage")
						.addObject("householdList", householdList)
						.addObject("household", household);
	}
	
	//post提交
	@RequestMapping(value = "/toManage.html", method = RequestMethod.POST)
	public ModelAndView toManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(new RedirectView(request.getContextPath()+"/household/manage.html"));

	}
	
	
	@RequestMapping(value = "/select.html", method = RequestMethod.POST)
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Household household = new Household();
		household.setName(request.getParameter("name"));
		List<Household> householdList = householdService.obtainHouseholdList(household);
		return new ModelAndView("admin/household/manage")
						.addObject("householdList", householdList)
						.addObject("household", household);
	}
	
	
	
	@RequestMapping(value = "/insert.html", method = RequestMethod.POST)
	public ModelAndView insertHousehold(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Household household = new Household();
		household.setId(Uuid.getUUID());
		household.setName(request.getParameter("name"));
		household.setSex(request.getParameter("sex"));
		household.setAge(request.getParameter("age"));
		household.setTelephone(request.getParameter("telephone"));
		household.setAddress(request.getParameter("address"));
		household.setRegdate(request.getParameter("regdate"));
		household.setRemarks(request.getParameter("remarks"));
		household.setPassword(request.getParameter("password"));
		householdService.insertHousehold(household);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/household/manage.html"));
	}

	@ResponseBody 
	@RequestMapping(value = "/delete.html")
	public Object deleteHousehold(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("checkedId");
		try { 
			householdService.deleteHousehold(id);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
	
	@ResponseBody 
	@RequestMapping(value = "/toUpdate.html")
	public Object toUpdate(HttpServletRequest request, HttpSession session) throws Exception {
		try { 
			String id = request.getParameter("checkedId");
			Household household = householdService.selectHousehold(id);
			session.setAttribute("household", household);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
	
	
	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public ModelAndView updateHousehold(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Household household = new Household();
		household.setId(request.getParameter("id"));
		household.setName(request.getParameter("name"));
		household.setSex(request.getParameter("sex"));
		household.setAge(request.getParameter("age"));
		household.setTelephone(request.getParameter("telephone"));
		household.setAddress(request.getParameter("address"));
		household.setRegdate(request.getParameter("regdate"));
		household.setRemarks(request.getParameter("remarks"));
		household.setPassword(request.getParameter("password"));
		householdService.updateHousehold(household);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/household/manage.html"));
	}

	
}

