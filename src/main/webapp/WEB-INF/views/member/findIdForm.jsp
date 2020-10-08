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
				<h1 align="center">ID찾기</h1>
			</div>
			<div class="grid-item grid-item--width6">
					<form method="post" action="/member/findIdPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" required="required">
					<div class="loginLabel"> 
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth"  required="required"
						size="7"> 
					-<input class="birthsecondInput" type="text" name="birth" required="required"
						size="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" maxlength="3" required="required">-<input class="telInput" required="required"
					type="text" name="tel" maxlength="4">-<input class="telInput" required="required" type="text" name="tel" maxlength="4">
					</div>
					<br><br>
					<button type="submit" class="btn btn-sm btn-grey">찾기</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey">취소</button>
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