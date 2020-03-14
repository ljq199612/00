package com.ljq.control;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljq.model.Admin;
import com.ljq.service.AdminService;

@Controller
//@RequestMapping(value="/")
public class AdminControl {
	@Resource(name = "adminService")
	private AdminService adminService;
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Admin admin = new Admin();
		admin.setUserName(request.getParameter("userName"));
		admin.setPassword(request.getParameter("password"));
		session.setAttribute("admin", admin);
		return new ModelAndView("admin/index");
	}

	@RequestMapping("/login.html") 
	@ResponseBody 
	public Object login(HttpServletRequest request, HttpSession session){ 
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		/*******************************************************/
		//System.out.println(userName + "  " + password);
		/*******************************************************/
		
		try { 
			if(adminService.selectAdmin(userName, password) == null){
				return "pwderror";  //不存在这个登录账号 
			}else{ 
				Admin admin = new Admin();
				admin.setUserName(userName);
				admin.setPassword(password);
				session.setAttribute("admin", admin);
				return "success"; 
			}
		} catch (Exception e) { 
			return "failed"; 
		} 
	}
	
	
	@RequestMapping("/changePassword.html") 
	@ResponseBody 
	public Object changePassword(HttpServletRequest request, HttpSession session){ 
		String userName = request.getParameter("userName");
		String newPassword = request.getParameter("newPassword");
		/*******************************************************/
		System.out.println(userName + "  " + newPassword);
		/*******************************************************/
		try { 
			if(adminService.updateAdmin(userName, newPassword)){
				Admin admin = new Admin();
				admin.setUserName(userName);
				admin.setPassword(newPassword);
				session.setAttribute("admin", admin);
				return "success"; 
			}else{ 
				return "failed"; 
			}
		} catch (Exception e) { 
			return "failed"; 
		} 
	}
	
	
	
	
	
}
