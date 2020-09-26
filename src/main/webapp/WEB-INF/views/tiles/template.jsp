<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>마시는 녀석들</title>
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/button.css" rel="stylesheet">
<!-- BooStrap CDN CSS only -->
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous"> -->
<!-- Ajax -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- JQuery  -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous"></script>
<!-- JQuery masonry CDN -->
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<style>
html, body {
	margin: 0px;
	
}
.grid { width:100% ; background-color: yellow; }
.grid-item {border:1px solid #8f99f3; margin-bottom:10px; border-radius: 10px; overflow:hidden;}
.grid-item img {display: block; min-width:100%; max-width: 100%; }

 
/*반응형웹(검색영역)*/

/*반응형웹(검색영역)*/
@media only screen and (min-width: 1440px)  {
    .grid {background-color: green;}
	..grid-sizer, .grid-item { width: calc(100% / 6); }
	.grid-item--width2 { width: calc(100% / 3); }
	.grid-item--width3 { width: calc(100% / 2); }
	.grid-item--width4 { width: calc(100% / 1.5); }
	.grid-item--width5 { width: calc(100% / 1.2); }
	.grid-item--width6 { width: calc(100%); }
}
@media only screen and (min-width: 1024px) and (max-width: 1439px) {
	.grid {background-color: black;}
	.grid-sizer, .grid-item { width: calc(100% / 5); }
	.grid-item--width2 { width: 48%; }
	.grid-item--width3 { width: 72%; }
	.grid-item--width4 { width: 96%; }
	.grid-item--width5 { width: 96%; }
	.grid-item--width6 { width: 96%; }
}
@media only screen and (min-width: 768px) and (max-width: 1023px) {
	.grid {background-color: navy;}
	.grid-sizer, .grid-item { width: 48%; }
	.grid-item--width2 { width: 96%; }
	.grid-item--width3 { width: 96%; }
	.grid-item--width4 { width: 96%; }
	.grid-item--width5 { width: 96%; }
	.grid-item--width6 { width: 96%; }
}
@media only screen and (min-width: 480px) and (max-width: 767px) {
	.grid {background-color: yellow;}
	.grid-sizer, .grid-item { width: 100%; }
}


</style>
</head>
<body>
 	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
</body>
</html>