<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrapCssTest</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12" style="border: 1px solid black;">상단 헤더 이미지</div>
		</div>
		<div class="row" style="border: 1px solid black;">
			<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
			<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
			<!-- 필요한 뷰포트에만 clearfix 를 추가하세요 -->
			<div class="clearfix visible-xs-block"></div>
			<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
			<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
		</div>
	</div>
</body>
</html>