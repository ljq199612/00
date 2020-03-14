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
import com.ljq.model.Charge;
import com.ljq.model.Household;
import com.ljq.service.AdminService;
import com.ljq.service.ChargeService;

@Controller
@RequestMapping(value="/charge")
public class ChargeControl {
	@Resource(name = "chargeService")
	private ChargeService chargeService;
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		List<Charge> chargeList = chargeService.obtainChargeList(new Charge());
		return new ModelAndView("admin/charge/manage")
						.addObject("chargeList", chargeList);
	}
	
	@ResponseBody 
	@RequestMapping(value = "/delete.html")
	public Object deleteHousehold(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("checkedId");
		try { 
			chargeService.deleteCharge(id);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}

	@ResponseBody 
	@RequestMapping(value = "/update.html")
	public Object update(HttpServletRequest request) throws Exception {
		try { 
			String id = request.getParameter("checkedId");
			String cost = request.getParameter("cost");
			String cause = request.getParameter("cause");
			String date = request.getParameter("date");
			Charge charge = new Charge();
			charge.setId(id);
			charge.setCost(cost);
			charge.setCause(cause);
			charge.setDate(date);
			chargeService.updateCharge(charge);
			return "success"; 
		} catch (Exception e) { 
			return "failed"; 
		}
	}
}
