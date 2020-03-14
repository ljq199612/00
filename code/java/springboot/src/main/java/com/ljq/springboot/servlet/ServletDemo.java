package com.ljq.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servletDemo", urlPatterns = "/servletDemo")
public class ServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
        respose.getWriter().println("Hello servlet !");
        respose.getWriter().flush();
        respose.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
        doPost(request,respose);
    }

}
