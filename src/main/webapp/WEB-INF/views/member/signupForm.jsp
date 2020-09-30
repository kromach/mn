<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
.birthWrapper{
	width:250px;
	margin-bottom: 5px;
	font-size: 14px;
	position: relative;
	border-radius: 4px;
	height: 30px;
}
</style>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">회원가입</h1>
			</div>
			<div class="grid-item grid-item--width6">
				<c:if test="${status eq 'salse' }">
					<form method="post" action="/member/signupSalesPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<c:if test="${status eq 'user' }">
					<form method="post" action="/member/signupUserPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<div class="loginWrapper">
					아이디<input class="loginInput" type="text" name="id"> 아이디
					중복체크
					<button>중복확인</button>
					비밀번호<input class="loginInput" type="text" name="pw"> 비밀번호
					확인<input class="loginInput" type="password" name="pwCh"> 이름<input
						class="loginInput" type="text" name="name"> 닉네임<input
						class="loginInput" type="text" name="nickName"> 아이디 중복체크
					<button>중복확인</button>
					<br><br>
					생년월일
					<br><br>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth"
						size="7">
					-<input class="birthsecondInput" type="text" name="birth"
						size="1">
					</div>
					<br><br>
					전화번호
					<br><br>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">
					</div>
					
					<c:if test="${status eq 'user' }">
					<br>
					주소
					<br>
					<input type="text" name="address" class="loginInput">
					<br>
					<button>주소찾기</button>
					</c:if>
					<c:if test="${status eq 'salse' }">
						사업장 주소
						<br>
						<input type="text" name="address" class="loginInput">
						사업자 등록증
						<br>
						<input type="file" name="file" class="loginInput">
						사업자 번호
						<br>
						<input type="text" name="licenseNum" class="loginInput">
					</c:if>
					<br><br>
					<button>가입</button>
					<button>재입력</button>
					<button>취소</button>
				</div>
				</form>
			</div>
		</div>
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