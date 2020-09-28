<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 활동 내역</title>
</head>
<body>

	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="grid-item grid-item--width6">
				<br />
				<br />
				<h1 align="center">마이 페이지</h1>
				<br />
				<br />
				<div style="border: 1px; float: left; width: 25%; padding: 10px;">
					내 글 보기</div>
				<div style="border: 1px; float: left; width: 25%; padding: 10px;">
					술 좋아요</div>
				<div style="border: 1px; float: left; width: 25%; padding: 10px;">
					글 좋아요</div>
				<div style="border: 1px; float: left; width: 25%; padding: 10px;">
					칭호</div>
				<br />
				<br />
				<table>
					<tr>
						<td>제목</td>
						<td>글쓴이</td>
						<td>작성일</td>
						<td>조회</td>
						<td>추천</td>
					</tr>

					<tr>
						<td>제목</td>
						<td>글쓴이</td>
						<td>작성일</td>
						<td>조회</td>
						<td>추천</td>
					</tr>

				</table>
				<br />
			</div>
		</div>
	</div>
	<div class="chat">
		<a href="/chat"><img src="/resources/img/main/chat.png"></a>
	</div>
</body>
<script src="/resources/js/imageLoad.js"></script>
<!-- 데이터 스크롤해서 붙이는 스크립트  -->
<script type="text/javascript">
	$(window).scroll(
			function() {
				// A(B+C) : document 높이 (고정)
				console.log($(document).height());
				// B : browser 높이 (최상단 기본값)
				console.log($(window).height());
				// C : 스크롤 위치
				console.log('SCROLL_TOP' + $(window).scrollTop());
				if ($(window).scrollTop() >= $(document).height()
						- $(window).height() - 100) {
					//호출 메서드
				}
			});
</script>
</body>
</html>