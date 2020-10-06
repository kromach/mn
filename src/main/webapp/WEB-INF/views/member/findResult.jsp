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
					${memberDTO.name }님의 ID는 ${memberDTO.id }입니다.
				</c:if>
				<c:if test="${result eq 'pw'}">
					${memberDTO.name }님의 PW는 ${memberDTO.pw }입니다.
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>