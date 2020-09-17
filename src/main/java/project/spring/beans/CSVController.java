package project.spring.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csv/")
public class CSVController {
	
	@Autowired
	private CsvServiceBean csvService = null;
	
	@RequestMapping("writerCsv")
	public String writeCSV(HttpServletRequest request){
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"abc","def","ghi","jkl"});
		data.add(new String[] {"1","23","456","7890"});
		//기본 SCV 폴더 위치는 resources\csv
		String savePath = request.getRealPath("resources\\csv");
		//resource폴더에 CSV파일이름기입
		savePath += "\\testscv.csv";		
		csvService.writeCSV(data,savePath);
		return "/util/csvTest";
	}
	
	@RequestMapping("readCsv")
	public String readCSV(HttpServletRequest request){
		//기본 SCV 폴더 위치는 resources\csv
		String readPath = request.getRealPath("resources\\csv");
		//resource폴더에 CSV파일이름기입
		readPath += "\\지역별 충전기 현황정보.csv";
		List<String[]> readData = csvService.readCSV(readPath);
		Iterator<String[]> it = readData.iterator();
		while(it.hasNext()) {
			String[] str = (String[])it.next();
			for(String  s : str) {
				System.out.print(s+" ");
			}
			System.out.println();
		}
		return "/util/csvTest";
	}
}
