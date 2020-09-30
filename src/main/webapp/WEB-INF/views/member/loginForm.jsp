<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<style>
</style>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<form method="post" action="/member/login"
						name="inputForm" onsubmit="return check()" accept-charset="utf-8">
				<div class="loginWrapper">
						<button type="button" class="kakaoBtn"
						onclick='kakaoLogin()'>카카오로 로그인</button>
					<input class="loginInput" type="text" name="id">
					<input class="loginInput" type="text" name="pw">
					<button type="submit" class="greyBtn" >로그인</button>
					<button type="button" class="greyBtn" onclick="window.location.href='/member/signup'">회원가입</button>
					<button type="button" class="greyBtn">ID찾기</button>
					<button type="button" class="greyBtn">PW찾기</button>
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


</script>
</html>