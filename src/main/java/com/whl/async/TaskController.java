package com.whl.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	@Autowired
	private Task1 task1;
	@Autowired
	private Task2 task2;

	@RequestMapping("/task1")
	public String task1() throws Exception {
		task1.doTaskOne();
		task1.doTaskTwo();
		task1.doTaskThree();
		return "task1";
	}
	@RequestMapping("/task2")
	public String task2() throws Exception {
		task2.doTaskOne();
		task2.doTaskTwo();
		task2.doTaskThree();
		return "task2";
	}
}
