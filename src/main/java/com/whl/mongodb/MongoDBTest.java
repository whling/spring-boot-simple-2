package com.whl.mongodb;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.whl.App;
import com.whl.demo.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MongoDBTest {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void save(){
//		User user=new User();
//		user.setId(2);
//		user.setName("maolin");
		DBCollection dbCollection = mongoTemplate.createCollection("c2");
		DBObject documents=new BasicDBObject();
		documents.put("id", 2);
		documents.put("name", "whling");
		dbCollection.insert(documents);
	}
	@Test
	public void findAll(){
		List<User> list = mongoTemplate.findAll(User.class);
		for (User user : list) {
			System.out.println(user.getName());
		}
	}
	@Test
	public void test(){
		User user=new User();
		user.setId(3);
		user.setName("kobe");
		DBObject document = PojoToMap.getDocument(user);
		DBCollection collection = mongoTemplate.getCollection("c1");
		collection.insert(document);
	}
}
