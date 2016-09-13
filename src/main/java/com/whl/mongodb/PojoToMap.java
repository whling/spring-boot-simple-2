package com.whl.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.whl.demo.bean.User;

public class PojoToMap {
	private static final Logger logger = LoggerFactory.getLogger(PojoToMap.class);

	/**
	 * @将POJO对象转成Map
	 */

	public static Map getMap(Object obj) {
		Map hashMap = new HashMap();
		try {
			Class c = obj.getClass();
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					// System.out.println("方法名："+m[i].getName());
					// System.out.println("值："+ m[i].invoke(obj, new
					// Object[0]));
					hashMap.put(m[i].getName(), m[i].invoke(obj, new Object[0]));
				}
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
		return hashMap;
	}

	public static DBObject getDocument(Object obj) {
		DBObject dbObject = new BasicDBObject();
		try {
			Class c = obj.getClass();
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					System.out.println("方法名：" + m[i].getName());
					System.out.println("值：" + m[i].invoke(obj, new Object[0]));
					dbObject.put(m[i].getName(), m[i].invoke(obj, new Object[0]));
				}
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
		return dbObject;
	}

	public static void main(String[] args) {
		User user = new User();
		user.setId(3);
		user.setName("kobe");
		DBObject document = getDocument(user);

	}

}
