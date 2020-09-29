<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.loginWrapper{
	width:33%;
	text-align: center;
	display:inline-block;
}
.loginWrapper >.kakaoBtn{
	width:250px;
	margin-bottom: 5px;
	color: black;
	font-size: 14px;
	position: relative;
	border-radius: 4px;
	min-height: 44px;
	background-color: #ffe812;
}
.loginWrapper >.greyBtn{
	width:250px;
	margin-bottom: 5px;
	font-size: 14px;
	position: relative;
	border-radius: 4px;
	min-height: 44px;
	background-color: gray;
}
.loginWrapper > .loginInput{
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
				<div class="loginWrapper">
						<button type="button" class="kakaoBtn"
						onclick='kakaoLogin()'>카카오로 로그인</button>
					<input class="loginInput" type="text" name="id">
					<input class="loginInput" type="text" name="pw">
					<button class="greyBtn">로그인</button>
					<button class="greyBtn">회원가입</button>
					<button class="greyBtn">ID찾기</button>
					<button class="greyBtn">PW찾기</button>
				</div>
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