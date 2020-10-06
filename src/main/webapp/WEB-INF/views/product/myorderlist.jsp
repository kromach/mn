<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록</title>
</head>
<c:if test="${myordercount==0}">
	<h1>구매한 상품이 없습니다</h1>
	<a><h1>구매하러가기</h1></a>
</c:if>
<c:if test="${myordercount >0}">
	<table>
		<body>
			<H1>구매 목록</H1>
			<tr>
				<td>주문번호</td>
				<td>상품명</td>
				<td>수량</td>
				<td>배송지</td>
				<td>주문상태</td>
			</tr>
			<c:forEach var="my" items="${myorderlist}">
				<tr>
					<td>${my.orCode}</td>
					<td>${my.prName}</td>
					<td>${my.prCount}</td>
					<td>${my.receiverAddr}</td>
					<td>${my.orStatus}</td>
				</tr>
			</c:forEach>
		
		</body>
	</table>
</c:if>
</html>