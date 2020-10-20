package project.spring.drink.service;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

@Service
public class DrinkServiceImpl implements DrinkService {
	
	private final DrinkDAO drinkDAO;
	
	@Autowired
	DrinkServiceImpl(DrinkDAO drinkDAO) {
		this.drinkDAO = drinkDAO;
	}
	
	@Override
	public List<DrinkVO> selectDrinkServiceList(HashMap<String, Object> schMap) throws SQLException {
		return drinkDAO.selectDrinkServiceList(schMap);
	}

	@Override
	public List<HashMap> selectBigCategoryList() throws SQLException {
		return drinkDAO.selectBigCategoryList();
	}

	@Override
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws SQLException {
		return drinkDAO.selectSmallCategoryList(bigCategoryCode);
	}

	@Override
	public HashMap selectItemValuesInfo(String bigCategoryCode) throws SQLException {
		return drinkDAO.selectItemValuesInfo(bigCategoryCode);
	}

	@Override
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectDrinkServiceInfo(dkCode);
	}

	@Override
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectCommentStarServiceInfo(dkCode);
	}

	@Override
	public List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectTagCloudServiceInfo(dkCode);
	}

	@Override
	public List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException {
		return drinkDAO.selectCommentServiceList(dkCode);
	}

	@Override
	public List<String> selectItemValuesList(String bigCategoryCode) throws SQLException {
		return drinkDAO.selectItemValuesList(bigCategoryCode);
	}
	
	@Override
	public String insertDrink(DrinkVO drinkVo) throws SQLException {
				
		// 에디터에 입력된 데이터를 3950 byte 기준으로 split 하여 3개의 컬럼에 삽입
		if (drinkVo.getDkContent().length() > 0) {
			String[] contentArr = getMaxByteStringArray(drinkVo.getDkContent(), 3950);
			
			int arrCnt = contentArr.length;
			
			drinkVo.setDkContent1(contentArr[0]);
			if (arrCnt > 1) drinkVo.setDkContent2(contentArr[1]);
			if (arrCnt > 2) drinkVo.setDkContent3(contentArr[2]);
		}

		return drinkDAO.insertDrink(drinkVo);
	}

	@Override
	public void updateDrinkTag(HashMap tagInfo) throws SQLException {
		drinkDAO.updateDrinkTag(tagInfo);
	}
	

	/*원하는 바이트수(maxLen)만큼만으로 
	 * 이루어진 String 배열을 str 문자열 끝까지 처리해서 리턴*/
	public String[] getMaxByteStringArray(String str, int maxLen) {
		return getMaxByteStringArray(str, maxLen, -1);
	}
	
	/*메소드는 원하는 바이트수(maxLen)만큼만으로 이루어진 String 배열을 최대 maxArrays만큼 길이로 리턴*/
	public String[] getMaxByteStringArray(String str, int maxLen, int maxArrays) {
		StringBuilder sb = new StringBuilder();
		ArrayList strList = new ArrayList();
		int curLen = 0;
		String curChar;
		for (int i = 0; i < str.length(); i++) {
			curChar = str.substring(i, i + 1);
			curLen += curChar.getBytes().length;
			if (curLen > maxLen) {
				if (maxArrays == -1 || strList.size() <= maxArrays - 2) {
					strList.add(sb.toString());
					sb = new StringBuilder();
					curLen = 0;
					i--;
				} else
					break;
			} else
				sb.append(curChar);
		}
		strList.add(sb.toString());
		String[] strArr = new String[strList.size()];
		for (int i = 0; i < strList.size(); i++) {
			strArr[i] = (String) strList.get(i);
		}
		return strArr;
	}

	@Override
	public String insertDrinkImg(MultipartHttpServletRequest request) throws SQLException {
	 	
		// 파일정보 꺼내기
		MultipartFile mf = null;
				
		String imgPath = null;

		try {
			mf = request.getFile("dkimage"); // 업로드 가져오기
			
			// 이미지 이름 중복처리 
			String orgName = mf.getOriginalFilename();
			System.out.println(orgName);
			
			// 파일명은 주류 코드로 변경
			String dkCode = (String)request.getAttribute("dkCode");
			
			// 파일의 확장자만 추출
			String ext = orgName.substring(orgName.lastIndexOf("."));
			
			String newName = dkCode + ext;  // 주류코드 + 확장자
			System.out.println(newName);
						
			//파일 기본경로 _ 상세경로
			String path = request.getRealPath("resources/img/drink") + File.separator;
//			System.out.println("req :" + request.getRealPath("resources/img/upload"));
			// System.out.println(path + newName);
			File file = new File(path);
			
			//디렉토리 존재하지 않을경우 디렉토리 생성
			if(!file.exists()) {
				file.mkdirs();
			}
			
			imgPath = "/resources/img/drink/" + newName;
			System.out.println("service 이미지 경로 : " + imgPath);
			File copyFile = new File(path + newName); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
			
			mf.transferTo(copyFile); // 지정된 경로로 파일 저장

		} catch (Exception e) {
			e.printStackTrace();
		}

		return imgPath;
	}

	@Override
	public String modifyDrink(DrinkVO drinkVo) throws SQLException {
		
		// 에디터에 입력된 데이터를 3950 byte 기준으로 split 하여 3개의 컬럼에 삽입
		if (drinkVo.getDkContent().length() > 0) {
			String[] contentArr = getMaxByteStringArray(drinkVo.getDkContent(), 3950);
			
			int arrCnt = contentArr.length;
			
			drinkVo.setDkContent1(contentArr[0]);
			if (arrCnt > 1) drinkVo.setDkContent2(contentArr[1]);
			if (arrCnt > 2) drinkVo.setDkContent3(contentArr[2]);
		}

		return drinkDAO.modifyDrink(drinkVo);
	}
	
	@Override
	public String selectDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException {
		
		return  drinkDAO.selectDrinkLikeInfo(drinkLikeMap);
	}

	@Override
	public String updateDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException {
		
		// 현재 값 체크. Y 등록 / N 미등록
		String drinkLikeYN = selectDrinkLikeInfo(drinkLikeMap);
		
		drinkLikeMap.put("likeYn", drinkLikeYN);
				
		// 2. 1에서 가져온 정보가 좋아요면 좋아요 취소 처리, 아니면 좋아요 처리
		String drinkLikeInfo = drinkDAO.updateDrinkLikeInfo(drinkLikeMap);
		System.out.println("좋아요 결과 : " + drinkLikeInfo);
		
		// 3. 주류 정보 좋아요 값 업데이트 (더하기, 빼기)
		updateDrinkLikeCount(drinkLikeMap);
		
		return drinkLikeInfo;
	}
	
	@Override
	public void updateDrinkLikeCount(HashMap drinkLikeMap) throws SQLException {
		
		drinkDAO.updateDrinkLikeCount(drinkLikeMap);
	}

}
