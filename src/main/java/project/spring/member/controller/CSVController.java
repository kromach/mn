package project.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csv/")
public class CSVController {
	
	@RequestMapping("writerCsv")
	public String writeCSV(){
		return "/util/csvTest";
	}
	
	@RequestMapping("readCsv")
	public String readCSV(){
		return "/util/csvTest";
	}
}
