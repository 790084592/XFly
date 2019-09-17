package com.xush.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * XFly启动类
   *   继承SpringBootServletInitializer，用于war包Tomcat部署
 * @author xush
 * @since  2019年7月2日
 */
@SpringBootApplication
public class XFlyApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(XFlyApplication.class, args);
	}

	@Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(XFlyApplication.class);
  }
}
