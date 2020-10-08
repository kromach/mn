<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<!-- 에디터 js -->
<script type="text/javascript" src="<c:url value="/resources/smarteditor/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<script src="/resources/js/smartEditor.js"></script>
<script>
function searchDk(){
	var input = $('#dkSch').val();
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	$.ajax({
		url: context +'/drinkSearch?input='+input,
		type: "get",
		success : function(data){
			console.log(data);
			
			$('#option').empty();
			$('#option').append('<option value="option">선택</option>');
			
			var dataLog;
			for(var i in data){
				if(data[i].length>0){
					dataLog = data[i];
				}
			}	
			for(var j in dataLog){
				console.log(dataLog[j].DK_NAME);
				$('#option').append('<option value="'+dataLog[j].DK_NAME+'">'+dataLog[j].DK_NAME+'</option>');
			} 	
		}
	});
}
</script>
<body>
	<div class="grid-Wrapper">	
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<table class="tableCss table">
						<tr>
							<th>제목</th>
							<td ><input type="text" name="title" id="title" class = "boardTitle_large" class="required" msg="제목을" placeholder="제목"></td>
						</tr>
						<tr>
							<th>술 검색</th>
							<td><input id="dkSch" name="dkSch" class ="boardTitle_middle" /><button class="btn btn-sm btn-grey" onclick="searchDk()" >검색</button></td>
						</tr>
						<tr>
							<th>술 선택</th>
							<td>
							<select id="option" name="option">
									<option value="option">선택</option>
							</select>
							</td>
						</tr>
						<tr>
							<td style="margin:0; padding:0;" colspan="3">
							<!-- 에디터를 붙여야 할 위치에 textarea를 주고 id 값을 원하는 값 (또는 smart-editor 로 그대로 써도 무관) 으로 입력 -->
							<textarea name="tarea" id="smart-editor" type="textarea" class="required" msg="내용을"  rows="10" cols="100" style="width:100%; height:412px;"></textarea>
							</td>
						</tr>
					</table>
					<div class="text-center pad-top10">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="전송">
						<input type="button" class="btn btn-md btn-grey" value="취소" onclick="window.location='/article'" />
					</div>
			</div>
		</div>
	</div>
</body>
</html>