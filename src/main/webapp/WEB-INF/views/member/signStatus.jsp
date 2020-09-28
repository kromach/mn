<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
		<h1 align="center">회원가입</h1><br>
			<div style="display: block; text-align: center;">
				<table style="display: inline-block; border: 2px solid black;  padding: 5px; align-content: center;">
					<tr>
						<td style="width: 40%"><button>일반사용자</button></td>
						<td style="width: 40%"><button>판매자</button></td>
					</tr>
				</table>
			</div>
		</div>
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
</html>