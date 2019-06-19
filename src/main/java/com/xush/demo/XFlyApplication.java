package com.xush.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xush.demo.orm.demo")
public class XFlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(XFlyApplication.class, args);
	}

}
