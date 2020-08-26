package project.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {

//	@Pointcut("execution(* test*(..))")
//	private void testPC() {}
//	
//	@Around("testPC()")
//	public Object around(ProceedingJoinPoint j) throws Throwable {
//		
//		
//		return j.proceed();
//	}
	@Around("execution(* test*(..))")
	public Object around(ProceedingJoinPoint j)
			throws Throwable {
		// 타겟메서드로 넘어가는 매개변수 꺼내줌 꺼낸후 검사해서 경로 return/viewResolver경로 우회
		Object[] obj = j.getArgs();
		for (Object o : obj) {
			if (o instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) o;
				HttpSession session = request
						.getSession();
				String memId = (String) session
						.getAttribute("memId");
				if (memId == null) {
					System.out.println(
							"memberAspect.로그인 필요=");
					//return "aopMember/loginForm";
				}
			}
		}

		ServletRequestAttributes rs = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = rs.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("memId")==null) {
			System.out.println("memberAspect.로그인 필요=2");
			return "aopMember/loginForm";
		}
		// 로그인 체크 : 로그인된 상태로 접근해야되는 페이지
		Object result = j.proceed();
		return result;
	}
}
