package com.ljq.springboot.control;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.springboot.model.Admin;
import com.ljq.springboot.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminControl {

    private static String baseLocation = "admin/";

    @Resource
    private AdminService adminServiceImpl;

    @RequestMapping(
            value= {"/{currentPage:[1-9][0-9]*}/{pageSize:[1-9][0-9]*}",
                    "/index.html/{currentPage}/{pageSize}"},
            method = RequestMethod.GET)
    public String show(Model model, HttpServletRequest request, @PathVariable("currentPage") int pageNum, @PathVariable("pageSize") int pageSize){
        // 分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admin = adminServiceImpl.selectAll();

        model.addAttribute("info", "springboot使用jsp");
        model.addAttribute("admin", admin);
        model.addAttribute("pageInfo", new PageInfo<>(admin));
        return baseLocation + "index";
    }


}
