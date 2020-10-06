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
				글 좋아요
			</div>
			<div class="linkBar" id="likeDrink">
				술 좋아요
			</div>
			<div class="linkBar" id="title">
				칭호
			</div>
			<br/>
			<br/>
			<div class="grid-sizer"></div>
			<div class="grid-item grid-item--width6">
				<div id="index">
					<table class="tableCss" style="width: 100%;">
						<tr>
							<td>번호</td>
							<td>제목</td>
							<td>작성일</td>
							<td>조회</td>
							<td>추천</td>
						</tr>
						<c:forEach var="myArticle" items="${myArticle}">
							<tr>
								<td>${myArticle.bnIdx}</td>
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
				<div id="likeDrink_result">
				
				</div>
				<div id="title_result">
				
				</div>
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
						var likeArticle = data;
						$('#index').empty();
						$('#likeDrink_result').empty();
						$('#title_result').empty();
						$('#likeArticle_result').empty();	
						
						$('#likeArticle_result').append('<table class="tableCss" style="width: 100%;">');
						$('#likeArticle_result > table').append('<tr>');
						$('#likeArticle_result > table > tr').append('<td>번호</td>');
						$('#likeArticle_result > table > tr').append('<td>제목</td>');
						$('#likeArticle_result > table > tr').append('<td>작성일</td>');
						$('#likeArticle_result > table > tr').append('<td>조회</td>');
						$('#likeArticle_result > table > tr').append('<td>추천</td>');
						$('#likeArticle_result > table').append('</tr>');		
						
						for(var i in likeArticle){
							console.log(likeArticle[i]);
							$('#likeArticle_result > table').append('<tr>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].bnIdx+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].bnTitle+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].insertDay+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].readcount+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].heart+'</td>');
							$('#likeArticle_result > table').append('</tr>');
						}
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
						var likeArticle = data;
						$('#index').empty();
						$('#likeDrink_result').empty();
						$('#title_result').empty();
						$('#likeArticle_result').empty();	
						
						$('#likeArticle_result').append('<table class="tableCss" style="width: 100%;">');
						$('#likeArticle_result > table').append('<tr>');
						$('#likeArticle_result > table > tr').append('<td>번호</td>');
						$('#likeArticle_result > table > tr').append('<td>제목</td>');
						$('#likeArticle_result > table > tr').append('<td>작성일</td>');
						$('#likeArticle_result > table > tr').append('<td>조회</td>');
						$('#likeArticle_result > table > tr').append('<td>추천</td>');
						$('#likeArticle_result > table').append('</tr>');		
						
						for(var i in likeArticle){
							console.log(likeArticle[i]);
							$('#likeArticle_result > table').append('<tr>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].bnIdx+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].bnTitle+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].insertDay+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].readcount+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].heart+'</td>');
							$('#likeArticle_result > table').append('</tr>');
						}
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