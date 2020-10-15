<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- 이벤트 등록시 이미지 경로 확인하고 사진 띄우기 사진경로 전체적으로 확인! -->
<body>
	<c:if test="${count == 0}">
		<div class="grid-Wrapper">
				<div class="grid">
					<div class="grid-sizer"></div>
					<div class="gutter-sizer"></div>
					<div><h2>진행중인 이벤트가 없습니다.</h2></div>
				</div>
		</div>	
	</c:if>
	<c:if test="${count > 0 }">
	<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
				<c:forEach var="eventList" items="${eventList }">
					<div class="grid-item">
						<a href="eventdetail?eventCode=${eventList.eventCode }"></a>
						<img  src="${eventList.thumImg}">
					</div>
				</c:forEach>
		</div>
	</div>
	<script src="/resources/js/imageLoad.js"></script>
	<!-- 데이터 스크롤해서 붙이는 스크립트  -->		
	
	</c:if>
</body>
</html>