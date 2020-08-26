package project.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {
	@Before("execution(* project.spring.test.controller.*Controller.*(..))")
	public void before() {
		System.out.println("before:");
	}
	
//	@After("execution(* project.spring.test.controller.*Controller.*(..))")
//	public void after() {
//		System.out.println("after:");
//	}
	
//	@Around("execution(* project.spring.test.controller.*Controller.*(..))")
//	public Object around(ProceedingJoinPoint j)
//			throws Throwable {
//		return 404;
//		// Object result = j.proceed();
//	}
	
}
