package com.ljq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient // [1]
@RestController
public class SpringCloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientApplication.class, args);
	}


	@GetMapping("/")
	public String index() {
		return "spring-cloud-eureka-client !";
	}
}
/*
[1] 注意这里也可使用 @EnableEurekaClient, 但由于springcloud是灵活的，
注册中心支持eureka、consul、zookeeper等。 若写了具体的注册中心注解，则
当替换成其他注册中心时，又需要替换成对应的注解了。 所以 直接使用 @EnableDiscoveryClient
启动发现。这样在替换注册中心时，只需要替换相关依赖即可。

*/
