package com.ljq.control;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ljq.model.Environment;
import com.ljq.service.EnvironmentService;

@Controller
@RequestMapping(value="/environment")
public class EnvironmentControl {
	@Resource(name = "environmentService")
	private EnvironmentService environmentService;
	
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Environment environment = new Environment();
		List<Environment> environmentList = environmentService.obtainEnvironmentList(environment);
		return new ModelAndView("admin/environment/manage")
						.addObject("environmentList", environmentList);
	}
	
	//post提交
	@RequestMapping(value = "/toManage.html", method = RequestMethod.POST)
	public ModelAndView toManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(new RedirectView(request.getContextPath()+"/environment/manage.html"));

	}
	
	/*
	@RequestMapping(value = "/insert.html", method = RequestMethod.POST)
	public ModelAndView insertEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Environment environment = new Environment();
		environment.setId(Uuid.getUUID());
		environment.setName(request.getParameter("name"));
		environment.setSex(request.getParameter("sex"));
		environment.setAge(request.getParameter("age"));
		environment.setTelephone(request.getParameter("telephone"));
		environment.setAddress(request.getParameter("address"));
		environment.setRegdate(request.getParameter("regdate"));
		environment.setRemarks(request.getParameter("remarks"));
		environment.setPassword(request.getParameter("password"));
		environmentService.insertEnvironment(environment);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/environment/manage.html"));
	}

	@ResponseBody 
	@RequestMapping(value = "/delete.html")
	public Object deleteEnvironment(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("checkedId");
		try { 
			environmentService.deleteEnvironment(id);
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
			Environment environment = environmentService.selectEnvironment(id);
			session.setAttribute("environment", environment);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
	
	
	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public ModelAndView updateEnvironment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Environment environment = new Environment();
		environment.setId(request.getParameter("id"));
		environment.setName(request.getParameter("name"));
		environment.setSex(request.getParameter("sex"));
		environment.setAge(request.getParameter("age"));
		environment.setTelephone(request.getParameter("telephone"));
		environment.setAddress(request.getParameter("address"));
		environment.setRegdate(request.getParameter("regdate"));
		environment.setRemarks(request.getParameter("remarks"));
		environment.setPassword(request.getParameter("password"));
		environmentService.updateEnvironment(environment);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/environment/manage.html"));
	}

*/	
}

