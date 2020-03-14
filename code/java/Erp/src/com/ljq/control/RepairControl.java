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
import com.ljq.model.Repair;
import com.ljq.model.Household;
import com.ljq.service.AdminService;
import com.ljq.service.RepairService;

@Controller
@RequestMapping(value="/repair")
public class RepairControl {
	@Resource(name = "repairService")
	private RepairService repairService;
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		List<Repair> repairList = repairService.obtainRepairList(new Repair());
		return new ModelAndView("admin/repair/manage")
						.addObject("repairList", repairList);
	}
	
	@ResponseBody 
	@RequestMapping(value = "/delete.html")
	public Object deleteHousehold(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("checkedId");
		try { 
			repairService.deleteRepair(id);
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
			//Complain complain = complainService.selectComplain(id);
			Repair repair = repairService.selectRepair(id);
			repair.setState("已受理");
			repairService.updateRepair(repair);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}

	
	
	
}
