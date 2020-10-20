<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link href="/resources/css/base.css" rel="stylesheet">
	<link href="/resources/css/button.css" rel="stylesheet">	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="tableCss table tbl-lg">
	<c:if test="${count == 0 }">
		<tr>
			<td>해당 이벤트 신청자가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${count >= 0 }">
		<c:set var="check" value="1" />
			
		<c:forEach var="list" items="${list}">
			<c:if test="${check == 1 }">
				<tr align="center">
					<td colspan="2">
						${list.eventName}
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>신청일</td>
				</tr>
				<c:set var="check" value="0" />
			</c:if>
			
			<tr>
				<td>${list.id}</td>
				<td>${list.joinInsertDay}</td>
			</tr>	
		</c:forEach>
	</c:if>
</table>


</body>
</html>