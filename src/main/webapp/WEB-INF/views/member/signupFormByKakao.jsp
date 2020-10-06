<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">Kakao </h1>
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
					<input	class="loginInput" type="text" name="nickName" value="${kakaoMember.nickName}" required="required">
					닉네임 중복체크<button type="button">중복확인</button>
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
					<input type="text" name="address" class="loginInput">
					<button type="button">주소찾기</button>
					<br><br>
					<button type="submit">추가입력</button>
					<button type="reset">재입력</button>
					<button type="button">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>