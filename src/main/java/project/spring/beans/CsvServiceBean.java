package project.spring.beans;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

@Service
public class CsvServiceBean {

	// 1. 파일 쓰기
	public void writeCSV(List<String[]> data,
			String fileName) {
		CSVWriter csvWriter = null;
		try {
			csvWriter = new CSVWriter(new FileWriter(fileName),',','"');
			csvWriter.writeAll(data);
			csvWriter.flush();
//			Iterator<String[]> it =  data.iterator();
//			while(it.hasNext()) {
//				String[] s = (String[])it.next();
//				csvWriter.writeNext(s);
//			}
		} catch (Exception e) {e.printStackTrace();} 
		finally {if (csvWriter != null)try {csvWriter.close();} catch (IOException e) {e.printStackTrace();}
		}
	}

	// 2. 파일 읽기
	public List<String[]> readCSV(String fileName) {
		List<String[]> data = new ArrayList<String[]>();
		CSVReader reader= null;
		try {
			//한글일경우
			//국내파일 MS949 / 해외파일 UTF-8
			reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName),"MS949"), ',','"',0);
			data = reader.readAll();
			//영문일 경우 사용
			//reader = new CSVReader(new FileReader(fileName),',');
			//data = reader.readAll();
			//String[] s;
			//while((s=reader.readNext())!= null) {
			//	data.add(s);
			//}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(reader!=null)try {reader.close();}catch (Exception e2) {e2.printStackTrace();}
		}
		return data;
	}
}
