<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
	<form id="frm" name="frm" action="https://kauth.kakao.com/oauth/token"
		method="POST">
		<input type="hidden" id="grant_type" name="grant_type" value="authorization_code">
		<input type="hidden" id="client_id" name="client_id" value="${restApikey}">
		<input type="hidden" id="redirect_uri" name="redirect_uri" value="${callback_URL }">
		<input type="hidden" id="code" name="code" value="${code}">
		<input type="hidden" id="client_secret" name="client_secret" value="secret">
		<input type="submit" value="요청">
	</form>
<!-- <script type="text/javascript">
	$(document).ready(function() {
		$("#frm").submit();
	});
</script> -->
</body>
</html>