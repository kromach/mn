<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<style>
.linkBar {
	width: 25%;
	border: 0;
	float: left;
	text-align: center;
}
</style>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<h1 align="center">마이 페이지</h1>
			<br/>
			<br/>
			<div class="linkBar">
				<a href="/myAct">내 글 보기</a>
			</div>
			<div class="linkBar" id="likeArticle">
				<a onclick="">글 좋아요</a>
			</div>
			<div class="linkBar" id="likeDrink">
				<a>술 좋아요</a>
			</div>
			<div class="linkBar" id="title">
				칭호</a>
			</div>
			<br/>
			<br/>
			<div class="grid-sizer"></div>
			<div class="grid-item grid-item--width6">
				<table class="tableCss" style="width: 100%;">
					<tr>
						<td>제목</td>
						<td>작성일</td>
						<td>조회</td>
						<td>추천</td>
					</tr>
					<c:forEach var="myArticle" items="${myArticle}">
						<tr>
							<td>${myArticle.bnTitle}</td>
							<td>${myArticle.insertDay}</td>
							<td>${myArticle.readcount}</td>
							<td>${myArticle.heart}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="likeArticle_result">
			
			</div>
			<div id="result2">
			
			</div>
			<div id="result3">
			
			</div>
		</div>
	</div>
	<input type = "hidden" value="${sessionScope.memId}" id="memId">
</body>
	<script>
			$(document).ready(function(){
			$("#likeArticle").click(function(){
				$.ajax({
					type:"POST",
					url:"/myAct/likeArticle",
					data:{id:$("#memId").val()},
					success:function(data){
						console.log(data);
						$("#likeArticle_result").html(data);
					}
				});
			});
		});
	
	</script>
		<script>
		$(document).ready(function(){
			$("#likeDrink").click(function(){
				$.ajax({
					type:"POST",
					url:"/myAct/likeDrink",
					data:{id:$("#memId").val()},
					success:function(data){
						console.log(data);
						$("#result2").html(data);
					}
				});
			});
		});
	
	</script>
	
		<script>
		$(document).ready(function(){
			$("#title").click(function(){
				$.ajax({
					type:"POST",
					url:"/myAct/title",
					data:{id:$("#memId").val()},
					success:function(data){
						console.log(data);
						$("#result3").html(data);
					}
				});
			});
		});
	
	</script>
	
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