<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록</title>
</head>
<body>
	<H1>구매 목록</H1>
	<tr>
		<td>주문번호</td>
		<td>상품명</td>
		<td>수량</td>
		<td>배송지</td>
		<td>주문상태</td>
	</tr>
	<tr>
		<c:forEach var="my" items="${ordervo}">
		
		</c:forEach>
		
	</tr>

</body>
</html>