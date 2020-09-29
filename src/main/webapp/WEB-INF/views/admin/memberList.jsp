<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>멤버리스트</h1>
	<c:forEach var="memberList" items="${memberList }">
		<h1>${memberList.id }</h1>	
	</c:forEach>

</body>
</html>