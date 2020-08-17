<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kakao_auto_form</title>
</head>
<body>
	<hr>
	<h1>Kakao_auto_form</h1>
	<hr>
	<a href="https://kauth.kakao.com/oauth/authorize?response_type=code
		&client_id=${restApikey }
		&redirect_uri=${callback_URL}">
		<img src="/mvc/resources/kakao_login_button.png" />
	</a>
</body>
</html>