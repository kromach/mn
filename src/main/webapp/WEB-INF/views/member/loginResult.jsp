<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test="${result == 1 }">
		<script type="text/javascript">
			window.location.href= '/';
		</script>
	</c:if>
	<c:if test="${result==0 }">
		<script type="text/javascript">
			alert("비밀번호가 일치하지 않습니다");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${result==-1 }">
		<script type="text/javascript">
			alert("아이디가 존재하지 않습니다");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>