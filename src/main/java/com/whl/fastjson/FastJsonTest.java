package com.whl.fastjson;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.whl.App;
import com.whl.demo.bean.User;

/**
 * fastjson简介：
 * 
 * Fastjson是一个Java语言编写的高性能功能完善的JSON库。fastjson采用独创的算法，将parse的速度提升到极致，超过所有json库，
 * 包括曾经号称最快的jackson。并且还超越了google的二进制协议protocol
 * buf。Fastjson完全支持http://json.org的标准，也是官方网站收录的参考实现之一。支持各种JDK类型。包括基本类型、JavaBean、
 * Collection、Map、Enum、泛型等。支持JDK 5、JDK 6、Android、阿里云手机等环境。
 * 
 * 一. fastjson生成json字符串(JavaBean,List<JavaBean>,List<String>,List<Map
 * <String,Object>)
 * 
 * String jsonStrng = JSON.toJSONString(object);
 * 
 * 二. fastjson 解析json字符串为四种类型
 * 
 * 1. JavaBean
 * 
 * Person person = JSON.parseObject(jsonString, Person.class);
 * 
 * 2. List<JavaBean>
 * 
 * List<Person> listPerson =JSON.parseArray(jsonString, Person.class);
 * 
 * 3. List<String>
 * 
 * List<String> listString = JSON.parseArray(jsonString, String.class);
 * 
 * 4. List<Map<String,Object>>
 * 
 * List<Map<String, Object>> listMap = JSON.parseObject(jsonString, new
 * TypeReference<List<Map<String,Object>>>(){}); (注意：此处可看出fastjson反射机制比gson更准确，
 * id = 1001 通过fastjson反射仍是 id = 1001 , 而通过gson反射结果 为 id =1001.0 ,
 * 
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class FastJsonTest {

	@Test
	public void parseObj() {
		User user=new User();
		user.setId(1);
		user.setName("whling");
		String jsonString = JSON.toJSONString(user);
		System.out.println(jsonString);
	}
	@Test
	public void parseJson() {
		String jsonStr = "{id:1,name:\"whling\"}";
		User user = JSON.parseObject(jsonStr, User.class);
		System.out.println(user);
	}

	@Test
	public void parseJsonToList() {
		String jsonStr = "[{id:1,name:\"whling\"},{id:2,name:\"maolin\"}]";
		List<String> list = JSON.parseArray(jsonStr, String.class);
		System.out.println(list.get(1));
	}
}
