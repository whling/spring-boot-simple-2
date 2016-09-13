package com.whl.aspect;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
//@Order(i)  通过i设置切面的优先级，从小到大
//在切入点前的操作，按order的值由小到大执行
//在切入点后的操作，按order的值由大到小执行，在实际中order值可以设置为负值，确保是第一个进行执行的。
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	/* 一次访问的时间记录，注意为了避免线程安全问题，使用ThreadLocal对象  */
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	/**
	 * 定义一个切入点. 解释下：
	 *
	 * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 任意包名 ~ 第三个 * 代表任意方法. ~ 第四个 * 定义在web包或者子包
	 * ~第五个 * 任意方法 ~ .. 匹配任意数量的参数.
	 */
	@Pointcut("execution(public * com.whl.*.service.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		logger.info("WebLogAspect.doBefore()");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		// 获取所有参数方法一：
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			System.out.println(paraName + ": " + request.getParameter(paraName));
		}
	}

	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("WebLogAspect.doAfterReturning()");

		logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
	}
}
