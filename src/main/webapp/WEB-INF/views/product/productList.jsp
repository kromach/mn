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
<c:if test="${count == 0}">
		<h1>판매중인술이 없습니다</h1>
	<body>
		<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
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
			</div>
		</div>
	</body>
</c:if>
<c:if test="${count > 0}">
	<body>
		<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
					<div class="grid-item grid-item--width6 text-left">
						<form action="productList">
							<div>
								<select>
									<option value="S006">탁주</option>
									<option value="S007">청주</option>
									<option value="S008">약주</option>
									<option value="S009">증류주</option>
								</select>
								검색어 : <input type="text" name="name" >
							</div>
							<div>
								<label><input type="checkbox" name="schPdAlcohol" value="0-5"> 0~5도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="6-10"> 6~10도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="11-15"> 11~15도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="16-20"> 16~20도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="21-25"> 21~25도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="26-30"> 26~30도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="31-35"> 31~35도</label>
								<label><input type="checkbox" name="schPdAlcohol" value="36-40"> 36~40도</label>
							</div>
							<div>
								<label><input type="checkbox" name="price" value="0-10000"> 만원 이하</label>
								<label><input type="checkbox" name="price" value="10000-20000"> 1만원~2만원</label>
								<label><input type="checkbox" name="price" value="20000-30000"> 2만원~3만원</label>
								<label><input type="checkbox" name="price" value="30000~"> 3만원 이상</label>
							</div>
							<div><input type="submit" value="검색" class="btn btn-sm btn-blue"></div>
						</form>
					</div>
				<c:forEach var="pr" items="${productlist}">
					<div class="grid-item">
						<a href="productdetail?prcode=${pr.prCode}"><img
							src="${pr.prImg}" /></a>
					</div>
				</c:forEach>
			</div>
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