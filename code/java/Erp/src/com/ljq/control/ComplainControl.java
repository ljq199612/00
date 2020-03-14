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

import com.ljq.model.Admin;
import com.ljq.model.Complain;
import com.ljq.model.Household;
import com.ljq.service.AdminService;
import com.ljq.service.ComplainService;

@Controller
@RequestMapping(value="/complain")
public class ComplainControl {
	@Resource(name = "complainService")
	private ComplainService complainService;
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		List<Complain> complainList = complainService.obtainComplainList(new Complain());
		return new ModelAndView("admin/complain/manage")
						.addObject("complainList", complainList);
	}
	

	@ResponseBody 
	@RequestMapping(value = "/delete.html")
	public Object deleteHousehold(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("checkedId");
		try { 
			complainService.deleteComplain(id);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
	
	@ResponseBody 
	@RequestMapping(value = "/toUpdate.html")
	public Object toUpdate(HttpServletRequest request) throws Exception {
		try { 
			String id = request.getParameter("checkedId");
			Complain complain = complainService.selectComplain(id);
			complain.setState("已受理");
			complainService.updateComplain(complain);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
	


	
}
