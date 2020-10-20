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
public class AdminAspect {

	// 어느 매서드인지 필요하고 어디 부분까지 사용하겠다고 명시  --> 뒤에 Ad가 붙어있는 컨트롤러 매서드까지
	@Pointcut("execution(* project.spring.*.controller.*Controller.*Ad(..))")
	private void adminPointCut() {
		
	}
	
	@Around("adminPointCut()")
	public Object adminAop(ProceedingJoinPoint joinpoint) throws Throwable {
		// 공통기능 적용 매서드가 어느것인지 확인할수 있도록 함
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + "aop 시작");
		//HttpservletRequest 사용 할 수 있도록 만드는 매서드
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("memId")==null) {
			System.out.println("memberAspect.로그인 필요=3333");
			return "redirect:/member/login";
		}else if(session.getAttribute("memId") != null && !session.getAttribute("memId").equals("admin")) {
			System.out.println("111111111111111111");
			return "redirect:/";
		}
		
		//before 종료 after 실행
		Object obj = joinpoint.proceed();
		System.out.println(signatureStr + "종료");
		return obj;
	}
}
