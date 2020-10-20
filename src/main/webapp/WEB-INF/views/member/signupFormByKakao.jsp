<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/formCheck.js"></script>
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
						enctype="multipart/form-data" name="inputForm" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">아이디</div>
					<input class="loginInput" type="text" name="id" value="${kakaoMember.id }" readonly="readonly" >
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput required" type="password" name="pw" msg="비밀번호를">
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput required" type="password" name="pwCh" msg="비밀번호 확인란을"> 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" value="${kakaoMember.name }" readonly="readonly">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput required" type="text" name="nickName" id="nickName" value="${kakaoMember.nickName}" msg="닉네임을" >
					<div class="loginLabel" id="nickName_label"></div>
					<div class="loginLabel">
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput required" type="text" name="birth" msg="생년월일을"
						size="6" maxlength="6">
					-<input class="birthsecondInput required" type="text" name="birth" msg="생년월일을"
						size="1" maxlength="1">
					</div>
						<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput required"
					type="text" name="tel" maxlength="3" msg="전화번호를">-<input class="telInput required"
					type="text" name="tel" maxlength="4" msg="전화번호를">-<input class="telInput required"
					type="text" name="tel" maxlength="4" msg="전화번호를">
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
					<button type="button" class="btn btn-sm btn-grey" onclick="insert()">추가입력</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey">취소</button>
				</div>
				</form>
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
	<script src="/resources/js/memberForm.js"></script>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>