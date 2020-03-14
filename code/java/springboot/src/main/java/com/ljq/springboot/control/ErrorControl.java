package com.ljq.springboot.control;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/error")
class MainErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    /**
     * 页面错误异常处理
     */
    @RequestMapping(produces="text/html")
    public String errorPageHandler(HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case 403:
                return ERROR_PATH + "/403";
            case 400:
                return ERROR_PATH + "/400";
            case 404:
                return ERROR_PATH + "/404";
            case 500:
                return ERROR_PATH + "/500";
            default:
                return ERROR_PATH + "/404";
        }
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}