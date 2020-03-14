package com.ljq.springboot.config;

import com.ljq.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   // 等价于一个spring的xml文件 比如applicationContext.xml
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截器要拦截的访问路径，
        String [] pathPatterns = {
                "/**"
        };
        // 拦截器不拦截的路径, NOTICE:是页面访问路径 URL, 不是文件所在路径
        String [] excluedPathPatterns = {
                "/*",
                "/static/**"
        };

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(pathPatterns)
                .excludePathPatterns(excluedPathPatterns);
        //如果项目中有多个拦截器,把上面代码在复制一行,修改参数即可

    }

}
