<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="/resources/css/base.css" rel="stylesheet">
	<link href="/resources/css/button.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no"> <!-- ios 숫자 전화번호 인식 차단 -->
	<title>windowOpen</title>
</head>
<script type="text/javascript">
	function submit(){
		console.log('onsubmit');
	}
</script>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="detail-item detail-width6">
				<form action="/article/replyPro" onsubmit="submit();">
					<div class="text-center pad-top10">
						<textarea name="content" id="ckeditor" type="textarea" class="required" msg="내용을" 
								style="width: 270px; border: 1px solid; height: 109px; margin: 0px; resize: none;" ></textarea>
					</div>
					<div class="text-center pad-top10">
						<input id="addBtn" type="submit" class="btn btn-md btn-blue" value="전송">	
						<input type="button" class="btn btn-md btn-grey" value="취소" onclick="window.close()" />
					</div>
				</form>
				<input type="hidden" value="${param.bnIdx}" name="bnIdx">
				<input type="hidden" value="${sessionScope.memId}" name="insertId">
			</div>
			</div>
			</div>
</body>
</html>
