package project.spring.admin.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

		@Autowired
		private AdminServiceImpl adminService = null;

		@RequestMapping("/memberList")
		public String memberList(String pageNum, Model model)throws SQLException {
			
			
				if(pageNum == null) {
					pageNum = "1";
				}
				int pageSize = 10;
				int currPage = Integer.parseInt(pageNum);
				int startRow = (currPage - 1) * pageSize + 1;
				int endRow = currPage * pageSize;
				int number = 0;
				
				List memberList = null;
				int count = adminService.memberCount();
				
				if(count > 0) {
					memberList = adminService.memberLIst(startRow, endRow);
				}
				number = count - (currPage - 1) * pageSize;
			
			
			return "/admin/memberList.mn";
		}
	
	
	
	
}
