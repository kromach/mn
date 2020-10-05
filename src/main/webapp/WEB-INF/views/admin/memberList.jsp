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
			
			<c:forEach var="memberList" items="${memberList }">
				<tr>
					<td><a href="#"></a>${memberList.id}</td>
					<td>${memberList.nickname}</td>
					<td>${memberList.insertDate }</td>
					<td>${memberList.reportNumber}<!-- jk0001, jk0002 로 저장되므로 스플릿으로 나누어
					a태그로 걸기 --></td>
					<td>${memberList.reportNumber }</td>
					<td><button>강퇴</button>
				</tr>	
			</c:forEach>
			
		</table>
	</div>

</body>
</html>