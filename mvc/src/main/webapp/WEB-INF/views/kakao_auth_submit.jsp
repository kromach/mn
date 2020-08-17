<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<form id="frm" name="frm" action="https://kauth.kakao.com/oauth/token"
		method="POST">
		<input type="hidden" id="grant_type" name="grant_type" value="authorization_code">
		<input type="hidden" id="client_id" name="client_id" value="${restApikey}">
		<input type="hidden" id="redirect_uri" name="redirect_uri" value="${callback_URL}">
		<input type="hidden" id="code" name="code" value="${code}">
		<input type="hidden" id="client_secret" name="client_secret" value="secret">
	</form>
	<input type="submit" value="요청">
	<!-- <script type="text/javascript">
		$(document).ready(function() {
			console.log("TEST");
			$("#frm").submit();
		});
	</script> -->
</body>
</html>