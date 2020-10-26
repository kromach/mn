<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/resources/js/jquery.selectric.js"></script>
<link rel="stylesheet" href="/resources/css/selectric.css">
<!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<!-- 에디터 js -->
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/ckeditor/adapters/jquery.js"></script>
<script charset="utf-8">
	function searchDk() {
		var input = $('#dkSch').val();
		var context = window.location.pathname.substring(0,
				window.location.pathname.indexOf("/", 2));
		$.ajax({
			url : context + '/drinkSearch?input=' + input,
			type : "get",
			success : function(data) {
				console.log(data);
				$('#option').empty();
				$('#option').append('<option value="option">선택</option>');
				var dataLog;
				var dataArr = new Array();
				var forCheck = new Array();
				//Data로 나온 list 중복상관없이 하나의 배열로 합치기
				for ( var i in data) {
					if (data[i].length > 0) {
						for(var j in data[i]){
							dataArr.push(data[i][j]);
							forCheck.push(data[i][j].DK_NAME);
						}
					}
				}
				//중복이 제거된 DK_NAME
				var filteredArray = forCheck.filter((item, index) => forCheck.indexOf(item) === index );
				//filteredArray로 uniquerArr만들기
				var uniqueVal ;
				var uniqueKey ;
				var uniqueArr = new Array();
				for(var j=0;j<filteredArray.length;j++){
					for (var i=0; i<dataArr.length; i++) {
						if(filteredArray[j] == dataArr[i].DK_NAME){
							uniqueVal = dataArr[i].DK_CODE; 
							uniqueKey = filteredArray[j];
						}
					}
					uniqueArr[uniqueKey] = uniqueVal;
				}
			   	//붙이기
			   	for(var j in uniqueArr){
			   		 $('#option').append('<option value="'+uniqueArr[j]+'">'
							+j + '</option>'); 
			   	}
			   	$("#option").selectric("refresh");
			}
		});
	}
	
	// select box 설정
	$(function(){
		$("#option").selectric();
	});
	
	// ckeditor 설정
	CKEDITOR.on('dialogDefinition', function (ev) {
		
		var dialogName = ev.data.name;
		var dialog = ev.data.definition.dialog;
		var dialogDefinition = ev.data.definition;

		if (dialogName == 'image') {
			dialog.on('show', function (obj) {
				this.selectPage('Upload'); //사진 추가 버튼 클릭시 업로드탭으로 시작
			});	
			dialogDefinition.removeContents('advanced'); // 자세히탭 제거
			dialogDefinition.removeContents('Link'); // 링크탭 제거
		}
	});
	//이미지 업로드 url 설정
	var ckedit_config = {
		filebrowserUploadUrl : '/editor/ckuploader' ,  // 통신할 컨트롤러 매핑 주소 
		toolbar : ''
	}
	// ckeditor 설정 종료
	//clickEvent부여 및 유효성 검사
	function modify(){
		if (checkFormjquery()) {
			// 에디터 입력값 체크 
			var value = CKEDITOR.instances['ckeditor'].getData();
			if(!value) {
	    		alert("수정 정보를 입력해주세요.");    			
	    		return false;
			}    		
			if (confirm("정보를 수정하시겠습니까?")) {
	    		$("form[name='inputForm']").submit();
			}
		}
	}
</script>
<body>
	<div class="grid-Wrapper">
		<div class="grid2">
			<div class="detail-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="detail-item detail-width6">
				<h1 class="text-left pad-y10">글 수정</h1>
				<form action="/article/updatePro" method="post" id="frm" accept-charset="utf-8" name="inputForm">
					<input type="hidden" value="${article.bnIdx }" name="bnIdx" />
					<table class="tableCss">
						<colgroup>
							<col width="172px" />
							<col width="*" />
						</colgroup>					
						<tr>
							<th>제목</th>
							<td class="text-left">
								<input type="text" name="bnTitle" id="bnTitle" class="input-lg required" msg="제목을" value="${article.bnTitle }" style="height: 28px; padding-left: 10px;">
							</td>
						</tr>
						<tr>
							<th>관련 주류 검색 (Optional)</th>
							<td class="text-left">
								<input id="dkSch" name="dkSch" style="height: 28px; padding-left: 10px; width:72%; max-width: 597px;" />
								<button type="button" class="btn btn-sm btn-mint" onclick="searchDk()">검색</button>
							</td>
						</tr>
						<tr>
							<th>주류 선택 (Optional)</th>
							<td class="text-left">
								<select id="option" name="dkCode" class="sel middle">
									<option value="option">선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea name="content" id="ckeditor" class="required" style="width: 100%; height: 600px;">${article.content}</textarea>
								<script type="text/javascript">CKEDITOR.replace('ckeditor', ckedit_config); </script>
							</td>
						</tr>
					</table>					
				</form>
				<div class="text-center pad-top10">
					<input id="addBtn" type="button" class="btn btn-lg btn-blue" value="수정" onclick="modify()">
					<input type="button" class="btn btn-lg btn-grey" value="취소" onclick="window.location='/article'" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>