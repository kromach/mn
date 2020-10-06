<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
	<h1>${result}</h1>
	<c:if test="${result == 1 }">
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">${sessionScope.memNickName}님 환영합니다.</div>
		</div>
	</div>
	</div>
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