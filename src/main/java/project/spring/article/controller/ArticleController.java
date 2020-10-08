package project.spring.article.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
		@Autowired
		private ArticleServiceImpl articleService = null;
	
		@RequestMapping
		public String index(){
			return "article/articleList.mn";
		}
		
		@RequestMapping("/writeForm")
		public String indexTest(){
			return "article/writeForm.mn";
		}	
		
		@RequestMapping("/drinkSearch")
		@ResponseBody
		public List drinkSearch(
				@RequestParam(value = "input", required = false) String input) {
			
			System.out.println("drink Ajax");
			List list = null;
			if(input !=null) {
			list = articleService.getDrinkSearch(input);
			}
			return list;
		}
	}
