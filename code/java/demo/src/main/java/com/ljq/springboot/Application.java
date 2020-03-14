package com.ljq.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {
		"com.ljq.springboot.servlet",
		"com.ljq.springboot.filter"
})

@ComponentScan(basePackages = {
		"com.ljq.springboot.mapper",
		"com.ljq.springboot.service"
})

@MapperScan(basePackages = {"com.ljq.springboot.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*---------------------------------------------------------------------------------
  					              Note
-----------------------------------------------------------------------------------
 @SpringBootApplication
	=  @ComponentScan + @SpringBootConfiguration + @EnableAutoConfiguration。

1) @SpringBootConfiguration继承自@Configuration，二者功能也一致，标注当前类是配置类，
   并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中，并且实例名就是方法名。

2) @EnableAutoConfiguration的作用启动自动的配置。
   @EnableAutoConfiguration注解的意思就是Springboot根据你添加的jar包来配置你项目的默认配置，
   比如根据spring-boot-starter-web，来判断你的项目是否需要添加了webmvc和tomcat，
   就会自动的帮你配置web项目中所需要的默认配置。

3) @ComponentScan，扫描当前包及其子包下被 @Component，@Controller，@Service，@Repository注解标记的类
   并纳入到spring容器中进行管理。是以前的<context:component-scan>

-----------------------------------------------------------------------------------
 */