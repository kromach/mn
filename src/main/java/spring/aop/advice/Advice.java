package spring.aop.advice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Advice {
	public void before(JoinPoint j) {
		System.out.println("before=");
		System.out.println("target="+j.getTarget());
		RequestAttributes rs = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes)rs;
		HttpServletRequest request  = sa.getRequest();
		HttpSession session = request.getSession();
		//session.getAttribute("memId");
	}
	public void after() {
		System.out.println("after=");
	}
	public void afterReturning() {
		System.out.println("afterReturning=");
	}
	public void afterThrowing(Throwable e) {
		System.out.println("afterThrowing=");
	}
//	around는 1번째 매개변수 JoinPoint로 지정해야하고 
//	returntype을 joinPoint.proceed로 리턴해저야 원하는 
//	페이지까지 처리가 잘된다.
//  ##around를 많이쓴다
	public Object around(ProceedingJoinPoint j) throws Throwable {
		System.out.println("around Before=");
		Object obj = j.proceed();
		System.out.println("around After=");
		return obj;
	}
}
