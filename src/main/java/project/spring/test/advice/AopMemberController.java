package project.spring.test.advice;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aopMember/")
public class AopMemberController {

	@RequestMapping("main")
	public String testmain()throws Exception {
		return "aopMember/main";
	}
}
