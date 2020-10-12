package project.spring.article.controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.Editor_imageVO;
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
		public String indexTestSs(){
			return "article/writeForm.mn";
		}	
		
		@RequestMapping("/drinkSearch")
		@ResponseBody
		public List drinkSearch(
				@RequestParam(value = "input", required = false) String input) {
			System.out.println("drink Ajax search Input : "+input);
			List list = null;
			if(input !=null && !input.equals("")) {
				list = articleService.getDrinkSearch(input);
			}
			return list;
		}
		
		//insertTag
		@RequestMapping("/writePro")
		public String writePro(ArticleDTO dto)  throws IOException, FileUploadException {
			//기본값을 FreeBoard
			if(dto.getDkCode()!=null&& dto.getDkCode().equals("선택")) {
				dto.setDkCode(null);
			}
			dto.setKind("F");
			System.out.println(dto);
			//insert
			int result = articleService.insertItem(dto);
			//imgInsert
			//insertTags
			return "article/articleList.mn";
		}
		
		
		@RequestMapping("/articleSearch")
		public String articleSearch(
				@RequestParam(required = false, name = "selectOption")String selectOption,
				@RequestParam(required = false, name = "search")String search,
				HttpServletRequest request
				) {
				List<ArticleDTO> list = null;
			if(!search.equals("")) {
				System.out.println("articleSearchService");
				list = articleService.searchArticle(selectOption,search);
			}
			request.setAttribute("list", list);
			return "article/articleList.mn";
		}
	}	
