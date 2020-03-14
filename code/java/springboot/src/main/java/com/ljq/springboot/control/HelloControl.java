package com.ljq.springboot.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.springboot.model.Admin;
import com.ljq.springboot.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloControl {
    @RequestMapping(value = {"/login.html"})
    public String login(Model model, HttpServletRequest request)
    {
        return "/login";
    }

    @RequestMapping(value = {"/_logout"})
    public String logout(Model model, HttpServletRequest request)
    {
        request.getSession().setAttribute("user", "");
        return "redirect:login.html";
    }

    @RequestMapping("/_check")
    public String check(Model model, HttpServletRequest request) {
        String user = request.getParameter("user");
        request.getSession().setAttribute("user", user);
        if(user.trim()==""){
            return "redirect:login.html";
        }
        return "redirect:/";
    }

    @RequestMapping({"/index.html", "/"})
    public String index(Model model, HttpServletRequest request) {
        String user = request.getParameter("user");
        return "index";
    }
}
