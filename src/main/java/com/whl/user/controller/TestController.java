package com.whl.user.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whl.demo.bean.User;
import com.whl.user.service.UserService;

@RestController
/* 如果没有这个注解，那么需要使用@Value注解进行注入 */
@ConfigurationProperties(prefix = "com.whl.blog")
public class TestController {
	@Autowired
	private UserService userService;
	/* @Max(value = 20)针对整形 */
	@NotNull
	private String name;
	@NotEmpty
	private String title;
	/**
	 * 使用${}进行引用
	 */
	private String active;

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/test1")
	public String test() {
		LOGGER.info("进入controller");
		List<User> list = userService.getList();
		if (list != null && list.size()>0) {
			for (User user : list) {
				System.out.println(user.getName());
			}
		}
		// for(User d : userService.getListByDs1()){
		// System.out.println(d);
		// }
		LOGGER.info("完成");
		LOGGER.info("debug测试");
		System.out.println(active);
		return "ok";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
