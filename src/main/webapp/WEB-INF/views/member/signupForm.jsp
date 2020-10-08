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
					<div class="loginLabel" >아이디</div>
					<input class="loginInput" type="text" name="id" required="required" id="id">
					<div class="loginLabel" id="id_label"></div>
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput" type="text" name="pw" required="required">
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput" type="password" name="pwCh"> 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" required="required">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput" type="text" name="nickName" required="required" id="nickName">
					<div class="loginLabel" id="nickName_label"></div>
					<div class="loginLabel">
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth" required="required"
						size="7">
					-<input class="birthsecondInput" type="text" name="birth" required="required"
						size="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" maxlength="3" required="required">-<input class="telInput"
					type="text" name="tel" maxlength="4" required="required">-<input class="telInput"
					type="text" name="tel" maxlength="4" required="required">
					</div>
					<c:if test="${status eq 'user' }">
					<div class="loginLabel">
					주소
					</div>
					<input type="text" name="address" class="loginInput" id="address">
					<div class="loginLabel">
					상세 주소
					</div>
					<input type="text" name="address" class="loginInput"> 
					<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
					</c:if>
					<c:if test="${status eq 'salse' }">
						<div class="loginLabel">
						사업장 주소
						</div>
						<input type="text" name="address" class="loginInput" id="address">
						<div class="loginLabel">
						상세 주소
						</div>
						<input type="text" name="address" class="loginInput"> 
						<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
						<div class="loginLabel">
						사업자 등록증
						</div>
						<input type="file" name="file" class="loginInput">
						<div class="loginLabel">
						사업자 번호
						</div>
						<input type="text" name="licenseNum" class="loginInput" required="required">
					</c:if>
					<br><br>
					<button type="submit" class="btn btn-sm btn-grey">가입</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey" onclick="window.location.href='/'">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/resources/js/memberForm.js"></script>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>