package project.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {
	private static final Logger logger = LoggerFactory.getLogger(MemberAspect.class);
	@Around("execution(* project.spring.test.controller.*Controller.*(..))")
	public Object around(ProceedingJoinPoint j)
			throws Throwable {

		logger.info("beforeMethod()==");
		System.out.println(
				"memberAspect.beforeMethod");
		Object result = j.proceed();
		logger.info("afterMethod()==");
		System.out.println(
				"memberAspect.afterMethod");
		return result;
	}
}
