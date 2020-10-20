<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="/resources/js/formCheck.js"></script>
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
						enctype="multipart/form-data" name="inputForm" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput required" type="text" name="name" msg="이름을">
					<div class="loginLabel"> 
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput required" type="text" name="birth"  msg="생년월일"
						maxlength="6"> 
					-<input class="birthsecondInput required" type="text" name="birth" msg="생년월일" 
						maxlength="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput required"
					type="text" name="tel" maxlength="3" msg="전화번호를">-<input class="telInput required" msg="전화번호를"
					type="text" name="tel" maxlength="4">-<input class="telInput required" msg="전화번호를" type="text" name="tel" maxlength="4">
					</div>
					<br><br>
					<button type="button" class="btn btn-sm btn-grey" onclick="insert()">찾기</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<script type="text/javascript">
	function insert(){
		if (checkFormjquery()) {
			$("form[name='inputForm']").submit();
		}
	}
	</script>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>