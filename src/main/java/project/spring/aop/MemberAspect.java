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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {

	@Pointcut("execution(* project.spring.*.controller.*Controller.*Ss(..))")
	// @Pointcut("within(project.spring.test.controller.HomeController)")
	private void pointcutMethod() {

	}

	@Around("pointcutMethod()") // a
	public Object measure(
			ProceedingJoinPoint joinpoint)
			throws Throwable {
		String signatureStr = joinpoint
				.getSignature().toShortString();
		System.out.println(signatureStr + "시작");
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request=attr.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("memId")==null) {
			System.out.println("memberAspect.로그인 필요=2");
			return "redirect:/member/login";
		}
		
		Object obj = joinpoint.proceed(); // 핵심 기능 실행
		System.out.println(signatureStr + "끝");
		return obj;
	}
}
