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
import org.apache.tiles.velocity.template.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.ArticleReplyDTO;
import project.spring.article.vo.Editor_imageVO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
		@Autowired
		private ArticleServiceImpl articleService = null;
		/////////////////////POST////////////////////////
		@RequestMapping(value = "/post")
		public String post() {
			return "forward:/article/post/postSearch";
		}
		
		//Search >> thumbNail뽑아서 list return
				@RequestMapping("/post/postSearch")
				public String postSearch (
						@RequestParam(required = false, name = "selectOption")String selectOption,
						@RequestParam(required = false, name = "search")String search,
						HttpServletRequest request
						) throws Exception{
					
					List<ArticleDTO> list = null;
					if(search!=null&&!search.equals("")) {
						list = articleService.searchPost(selectOption,search);
						String imgThum = "";
						//썸네일 뽑기
						Iterator<ArticleDTO> it = list.iterator();
						while(it.hasNext()) {
							ArticleDTO dto = it.next();
							String str = dto.getContent();
							String[] str_ = str.split("src=\"");
							for(int i=0;i<str_.length;i++) {
								if(str_[i].contains("/resources")) {
									imgThum = str_[i].split("\"")[0];
									dto.setThumbNail(imgThum);
								}
							}
						}
					}else {
						//전부 돌려서 랜덤뽑기
						list = articleService.searchPost();
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
					return "article/post/postList.mn";
				}
		
		////////////////////////////////////////////////
		@RequestMapping
		public String index() throws Exception{
			return "forward:/article/articleSearch";
		}
		
		@RequestMapping("/writeForm")
		public String indexTestSs() throws Exception{
			return "article/writeForm.mn";
		}	
		
		@RequestMapping("/drinkSearch")
		@ResponseBody
		public List drinkSearch (
				@RequestParam(value = "input", required = false) String input) throws SQLException {
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
		@RequestMapping("/update")
		public String updateSs(@RequestParam(name = "bnIdx" , required = false) Integer bnIdx,Model model) {
			HttpServletRequest req = 
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String session = req.getSession().getAttribute("memId").toString();
			HashMap map = new HashMap();
			map.put("bnIdx",bnIdx);
			map.put("session",session);
			ArticleDTO dto = articleService.read(bnIdx);
			System.out.println(dto);
			model.addAttribute("article", dto);
			
			return "article/updateForm.mn";
		}
		
		@RequestMapping("/updatePro")
		public String updateProSs(ArticleDTO dto) throws SQLException {
			System.out.println(dto);
			
			///update작성0
			int result = articleService.updateItem(dto);
			
			return "redirect:/article";
		}
		
		
		@RequestMapping("/delete")
		public String deleteSs(@RequestParam(name = "bnIdx" , required = false) Integer bnIdx) throws SQLException{
			HttpServletRequest req = 
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String session = req.getSession().getAttribute("memId").toString();
			System.out.println(bnIdx+":"+session);
			HashMap map = new HashMap();
			map.put("bnIdx",bnIdx);
			map.put("session",session);
			
			int result = articleService.deleteItem(map);
			System.out.println("deleteArticle"+result);
			
			
			return "redirect:/article";
		}
		
		
		//Search >> thumbNail뽑아서 list return
		@RequestMapping("/articleSearch")
		public String articleSearch(
				@RequestParam(required = false, name = "selectOption")String selectOption,
				@RequestParam(required = false, name = "search")String search,
				HttpServletRequest request
				)throws SQLException {
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
		public String detail(
				@RequestParam(required = false) String pageNum,
				@RequestParam(name="idx",required = false) Integer idx,Model model) 
						throws SQLException{
			int idx_ = 0;
			if(idx!=null) idx_ = idx;
			//조회수 올리는 메서드
			articleService.plusOneReadCount(idx_);
			//내용 읽어오는 메서드
			ArticleDTO dto = articleService.read(idx_);
			//밑에 게시글 뿌리는 메서드
			List list = articleService.searchArticleByAdd(0);
			//내용
			model.addAttribute("articleDTO", dto);
			//밑에 추가게시글
			model.addAttribute("list", list);
			//댓글
			List<ArticleReplyDTO> reply = articleService.getReply(0,idx);
			
			System.out.println("내용============");
			System.out.println(dto);
			for(ArticleReplyDTO re : reply) {
				System.out.println(re);
			}
			
			model.addAttribute("reply", reply);
			
			//댓글 pager
			int count = articleService.getAllReplyCount(idx);
			Pager pager = new Pager();
			PageVO pageVO = pager.pager("1",count);
			model.addAttribute("count", count);
			model.addAttribute("pageVO", pageVO);
			
			return "article/detail.mn";
		}
		
		//////AJAX
		@RequestMapping(value = "/replyReload")
		@ResponseBody
		public List replyReload(@RequestParam(name = "index") int index,
								@RequestParam(name = "idx") int idx) throws SQLException{
			System.out.println(index+":"+idx);
			//댓글
			List reply = articleService.getReply(index-1,idx);
			return reply;
		}
		@RequestMapping(value = "/deleteReply")
		@ResponseBody
		public int deleteReply(
			@RequestParam(name = "coIdx") int coIdx,
			@RequestParam(name = "session") String session)throws SQLException { 
			
			int result = articleService.deleteReply(coIdx,session);
			
			return 0;
		}
		
		@RequestMapping(value = "/more")
		@ResponseBody
		public List more(@RequestParam(name="num",required = false) Integer num) throws SQLException{
			//밑에 게시글 뿌리는 메서드
			List list = articleService.searchArticleByAdd(num);
			System.out.println(list);
			return list;
		}
		@RequestMapping(value = "/like")
		@ResponseBody
		public int likeSs(@RequestParam(name="num",required = false) Integer num,
						@RequestParam(name="id",required = false) String memId,
						@RequestParam(name="insertId",required = false) String insertId
				)throws SQLException {
			
			//기본값 -1
			int result = -1;
			result = articleService.like(num,memId,insertId);
			//unlike
			return result;
		}
		@RequestMapping(value = "/unlike")
		@ResponseBody
		public int unlikeSs(@RequestParam(name="num",required = false) Integer num,
				@RequestParam(name="id",required = false) String id,
				@RequestParam(name="insertId",required = false) String insertId
				)throws SQLException {
			
			//기본값 -1
			int result = -1;
			result = articleService.unlike(num,id,insertId);
			//unlike
			return result;
		}
		@RequestMapping(value = "/report")
		@ResponseBody
		public int reportSs(@RequestParam(name="num",required = false) Integer num,
				@RequestParam(name="insertId",required = false) String insertId,
				@RequestParam(name="reportId",required = false) String reportId
				) throws SQLException{
			int result = 0;
			System.out.println(num+"|"+insertId+"|"+reportId);
			result = articleService.report(num,insertId,reportId);
			return result;
		}
		
		@RequestMapping(value = "/reply")
		@ResponseBody
		public String replySs(
				@RequestParam(name="bnIdx",required = false) String bnIdx,
				@RequestParam(name="session",required = false) String session,
				@RequestParam(name="text",required = false) String text
				)throws SQLException{
			System.out.println("reply");
			Map map = new HashMap();
			map.put("bnIdx", bnIdx);
			map.put("BN_COMMENT", text);
			map.put("INSERT_ID", session);
			//댓글입력
			articleService.insertReply(map);
			//다시 댓글가져오기
			
			
			String log = bnIdx+":"+session+":"+text;
			return log;
		}
		@RequestMapping(value = "/move")
		@ResponseBody
		public int moveSs(@RequestParam(name="num",required = false) Integer num,
				@RequestParam(name="code",required = false) String code
				) throws SQLException{
			int result = 0;
			System.out.println("CODE===="+code);
			
			if(code.equals("move")) {
				result = articleService.moveArticle(num);
			}else if(code.equals("back")) {
				result = articleService.backArticle(num);
			}
			return result;
		}
	}	
