<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				for ( var i in data) {
					if (data[i].length > 0) {
						dataLog = data[i];
					}
				}
				for ( var j in dataLog) {
					console.log(dataLog[j].DK_NAME);
					$('#option').append(
							'<option value="'+dataLog[j].DK_CODE+'">'
									+ dataLog[j].DK_NAME + '</option>');
				}
			}
		});
	}
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
	$(function() {
		$("#addBtn").click(function() {
			checkFormjquery();
			if (checkFormjquery()){
				$("#frm").submit();
			}
		});
	});
</script>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<div class="text-center pad-top10">
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="전송">
					<input type="button" class="btn btn-md btn-grey" value="취소" onclick="window.location='/article'" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>