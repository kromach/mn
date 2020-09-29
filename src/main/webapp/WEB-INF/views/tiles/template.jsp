<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<!-- mobile Device  -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>마시는 녀석들</title>	
	<script src="https://kit.fontawesome.com/78c316e334.js" crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<!-- JQuery  -->
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
	<!-- JQuery masonry CDN -->
	<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
	<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
	<link href="/resources/css/base.css" rel="stylesheet">
	<link href="/resources/css/button.css" rel="stylesheet">	
	<!-- BooStrap CDN CSS only -->
	<!-- <link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
		crossorigin="anonymous"> -->
</head>
<body>
 	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>