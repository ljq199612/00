package com.ljq.springboot.config;

import com.ljq.springboot.interceptor.LoginInterceptor;
import com.ljq.springboot.servlet.ServletDem2;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class servletConfig {

    @Bean
    public ServletRegistrationBean heServletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ServletDem2(),"/1/servletDemo2");
        return registrationBean;
    }
}
