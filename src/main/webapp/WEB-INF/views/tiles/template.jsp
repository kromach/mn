<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>이술어때</title>
    <link href="/resources/css/base.css" rel="stylesheet">
    <link href="/resources/css/button.css" rel="stylesheet">
    <!-- BooStrap CDN CSS only -->
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
	<!-- Ajax -->	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- JQuery  -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
		integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
		crossorigin="anonymous"></script>
<style>
#header {
	height: 50px;
	text-align: center;
	border: 1px solid black;
}
#left {
	float: left;
	width: 15%;
	background-color: gray;
}
#main {
	float: left;
	width: 85%;
	background-color: gray;
}
#left, #main {
	min-height: 600px;
}
</style>
</head>
<body>
	<div style="width: 100%; height: 100%;">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="container">
			<div id="contentwrap">
				<div id="content">
					<div id="main_content">
						<div id="main">
							<tiles:insertAttribute name="body" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
        $(function() {

        });    
    </script>    
</body>
</html>