<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 관리</title>
</head>
<body>

	<h1>멤버리스트</h1>
	<c:forEach var="memberList" items="${memberList }">
		<h1>${memberList.id }</h1>	
	</c:forEach>

	<div>
		<table>
			<tr>
				<th>아이디</th>
				<th>별명</th>
				<th>가입일</th>
				<th>생년월일</th>
				<th>신고글</th>
				<th>신고회수</th>
				<th>강퇴</th>
			</tr>
		</table>
	</div>

</body>
</html>