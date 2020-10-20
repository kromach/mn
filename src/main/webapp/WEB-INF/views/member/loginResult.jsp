<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 로그인 후 돌아갈 url 경로 설정 -->
	<c:if test="${empty returnPage}">
		<c:url var="returnPath" value="/" />
	</c:if>
	
	<c:if test="${!empty returnPage}">
		<c:url var="returnPath" value="${returnPage}" />
	</c:if>
	
	<div>${returnUrl}</div>
	<div>${returnPath}</div>
	
	<c:if test="${result==-2 }">
		<script type="text/javascript">
			alert("서비스 이용이 차단된 계정입니다.");
			window.location.href= '${returnPath}';
		</script>
	</c:if>
	<c:if test="${result == 1 }">
		<script type="text/javascript">
			window.location.href= '${returnPath}';
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