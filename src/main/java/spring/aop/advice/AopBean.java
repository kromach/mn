package spring.aop.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aop/")
public class AopBean {

//	@Autowired
//	private Tv tv = null;
	
	@RequestMapping("main.git")
	public String main() throws Throwable{
		System.out.println("AOPmain=");
		
		return "aop/testAOP";
	}
	@RequestMapping("main2.git")
	public String main2(String id) {
		System.out.println("AOPmain2="+id);
		
		return "aop/testAOP";
	}
	@RequestMapping("main3.git")
	public String main3() {
		System.out.println("AOPmai3=");
		main100();
		return "aop/testAOP";
	}
	public String main100() {
		System.out.println("main100=");
		
		return "aop/testAOP";
	}
}
