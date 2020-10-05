<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<style>

</style>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
			<h1 align="center">회원가입</h1>
		</div>
		<div class="grid-item detail-width3">
			<button class="statusBtnUser" onclick="window.location.href='/member/signupUser'">일반사용자</button>	
		</div>
		<div class="grid-item detail-width3">
			<button class="statusBtnSales" onclick="window.location.href='/member/signupSales'">판매자</button>
		</div>
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
</html>