package com.ljq.springboot.interceptor;

import com.ljq.springboot.tools.CustomLogger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //进入controller层之前拦截请求   // [1]
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomLogger.startMethodLog();
        CustomLogger.log.info("\n>>>在请求处理之前进行调用（Controller方法调用之前）");

        Object user = request.getSession().getAttribute("user");
        if(user == null || user == ""){
            request.setAttribute("error","当前处于未登录状态!");
            response.sendRedirect("/login.html");
            return false;
        }
           return true;
    }

    //访问controller之后 访问视图之前被调用   // [2]
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        CustomLogger.startMethodLog();
        CustomLogger.log.info("\n>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    //访问视图之后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CustomLogger.startMethodLog();
        CustomLogger.log.info("\n>>> 视图渲染后的操作 ---------");

    }

}



/*------------------------------------------------------------------------------
  					              Note
--------------------------------------------------------------------------------
[1] preHandle 方法是进行处理器拦截用的，顾名思义，该方法将在 Controller 处理之前进行调用，
SpringMVC 中的 Interceptor 拦截器是链式的，可以同时存在多个 Interceptor，然后 SpringMVC
会根据声明的前后顺序一个接一个的执行，而且所有的 Interceptor 中的 preHandle 方法都会在
Controller 方法调用之前调用。 SpringMVC 的这种 Interceptor 链式结构也是可以进行中断的，
这种中断方式是令 preHandle 的返回值为false，当 preHandle 的返回值为false的时候整个请求就
结束了。

[2] postHandle 方法只会在当前这个 Interceptor 的 preHandle 方法返回值为 true 的时候才会执行。
postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后，也就是在 Controller
的方法调用之后执行，但是它会在 DispatcherServlet 进行视图的渲染之前执行，也就是说在这个
方法中你可以对 ModelAndView 进行操作。这个方法的链式结构跟正常访问的方向是相反的，也就是说
先声明的 Interceptor 拦截器该方法反而会后调用，这跟 Struts2 里面的拦截器的执行过程有点像，
只是 Struts2 里面的 intercept 方法中要手动的调用 ActionInvocation的invoke 方法，Struts2
中调用 ActionInvocation 的 invoke 方法就是调用下一个 Interceptor 或者是调用 action，
然后要在 Interceptor 之前调用的内容都写在调用 invoke 之前，要在 Interceptor 之后调用



 */


