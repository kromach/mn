<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">정보수정${status }</h1>
			</div>
			<div class="grid-item grid-item--width6">
					<form method="post" action="/member/findPwPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" >
					<div class="loginLabel"> 
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth" 
						size="7"> 
					-<input class="birthsecondInput" type="text" name="birth" 
						size="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">
					</div>
					<br><br>
					<button type="submit">찾기</button>
					<button type="reset">재입력</button>
					<button type="button">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>