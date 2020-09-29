<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
</head>
count ${count}
<c:if test="${count == 0}">
		<h1>판매중인술이 없습니다</h1>
	<body>
		<div>
			<form action="">
				<select>
					<option value="S006">탁주</option>
					<option value="S007">청주</option>
					<option value="S008">약주</option>
					<option value="S009">증류주</option>
				</select>
				<input type="text" name="name" >
				<input type="submit" value="검색">
			</form>
		</div>
		<h1>상품 목록 주르륵</h1>
	</body>
</c:if>
<c:if test="${count > 0}">
	<body>
	<div>
		<form action="">
			<select>
				<option value="S006">탁주</option>
				<option value="S007">청주</option>
				<option value="S008">약주</option>
				<option value="S009">증류주</option>
			</select>
			<input type="text" name="name" >
			<input type="submit" value="검색">
		</form>
	</div>
	<h1>상품 목록 주르륵</h1>
		<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
				<c:forEach var="pr" items="${productlist}">
					<div class="grid-item">
						<a href="productdetail?prcode=${pr.prCode}"><img
							src="${pr.prImg}" /></a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="chat">
		<a href="/chat"><img src="/resources/img/main/chat.png" ></a>
	</div>
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
</c:if>
</html>