<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/resources/js/formCheck.js"></script>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<form method="post" action="/member/loginPro" name="inputForm">
				<div class="loginWrapper"> 
						<button type="button" class="kakaoBtn" onclick='kakaoLogin()'>카카오로 로그인</button>
					<input type="hidden" name="returnPage" value="${returnPage}"/>
					<input class="loginInput required" type="text" name="id" msg="아이디를">
					<input class="loginInput required" type="PASSWORD" name="pw" msg="비밀번호를">
					<button type="button" class="greyBtn" onclick="insert()">로그인</button>
					<button type="button" class="greyBtn" onclick="window.location.href='/member/signup'">회원가입</button>
					<button type="button" class="greyBtn" onclick="window.location.href='/member/findId'">ID찾기</button>
					<button type="button" class="greyBtn" onclick="window.location.href='/member/findPw'">PW찾기</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function kakaoLogin() {
	var client_id = "${restApikey}";
	var redirect_uri = "${callback_URL}";
	var location ='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id='+client_id+'&redirect_uri='+redirect_uri;
	window.location.href=location;
}
function insert(){
	console.log('insert');
	if (checkFormjquery()) {
		console.log('insertInput');
		$("form[name='inputForm']").submit();
	}
}
</script>
</html>