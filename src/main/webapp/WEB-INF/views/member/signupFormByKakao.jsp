<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">Kakao 추가정보</h1>
			</div>
			<div class="grid-item grid-item--width6">
					<form method="post" action="/member/signUpKakaoPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">아이디</div>
					<input class="loginInput" type="text" name="id" value="${kakaoMember.id }" readonly="readonly" >
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput" type="text" name="pw" required="required" >
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput" type="password" name="pwCh" required="required" > 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" value="${kakaoMember.name }" readonly="readonly">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput" type="text" name="nickName" value="${kakaoMember.nickName}" required="required" id="nickName">
					<div class="loginLabel" id="nickName_label"></div>
					<div class="loginLabel">
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth" required="required" 
						size="7" required="required">
					-<input class="birthsecondInput" type="text" name="birth" required="required" 
						size="1" required="required">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" size="3" required="required">-<input class="telInput"
					type="text" name="tel" size="3" required="required">-<input class="telInput"
					type="text" name="tel" size="3" required="required">
					</div>
					<div class="loginLabel">
					주소
					</div>
					<input type="text" name="address" class="loginInput" id="address">
					<div class="loginLabel">
					상세 주소
					</div>
					<input type="text" name="address" class="loginInput"> 
					<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
					<br><br>
					<button type="submit" class="btn btn-sm btn-grey">추가입력</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/resources/js/memberForm.js"></script>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>