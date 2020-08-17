<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>PJAX TEST</title>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content=""> 
<meta http-equiv="Expires" CONTENT="-1">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="/mvc/resources/jquery.pjax.js" ></script>
<script>
   function moveDetail() {
     $.pjax({url: '/detail.html', container: '#pjax-container'})
   }
   function more() {
    var html = "";
    for(var i = 0;i<50;i++)
     html += "<div onclick='moveDetail()'>append"+i+"</div>";
    
     $("#list").append(html);
   }
</script>
</head>
<h1>PJAX TEST</h1>
<hr>
<head>
<meta charset="utf-8" />
<title>PJAX test</title>
</head>
<body>
	<div id="pjax-container">
		<div id="list">
			<div onclick="moveDetail()">Test1</div>
			<div onclick="moveDetail()">Test2</div>
			<div onclick="moveDetail()">Test3</div>
			<div onclick="moveDetail()">Test4</div>
		</div>
		<div onclick="more()">더보기</div>
	</div>
	<!-- <div>
		<img src="/mvc/resources/1.jpg">
	</div> -->
</body>
</html>

