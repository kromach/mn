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
	
	@Pointcut("execution(* project.spring.test.controller.*Controller.*(..))")
    private void pointcutMethod(){ 
        
    }
	 
	@Around("pointcutMethod()") // a
	public Object measure(
			ProceedingJoinPoint joinpoint)
			throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + "시작"); // 메서드 실행
		try {
			//Object obj = joinpoint.proceed(); // 핵심 기능 실행
			return null;
		} finally {
			System.out.println(signatureStr + "끝");
		}
	}
}
