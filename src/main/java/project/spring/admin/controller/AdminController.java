package project.spring.admin.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.digester.SetRootRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/admin")
public class AdminController {

		@Autowired
		private AdminServiceImpl adminService = null;

		@RequestMapping("/memberList")
		public String memberListAd(String pageNum, Model model)throws SQLException {
			
			
				if(pageNum == null) {
					pageNum = "1";
				}
				int pageSize = 10;
				int currPage = Integer.parseInt(pageNum);
				int startRow = (currPage - 1) * pageSize + 1;
				int endRow = currPage * pageSize;
				int number = 0;
				
				List <AdminVO>memberList = null;
				int count = adminService.memberCount();
				
				if(count > 0) {
					memberList = adminService.memberLIst(startRow, endRow);
					System.out.println("가져오기 확인!!");
					String newReportArr  = "";
					int x = -1;
					for(int i = 0; i < memberList.size(); i++) {
						if(memberList.get(i).getReportNumber() != null && memberList.get(i).getReportNumber().length() > 0) {
							x = 0;
							String rep = memberList.get(i).getReportNumber();
							String [] report = rep.split(",");
							for(int j = 0; j < report.length; j++) {
								System.out.println(report[j]);
								int index = report[j].indexOf("F");
								String chReport = report[j].substring(0, index);
								System.out.println(chReport);
								newReportArr += chReport + ",";
								System.out.println("newre" + newReportArr);
								
							}
						}else {
							x = -1;
						}
						if(x != -1) {
							memberList.get(i).setReportNumber(newReportArr);
							System.out.println(memberList.get(i).getReportNumber());
							}
					}
					
				}
				number = count - (currPage - 1) * pageSize;
			
				Pager pager = new Pager();
				PageVO pageVO = pager.pager(pageNum,count);
				
				
				
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("memberList", memberList);
				model.addAttribute("count", count);
				model.addAttribute("pageVO", pageVO);
				
			return "admin/memberList.mn";
		}
	
		@RequestMapping("/drinkList")
		public String drinkListAd(String pageNum, Model model)throws SQLException{
			
			if(pageNum == null) {
				pageNum = "1";
			}
			
			List drinkList = null;
			
			Pager pager = new Pager();
			
			PageVO pageVo = null;
			
			int number = 0;
			
			int count = 0;
			
			count = adminService.drinkCount();
			
			if(count > 0) {
				pageVo = pager.pager(pageNum, count);
				drinkList = adminService.drinkList(pageVo.getStartRow(), pageVo.getEndRow());
				number = count-(pageVo.getCurrPage()-1)*pageVo.getPageSize();
			}
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("drinkList", drinkList);
			model.addAttribute("count", count);
			model.addAttribute("pageVO", pageVo);
			// 주류 글 가져오기
			
			
			
			return "admin/drinkList.mn";
		}
		
		@RequestMapping("exitUser")
		@ResponseBody
		public void exitUserAd(@RequestParam String memberId)throws SQLException{
			System.out.println("유저 강퇴하기");
			adminService.deleteItem(memberId);
		}
	
		@RequestMapping("checkApprove")
		@ResponseBody
		public int checkApproveAd(@RequestParam String dkCode, @RequestParam String check)throws SQLException {
			
			System.out.println("게시글 상태 변경");
			System.out.println("dkCode : " + dkCode);
			System.out.println("check : " +check);
			
			int i = 0;
			
			//게시글 승인
			if(check.equals("1")) {
				adminService.approveDrink(dkCode, check);
				i = 1;
			}else {
				i = 0;
			}
			
			System.out.println("i 체크하기" + i);
			return i;
		}
		
	
}
