<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<!-- 에디터 js -->
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">

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
		filebrowserUploadUrl : 'ckuploader' ,  // 통신할 컨트롤러 매핑 주소 
		toolbar : ''
	}
	// ckeditor 설정 종료
	
</script>
<form action="ckwrite" method="post" id="frm" accept-charset="utf-8">
	<table class="tableCss tbl-md">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" id="title" class="required" msg="제목을" placeholder="제목"></td>
		</tr>
		<tr>
			<td style="margin:0; padding:0;" colspan="2">
				<!-- 
					1. 에디터를 붙여야 할 위치에 textarea를 넣고 
					   id 값을 원하는 값 (또는 ckeditor 로 그대로 써도 무관) 으로 입력 
					2. textarea 바로 하단에 아래의 스크립트 한줄 추가. 
					   replece의 앞에 'ckeditor' 는 textarea id 값으로 맞추기
				-->
				<textarea name="tarea" id="ckeditor" type="textarea" class="required" msg="내용을"  rows="10" cols="100" style="width:100%; height:412px;"></textarea>
				<script type="text/javascript">CKEDITOR.replace( 'ckeditor' , ckedit_config);</script>
			</td>
		</tr>
	</table>
	
	<div class="text-center pad-top10">
		<!-- 유효성 검사 실시를 위해서 버튼 type은 submit 이면 안됨 -->
		<input id="addBtn" type="button" class="btn btn-md btn-blue" value="전송">
	</div>
</form>

<script>
$(function() {
	// 전송버튼 클릭이벤트 
	// 에디터의 처리가 필요하므로 클릭 이벤트가 필요.
    $("#addBtn").click(function(){
    	
    	/*
    	 checkFormjquery() form 유효성 검사 JS 
    	 input 등의 class에 required 가 붙은 항목을 검사 후 입력 값이 없으면 msg의 값을 바탕으로
    	 팝업을 보여주고 멈춤.
    	*/
    	// if (checkFormjquery()) {  // 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    
            // 해당 입력 폼의 id나 name 등으로 select 한 후에 submit() 실행
            $("#frm").submit();
    	
    	//}   						// 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    });
});
</script>
