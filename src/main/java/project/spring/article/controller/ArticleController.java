package project.spring.article.controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
			return "forward:/article/articleSearch";
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
			return "redirect:/article";
		}
		
		//Search >> thumbNail뽑아서 list return
		@RequestMapping("/articleSearch")
		public String articleSearch(
				@RequestParam(required = false, name = "selectOption")String selectOption,
				@RequestParam(required = false, name = "search")String search,
				HttpServletRequest request
				) {
			List<ArticleDTO> list = null;
			if(search!=null&&!search.equals("")) {
				list = articleService.searchArticle(selectOption,search);
				String imgThum = "";
				//썸네일 뽑기
				Iterator<ArticleDTO> it = list.iterator();
				while(it.hasNext()) {
					ArticleDTO dto = it.next();
					String str = dto.getContent();
					String[] str_ = str.split("src=\"");
					for(int i=0;i<str_.length;i++) {
						System.out.println(str_[i]+"|"+str_[i].contains("src=\""));
						if(str_[i].contains("/resources")) {
							imgThum = str_[i].split("\"")[0];
							dto.setThumbNail(imgThum);
						}
					}
				}
			}else {
				//전부 돌려서 랜덤뽑기
				list = articleService.searchArticle();
				String imgThum = "";
				//썸네일 뽑기
				Iterator<ArticleDTO> it = list.iterator();
				while(it.hasNext()) {
					ArticleDTO dto = it.next();
					String str = dto.getContent();
					String[] str_ = str.split("src=\"");
					for(int i=0;i<str_.length;i++) {
						System.out.println(str_[i]+"|"+str_[i].contains("src=\""));
						if(str_[i].contains("/resources")) {
							imgThum = str_[i].split("\"")[0];
							dto.setThumbNail(imgThum);
						}
					}
				}
			}
			
			System.out.println(list);
			request.setAttribute("list", list);
			return "article/articleList.mn";
		}
		@RequestMapping(value = "/detail")
		public String detail(@RequestParam(name="idx",required = false) Integer idx,Model model) {
			int idx_ = 0;
			if(idx!=null) idx_ = idx;
			//조회수 올리는 메서드
			articleService.plusOneReadCount(idx_);
			//내용 읽어오는 메서드
			ArticleDTO dto = articleService.read(idx_);
			//밑에 게시글 뿌리는 메서드
			List list = articleService.searchArticleByAdd(0);
			System.out.println(list);
			model.addAttribute("articleDTO", dto);
			model.addAttribute("list", list);
			
			return "article/detail.mn";
		}
		
		//////AJAX
		@RequestMapping(value = "/more")
		@ResponseBody
		public List more(@RequestParam(name="num",required = false) Integer num) {
			//밑에 게시글 뿌리는 메서드
			List list = articleService.searchArticleByAdd(num);
			System.out.println(list);
			return list;
		}
		@RequestMapping(value = "/like")
		@ResponseBody
		public int likeSs(@RequestParam(name="num",required = false) Integer num,
						@RequestParam(name="nick",required = false) String memNickName,
						@RequestParam(name="insertId",required = false) String insertId
				) {
			
			//기본값 -1
			int result = -1;
			result = articleService.like(num,memNickName,insertId);
			//unlike
			return result;
		}
		@RequestMapping(value = "/unlike")
		@ResponseBody
		public int unlikeSs(@RequestParam(name="num",required = false) Integer num,
				@RequestParam(name="nick",required = false) String memNickName,
				@RequestParam(name="insertId",required = false) String insertId
				) {
			
			//기본값 -1
			int result = -1;
			result = articleService.unlike(num,memNickName,insertId);
			//unlike
			return result;
		}
		@RequestMapping(value = "/report")
		@ResponseBody
		public int reportSs(@RequestParam(name="num",required = false) Integer num,
				@RequestParam(name="insertId",required = false) String insertId,
				@RequestParam(name="reportId",required = false) String reportId
				) {
			int result = 0;
			System.out.println(num+"|"+insertId+"|"+reportId);
			result = articleService.report(num,insertId,reportId);
			return result;
		}
		@RequestMapping(value = "/reply")
		@ResponseBody
		public int replySs(@RequestParam(name="num",required = false) Integer num) {
			int result = 0;
			//result = articleService.searchArticleByAdd(num);
			return result;
		}
		@RequestMapping(value = "/move")
		@ResponseBody
		public int moveSs(@RequestParam(name="num",required = false) Integer num) {
			int result = 0;
			//result = articleService.searchArticleByAdd(num);
			return result;
		}
	}	
