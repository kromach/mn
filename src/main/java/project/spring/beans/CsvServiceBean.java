package project.spring.beans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

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
	public void readCSV() {

	}
}
