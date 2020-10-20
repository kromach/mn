<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<c:if test="${result eq 'id'}">
					<c:if test="${not empty memberDTO.id}">
						<h2>${memberDTO.name }님의 ID는 ${memberDTO.id }입니다.</h2>
						<button type = "button" class="btn btn-lg btn-mint" onclick="window.location.href='/'">메인으로</button>
					</c:if>
					<c:if test="${empty memberDTO.id}">
						<script type="text/javascript">
							alert('해당 정보로 아이디가 존재하지 않습니다.');
							window.location.href ='/';
						</script>
					</c:if>
				</c:if>
				<c:if test="${result eq 'pw'}">
					<c:if test="${not empty memberDTO.pw}">
						<h2>${memberDTO.name }님의 PW는 ${memberDTO.pw }입니다.</h2>
						<button type = "button" class="btn btn-lg btn-mint" onclick="window.location.href='/'">메인으로</button>
					</c:if>
					<c:if test="${empty memberDTO.pw}">
						<script type="text/javascript">
							alert('해당 정보로 비밀번호가 존재하지 않습니다.');
							window.location.href ='/';
						</script>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>