package com.whl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.whl.config.datasource.dynamic.DynamicDataSourceRegister;

@SpringBootApplication
@Import({ DynamicDataSourceRegister.class })
/* 启动异步任务,使@Async生效 */
@EnableAsync
@MapperScan(value = "com.whl.mybatis.mapper")
@EnableScheduling // 启用任务调度.
public class App extends WebMvcConfigurationSupport {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 1、 extends WebMvcConfigurationSupport 2、重写下面方法configurePathMatch;
	 * setUseSuffixPatternMatch: 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
	 * setUseTrailingSlashMatch :设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
	 */

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
		super.configurePathMatch(configurer);

	}

}
