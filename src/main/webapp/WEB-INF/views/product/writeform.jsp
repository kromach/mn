<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- 에디터 js -->
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/ckeditor/adapters/jquery.js"></script>
<script charset="utf-8">

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
	function insert(){
		if (checkFormjquery()) {
			// 에디터 입력값 체크 
    		var value = CKEDITOR.instances['ckeditor'].getData();
    		if(!value) {
	    		alert("후기를 입력해주세요.");    			
	    		return false;
    		}    		
    		if (confirm("후기를 입력하시겠습니까?")) {
	    		$("form[name='inputForm']").submit();
			}
		}
	}
</script>

	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<form action="/product/writePro" method="post" id="frm" accept-charset="utf-8" name="inputForm">
				<input type="hidden" name="prcode" value="${prcode}" />
				<input type="hidden" name="prname" value="${prname}" />
				<input type="hidden" name="kind" value="${kind}" />
				<table class="tableCss table">
					<tr>
						<th>제목</th>
						<td><input type="text" name="bnTitle" id="bnTitle"
							class="boardTitle_large required" msg="제목을" placeholder="제목"></td>
					</tr>
					<tr>
						<th>술이름</th>
						<td>${prname}</td>
					</tr>
				
					<tr>
						<td style="margin: 0; padding: 0;" colspan="2">
						<textarea name="content" id="ckeditor" type="textarea" 
						rows="10" cols="100" style="width: 100%; height: 600px;">
						</textarea>
						<script type="text/javascript">CKEDITOR.replace('ckeditor', ckedit_config); </script>
						</td>
					</tr>
					<input type="hidden" value="${sessionScope.memId}" name="insertId">
					
				</table>
				</form>
				<div class="text-center pad-top10">
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="전송" onclick="insert()">
					<input type="button" class="btn btn-md btn-grey" value="취소" onclick="window.location='/product/productdetail?prcode=${prcode}'" />
				</div>
			</div>
		</div>
	</div>